package vilhena.quatroemlinha.iu.gui;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import vilhena.quatroemlinha.logica.JogoObservavel;

import static vilhena.quatroemlinha.iu.gui.UtilsGui.QUATRO_EM_LINHA;

public class Root extends BorderPane {
    private JogoObservavel observavel;
    private PanePrincipal panePrincipal;

    public Root(JogoObservavel jogoObservavel) {
        this.observavel = jogoObservavel;
        criarLayout();
        registarObservador();
        atualiza();
    }

    private void registarObservador() {
        observavel.addPropertyChangeListener(QUATRO_EM_LINHA, evt -> {
            atualiza();
        });
    }

    private void criarLayout() {
        panePrincipal = new PanePrincipal(observavel);
        setBackground(new Background(new BackgroundFill(Color.HONEYDEW,null,null)));
        setCenter(panePrincipal);
    }

    private void atualiza() {

    }
}
