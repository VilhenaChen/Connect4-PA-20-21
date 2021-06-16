package vilhena.quatroemlinha.iu.gui.estados;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import vilhena.quatroemlinha.logica.JogoObservavel;

import static vilhena.quatroemlinha.iu.gui.UtilsGui.QUATRO_EM_LINHA;
import static vilhena.quatroemlinha.logica.Situacao.Inicio;
import static vilhena.quatroemlinha.utils.Util.*;

public class InicioPane extends VBox {
    private JogoObservavel observavel;
    VBox inicio;
    VBox nomesJogadores;

    public InicioPane(JogoObservavel observavel) {
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
        Label menuLabel = new Label("Menu Principal");
        menuLabel.setFont(Font.font("comic sans", FontWeight.NORMAL, FontPosture.REGULAR,40));

        HBox titulo = new HBox();
        titulo.setAlignment(Pos.TOP_CENTER);

        HBox primeiroJogador = new HBox();
        //primeiroJogador.setPadding(new Insets(0,20,10,0));

        HBox segundoJogador = new HBox();
        //segundoJogador.setPadding(new Insets(0,20,10,0));

        nomesJogadores = new VBox();
        nomesJogadores.setVisible(false);
        nomesJogadores.setAlignment(Pos.CENTER);
        nomesJogadores.setPadding(new Insets(0,10,100,0));
        nomesJogadores.setSpacing(10);

        inicio = new VBox();
        inicio.setAlignment(Pos.CENTER);

        Label primeiroJogadorLabel = new Label("Nome do 1º Jogador: ");
        primeiroJogadorLabel.setFont(Font.font("comic sans", FontWeight.NORMAL, FontPosture.REGULAR,15));
        Label segundoJogadorLabel = new Label("Nome do 2º Jogador: ");
        segundoJogadorLabel.setFont(Font.font("comic sans", FontWeight.NORMAL, FontPosture.REGULAR,15));
        TextField primeiroJogadorText = new TextField();
        TextField segundoJogadorText = new TextField();

        //Alertas
        Alert alertaAmbosOsNomes = new Alert(Alert.AlertType.ERROR);
        alertaAmbosOsNomes.setTitle("Erro");
        alertaAmbosOsNomes.setContentText("Por favor insira ambos os nomes");
        Alert alertaUnicoNome = new Alert(Alert.AlertType.ERROR);
        alertaUnicoNome.setTitle("Erro");
        alertaUnicoNome.setContentText("Por favor insira o nome do primeiro Jogador");
        Alert alertaNomeIguais = new Alert(Alert.AlertType.ERROR);
        alertaNomeIguais.setTitle("Erro");
        alertaNomeIguais.setContentText("Por favor insira nomes distintos para cada utilizador");

        Button HvsHButton = new Button("Humano vs Humano");
        Button HvsCButton = new Button("Humano vs CPU");
        Button CvsCButton = new Button("CPU vs CPU");
        Button SairButton = new Button("Sair");
        Button btnComecar = new Button("Comecar");
        btnComecar.setMinSize(30,30);

        titulo.getChildren().add(menuLabel);
        primeiroJogador.getChildren().addAll(primeiroJogadorLabel,primeiroJogadorText);
        segundoJogador.getChildren().addAll(segundoJogadorLabel,segundoJogadorText);
        nomesJogadores.getChildren().addAll(primeiroJogador,segundoJogador,btnComecar);
        setAlignment(Pos.CENTER);
        inicio.getChildren().addAll(titulo,HvsHButton, HvsCButton,CvsCButton,SairButton);
        getChildren().addAll(inicio, nomesJogadores);
        HvsHButton.setOnAction((e)-> {
            /*if(primeiroJogadorText.getText().isEmpty() || segundoJogadorText.getText().isEmpty()) {
                alertaAmbosOsNomes.showAndWait();
            }
            else if(primeiroJogadorText.getText().equals(segundoJogadorText.getText())) {
                alertaNomeIguais.showAndWait();
            }
            else {
                observavel.comeca(primeiroJogadorText.getText(), segundoJogadorText.getText(), HUMANO_HUMANO);
            }*/
            inicio.setVisible(false);
            nomesJogadores.setVisible(true);
        });
        HvsCButton.setOnAction((e)-> {
            inicio.setVisible(false);
            nomesJogadores.setVisible(true);
            segundoJogadorText.setDisable(true);
            segundoJogadorText.setText("CPU 1");
        });
        CvsCButton.setOnAction((e)-> observavel.comeca("CPU 1", "CPU 2", CPU_CPU));
        btnComecar.setOnAction((e)-> {
            if(!segundoJogadorText.getText().equals("CPU 1")) {
                if(primeiroJogadorText.getText().isEmpty() || segundoJogadorText.getText().isEmpty()) {
                    alertaAmbosOsNomes.showAndWait();
                }
                else if(primeiroJogadorText.getText().equals(segundoJogadorText.getText())) {
                    alertaNomeIguais.showAndWait();
                }
                else {
                    observavel.comeca(primeiroJogadorText.getText(), segundoJogadorText.getText(), HUMANO_HUMANO);
                }
            }
            else {
                if(primeiroJogadorText.getText().isEmpty()) {
                    alertaUnicoNome.showAndWait();
                }
                else {
                    observavel.comeca(primeiroJogadorText.getText(),segundoJogadorText.getText(),HUMANO_CPU);
                }
            }
        });
        SairButton.setOnAction((e)-> Platform.exit());
    }

    private void atualiza() {
        this.setVisible(observavel.getSituacao() == Inicio);
        if (observavel.getSituacao() == Inicio) {
            inicio.setVisible(true);
            nomesJogadores.setVisible(false);
        }
    }

}
