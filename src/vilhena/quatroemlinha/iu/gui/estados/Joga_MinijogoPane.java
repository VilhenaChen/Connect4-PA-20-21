package vilhena.quatroemlinha.iu.gui.estados;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import vilhena.quatroemlinha.logica.JogoObservavel;

import static vilhena.quatroemlinha.iu.gui.UtilsGui.*;
import static vilhena.quatroemlinha.iu.gui.UtilsGui.QUATRO_EM_LINHA;
import static vilhena.quatroemlinha.logica.Situacao.*;

public class Joga_MinijogoPane extends VBox {
    private JogoObservavel observavel;
    Label perguntaLabel;

    public Joga_MinijogoPane (JogoObservavel observavel) {
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
        perguntaLabel = new Label("");
        perguntaLabel.setFont(Font.font("comic sans", FontWeight.NORMAL, FontPosture.REGULAR,20));
        perguntaLabel.setWrapText(true);
        perguntaLabel.setTextAlignment(TextAlignment.CENTER);

        Button btnSim = new Button("Sim");
        btnSim.setScaleX(1.5);
        btnSim.setScaleY(1.5);
        Button btnNao = new Button("Nao");
        btnNao.setScaleX(1.5);
        btnNao.setScaleY(1.5);

        HBox butoesHBox = new HBox();
        butoesHBox.setAlignment(Pos.CENTER);
        butoesHBox.setSpacing(25);
        butoesHBox.getChildren().addAll(btnSim,btnNao);
        HBox perguntaHBox = new HBox();
        perguntaHBox.setMaxWidth(INFO_BOX_X);
        perguntaHBox.setMaxHeight(INFO_BOX_Y);
        perguntaHBox.getChildren().add(perguntaLabel);
        getChildren().addAll(perguntaHBox,butoesHBox);
        setAlignment(Pos.CENTER);
        setSpacing(20);

        btnSim.setOnAction((e)-> observavel.comecaMinijogo());
        btnNao.setOnAction((e)->observavel.naoJogaMinijogo());
    }

    private void atualiza() {
        this.setVisible(observavel.getSituacao() == Joga_MiniJogo);
        if(observavel.getSituacao() == Joga_MiniJogo) {
            perguntaLabel.setText(observavel.getNomeJogadorAtual() + " deseja jogar um minijogo" +
                    " para tentar ganhar uma peca especial?");
        }
    }
}
