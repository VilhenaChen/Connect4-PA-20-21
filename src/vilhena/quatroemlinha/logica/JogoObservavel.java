package vilhena.quatroemlinha.logica;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

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


}
