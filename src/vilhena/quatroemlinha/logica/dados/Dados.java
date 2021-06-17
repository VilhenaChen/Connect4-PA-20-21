package vilhena.quatroemlinha.logica.dados;

import vilhena.quatroemlinha.logica.dados.minijogos.MiniJogos;
import vilhena.quatroemlinha.logica.dados.minijogos.Minijogo_Contas;
import vilhena.quatroemlinha.logica.dados.minijogos.Minijogo_Palavras;
import vilhena.quatroemlinha.utils.Util;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Dados implements Util, Cloneable, Serializable {
    @Serial
    private static final long serialVersionUID = 4L;

    ArrayList<Jogador> jogadores; //Array de Jogadores
    int turno; //Turno
    int joga; //Variavel para saber que jogador esta a jogar 0 - Jogador1 / 1 - Jogador2
    ArrayList<ArrayList<Character>> board; //tabuleiro de jogo [Largura][Altura]
    int turnosCreditos; //Variavel de controlo para saber quando e possivel voltar atras
    ArrayList<MiniJogos> minijogos; //Array que contem os minijogos 0-> MiniJogo Contas 1-> MiniJogo Palavras

    public Dados() {
        this.jogadores = new ArrayList<>();
        this.turno = 1;
        board = new ArrayList<>();
        for(int i = 0; i < LARGURA; i++) {
            board.add(new ArrayList<>());
            for(int j = 0; j < ALTURA; j++) {
                board.get(i).add(' ');
            }
        }
        this.turnosCreditos = 0;
        minijogos = new ArrayList<>();
        minijogos.add(new Minijogo_Contas());
        minijogos.add(new Minijogo_Palavras());
    }

    public void criaJogadores(String nome1, String nome2,int GameMode) {
        if(GameMode == HUMANO_HUMANO) {
            jogadores.add(new Jogador(nome1,true));
            jogadores.add(new Jogador(nome2,true));
        }
        else {
            if(GameMode == HUMANO_CPU) {
                jogadores.add(new Jogador(nome1,true));
                jogadores.add(new Jogador(nome2,false));
            }
            else {
                if(GameMode == CPU_CPU) {
                    jogadores.add(new Jogador(nome1,false));
                    jogadores.add(new Jogador(nome2,false));
                }
            }
        }
    }

    //-------------------------------- FUNCOES DO JOGO --------------------------------------

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public int getJoga() { //funcao para ver quem joga
        return joga;
    }

    public void setJoga(int joga) {
        this.joga = joga;
    }

    public String getNomeJogadorAtual() {
        return jogadores.get(joga).getNome();
    }

    public boolean getJogou(int jog) {
        return jogadores.get(jog).getJogou();
    }

    public void setJogou(int jog, boolean jogou) {  //Funcao para sinalizar que naquele turno o jogador ja efetou o seu movimento
        jogadores.get(jog).setJogou(jogou);
    }

    public int getBonus() {
        return jogadores.get(joga).getBonus();
    }

    public void setBonusJogador(int bonus) {
        jogadores.get(joga).setBonus(bonus);
    }

    public void resetBonusJogadorCreditos() {
        if(joga == J1) {
            jogadores.get(1).setBonus(0);
        }
        else {
            jogadores.get(0).setBonus(0);
        }
    }

    public void setBonusTodos(int bonus) {
        jogadores.get(J1).setBonus(bonus);
        jogadores.get(J2).setBonus(bonus);
    }

    public String getCorJogadorAtual() {
        if(joga == J1)
            return corJ1;
        return corJ2;
    }

    public char getCharCorJogadorAtual() {
        if(joga == J1)
            return CORJ1;
        return CORJ2;
    }

    public char getCharCorJogador(int pos) {
        if(pos == J1)
            return CORJ1;
        return CORJ2;
    }

    public int getTurnosCreditos() {
        return turnosCreditos;
    }

    public void adicionaTurnoCreditos() {
        turnosCreditos = turnosCreditos + 1;
    }

    public void resetTurnosCreditos() {
        this.turnosCreditos = 0;
    }

    public boolean isHuman() {
        return jogadores.get(joga).getHuman();
    }

    public int getPecaEspecial() {
        return jogadores.get(joga).getPecaEspecial();
    }

    public void adicionaPecaEspecial() {
        jogadores.get(joga).setPecaEspecial(getPecaEspecial() + 1);
    }

    public void tiraPecaEspecial() {jogadores.get(joga).setPecaEspecial(getPecaEspecial() - 1);}

    public int getCreditos() {
        return jogadores.get(joga).getCreditos();
    }

    public void tiraCreditos(int nr) {
        jogadores.get(joga).setCreditos(jogadores.get(joga).getCreditos() - nr);
    }

    public void tiraCreditosAoOutroJogador(int nr) {
        if(joga == J1) {
            jogadores.get(1).setCreditos(jogadores.get(joga).getCreditos() - nr);
        }
        else {
            jogadores.get(0).setCreditos(jogadores.get(joga).getCreditos() - nr);
        }
    }

    public void setGanhou() {
        jogadores.get(joga).setGanhou(true);
    }

    public boolean veSeGanhou() {
        return jogadores.get(joga).isGanhou();
    }

    public int getMinijogoJogado() { return jogadores.get(joga).getMinijogo(); }

    public void setMinijogoJogado(int valor) {  jogadores.get(joga).setMinijogo(valor); }

    public boolean isVencedor() {
        char cor = getCharCorJogadorAtual();

        //Verifica Coluna
        for (int i = 0; i < LARGURA; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.get(i).get(5 - j) == cor &&
                        board.get(i).get(5 - (j + 1)) == cor &&
                        board.get(i).get(5 - (j + 2)) == cor &&
                        board.get(i).get(5 - (j + 3)) == cor)
                    return true;
            }
        }

        //Verifica Linha
        for (int j = 0; j < ALTURA; j++) {
            for (int i = 0; i < 3; i++) {
                if (board.get(i).get(5 - j) == cor &&
                        board.get(i+1).get(5 - j) == cor &&
                        board.get(i+2).get(5 - j) == cor &&
                        board.get(i+3).get(5 - j) == cor)
                    return true;
            }
        }

        //Verifica Diagonais
        for(int i = LARGURA-1; i >= 3; i--){ //Diagonal Asc
            for(int j = 0; j < ALTURA-3; j++){
                if(board.get(i).get(j) == cor &&
                        board.get(i-1).get(j+1) == cor &&
                        board.get(i-2).get(j+2) == cor &&
                        board.get(i-3).get(j+3) == cor){
                    return true;
                }
            }
        }

        for(int i = 0; i < LARGURA-3; i++){ //Diagonal Dsc
            for(int j = 0; j < ALTURA-3; j++){
                if(board.get(i).get(j) == cor &&
                        board.get(i+1).get(j+1) == cor &&
                        board.get(i+2).get(j+2) == cor &&
                        board.get(i+3).get(j+3) == cor){
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isBoardFull() { //Verificar se o tabuleiro ta cheio
        for(int i = 0; i < ALTURA; i++) {
            for(int j = 0; j < LARGURA; j++) {
                if(lePosArray(j,i) == ' ')
                    return false;
            }
        }
        return true;
    }

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(ArrayList<Jogador> jogs) {
        jogadores = jogs;
    }

    public ArrayList<ArrayList<Character>> getTabbuleiro() {
        return board;
    }

    public void setTabuleiro(ArrayList<ArrayList<Character>> tab) {
        board = tab;
    }



    //-------------------------------- FUNCOES DOS MINIJOGOS --------------------------------------

    //MiniJogo Contas
    public void comecaContadorContas() {
        minijogos.get(0).comecaTempo();
    }

    public String getConta() {
        return minijogos.get(0).getPergunta();
    }

    public void setUserInputContas(String num) {
        minijogos.get(0).inputUser(num);
    }

    public int isWinnerMinijogoContas() {
        return minijogos.get(0).isWinner();
    }

    //Minijogo Palavras
    public void comecaContadorPalavras() {
        minijogos.get(1).comecaTempo();
    }

    public String getPalavras() {
        return minijogos.get(1).getPergunta();
    }

    public void setUserInputPalavras(String num) {
        minijogos.get(1).inputUser(num);
    }

    public int isWinnerMinijogoPalavras() {
        return minijogos.get(1).isWinner();
    }



    //-------------------------------- FUNCOES DO ARRAY --------------------------------------

    public boolean jogaPeca(int coluna, int player) { //Return true, caso consiga jogar naquela coluna, caso contrario da return false
        if(player == J1)
            for(int i = 0; i < ALTURA; i ++) {
                if(board.get(coluna).get(5-i) == ' ') {
                    board.get(coluna).set(5 - i, CORJ1);
                    return true;
                }
            }
        else {
            for(int i = 0; i < ALTURA; i ++) {
                if(board.get(coluna).get(5-i) == ' ') {
                    board.get(coluna).set(5-i,CORJ2);
                    return true;
                }
            }
        }
        return false;
    }

    public char lePosArray(int col, int linha) {
        return board.get(col).get(linha);
    }

    public boolean verificaColuna(int coluna) {
        for (int i = 0; i < ALTURA; i++) {
            if (board.get(coluna).get(5 - i) == ' ') {
                return true;
            }
        }
        return false;
    }

    public void limpaColuna(int col) { //Util para a peca especial
        for(int i = 0; i < ALTURA; i++) {
            board.get(col).set(i, ' ');
        }
    }

    @Override
    public Object clone() { //Criar um clone desta Classe
        Dados clone = null;
        try {
            clone =(Dados) super.clone();
            clone.joga = this.joga;
            clone.turno = this.turno;
            ArrayList<Jogador> cloneJogador = new ArrayList<>(jogadores.size());
            for(Jogador i: jogadores) {
                cloneJogador.add( (Jogador) i.clone());
            }
            clone.jogadores = cloneJogador;

            ArrayList<ArrayList<Character>> cloneTabuleiro = new ArrayList<>();
            for(int i = 0; i < LARGURA; i++) {
                cloneTabuleiro.add(new ArrayList<>());
                for(int j = 0; j < ALTURA; j++) {
                    cloneTabuleiro.get(i).add(board.get(i).get(j));
                }
            }
            clone.board = cloneTabuleiro;

            ArrayList<MiniJogos> cloneMinijogos = new ArrayList<>();
            for(MiniJogos i: minijogos) {
                cloneMinijogos.add((MiniJogos) i.clone());
            }
            clone.minijogos = cloneMinijogos;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

    @Override
    public String toString() {
        return jogadores.get(0).toString() + " " + getCharCorJogador(0) + " vs " + jogadores.get(1).toString() + " " + getCharCorJogador(1);
    }
}
