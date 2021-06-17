package vilhena.quatroemlinha.iu.gui.estados;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
import static vilhena.quatroemlinha.logica.Situacao.GameOver;

public class GameOverPane extends VBox {
    private JogoObservavel observavel;
    Label vencedor;

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

        vencedor = new Label("");
        vencedor.setFont(Font.font("comic sans", FontWeight.NORMAL, FontPosture.REGULAR,20));
        vencedor.setAlignment(Pos.CENTER);
        vencedor.setWrapText(true);
        vencedor.setTextAlignment(TextAlignment.CENTER);

        HBox vencedorHBox = new HBox();
        vencedorHBox.setMaxWidth(INFO_BOX_X);
        vencedorHBox.setMaxHeight(INFO_BOX_Y);
        vencedorHBox.setAlignment(Pos.CENTER);
        vencedorHBox.setPadding(new Insets(10,0,10,0));
        vencedorHBox.getChildren().add(vencedor);


        VBox buttonsVBox = new VBox();
        buttonsVBox.setAlignment(Pos.CENTER);

        Button repetirbtn = new Button("Jogar outra Vez");
        repetirbtn.setScaleX(1.2);
        repetirbtn.setScaleY(1.2);
        Button sairbtn = new Button("Sair");
        sairbtn.setScaleX(1.2);
        sairbtn.setScaleY(1.2);
        buttonsVBox.setSpacing(15);
        buttonsVBox.getChildren().addAll(repetirbtn,sairbtn);
        getChildren().addAll(gameoverLabel,vencedorHBox,buttonsVBox);
        setAlignment(Pos.CENTER);
        repetirbtn.setOnAction((e)-> observavel.jogaOutraVez());
        sairbtn.setOnAction((e)-> Platform.exit());
    }

    private void atualiza() {
        this.setVisible(observavel.getSituacao() == GameOver);
        if(observavel.getSituacao() == GameOver) {
            observavel.guardaHistorico();
            observavel.guardaHistoricoFicheiro();
            if(observavel.verSeGanhou()) {
                vencedor.setText(observavel.getNomeJogadorAtual() + " foi o Vencedor");
            }
            else {
                vencedor.setText("O tabuleiro fico cheio");
            }

        }
    }
}
