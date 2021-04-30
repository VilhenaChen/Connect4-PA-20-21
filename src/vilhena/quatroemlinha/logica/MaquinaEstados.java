package vilhena.quatroemlinha.logica;

import vilhena.quatroemlinha.logica.dados.Dados;
import vilhena.quatroemlinha.logica.estados.IEstado;
import vilhena.quatroemlinha.logica.estados.Inicio;

public class MaquinaEstados {
    IEstado atual;
    Dados data;

    public MaquinaEstados() {
        this.data = new Dados();
        this.atual = new Inicio(data);

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


    //-------------------------------- FUNCOES DO DECORRER DO JOGO --------------------------------

    public int getTurno() {
        return data.getTurno();
    }

    public String getNomeJogadorAtual() {
        return data.getNomeJogadorAtual();
    }

    public String getCorJogador() {return data.getCorJogador();}

    public Situacao getSituacaoAtual() {
        return atual.getSituacao();
    }

    public void escrevePosArray(int coluna, int player) {
        data.escrevePosArray(coluna, player);
    }

    public char lePosArray(int col, int linha) {
        return data.lePosArray(col, linha);
    }

    public int getJoga() {
        return data.getJoga();
    }

}