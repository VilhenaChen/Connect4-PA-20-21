package vilhena.quatroemlinha.logica.dados;

import vilhena.quatroemlinha.utils.Util;

import java.util.ArrayList;

public class Dados implements Util {
    ArrayList<Jogador> jogadores;
    int turno;
    int joga;
    ArrayList<ArrayList<Character>> board;

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

    public int getJoga() {
        return joga;
    } //funcao para ver quem joga

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

    public String getCorJogador() {
        if(joga == J1)
            return corJ1;
        return corJ2;
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

    public boolean isBoardFull() { //Verificar se o tabuleiro ta cheio
        for(int i = 0; i < ALTURA; i++) {
            for(int j = 0; j < LARGURA; j++) {
                if(lePosArray(j,i) == ' ')
                    return false;
            }
        }
        return true;
    }

    public void limpaColuna(int col) { //Util para a peca especial
        for(int i = 0; i < ALTURA; i++) {
            board.get(col).set(i, ' ');
        }
    }
}
