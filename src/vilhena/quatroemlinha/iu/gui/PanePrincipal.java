package vilhena.quatroemlinha.iu.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import vilhena.quatroemlinha.iu.gui.estados.*;
import vilhena.quatroemlinha.logica.JogoObservavel;

import static vilhena.quatroemlinha.iu.gui.UtilsGui.*;

public class PanePrincipal extends BorderPane {
    private JogoObservavel observavel;
    private Label quatroEmLinhaLabel;
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
        quatroEmLinhaLabel = new Label("Quatro em Linha");
        quatroEmLinhaLabel.setFont(Font.font("comic sans", FontWeight.NORMAL, FontPosture.REGULAR,30));

        HBox labelHBox = new HBox();
        labelHBox.getChildren().add(quatroEmLinhaLabel);
        labelHBox.setAlignment(Pos.CENTER);

        //Tabuleiro
        TabuleiroPane tabuleiroPane = new TabuleiroPane(observavel);

        VBox leftbox = new VBox(10);
        leftbox.setPrefSize(TABULEIRO_BOX_X,TABULEIRO_BOX_Y);
        leftbox.setMinSize(TABULEIRO_BOX_X,TABULEIRO_BOX_Y);
        leftbox.setPadding(new Insets(10,10,10,10));
        leftbox.getChildren().addAll(labelHBox,tabuleiroPane);

        //StackPane
        //Iniciar as panes
        InicioPane inicioPane = new InicioPane(observavel);
        Espera_JogadaPane espera_jogadaPane = new Espera_JogadaPane(observavel);
        Espera_Peca_NormalPane espera_peca_normalPane = new Espera_Peca_NormalPane(observavel);
        Espera_Peca_EspecialPane espera_peca_especialPane = new Espera_Peca_EspecialPane(observavel);
        Joga_MinijogoPane joga_minijogoPane = new Joga_MinijogoPane(observavel);
        Minijogo_ContasPane minijogo_contasPane = new Minijogo_ContasPane(observavel);
        Minijogo_PalavrasPane minijogo_palavrasPane = new Minijogo_PalavrasPane(observavel);
        Ver_HistoricoPane historicoPane = new Ver_HistoricoPane(observavel);
        GameOverPane gameOverPane = new GameOverPane(observavel);
        StackPane stackPane = new StackPane(inicioPane, espera_jogadaPane,espera_peca_normalPane,
                espera_peca_especialPane, joga_minijogoPane,minijogo_contasPane,minijogo_palavrasPane,
                historicoPane, gameOverPane);

        stackPane.setBackground(new Background(new BackgroundFill(Color.INDIANRED,new CornerRadii(40),null)));
        stackPane.setMinSize(INFO_BOX_X,INFO_BOX_Y);
        stackPane.setBorder(new Border(new BorderStroke(Color.LIGHTSKYBLUE, BorderStrokeStyle.SOLID,new CornerRadii(40),new BorderWidths(5))));
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
