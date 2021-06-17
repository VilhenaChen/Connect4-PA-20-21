package vilhena.quatroemlinha.iu.gui.estados;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import vilhena.quatroemlinha.logica.JogoObservavel;

import static vilhena.quatroemlinha.iu.gui.UtilsGui.*;
import static vilhena.quatroemlinha.logica.Situacao.Minijogo_Contas;
import static vilhena.quatroemlinha.logica.Situacao.Minijogo_Palavras;

public class Minijogo_PalavrasPane extends VBox {
    private JogoObservavel observavel;
    Label frase;
    TextField input;


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
        frase = new Label("");
        frase.setFont(Font.font("comic sans", FontWeight.BOLD, FontPosture.REGULAR,20));
        frase.setWrapText(true);
        frase.setTextAlignment(TextAlignment.CENTER);

        HBox fraseHBox = new HBox();
        fraseHBox.setAlignment(Pos.CENTER);
        fraseHBox.setMaxWidth(INFO_BOX_X);
        fraseHBox.setMaxHeight(INFO_BOX_Y);
        fraseHBox.getChildren().add(frase);

        input = new TextField();
        input.setPrefSize(5,100);
        input.setFont(Font.font("comic sans", FontWeight.BOLD, FontPosture.REGULAR,15));
        input.setAlignment(Pos.CENTER);


        VBox minijogoVBox = new VBox();
        minijogoVBox.getChildren().addAll(fraseHBox,input);
        getChildren().addAll(minijogoVBox);
        setAlignment(Pos.CENTER);
        setSpacing(20);

        input.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode().equals(KeyCode.ENTER)) {
                    observavel.InputMinijogoPalavras(input.getText());
                }
            }
        });
    }

    private void atualiza() {
        this.setVisible(observavel.getSituacao() == Minijogo_Palavras);
        if (observavel.getSituacao() == Minijogo_Palavras) {
            input.setText("");
            frase.setText(observavel.getFrase());
            if (observavel.isWinnerMInijogoPalavras() == 1) { //Ganhou o minijogo
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Ganhou o minijogo");
                a.setContentText("Ganhou uma peca especial");
                a.show();
                observavel.fimMinijogo(true);
            } else if (observavel.isWinnerMInijogoPalavras() == 2) {
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText(null);
                a.setTitle("Perdeu o minijogo");
                a.setContentText("Infelizmente nao ganhou a peca especial");
                a.show();
                observavel.fimMinijogo(false);
            }
        }
    }
}
