package vilhena.quatroemlinha.iu.texto;

import vilhena.quatroemlinha.logica.MaquinaEstados;
import vilhena.quatroemlinha.utils.Util;

import java.util.Scanner;

public class UITexto implements Util {
    MaquinaEstados maquinaEstados;
    Scanner sc = new Scanner(System.in);
    boolean sair;

    public UITexto(MaquinaEstados maquinaEstados) {
        this.maquinaEstados = maquinaEstados;
    }

    public void corre() {
        sair = false;
        while(!sair) {
            switch (maquinaEstados.getSituacaoAtual()) {
                case Inicio:
                    uiInicio();
                    break;
                case Espera_Jogada:
                    uiJoga();
                    break;
                case Espera_Peca_Normal:
                    uiPecaNormal();
                    break;
                case Espera_Peca_Especial:
                    uiPecaEspecial();
                    break;
                case Joga_MiniJogo:
                    uiMiniJogo();
                    break;
                case GameOver:
                    uiGameOver();
                    break;
            }
        }
    }

    private void uiInicio() {
        int op;
        String nome1, nome2;
        System.out.println("4 em Linha");
        System.out.println("Escolha uma opcao: ");
        System.out.println("1 -> Humano vs Humano");
        System.out.println("2 -> Humano vs CPU");
        System.out.println("3 -> CPU vs CPU");
        System.out.println("0 -> Sair");
        System.out.print(">");
        while (!sc.hasNextInt())
            sc.next();
        op = sc.nextInt();
        sc = new Scanner(System.in);
        switch(op) {
            case HUMANO_HUMANO:
                do {
                    System.out.println("Insira o nome do 1º Jogador: ");
                    nome1 = sc.nextLine();
                    System.out.println("Insira o nome do 2º Jogador: ");
                    nome2 = sc.nextLine();
                    if (nome1.equals(nome2) || nome1.isEmpty() || nome2.isEmpty()) {
                        System.out.println("ERRO!! Os nomes tem de ser diferentes e nao podem estar vazios!!!!");
                    }
                }while(nome1.equals(nome2) || nome1.isEmpty() || nome2.isEmpty());
                maquinaEstados.comeca(nome1,nome2,HUMANO_HUMANO);
                break;
            case HUMANO_CPU:
                do {
                    System.out.println("Insira o nome do Jogador: ");
                    nome1 = sc.nextLine();
                    if (nome1.isEmpty()) {
                        System.out.println("ERRO!! O nome nao pode estar vazio!!!!");
                    }
                }while(nome1.isEmpty());
                nome2 = "CPU1";
                maquinaEstados.comeca(nome1,nome2,HUMANO_CPU);
                break;
            case CPU_CPU:
                nome1 = "CPU1";
                nome2 = "CPU2";
                maquinaEstados.comeca(nome1,nome2,CPU_CPU);
                break;
            case 0:
                sair = true;
                break;
            default:
                System.out.println("Insira uma opcao valida!!!");
                break;
        }
    }

    private void uiJoga() {
        int op;
        System.out.println("-----------------------------------------");
        desenhaTabuleiro();
        System.out.println("Turno: " + maquinaEstados.getTurno());
        System.out.println("Jogador: " + maquinaEstados.getNomeJogadorAtual() + " " + maquinaEstados.getCorJogador());
        System.out.println("1 -> Jogar Peca Normal");
        System.out.println("2 -> Jogar Peca Especial");
        System.out.print("> ");
        while (!sc.hasNextInt())
            sc.next();
        op = sc.nextInt();
        switch (op) {
            case 1:
                maquinaEstados.jogaPecaNormal();
                break;
            case 2:
                maquinaEstados.jogaPecaEspecial();
                break;
            default:
                System.out.println("Insira uma opcao valida!!!!");
                break;
        }
    }

    private void uiPecaNormal() {
        int col;
        boolean flag = false;
        do {
            System.out.println("Escolha a coluna para lancar a peca: ");
            System.out.print("> ");
            while (!sc.hasNextInt())
                sc.next();
            col = sc.nextInt();
            if(col < 7 || col >= 1) {
                if (maquinaEstados.verificaColuna(col - 1) == true) {
                    maquinaEstados.pecaJogada(col - 1);
                    flag = true;
                }
                else {
                    System.out.println("ERRO!! A Coluna onde pretendo jogar ja se encontra cheia!!!");
                    flag = false;
                }
            }
            else {
                System.out.println("ERRO!!O valor digitado deve ser entre 1 e 7!!!");
                flag = false;
            }
        }while(flag == false);
    }

    private void uiPecaEspecial() {
        int col;
        boolean flag = false;
        do {
            System.out.println("Escolha a coluna para lancar a peca: ");
            System.out.print("> ");
            while (!sc.hasNextInt())
                sc.next();
            col = sc.nextInt();
            if(col < 7 || col >= 1) {
                if (maquinaEstados.verificaColuna(col - 1) == true) {
                    maquinaEstados.pecaJogada(col - 1);
                    flag = true;
                }
                else {
                    System.out.println("ERRO!! A Coluna onde pretendo jogar ja se encontra cheia!!!");
                    flag = false;
                }
            }
            else {
                System.out.println("ERRO!!O valor digitado deve ser entre 1 e 7!!!");
                flag = false;
            }
        }while(flag == false);
    }

    private void uiMiniJogo() {

    }

    private void uiGameOver() {
        int op;
        System.out.println("GAME OVER!!!!");
        System.out.println("1 -> Jogar Outra vez");
        System.out.println("0 -> Sair");
        System.out.print("> ");
        while (!sc.hasNextInt())
            sc.next();
        op = sc.nextInt();
        switch (op){
            case 1:
                maquinaEstados.jogaOutraVez();
                break;
            case 0:
                sair = true;
                break;
            default:
                System.out.println("Insira uma opcao valida!!!!");
                break;
        }

    }




    private void desenhaTabuleiro() { //Funcao para desenhar o tabuleiro
        System.out.println(" |1||2||3||4||5||6||7|");
        System.out.println("______________________");
        for(int i = 0; i < ALTURA; i++) {
            System.out.print(i + 1);
            for(int j = 0; j < LARGURA; j++) {
                System.out.print("|" + maquinaEstados.lePosArray(j,i) + "|");
            }
            System.out.println("\n----------------------");
        }
    }
}
