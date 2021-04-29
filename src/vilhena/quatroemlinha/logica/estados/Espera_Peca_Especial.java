package vilhena.quatroemlinha.logica.estados;

import vilhena.quatroemlinha.logica.Situacao;
import vilhena.quatroemlinha.logica.dados.Dados;

public class Espera_Peca_Especial extends EstadoAdapter{

    public Espera_Peca_Especial(Dados data) {
        super(data);
    }

    @Override
    public Situacao getSituacao() {
        return Situacao.Espera_Peca_Especial;
    }
}
