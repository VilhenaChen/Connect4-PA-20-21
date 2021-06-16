package vilhena.quatroemlinha.logica;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

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

    public String getNrCreditos() {
        return Integer.toString(maquinaEstados.getCreditos());
    }

    public String getNrPecasEspeciais() {
        return Integer.toString(maquinaEstados.getPecaEspecial());
    }

    public String getCorJogadorAtual() {
        return maquinaEstados.getCorJogador();
    }

    public Boolean getTipoJogador() {
        return maquinaEstados.isHuman();
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

    public void pecaJogada(int col) {
        maquinaEstados.pecaJogada(col);
        propertyChangeSupport.firePropertyChange(QUATRO_EM_LINHA,null,null);
    }

}
