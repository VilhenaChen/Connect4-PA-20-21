package vilhena.quatroemlinha.iu.gui.estados;

import javafx.scene.layout.VBox;
import vilhena.quatroemlinha.logica.JogoObservavel;

import static vilhena.quatroemlinha.iu.gui.UtilsGui.QUATRO_EM_LINHA;
import static vilhena.quatroemlinha.logica.Situacao.Minijogo_Palavras;

public class Minijogo_PalavrasPane extends VBox {
    private JogoObservavel observavel;

    public Minijogo_PalavrasPane (JogoObservavel observavel) {
        this.observavel = observavel;
        registarObservador();
        criarLayoutERegistaListeners();
        atualiza();
    }

    private void registarObservador() {
        observavel.addPropertyChangeListener(QUATRO_EM_LINHA, evt -> {
            atualiza();
        });
    }
    private void criarLayoutERegistaListeners() {

    }

    private void atualiza() {
        this.setVisible(observavel.getSituacao() == Minijogo_Palavras);
        if (observavel.getSituacao() == Minijogo_Palavras) {
            if (!observavel.getTipoJogador()) {
            }
        }
    }
}
