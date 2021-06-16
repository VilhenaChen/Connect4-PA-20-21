package vilhena.quatroemlinha.iu.gui.estados;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import vilhena.quatroemlinha.logica.JogoObservavel;

import static vilhena.quatroemlinha.iu.gui.UtilsGui.QUATRO_EM_LINHA;
import static vilhena.quatroemlinha.logica.Situacao.Espera_Peca_Especial;

public class Espera_Peca_EspecialPane extends VBox {
    private JogoObservavel observavel;
    Label nomeJogador, turno, nrCreditos, nrPecasEspeciais;

    public Espera_Peca_EspecialPane(JogoObservavel observavel) {
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
        this.setVisible(observavel.getSituacao() == Espera_Peca_Especial);
        if(observavel.getSituacao() == Espera_Peca_Especial) {

        }
    }
}
