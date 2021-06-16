package vilhena.quatroemlinha.iu.gui.estados;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import vilhena.quatroemlinha.logica.JogoObservavel;
import vilhena.quatroemlinha.logica.Situacao;

import static vilhena.quatroemlinha.iu.gui.UtilsGui.QUATRO_EM_LINHA;
import static vilhena.quatroemlinha.logica.Situacao.Inicio;

public class InicioPane extends VBox {
    private JogoObservavel observavel;

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
        HBox primeiroJogador = new HBox();
        HBox segundoJogador = new HBox();
        HBox botoes = new HBox();
        Label primeiroJogadorLabel = new Label("Insira o Nome do 1º Jogador:");
        Label segundoJogadorLabel = new Label("Insira o Nome do 2º Jogador:");
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
        primeiroJogador.getChildren().addAll(primeiroJogadorLabel,primeiroJogadorText);
        segundoJogador.getChildren().addAll(segundoJogadorLabel,segundoJogadorText);
        Button HvsHButton = new Button("Humano vs Humano");
        Button HvsCButton = new Button("Humano vs CPU");
        Button CvsCButton = new Button("CPU vs CPU");
        Button SairButton = new Button("Sair");
        setAlignment(Pos.CENTER);
        getChildren().addAll(menuLabel,primeiroJogador,segundoJogador,HvsHButton,HvsCButton,CvsCButton,SairButton);
        HvsHButton.setOnAction((e)-> {
            if(primeiroJogadorText.getText().isEmpty() || segundoJogadorText.getText().isEmpty()) {
                alertaAmbosOsNomes.showAndWait();
            }
            else {
                observavel.comeca(primeiroJogadorText.getText(), segundoJogadorText.getText(), 1);
            }
        });
        HvsCButton.setOnAction((e)-> {
            if(primeiroJogadorText.getText().isEmpty()) {
                alertaUnicoNome.showAndWait();
            }
            else {
                observavel.comeca(primeiroJogadorText.getText(), "CPU 2", 1);
            }
        });
        CvsCButton.setOnAction((e)-> observavel.comeca("CPU 1", "CPU 2", 1));
        SairButton.setOnAction((e)-> Platform.exit());
    }

    private void atualiza() {
        this.setVisible(observavel.getSituacao() == Inicio);
    }

}
