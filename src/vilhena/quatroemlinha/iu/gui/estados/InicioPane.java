package vilhena.quatroemlinha.iu.gui.estados;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
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
        Button HvsHButton = new Button("Humano vs Humano");
        Button HvsCButton = new Button("Humano vs CPU");
        Button CvsCButton = new Button("CPU vs CPU");
        Button SairButton = new Button("Sair");
        setBackground(new Background(new BackgroundFill(Color.INDIANRED,new CornerRadii(40),null)));
        setAlignment(Pos.CENTER);
        getChildren().addAll(menuLabel,HvsHButton,HvsCButton,CvsCButton,SairButton);
    }

    private void atualiza() {
        this.setVisible(observavel.getSituacao() == Inicio);
    }

}
