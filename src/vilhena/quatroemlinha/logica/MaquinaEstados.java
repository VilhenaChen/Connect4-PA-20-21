package vilhena.quatroemlinha.logica;

import vilhena.quatroemlinha.logica.dados.Dados;
import vilhena.quatroemlinha.logica.estados.IEstado;
import vilhena.quatroemlinha.logica.estados.Inicio;
import vilhena.quatroemlinha.utils.Util;

import java.util.ArrayList;

public class MaquinaEstados implements Util {
    IEstado atual;
    Dados data;
    ArrayList<ArrayList<Dados>> historico;
    ArrayList<Dados> temporario;
    int jogoHistorico;
    int turnoHistorico;

    public MaquinaEstados() {
        this.data = new Dados();
        this.atual = new Inicio(data);
        historico = new ArrayList<>();
        temporario = new ArrayList<>();
    }

    //-------------------------------- AVANCAR NOS ESTADOS --------------------------------------

    public void comeca(String nome1, String nome2,int GameMode) {
        atual = atual.comecaJogo(nome1,nome2,GameMode);
    }

    public void jogaPecaNormal() {
        atual = atual.jogaPecaNormal();
    }

    public void jogaPecaEspecial() {
        atual = atual.jogaPecaEspecial();
    }

    public void pecaJogada(int col) {
        atual = atual.pecaJogada(col);
    }

    public void jogaMiniJogo() { atual = atual.jogaMiniJogo();}

    public void fimMinijogo(int jogo) { atual = atual.fimMiniJogo(jogo);}

    public void jogaOutraVez() {
        this.data = new Dados();
        atual = atual.jogaOutraVez(data);
    }


    //-------------------------------- FUNCOES DO DECORRER DO JOGO --------------------------------

    public int getTurno() {
        return data.getTurno();
    }

    public String getNomeJogadorAtual() {
        return data.getNomeJogadorAtual();
    }

    public String getCorJogador() {return data.getCorJogadorAtual();}

    public Situacao getSituacaoAtual() {
        return atual.getSituacao();
    }

    public char lePosArray(int col, int linha) {
        return data.lePosArray(col, linha);
    }

    public boolean verificaColuna(int col) {
        return  data.verificaColuna(col);
    }

    public int getBonus() {return data.getBonus();}

    public int getJoga() {
        return data.getJoga();
    }

    public boolean isHuman() {
        return data.isHuman();
    }

    public int getPecaEspecial() {
        return data.getPecaEspecial();
    }

    public int getCreditos() {
        return data.getCreditos();
    }

    public void tiraCreditos(int nr) {
        data.tiraCreditos(nr);
    }

    public boolean veSeGanhou() {
        return data.veSeGanhou();
    }

    public String getJogoHistorico(int pos) {
        return historico.get(pos).get(0).toString();
    }

    public int getHistoricoSize() {
        return historico.size();
    }

    public String getHistorico() { //Mostarr a lista de jogos guardados
        StringBuilder hist = new StringBuilder();
        if(historico.size() == 0) {
            //acabar isto
        }
        for(int i = 0; i < historico.size(); i++) {
            hist.append("Jogo " + (i + 1) + " " + getJogoHistorico(i) + "\n");
        }
        return hist.toString();
    }

    public void iniciaHistorico(int jogo) { //Comeca a mostrar o historico
        jogoHistorico = jogo;
        turnoHistorico = 0;
        data = historico.get(jogoHistorico).get(0);
        replayHistorico(0);
    }

    public String replayHistorico(int num) { //Andar com o Historico para a frente
        StringBuilder sb = new StringBuilder();
        if(num == AVANCAR) {
            turnoHistorico++;
        }
        else if(num == RECUAR && turnoHistorico != -1) {
            turnoHistorico--;
        }
        try {
            data = historico.get(jogoHistorico).get(turnoHistorico);
            sb.append("Turno " + data.getTurno() + "\n");
        }catch (IndexOutOfBoundsException e) {
            throw e;
        }
        return sb.toString();
    }

    public void GuardaEstado() {
        temporario.add((Dados) data.clone());
    }

    public void guardaHistorico() {
        historico.add(temporario);
        if(historico.size() > 5) {
            historico.remove(0);
        }
    }

}