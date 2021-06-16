package vilhena.quatroemlinha.iu.gui.estados;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
    Label nomeJogador, turno, nrCreditos, nrPecasEspeciais;

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
        nomeJogador = new Label("");
        turno = new Label("");
        nrCreditos = new Label("");
        nrPecasEspeciais = new Label("");
        turno.setFont(Font.font("comic sans", FontWeight.NORMAL, FontPosture.REGULAR,40));
        nomeJogador.setFont(Font.font("comic sans", FontWeight.NORMAL, FontPosture.REGULAR,30));
        nrPecasEspeciais.setFont(Font.font("comic sans", FontWeight.NORMAL, FontPosture.REGULAR,15));
        nrCreditos.setFont(Font.font("comic sans", FontWeight.NORMAL, FontPosture.REGULAR,15));
        Button btnpecaNormal = new Button("Jogar Peca Normal");
        Button btnpecaEspecial = new Button("Jogar Peca Especial");
        Button btnCreditos = new Button("Usar creditos");
        Button btngravarJogo = new Button("Gravar Jogo");
        Button btnLogs = new Button("Ver Logs");
        Button btnSair = new Button("Sair");
        HBox turnoHBox = new HBox();
        HBox jogadorHBox = new HBox();
        HBox especialHBox = new HBox();
        HBox creditosHBox = new HBox();
        VBox botoesVBox = new VBox();
        botoesVBox.setAlignment(Pos.CENTER);
        turnoHBox.setAlignment(Pos.CENTER);
        turnoHBox.getChildren().addAll(turno);
        jogadorHBox.setAlignment(Pos.CENTER);
        jogadorHBox.getChildren().addAll(nomeJogador);
        especialHBox.setAlignment(Pos.CENTER);
        especialHBox.getChildren().addAll(nrPecasEspeciais);
        creditosHBox.setAlignment(Pos.CENTER);
        creditosHBox.getChildren().addAll(nrCreditos);
        botoesVBox.getChildren().addAll(btnpecaNormal,btnpecaEspecial,btnCreditos,btngravarJogo,btnLogs,btnSair);
        getChildren().addAll(turnoHBox,jogadorHBox,especialHBox,creditosHBox,botoesVBox);
    }

    private void atualiza() {
        this.setVisible(observavel.getSituacao() == Espera_Peca_Normal);
        if(observavel.getSituacao() == Espera_Peca_Normal) {
            turno.setText("Turno: " + observavel.getTurno());
            nomeJogador.setText("Jogador: " + observavel.getNomeJogadorAtual());
            nrCreditos.setText("Nr Creditos: " +observavel.getNrCreditos());
            nrPecasEspeciais.setText("Pecas Especias: " + observavel.getNrPecasEspeciais());
        }
    }
}
