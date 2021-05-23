package vilhena.quatroemlinha.logica;

import vilhena.quatroemlinha.logica.dados.Dados;
import vilhena.quatroemlinha.logica.estados.Espera_Jogada;
import vilhena.quatroemlinha.logica.estados.IEstado;
import vilhena.quatroemlinha.logica.estados.Inicio;
import vilhena.quatroemlinha.utils.Util;
import java.io.*;
import java.util.ArrayList;

public class MaquinaEstados implements Util, Serializable {
    IEstado atual;
    Dados data;
    ArrayList<ArrayList<Dados>> historico;
    ArrayList<Dados> temporario;
    int jogoHistorico;
    int turnoHistorico;
    ArrayList<String> log;

    public MaquinaEstados() {
        this.data = new Dados();
        this.atual = new Inicio(data);
        historico = new ArrayList<>();
        temporario = new ArrayList<>();
        log = new ArrayList<>();
    }

    //-------------------------------- AVANCAR NOS ESTADOS --------------------------------------

    public void comeca(String nome1, String nome2,int GameMode) {
        addLog(Situacao.Inicio.toString());
        atual = atual.comecaJogo(nome1,nome2,GameMode);
    }

    public void jogaPecaNormal() {
        addLog(Situacao.Espera_Peca_Normal.toString());
        atual = atual.jogaPecaNormal();
    }

    public void jogaPecaEspecial() {
        addLog(Situacao.Espera_Peca_Especial.toString());
        atual = atual.jogaPecaEspecial();
    }

    public void pecaJogada(int col) {
        atual = atual.pecaJogada(col);
    }

    public void jogaMiniJogo() { atual = atual.jogaMiniJogo();}

    public void fimMinijogo(int jogo) { atual = atual.fimMiniJogo(jogo);}

    public void verHistorico() {
        addLog(Situacao.Ver_Historico.toString());
        atual = atual.verHistorico(); }

    public void sairHistorico() {
        addLog(Situacao.Inicio.toString());
        data = new Dados();
        atual = atual.sairHistorico(data);}

    public void jogaOutraVez() {
        addLog(Situacao.Inicio.toString());
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

    public void setBonusJogAtual(int bonus) {
        data.setBonusJogador(bonus);
    }

    //-------------------------------- FUNCOES DO HISTORICO --------------------------------

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

    public void guardaHistoricoFicheiro() {
        try{
            FileOutputStream file = new FileOutputStream("historico.dat");
            ObjectOutputStream obj = new ObjectOutputStream(file);
            obj.writeObject(historico);
            obj.close();
        } catch (IOException e) {
            System.out.println("ERRO A GUARDAR HISTORICO!!!");
            e.printStackTrace();
        }
    }

    public void leHistoricoFicheiro() {
        FileInputStream file = null;
        try {
            file = new FileInputStream("historico.dat");
        }catch (FileNotFoundException e) {
            return;
        }
        if(file != null) {
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(file);
                if(ois != null){
                    try{
                        historico = (ArrayList<ArrayList<Dados>>) ois.readObject();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //-------------------------------- FUNCOES DE GRAVAR E CARREGR JOGO --------------------------------

    public void gravaJogo(String filename) {
        try{
            FileOutputStream file = new FileOutputStream(filename + ".dat");
            ObjectOutputStream obj = new ObjectOutputStream(file);
            obj.writeObject(data);
            obj.close();
        } catch (IOException e) {
            System.out.println("ERRO A GUARDAR JOGO!!!");
            e.printStackTrace();
        }
    }

    public void carregaJogo(String filename) {
        FileInputStream file = null;
        try {
            file = new FileInputStream(filename + ".dat");
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        if(file != null) {
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(file);
                if(ois != null){
                    try{
                        data = (Dados) ois.readObject();
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        atual = new Espera_Jogada(data);
    }

    //-------------------------------- LOGS --------------------------------
    public void addLog(String texto) {
        log.add(texto);
    }

    public String getLog() {
        return log.toString();
    }

    //-------------------------------- FUNCOES DA MAQUINA DO TEMPO --------------------------------
    public boolean usarCreditos(int creditos) {
        Dados dataTemp;
        if(data.getTurnosCreditos() < creditos) {
            return false;
        }
        try {
            dataTemp = (Dados) temporario.get(temporario.size() - 1 - creditos).clone();
        }catch (IndexOutOfBoundsException e) {
            return false;
        }
        dataTemp.tiraCreditos(creditos);
        data = dataTemp;
        data.resetTurnosCreditos();
        atual = new Espera_Jogada(data);
        return true;
    }
}