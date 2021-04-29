package vilhena.quatroemlinha;

import vilhena.quatroemlinha.iu.texto.UITexto;
import vilhena.quatroemlinha.logica.MaquinaEstados;

public class Main {
    public static void main(String[] args) {
        MaquinaEstados maquinaEstados = new MaquinaEstados();
        UITexto ui = new UITexto(maquinaEstados);
        ui.corre();
    }


}
