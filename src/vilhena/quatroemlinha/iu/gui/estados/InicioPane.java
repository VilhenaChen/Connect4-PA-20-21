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
    VBox nomesJogadores, carregarVBox;

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

        TextField carregaField = new TextField();
        carregaField.setPromptText("Insira o nome do ficheiro que pretende carregar");

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

        VBox buttonsVBox = new VBox();
        buttonsVBox.setSpacing(10);
        buttonsVBox.setAlignment(Pos.CENTER);

        HBox BotoesJogo = new HBox();
        BotoesJogo.setSpacing(10);
        BotoesJogo.setAlignment(Pos.CENTER);

        carregarVBox = new VBox();
        carregarVBox.setVisible(false);
        carregarVBox.setSpacing(10);
        carregarVBox.setAlignment(Pos.CENTER);

        inicio = new VBox();
        inicio.setAlignment(Pos.CENTER);

        Label primeiroJogadorLabel = new Label("Nome do 1º Jogador: ");
        primeiroJogadorLabel.setFont(Font.font("comic sans", FontWeight.NORMAL, FontPosture.REGULAR,15));
        Label segundoJogadorLabel = new Label("Nome do 2º Jogador: ");
        segundoJogadorLabel.setFont(Font.font("comic sans", FontWeight.NORMAL, FontPosture.REGULAR,15));
        TextField primeiroJogadorText = new TextField();
        TextField segundoJogadorText = new TextField();
        primeiroJogadorText.setText("");
        segundoJogadorText.setText("");
        segundoJogadorText.setDisable(false);

        //Alertas
        Alert alertaAmbosOsNomes = new Alert(Alert.AlertType.ERROR);
        alertaAmbosOsNomes.setHeaderText(null);
        alertaAmbosOsNomes.setTitle("Erro");
        alertaAmbosOsNomes.setContentText("Por favor insira ambos os nomes");
        Alert alertaUnicoNome = new Alert(Alert.AlertType.ERROR);
        alertaUnicoNome.setHeaderText(null);
        alertaUnicoNome.setTitle("Erro");
        alertaUnicoNome.setContentText("Por favor insira o nome do primeiro Jogador");
        Alert alertaNomeIguais = new Alert(Alert.AlertType.ERROR);
        alertaNomeIguais.setHeaderText(null);
        alertaNomeIguais.setTitle("Erro");
        alertaNomeIguais.setContentText("Por favor insira nomes distintos para cada utilizador");

        Button HvsHButton = new Button("Humano vs Humano");
        HvsHButton.setScaleX(1.2);
        HvsHButton.setScaleY(1.2);
        Button HvsCButton = new Button("Humano vs CPU");
        HvsCButton.setScaleX(1.2);
        HvsCButton.setScaleY(1.2);
        Button CvsCButton = new Button("CPU vs CPU");
        CvsCButton.setScaleX(1.2);
        CvsCButton.setScaleY(1.2);
        Button btnHistorico = new Button("Historico");
        btnHistorico.setScaleX(1.2);
        btnHistorico.setScaleY(1.2);
        Button btnCarregar = new Button("Carregar jogo");
        btnCarregar.setScaleX(1.2);
        btnCarregar.setScaleY(1.2);
        Button SairButton = new Button("Sair");
        SairButton.setScaleX(1.2);
        SairButton.setScaleY(1.2);
        Button btnComecar = new Button("Comecar");
        btnComecar.setScaleX(1.2);
        btnComecar.setScaleY(1.2);
        Button btnRegressar = new Button("Regressar");
        btnRegressar.setScaleX(1.2);
        btnRegressar.setScaleY(1.2);
        Button btnCarregarFile = new Button("Carregar");
        btnCarregarFile.setScaleX(1.2);
        btnCarregarFile.setScaleY(1.2);
        btnComecar.setMinSize(30,30);
        btnRegressar.setMinSize(30,30);
        buttonsVBox.setSpacing(15);
        buttonsVBox.getChildren().addAll(HvsHButton,HvsCButton,CvsCButton,btnHistorico,btnCarregar,SairButton);
        BotoesJogo.setSpacing(20);
        BotoesJogo.getChildren().addAll(btnComecar,btnRegressar);

        titulo.getChildren().add(menuLabel);
        primeiroJogador.getChildren().addAll(primeiroJogadorLabel,primeiroJogadorText);
        segundoJogador.getChildren().addAll(segundoJogadorLabel,segundoJogadorText);
        nomesJogadores.getChildren().addAll(primeiroJogador,segundoJogador,BotoesJogo);
        carregarVBox.getChildren().addAll(carregaField,btnCarregarFile);
        setAlignment(Pos.CENTER);
        inicio.getChildren().addAll(titulo,buttonsVBox);
        getChildren().addAll(inicio, nomesJogadores,carregarVBox);
        HvsHButton.setOnAction((e)-> {
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
        btnRegressar.setOnAction((e)->{
            inicio.setVisible(true);
            nomesJogadores.setVisible(false);
            primeiroJogadorText.setText("");
            segundoJogadorText.setText("");
            segundoJogadorText.setDisable(false);
        });
        btnCarregar.setOnAction((e)-> {
            carregarVBox.setVisible(true);
        });
        btnCarregarFile.setOnAction((e)-> {
            if(!carregaField.getText().isEmpty()) {
                if(observavel.carregaJogo(carregaField.getText())) {
                    Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                    a.setHeaderText(null);
                    a.setTitle("Carregar");
                    a.setContentText("Jogo carregado com sucesso");
                    a.show();
                    observavel.reload();
                }
                else
                {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setHeaderText(null);
                    a.setTitle("Carregar");
                    a.setContentText("Erro ao carregar o jogo");
                    a.show();
                }
            }
            else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText(null);
                a.setTitle("Carregar");
                a.setContentText("O nome do ficheiro nao pode estar vazio");
                a.show();
            }
        });
        SairButton.setOnAction((e)-> Platform.exit());
    }

    private void atualiza() {
        this.setVisible(observavel.getSituacao() == Inicio);
        if (observavel.getSituacao() == Inicio) {
            carregarVBox.setVisible(false);
            observavel.leHistoricoFicheiro();
            inicio.setVisible(true);
            nomesJogadores.setVisible(false);
        }
    }

}
