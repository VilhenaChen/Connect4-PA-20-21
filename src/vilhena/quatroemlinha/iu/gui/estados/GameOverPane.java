package vilhena.quatroemlinha.iu.gui.estados;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import vilhena.quatroemlinha.logica.JogoObservavel;

import static vilhena.quatroemlinha.iu.gui.UtilsGui.QUATRO_EM_LINHA;
import static vilhena.quatroemlinha.logica.Situacao.GameOver;

public class GameOverPane extends VBox {
    private JogoObservavel observavel;

    public GameOverPane(JogoObservavel observavel) {
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
        Label gameoverLabel = new Label("Game Over");
        gameoverLabel.setFont(Font.font("comic sans", FontWeight.NORMAL, FontPosture.REGULAR,40));
        gameoverLabel.setAlignment(Pos.CENTER);

        VBox buttonsVBox = new VBox();
        buttonsVBox.setAlignment(Pos.CENTER);

        Button repetirbtn = new Button("Jogar outra Vez");
        Button sairbtn = new Button("Sair");
        buttonsVBox.getChildren().addAll(repetirbtn,sairbtn);
        getChildren().addAll(gameoverLabel,buttonsVBox);
        repetirbtn.setOnAction((e)-> observavel.jogaOutraVez());
        sairbtn.setOnAction((e)-> Platform.exit());
    }

    private void atualiza() {
        this.setVisible(observavel.getSituacao() == GameOver);
        if(observavel.getSituacao() == GameOver) {
            observavel.guardaHistorico();
            observavel.guardaHistoricoFicheiro();
        }
    }
}
