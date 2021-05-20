package vilhena.quatroemlinha.logica.dados;

import vilhena.quatroemlinha.utils.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dados implements Util {
    ArrayList<Jogador> jogadores;
    int turno;
    int joga;
    ArrayList<ArrayList<Character>> board; //[Largura][Altura]

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

    public char getCharCorJogador() {
        if(joga == J1)
            return CORJ1;
        return CORJ2;
    }

    public boolean isHuman() {
        return jogadores.get(joga).getHuman();
    }

    public int getCreditos() {
        return jogadores.get(joga).getCreditos();
    }

    public void tiraCreditos(int nr) {
        jogadores.get(joga).setCreditos(jogadores.get(joga).getCreditos() - nr);
    }

    public void setGanhou() {
        jogadores.get(joga).setGanhou(true);
    }

    public boolean veSeGanhou() {
        return jogadores.get(joga).isGanhou();
    }


    public boolean jogoNumeros() {
        double start = System.currentTimeMillis() / 1000;
        double tempo;
        int num1;
        int num2;
        double resultado;
        double user;
        int corretas = 0;
        Scanner sc;
        do {
            num1 = (int) (Math.random() * 10) + 1;
            num2 = (int) (Math.random() * 10) + 1;
            int operador = (int) (Math.random() * 4);
            switch (operador) {
                case 0:
                    resultado = num1 + num2;
                    System.out.println(num1 + "+" + num2);
                    System.out.println("resutado: ");
                    sc = new Scanner(System.in);
                    user = sc.nextDouble();
                    if(resultado == user){
                        System.out.println("Acertei");
                        corretas++;
                    }
                    break;
                case 1:
                    resultado = num1 - num2;
                    System.out.println(num1 + "-" + num2);
                    System.out.println("resutado: ");
                    sc = new Scanner(System.in);
                    user = sc.nextDouble();
                    if(resultado == user){
                        System.out.println("Acertei");
                        corretas++;
                    }
                    break;
                case 2:
                    resultado = num1 * num2;
                    System.out.println(num1 + "*" + num2);
                    System.out.println("resutado: ");
                    sc = new Scanner(System.in);
                    user = sc.nextDouble();
                    if(resultado == user){
                        System.out.println("Acertei");
                        corretas++;
                    }
                    break;
                case 3:
                    resultado = num1 / num2;
                    System.out.println(num1 + "/" + num2);
                    System.out.println("resutado: ");
                    sc = new Scanner(System.in);
                    user = sc.nextDouble();
                    if(resultado == user){
                        System.out.println("Acertei");
                        corretas++;
                    }
                    break;
                default:
                    break;
            }
            tempo = ((System.currentTimeMillis() / 1000)- start);
            System.out.println(tempo);
            if(corretas == 5)
                return true;
        } while(tempo <= 30);
        return false;
    }

    public boolean jogoPalavras() {
        //Ler o Ficheiro
        int count = 0;
        String line;
        String[] palavras = new String[100];
        String[] escolhidas = new String[5];
        String[] lidas = new String[5];
        StringBuilder escolhi = new StringBuilder();
        double start;
        double tempo;
        double end;
        try {
            File ficheiro = new File("palavras.txt");
            Scanner sc = new Scanner(ficheiro);
            while(sc.hasNextLine()) {
                palavras[count] = sc.nextLine();
                count++;
            }
        }catch (FileNotFoundException e) {
            System.out.println("Problema a abrir o ficheiro");
            return false;
        }
        for(int i = 0; i < 5; i++) {
            escolhidas[i] = palavras[(int) (Math.random() * 100)];
            escolhi.append(escolhidas[i]);
            if(i != 4)
                escolhi.append(" ");
        }

        //comecar o jogo
        start = System.currentTimeMillis() / 1000;
        System.out.println(start);
        end = (escolhi.length() / 2);
        System.out.println(escolhi);
        Scanner sc = new Scanner(System.in);
        line = sc.nextLine();
        lidas = line.split(" ");
        for(int i = 0; i < 5; i++) {
            if(!lidas[i].equals(escolhidas[i]))
                return false;
        }
        tempo = ((System.currentTimeMillis() / 1000)- start);
        System.out.println("tempo: " + ((System.currentTimeMillis() / 1000)- start));
        if(tempo < end)
            return true;
        return false;
    }

    public boolean isVencedor() {
        char cor = getCharCorJogador();

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
            for (int i = 0; i < 5; i++) {
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
}
