package vilhena.quatroemlinha.logica;

import vilhena.quatroemlinha.logica.dados.Dados;
import vilhena.quatroemlinha.logica.estados.Espera_Jogada;
import vilhena.quatroemlinha.logica.estados.IEstado;
import vilhena.quatroemlinha.logica.estados.Inicio;
import vilhena.quatroemlinha.utils.Util;
import java.io.*;
import java.util.ArrayList;

public class MaquinaEstados implements Util, Serializable {
    IEstado atual; //Estado atual
    Dados data; //Dados do jogo
    ArrayList<ArrayList<Dados>> historico; //Array para o Historico
    ArrayList<Dados> temporario; //Array temporario do Historico
    ArrayList<String> log; //Logs

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

    public void jogaMiniJogo() {
        atual = atual.jogaMiniJogo();
    }

    public void naoJogaMiniJogo() {
        addLog("Nao joga Minijogo");
        atual = atual.naoJogaMiniJogo();
    }

    public void comecaMinijogo() {
        addLog("Jogou Minijogo");
        atual = atual.comecaMinijogo();
    }

    public void fimMinijogo(Boolean ganhou) { atual = atual.fimMiniJogo(ganhou);}

    public void verHistorico(int jogo) {
        addLog(Situacao.Ver_Historico.toString());
        atual = atual.verHistorico(jogo); }

    public void continuaHistorico(int num) {
        atual = atual.continuaHistorico(historico,num);
    }

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

    public boolean isHuman() {
        return data.isHuman();
    }

    public int getPecaEspecial() {
        return data.getPecaEspecial();
    }

    public int getCreditos() {
        return data.getCreditos();
    }

    public boolean veSeGanhou() {
        return data.veSeGanhou();
    }

    //-------------------------------- FUNCOES DOS MINIJOGOS --------------------------------
    public String getConta() {
        return data.getConta();
    }

    public void InputMinijogoContas(String num) {
        atual = atual.submeterInput(num);
    }

    public int isWinnerMinijogoContas() {
        return data.isWinnerMinijogoContas();
    }

    public String getPalavras() {
        return data.getPalavras();
    }

    public void InputMinijogoPalavras(String num) {
        atual = atual.submeterInput(num);
    }

    public int isWinnerMinijogoPalavras() {
        return data.isWinnerMinijogoPalavras();
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

    public void GuardaEstado() {
        temporario.add((Dados) data.clone());
    } //Guardar no temporario a cada iteracao do jogo

    public void guardaHistorico() { //Guardar para o arrau de historico no final
        historico.add(temporario);
        if(historico.size() > 5) {
            historico.remove(0);
        }
    }

    public void guardaHistoricoFicheiro() { //Guardar o Historico no ficheiro
        try{
            FileOutputStream file = new FileOutputStream("historico.dat");
            ObjectOutputStream obj = new ObjectOutputStream(file);
            obj.writeObject(historico);
            obj.close();
        } catch (IOException e) {
            System.out.println("ERRO A GUARDAR HISTORICO!!!");
        }
    }

    public void leHistoricoFicheiro() { //Ler o Historico no ficheiro
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

    public void gravaJogo(String filename) { //Gravar o jogo num ficheiro
        try{
            FileOutputStream file = new FileOutputStream(filename + ".dat");
            ObjectOutputStream obj = new ObjectOutputStream(file);
            obj.writeObject(data);
            obj.close();
        } catch (IOException e) {
            System.out.println("ERRO A GUARDAR JOGO!!!");
        }
    }

    public boolean carregaJogo(String filename) { //Carregar o Jogo do ficheiro passado como paramentro
        FileInputStream file = null;
        try {
            file = new FileInputStream(filename + ".dat");
        }catch (FileNotFoundException e) {
            return false;
        }
        if(file != null) {
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(file);
                if(ois != null){
                    try{
                        data = (Dados) ois.readObject();
                    } catch (IOException | ClassNotFoundException e) {
                        return false;
                    }
                }
            } catch (IOException e) {
                return false;
            }
        }
        atual = new Espera_Jogada(data);
        return true;
    }

    //-------------------------------- LOGS --------------------------------

    public void addLog(String texto) {
        log.add(texto);
    } //Adicionar ao log as msgs

    public String getLog() {
        return log.toString();
    } //Returna o Log

    //-------------------------------- FUNCOES DA MAQUINA DO TEMPO --------------------------------

    public boolean usarCreditos(int creditos) { //Usar os creditos
        Dados dataTemp; //variavel temporaria
        if(data.getTurnosCreditos() < creditos) { //Caso nao haja turnos suficientes
            return false;
        }
        try {
            dataTemp = (Dados) temporario.get(temporario.size() - 1 - creditos).clone();
        }catch (IndexOutOfBoundsException e) {
            return false;
        }
        dataTemp.tiraCreditos(creditos);
        dataTemp.setBonusJogador(0); //Reset ao contador de Minijogos
        data = dataTemp;
        data.resetTurnosCreditos();
        atual = new Espera_Jogada(data);
        addLog("Usados " + creditos + " creditos" );
        return true;
    }
}