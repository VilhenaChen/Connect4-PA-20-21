package vilhena.quatroemlinha.logica.estados;

import vilhena.quatroemlinha.logica.Situacao;
import vilhena.quatroemlinha.logica.dados.Dados;

public class Minijogo_Palavras extends EstadoAdapter{
    public Minijogo_Palavras(Dados data) {
        super(data);
    }

    @Override
    public IEstado submeterInput(String resposta) {
        data.setUserInputPalavras(resposta);
        return this;
    }

    @Override
    public IEstado fimMiniJogo(Boolean ganhou) {
        if(ganhou) {
            data.adicionaPecaEspecial();
        }
        else {
            if(data.getJoga() == J1)
                data.setJoga(J2);
            else
                data.setJoga(J1);
        }
        return new Espera_Jogada(data);
    }

    @Override
    public Situacao getSituacao() {
        return Situacao.Minijogo_Palavras;
    }
}
