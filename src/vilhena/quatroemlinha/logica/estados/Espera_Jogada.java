package vilhena.quatroemlinha.logica.estados;

import vilhena.quatroemlinha.logica.Situacao;
import vilhena.quatroemlinha.logica.dados.Dados;

public class Espera_Jogada extends EstadoAdapter{


    public Espera_Jogada(Dados data) {
        super(data);
    }

    @Override
    public IEstado jogaPecaEspecial() {
        return new Espera_Peca_Especial(data);
    }

    @Override
    public IEstado jogaPecaNormal() {
        return new Espera_Peca_Normal(data);
    }

    @Override
    public IEstado jogaMiniJogo() {
        return super.jogaMiniJogo();
    }

    @Override
    public Situacao getSituacao() {
        return Situacao.Espera_Jogada;
    }
}
