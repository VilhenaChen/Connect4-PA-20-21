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
        maquinaEstados.leHistoricoFicheiro();
        while(!sair) {
            switch (maquinaEstados.getSituacaoAtual()) {
                case Inicio:
                    uiInicio();
                    break;
                case Espera_Jogada:
                    uiJogador();
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
                case Ver_Historico:
                    uiEscolheHistorico();
                    break;
                case GameOver:
                    maquinaEstados.guardaHistorico();
                    maquinaEstados.guardaHistoricoFicheiro();
                    uiGameOver();
                    break;
            }
        }
        System.out.println("Obrigado por Jogar o 4 em Linha");
    }

    private void uiInicio() {
        int op;
        String nome1, nome2;
        System.out.println("4 em Linha");
        System.out.println("Escolha uma opcao: ");
        System.out.println("1 -> Humano vs Humano");
        System.out.println("2 -> Humano vs CPU");
        System.out.println("3 -> CPU vs CPU");
        System.out.println("4 -> Ver Historico");
        System.out.println("5 -> Carregar Jogo");
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
            case 4:
                maquinaEstados.verHistorico();
                break;
            case 5:
                System.out.println("Insira o nome do Ficheiro: ");
                System.out.println("> ");
                String nome = sc.nextLine();
                maquinaEstados.carregaJogo(nome);
                break;
            case 0:
                sair = true;
                break;
            default:
                System.out.println("Insira uma opcao valida!!!");
                break;
        }
    }

    private void uiJogador() {
        String nome;
        int op = 0;
        System.out.println("-----------------------------------------");
        if(!maquinaEstados.isHuman()) {
            desenhaTabuleiro();
            System.out.println("Turno: " + maquinaEstados.getTurno());
            System.out.println("Jogador: " + maquinaEstados.getNomeJogadorAtual() + " " + maquinaEstados.getCorJogador());
            maquinaEstados.jogaPecaNormal();
        }
        else {
            if(maquinaEstados.getBonus() == 4) {
                sc = new Scanner(System.in);
                System.out.println(maquinaEstados.getNomeJogadorAtual() +" Deseja Jogar um Minijogo para ganhar uma peca especial?");
                System.out.println("1 -> Sim");
                System.out.println("2 -> Nao");
                System.out.print("> ");
                while (!sc.hasNextInt())
                    sc.next();
                op = sc.nextInt();
                switch (op) {
                    case 1:
                        maquinaEstados.addLog("Joga Minijogo");
                        maquinaEstados.jogaMiniJogo();
                        return;
                    case 2:
                        maquinaEstados.addLog("Nao Joga Minijogo");
                        maquinaEstados.setBonusJogAtual(1);
                        break;
                    default:
                        System.out.println("Insira uma opcao valida!!!!");
                        break;
                }
            }
            op = 0;
            sc = new Scanner(System.in);
            desenhaTabuleiro();
            System.out.println("Turno: " + maquinaEstados.getTurno());
            System.out.println("Jogador: " + maquinaEstados.getNomeJogadorAtual() + " " + maquinaEstados.getCorJogador());
            System.out.println("Creditos: " + maquinaEstados.getCreditos());
            System.out.println("Pecas Especiais: " + maquinaEstados.getPecaEspecial());
            System.out.println("1 -> Jogar Peca Normal");
            System.out.println("2 -> Jogar Peca Especial");
            System.out.println("3 -> Usar Creditos");
            System.out.println("4 -> Gravar Jogo");
            System.out.println("5 -> Ver Logs");
            System.out.println("0 -> Sair");
            System.out.print("> ");
            while (!sc.hasNextInt())
                sc.next();
            op = sc.nextInt();
            switch (op) {
                case 1:
                    maquinaEstados.jogaPecaNormal();
                    break;
                case 2:
                    if(maquinaEstados.getPecaEspecial() == 0) {
                        System.out.println("Nao possui nenhuma peca especial");
                    }
                    else {
                        maquinaEstados.jogaPecaEspecial();
                    }
                    break;
                case 3:
                    if(maquinaEstados.getCreditos() == 0) {
                        System.out.println("Nao possui mais creditos");
                    }
                    else {
                        //Usar os creditos
                    }
                    break;
                case 4:
                    sc = new Scanner(System.in);
                    System.out.println("Insira o nome do ficheiro");
                    System.out.print("> ");
                    nome = sc.nextLine();
                    maquinaEstados.gravaJogo(nome);
                    break;
                case 5:
                    System.out.println(maquinaEstados.getLog());
                    break;
                case 0:
                    sc = new Scanner(System.in);
                    int escolha;
                    do {
                        System.out.println("Deseja gravar o jogo?");
                        System.out.println("1 -> Sim");
                        System.out.println("2 -> Nao");
                        System.out.print("> ");
                        escolha = sc.nextInt();
                    }while(escolha != 1 && escolha != 2);
                    if(escolha == 1) {
                        sc = new Scanner(System.in);
                        System.out.println("Insira o nome do ficheiro");
                        System.out.print("> ");
                        nome = sc.nextLine();
                        maquinaEstados.gravaJogo(nome);
                    }
                    maquinaEstados.jogaOutraVez();
                    break;
                default:
                    System.out.println("Insira uma opcao valida!!!!");
                    break;
            }
        }
    }

    private void uiPecaNormal() {
        sc = new Scanner(System.in);
        int col;
        boolean flag = false;
        if(!maquinaEstados.isHuman()) {
            do {
                col = (int) (Math.random() * 7);
            }while(!maquinaEstados.verificaColuna(col));
            System.out.println("Lancei a peca na coluna " + (col + 1));
            System.out.println("Press Enter to continue");
            sc.nextLine();
            maquinaEstados.pecaJogada(col);
        }
        else {
            do {
                System.out.println("Escolha a coluna para lancar a peca: ");
                System.out.print("> ");
                while (!sc.hasNextInt())
                    sc.next();
                col = sc.nextInt();
                if (col < 7 || col >= 1) {
                    if (maquinaEstados.verificaColuna(col - 1)) {
                        maquinaEstados.pecaJogada(col - 1);
                        flag = true;
                    } else {
                        System.out.println("ERRO!! A Coluna onde pretendo jogar ja se encontra cheia!!!");
                        flag = false;
                    }
                } else {
                    System.out.println("ERRO!!O valor digitado deve ser entre 1 e 7!!!");
                    flag = false;
                }
            } while (!flag);
        }
        maquinaEstados.GuardaEstado();
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
                if (maquinaEstados.verificaColuna(col - 1)) {
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
        }while(!flag);
        maquinaEstados.GuardaEstado();
    }

    private void uiMiniJogo() {
        int jogo = (int)(Math.random() *2) +1;
        System.out.println("-----------------------------------------");
        maquinaEstados.fimMinijogo(jogo);
    }

    private void uiEscolheHistorico() {
        int op;
        boolean flag = false;
        sc = new Scanner(System.in);
        do {
            System.out.println(maquinaEstados.getHistorico());
            System.out.println("Escolha um dos jogos (0 para sair)");
            System.out.print("> ");
            while (!sc.hasNextInt())
                sc.next();
            op = sc.nextInt();
            if(op > maquinaEstados.getHistoricoSize()) {
                System.out.println("Insira um valor valido");
                flag = false;
            }
            else {
                if (op == 0) {
                    flag = true;
                    maquinaEstados.sairHistorico();
                    return;
                }
                flag = true;
            }
        }while(!flag);
        maquinaEstados.iniciaHistorico(op - 1);
        uiHistorico();
    }

    private void uiHistorico() {
        boolean flag = true;
        System.out.println("-----------------------------------------");
        desenhaTabuleiro();
        do {
            int op;
            sc = new Scanner(System.in);
            System.out.println("-----------------------------------------");
            System.out.println("1 -> Avancar");
            System.out.println("2 -> Recuar");
            System.out.println("0 -> Sair");
            System.out.print("> ");
            while (!sc.hasNextInt())
                sc.next();
            op = sc.nextInt();
            switch (op) {
                case 1:
                    try {
                        System.out.println(maquinaEstados.replayHistorico(AVANCAR));
                        desenhaTabuleiro();
                    }catch(IndexOutOfBoundsException e) {
                        System.out.println("Fim do Historico");
                        flag = false;
                    }
                    break;
                case 2:
                    try {
                        System.out.println(maquinaEstados.replayHistorico(RECUAR));
                        desenhaTabuleiro();
                    }catch (IndexOutOfBoundsException e) {
                        System.out.println("Nao existem jogadas anteriores a esta");
                    }
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    break;
            }
        }while(flag);
        maquinaEstados.sairHistorico();
    }

    private void uiGameOver() {
        int op;
        System.out.println("-----------------------------------------");
        System.out.println("GAME OVER!!!!");
        System.out.println("Tabuleiro Final");
        desenhaTabuleiro();
        if(maquinaEstados.veSeGanhou()) {
            System.out.println("O vencedor foi o jogador " + maquinaEstados.getNomeJogadorAtual() + " " + maquinaEstados.getCorJogador());
            maquinaEstados.addLog("Game Over vencedor" + maquinaEstados.getNomeJogadorAtual());
        }
        else {
            maquinaEstados.addLog("Game Over");
        }
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
