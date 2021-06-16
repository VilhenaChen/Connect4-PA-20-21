package vilhena.quatroemlinha.iu.gui.estados;

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
import static vilhena.quatroemlinha.logica.Situacao.Espera_Peca_Especial;

public class Espera_Peca_EspecialPane extends VBox {
    private JogoObservavel observavel;

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
        Label info = new Label("Escolha a coluna:");
        info.setFont(Font.font("comic sans", FontWeight.NORMAL, FontPosture.REGULAR,25));

        Button btnSubmeter = new Button("Submeter");
        btnSubmeter.setMinSize(50,50);;

        ComboBox comboBox = new ComboBox();
        for(int i = 1; i <= 7; i++)
            comboBox.getItems().add(i);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(info,comboBox,btnSubmeter);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(15);
        vbox.setPadding(new Insets(200,0,0,0));

        getChildren().addAll(vbox);
        btnSubmeter.setOnAction((e)-> observavel.pecaJogada(((int)comboBox.getValue()) - 1));
    }

    private void atualiza() {
        this.setVisible(observavel.getSituacao() == Espera_Peca_Especial);
        if(observavel.getSituacao() == Espera_Peca_Especial) {
            if(!observavel.getTipoJogador()) {
            }
        }
    }
}
