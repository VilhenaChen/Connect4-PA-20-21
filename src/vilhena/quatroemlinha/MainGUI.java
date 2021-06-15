package vilhena.quatroemlinha;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vilhena.quatroemlinha.iu.gui.Root;
import vilhena.quatroemlinha.logica.JogoObservavel;
import vilhena.quatroemlinha.logica.MaquinaEstados;

public class MainGUI extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        MaquinaEstados maquinaEstado = new MaquinaEstados();
        JogoObservavel observavel = new JogoObservavel(maquinaEstado);

        Root root = new Root(observavel);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Quatro em Linha");
        stage.setOnCloseRequest(windowEvent -> Platform.exit());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
