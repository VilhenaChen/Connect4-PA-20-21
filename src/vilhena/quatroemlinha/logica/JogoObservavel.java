package vilhena.quatroemlinha.logica;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import static vilhena.quatroemlinha.iu.gui.UtilsGui.QUATRO_EM_LINHA;

public class JogoObservavel {
    private MaquinaEstados maquinaEstados;
    private final PropertyChangeSupport propertyChangeSupport;

    public JogoObservavel(MaquinaEstados maquinaEstados) {
        this.maquinaEstados = maquinaEstados;
        this.propertyChangeSupport = new PropertyChangeSupport(maquinaEstados);
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    //Gets
    public char lePosArray(int linha, int coluna) {
        return maquinaEstados.lePosArray(linha,coluna);
    }

    public Situacao getSituacao() {
        return maquinaEstados.getSituacaoAtual();
    }

    public String getNomeJogadorAtual() {
        return maquinaEstados.getNomeJogadorAtual();
    }

    public String getTurno() {
        return Integer.toString(maquinaEstados.getTurno());
    }

    public String getNrCreditosString() {
        return Integer.toString(maquinaEstados.getCreditos());
    }

    public String getNrPecasEspeciaisString() {
        return Integer.toString(maquinaEstados.getPecaEspecial());
    }

    public int getNrPecasEspeciais() {
        return maquinaEstados.getPecaEspecial();
    }

    public String getCorJogadorAtual() {
        return maquinaEstados.getCorJogador();
    }

    public Boolean getTipoJogador() {
        return maquinaEstados.isHuman();
    }

    public String getConta() {
        return maquinaEstados.getConta();
    }

    //Minijogos
    public void InputMinijogoContas(String result) {
        maquinaEstados.InputMinijogoContas(result);
        propertyChangeSupport.firePropertyChange(QUATRO_EM_LINHA,null,null);
    }

    public int isWinnerMInijogoContas() {
        return maquinaEstados.isWinnerMinijogoContas();
    }

    //utils
    public boolean verificaMinijogo() {
        if(maquinaEstados.isHuman()) {
            if(maquinaEstados.getBonus() == 4) {
                return true;
            }
        }
        return false;
    }

    //HISTORICO
    public void guardaEstado() {
        maquinaEstados.GuardaEstado();
    }

    public void guardaHistorico() {
        maquinaEstados.guardaHistorico();
    }

    public void guardaHistoricoFicheiro() {
        maquinaEstados.guardaHistoricoFicheiro();
    }

    public void leHistoricoFicheiro() {
        maquinaEstados.leHistoricoFicheiro();
    }

    //Tansicoes
    public void comeca(String nome1, String nome2,int gameMode) {
        maquinaEstados.comeca(nome1,nome2,gameMode);
        propertyChangeSupport.firePropertyChange(QUATRO_EM_LINHA,null,null);
    }

    public void jogaPecaNormal() {
        maquinaEstados.jogaPecaNormal();
        propertyChangeSupport.firePropertyChange(QUATRO_EM_LINHA,null,null);
    }

    public void jogaPecaEspecial() {
        maquinaEstados.jogaPecaEspecial();
        propertyChangeSupport.firePropertyChange(QUATRO_EM_LINHA,null,null);
    }

    public void pecaJogada(int col) {
        maquinaEstados.pecaJogada(col);
        propertyChangeSupport.firePropertyChange(QUATRO_EM_LINHA,null,null);
    }

    public void jogaMiniJogo() {
        maquinaEstados.jogaMiniJogo();
        propertyChangeSupport.firePropertyChange(QUATRO_EM_LINHA,null,null);
    }

    public void comecaMinijogo() {
        maquinaEstados.comecaMinijogo();
        propertyChangeSupport.firePropertyChange(QUATRO_EM_LINHA,null,null);
    }

    public void naoJogaMinijogo() {
        maquinaEstados.naoJogaMiniJogo();
        propertyChangeSupport.firePropertyChange(QUATRO_EM_LINHA,null,null);
    }

    public void fimMinijogo(boolean ganhou) {
        maquinaEstados.fimMinijogo(ganhou);
        propertyChangeSupport.firePropertyChange(QUATRO_EM_LINHA,null,null);
    }

    public void jogaOutraVez() {
        maquinaEstados.jogaOutraVez();
        propertyChangeSupport.firePropertyChange(QUATRO_EM_LINHA,null,null);
    }


}
