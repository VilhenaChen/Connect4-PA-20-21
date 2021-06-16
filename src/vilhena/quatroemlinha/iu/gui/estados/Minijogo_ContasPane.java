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

public class Minijogo_ContasPane extends VBox {
    private JogoObservavel observavel;
    Label conta, erro;
    TextField input;


    public Minijogo_ContasPane (JogoObservavel observavel) {
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
        conta = new Label("");
        conta.setFont(Font.font("comic sans", FontWeight.BOLD, FontPosture.REGULAR,40));

        HBox contaHBox = new HBox();
        contaHBox.setAlignment(Pos.CENTER);
        contaHBox.getChildren().add(conta);

        erro = new Label("");
        erro.setFont(Font.font("comic sans", FontWeight.BOLD, FontPosture.REGULAR,20));
        erro.setAlignment(Pos.BOTTOM_CENTER);
        erro.setWrapText(true);
        erro.setTextAlignment(TextAlignment.CENTER);

        HBox erroHBox = new HBox();
        erroHBox.setMaxWidth(INFO_BOX_X);
        erroHBox.setMaxHeight(INFO_BOX_Y);
        erroHBox.getChildren().add(erro);

        input = new TextField();
        input.setPrefSize(5,100);
        input.setFont(Font.font("comic sans", FontWeight.BOLD, FontPosture.REGULAR,15));
        input.setAlignment(Pos.CENTER);


        VBox minijogoVBox = new VBox();
        minijogoVBox.getChildren().addAll(contaHBox,input,erroHBox);
        getChildren().addAll(minijogoVBox);
        setAlignment(Pos.CENTER);
        setSpacing(20);

        input.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode().equals(KeyCode.ENTER)) {
                   try {
                        observavel.InputMinijogoContas(input.getText());
                   }catch (NumberFormatException e) {
                       erro.setText("Por favor insira um numero valido!!!");
                   }
                }
            }
        });
    }

    private void atualiza() {
        this.setVisible(observavel.getSituacao() == Minijogo_Contas);
        if (observavel.getSituacao() == Minijogo_Contas) {
            input.setText("");
            erro.setText("");
            conta.setText(observavel.getConta());
            if (observavel.isWinnerMInijogoContas() == 1) { //Ganhou o minijogo
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Ganhou o minijogo");
                a.setContentText("Ganhou uma peca especial");
                a.show();
                observavel.fimMinijogo(true);
            } else if (observavel.isWinnerMInijogoContas() == 2) {
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
