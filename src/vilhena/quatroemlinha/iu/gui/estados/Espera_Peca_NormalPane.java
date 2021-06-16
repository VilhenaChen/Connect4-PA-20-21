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
import vilhena.quatroemlinha.logica.JogoObservavel;

import static vilhena.quatroemlinha.iu.gui.UtilsGui.QUATRO_EM_LINHA;
import static vilhena.quatroemlinha.logica.Situacao.Espera_Peca_Normal;

public class Espera_Peca_NormalPane extends VBox {
    private JogoObservavel observavel;
    Label info, number;
    Button btnSubmeter;
    ComboBox comboBox;
    int col;

    public Espera_Peca_NormalPane(JogoObservavel observavel) {
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
        info = new Label("Escolha a coluna:");
        info.setFont(Font.font("comic sans", FontWeight.NORMAL, FontPosture.REGULAR,25));
        number = new Label("");
        number.setFont(Font.font("comic sans", FontWeight.NORMAL, FontPosture.REGULAR,25));

        btnSubmeter = new Button("Submeter");
        //btnSubmeter.setMinSize(50,50);;
        btnSubmeter.setScaleX(1.2);
        btnSubmeter.setScaleY(1.2);

        comboBox = new ComboBox();
        comboBox.setScaleX(1.2);
        comboBox.setScaleY(1.2);
        for(int i = 1; i <= 7; i++)
            comboBox.getItems().add(i);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(info,number,comboBox,btnSubmeter);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(15);
        vbox.setPadding(new Insets(200,0,0,0));

        getChildren().addAll(vbox);
        btnSubmeter.setOnAction((e)-> {
            if(!observavel.getTipoJogador()) {
                observavel.guardaEstado();
                observavel.pecaJogada(col);
            }
            else {
                observavel.guardaEstado();
                observavel.pecaJogada(((int) comboBox.getValue()) - 1);
            }
        });
    }

    private void atualiza() {
        this.setVisible(observavel.getSituacao() == Espera_Peca_Normal);
        if(observavel.getSituacao() == Espera_Peca_Normal) {
            if(!observavel.getTipoJogador()) {
                info.setText("Joguei na coluna");
                col = (int)(Math.random() * 7);
                number.setText(String.valueOf(col + 1)) ;
                comboBox.setVisible(false);
                btnSubmeter.setText("Avancar");
            }
            else {
                info.setText("Indique a coluna");
                comboBox.setVisible(true);
                number.setText("");
                btnSubmeter.setText("Submeter");
            }
        }
    }
}
