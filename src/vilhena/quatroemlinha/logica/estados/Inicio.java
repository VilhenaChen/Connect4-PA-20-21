package vilhena.quatroemlinha.logica.estados;

import vilhena.quatroemlinha.logica.Situacao;
import vilhena.quatroemlinha.logica.dados.Dados;

public class Inicio extends EstadoAdapter {

    public Inicio(Dados data) {
        super(data);
    }

    @Override
    public IEstado comecaJogo(String nome1, String nome2, int GameMode) {
        data.criaJogadores(nome1,nome2,GameMode);
        data.setJoga((int)(Math.random()*2));
        return new Espera_Jogada(data);
    }

    @Override
    public Situacao getSituacao() {
        return Situacao.Inicio;
    }
}
