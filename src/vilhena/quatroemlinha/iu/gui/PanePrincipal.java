package vilhena.quatroemlinha.iu.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import vilhena.quatroemlinha.iu.gui.estados.InicioPane;
import vilhena.quatroemlinha.logica.JogoObservavel;

import static vilhena.quatroemlinha.iu.gui.UtilsGui.*;

public class PanePrincipal extends BorderPane {
    private JogoObservavel observavel;
    private Label tabuleiroLabel;
    private Label cenasLabel;

    public PanePrincipal(JogoObservavel jogoObservavel) {
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
        setPrefSize(DIM_X_FRAME,DIM_Y_FRAME);
        setMinSize(DIM_X_FRAME,DIM_Y_FRAME);

        //Tabuleiro
        tabuleiroLabel = new Label("TABULEIRO");
        tabuleiroLabel.setFont(LETRA);

        TabuleiroPane tabuleiroPane = new TabuleiroPane(observavel);

        VBox leftbox = new VBox(10);
        leftbox.setPrefSize(TABULEIRO_BOX_X,TABULEIRO_BOX_Y);
        leftbox.setMinSize(TABULEIRO_BOX_X,TABULEIRO_BOX_Y);
        leftbox.setPadding(new Insets(10,10,10,10));
        leftbox.getChildren().addAll(tabuleiroLabel,tabuleiroPane);

        //StackPane
        //Iniciar as panes
        InicioPane inicioPane = new InicioPane(observavel);

        StackPane stackPane = new StackPane(inicioPane);

        //Box Central
        HBox center = new HBox(10);
        center.setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID,
                null, new BorderWidths(2))));
        center.setAlignment(Pos.CENTER);
        center.getChildren().addAll(leftbox,stackPane);
        setCenter(center);

    }

    private void atualiza() {

    }
}
