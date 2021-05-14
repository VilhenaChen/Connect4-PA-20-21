package vilhena.quatroemlinha.logica.estados;

import vilhena.quatroemlinha.logica.Situacao;
import vilhena.quatroemlinha.logica.dados.Dados;

public class Joga_MiniJogo extends EstadoAdapter{

    public Joga_MiniJogo(Dados data) {
        super(data);
    }

    @Override
    public IEstado fimMiniJogo() {
        return new Espera_Jogada(data);
    }

    @Override
    public Situacao getSituacao() {
        return Situacao.Joga_MiniJogo;
    }
}
