package vilhena.quatroemlinha.iu.gui.estados;

import com.sun.marlin.stats.Histogram;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import vilhena.quatroemlinha.logica.JogoObservavel;

import static vilhena.quatroemlinha.iu.gui.UtilsGui.*;
import static vilhena.quatroemlinha.logica.Situacao.Ver_Historico;
import static vilhena.quatroemlinha.utils.Util.*;

public class Ver_HistoricoPane extends VBox {
    private JogoObservavel observavel;
    Label nomeJogador, turno;
    Circle peca;

    public Ver_HistoricoPane(JogoObservavel observavel) {
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
        turno = new Label("");
        turno.setFont(Font.font("comic sans", FontWeight.NORMAL, FontPosture.REGULAR,40));
        nomeJogador = new Label("");
        nomeJogador.setFont(Font.font("comic sans", FontWeight.NORMAL, FontPosture.REGULAR,30));

        peca = new Circle();


        Button btnAvancar = new Button("Avancar");
        btnAvancar.setScaleX(1.2);
        btnAvancar.setScaleY(1.2);
        Button btnRecuar = new Button("Recuar");
        btnRecuar.setScaleX(1.2);
        btnRecuar.setScaleY(1.2);
        Button btnSair = new Button("Sair");
        btnSair.setScaleX(1.2);
        btnSair.setScaleY(1.2);

        HBox turnoHBox = new HBox();
        turnoHBox.setAlignment(Pos.CENTER);
        turnoHBox.getChildren().addAll(turno);
        HBox jogadorHBox = new HBox();
        jogadorHBox.setAlignment(Pos.CENTER);
        jogadorHBox.getChildren().addAll(nomeJogador,peca);
        VBox buttonsVBox = new VBox();
        buttonsVBox.setAlignment(Pos.CENTER);
        buttonsVBox.setPadding(new Insets(10,0,10,0));
        buttonsVBox.setSpacing(10);
        buttonsVBox.getChildren().addAll(btnAvancar,btnRecuar,btnSair);


        getChildren().addAll(turnoHBox,jogadorHBox,buttonsVBox);
        setAlignment(Pos.CENTER);
        btnAvancar.setOnAction((e)-> {
            try {
                observavel.continuaHistorico(AVANCAR);
            }catch (IndexOutOfBoundsException d) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText(null);
                a.setTitle("Erro Historico");
                a.setContentText("Nao existem mais jogadas para a frente");
                a.show();
            }
        });
        btnRecuar.setOnAction((e)-> {
            try {
                observavel.continuaHistorico(RECUAR);
            }catch (IndexOutOfBoundsException d) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText(null);
                a.setTitle("Erro Historico");
                a.setContentText("Nao existem mais jogadas para recuar");
                a.show();
            }
        });
        btnSair.setOnAction((e)->observavel.sairHistorico());
    }

    private void atualiza() {
        this.setVisible(observavel.getSituacao() == Ver_Historico);
        if(observavel.getSituacao() == Ver_Historico) {
            turno.setText("Turno: " + observavel.getTurno());
            nomeJogador.setText(observavel.getNomeJogadorAtual());
            if (observavel.getCorJogadorAtual().equals(corJ1)) {
                peca.setRadius(30);
                peca.setFill(Color.RED);
            } else {
                peca.setRadius(30);
                peca.setFill(Color.YELLOW);
            }
        }
    }
}
