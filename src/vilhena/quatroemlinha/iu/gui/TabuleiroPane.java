package vilhena.quatroemlinha.iu.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import vilhena.quatroemlinha.logica.JogoObservavel;

import java.awt.*;
import java.util.ArrayList;

import static vilhena.quatroemlinha.iu.gui.UtilsGui.*;
import static vilhena.quatroemlinha.utils.Util.*;

public class TabuleiroPane extends GridPane {
    private JogoObservavel observavel;
    private GridPane gridPane;

    public TabuleiroPane(JogoObservavel observavel) {
        this.observavel = observavel;
        criarLayout();
        registarObservador();
        atualiza();
    }

    private void registarObservador() {
        observavel.addPropertyChangeListener(QUATRO_EM_LINHA, evt -> {
            atualiza();
        });
    }

    private void criarLayout() {
        gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        setPrefSize(TABULEIRO_BOX_X,TABULEIRO_BOX_Y);
        setBorder(new Border(new BorderStroke(Color.LIGHTSKYBLUE, BorderStrokeStyle.SOLID,new CornerRadii(40),new BorderWidths(5))));

        setBackground(new Background(new BackgroundFill(Color.GOLDENROD,new CornerRadii(40),null)));

        this.setPadding(new Insets(30,30,30,30));
        this.setAlignment(Pos.CENTER);
        this.setHgap(GAP_X_TABULEIRO);
        this.setVgap(GAP_Y_TABULEIRO);
        this.getChildren().addAll(gridPane);
    }

    private void atualiza() {
        //percorrer o tabuleiro
        for(int i = 0; i < ALTURA; i++) {
            for(int j = 0; j < LARGURA; j++) {
                Circle circle = new Circle();
                if(observavel.lePosArray(j,i) == CORJ1) {
                    circle.setRadius(30);
                    circle.setFill(Color.RED);
                }
                else if(observavel.lePosArray(j,i) == CORJ2) {
                    circle.setRadius(30);
                    circle.setFill(Color.YELLOW);
                }
                else {
                    circle.setRadius(30);
                    circle.setFill(Color.WHITE);
                }
                gridPane.add(circle,j,i);
            }
        }
    }
}
