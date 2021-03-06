package vilhena.quatroemlinha.logica.estados;

import vilhena.quatroemlinha.logica.Situacao;
import vilhena.quatroemlinha.logica.dados.Dados;

public class GameOver extends EstadoAdapter{

    public GameOver(Dados data) {
        super(data);
    }

    @Override
    public IEstado jogaOutraVez(Dados newData) {
        return new Inicio(newData);
    }

    @Override
    public Situacao getSituacao() {
        return Situacao.GameOver;
    }
}
