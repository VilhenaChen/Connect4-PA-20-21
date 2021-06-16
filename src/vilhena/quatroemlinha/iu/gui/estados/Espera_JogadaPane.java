package vilhena.quatroemlinha.iu.gui.estados;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import vilhena.quatroemlinha.logica.JogoObservavel;

import static vilhena.quatroemlinha.iu.gui.UtilsGui.QUATRO_EM_LINHA;
import static vilhena.quatroemlinha.logica.Situacao.Espera_Jogada;
import static vilhena.quatroemlinha.utils.Util.*;

public class Espera_JogadaPane extends VBox {
    private JogoObservavel observavel;
    Label nomeJogador, turno, nrCreditos, nrPecasEspeciais;
    Circle peca;
    Button btnpecaNormal;
    Button btnpecaEspecial;
    Button btnCreditos;

    public Espera_JogadaPane(JogoObservavel observavel) {
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
        nomeJogador = new Label("");
        turno = new Label("");
        peca = new Circle();
        nrCreditos = new Label("");
        nrPecasEspeciais = new Label("");
        turno.setFont(Font.font("comic sans", FontWeight.NORMAL, FontPosture.REGULAR,40));
        nomeJogador.setFont(Font.font("comic sans", FontWeight.NORMAL, FontPosture.REGULAR,30));
        nrPecasEspeciais.setFont(Font.font("comic sans", FontWeight.NORMAL, FontPosture.REGULAR,15));
        nrCreditos.setFont(Font.font("comic sans", FontWeight.NORMAL, FontPosture.REGULAR,15));

        //Botoes
        btnpecaNormal = new Button("Jogar Peca Normal");
        btnpecaEspecial = new Button("Jogar Peca Especial");
        btnCreditos = new Button("Usar creditos");
        Button btngravarJogo = new Button("Gravar Jogo");
        Button btnLogs = new Button("Ver Logs");
        Button btnSair = new Button("Sair");

        //Boxes
        HBox turnoHBox = new HBox();
        HBox jogadorHBox = new HBox();
        HBox infoHBox = new HBox();
        VBox infoVBox = new VBox();
        VBox jogadorVBox = new VBox();
        VBox botoesVBox = new VBox();

        botoesVBox.setAlignment(Pos.CENTER);
        turnoHBox.setPadding(new Insets(30));
        jogadorVBox.setPadding(new Insets(30));
        botoesVBox.setAlignment(Pos.CENTER);
        turnoHBox.setAlignment(Pos.CENTER);
        turnoHBox.getChildren().addAll(turno);
        jogadorHBox.setAlignment(Pos.CENTER);
        jogadorHBox.getChildren().addAll(nomeJogador);
        infoVBox.getChildren().addAll(nrPecasEspeciais,nrCreditos);
        infoVBox.setPadding(new Insets(0,0,0,20));
        infoHBox.getChildren().addAll(peca,infoVBox);
        infoHBox.setPadding(new Insets(10,0,0,20));
        jogadorVBox.getChildren().addAll(jogadorHBox,infoHBox);
        botoesVBox.getChildren().addAll(btnpecaNormal,btnpecaEspecial,btnCreditos,btngravarJogo,btnLogs,btnSair);
        botoesVBox.setSpacing(5);
        getChildren().addAll(turnoHBox,jogadorVBox,botoesVBox);

        btnpecaNormal.setOnAction((e)-> observavel.jogaPecaNormal());
        btnpecaEspecial.setOnAction((e)->{
            if(observavel.getNrPecasEspeciais() == 0) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Pecas Especiais");
                a.setContentText("Nao tem pecas especiais");
                a.showAndWait();
            }
            else {
                observavel.jogaPecaEspecial();
            }
        });
    }

    private void atualiza() {
        this.setVisible(observavel.getSituacao() == Espera_Jogada);
        //Por a cor da peca
        if (observavel.getSituacao() == Espera_Jogada) {
            turno.setText("Turno: " + observavel.getTurno());
            nomeJogador.setText("Jogador: " + observavel.getNomeJogadorAtual());
            nrCreditos.setText("Creditos: " + observavel.getNrCreditosString());
            nrPecasEspeciais.setText("Pecas Especias: " + observavel.getNrPecasEspeciaisString());
            if (observavel.getCorJogadorAtual().equals(corJ1)) {
                peca.setRadius(30);
                peca.setFill(Color.RED);
            } else {
                peca.setRadius(30);
                peca.setFill(Color.YELLOW);
            }
            //Por o Tipo de jogador
            if(!observavel.getTipoJogador()) {
                btnpecaNormal.setText("Avancar");
                btnpecaEspecial.setVisible(false);
                btnCreditos.setVisible(false);
            }
            else {
                btnpecaNormal.setText("Jogar Peca Normal");
                btnpecaEspecial.setVisible(true);
                btnCreditos.setVisible(true);
            }
        }
    }

}
