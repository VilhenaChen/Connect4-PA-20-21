package vilhena.quatroemlinha.logica.estados;

import vilhena.quatroemlinha.logica.Situacao;
import vilhena.quatroemlinha.logica.dados.Dados;

public class Espera_Peca_Normal extends EstadoAdapter{

    public Espera_Peca_Normal(Dados data) {
        super(data);
    }

    @Override
    public IEstado jogaPecaNormal() {
        int jogadorAtual = data.getJoga();
        boolean flag;
        if(jogadorAtual == J1) {
            data.setJogou(jogadorAtual, true);
            if(data.getJogou(J1) == true && data.getJogou(J2) == true) {
                data.setTurno(data.getTurno() + 1);
            }
            data.setJoga(J2);
        }
        else {
            data.setJogou(jogadorAtual, true);
            if(data.getJogou(J1) == true && data.getJogou(J2) == true) {
                data.setTurno(data.getTurno() + 1);
            }
            data.setJoga(J1);
        }
        return new Espera_Jogada(data);
    }

    @Override
    public Situacao getSituacao() {
        return Situacao.Espera_Peca_Normal;
    }
}
