package vilhena.quatroemlinha.logica.estados;

import vilhena.quatroemlinha.logica.Situacao;
import vilhena.quatroemlinha.logica.dados.Dados;
import vilhena.quatroemlinha.logica.dados.Jogador;

import java.util.ArrayList;

public class Ver_Historico extends EstadoAdapter {
    int jogoHistorico; //Variavel util para saber que jogo do historico estamos a ver
    int turnoHistorico; //Variavel util para saber que turno do historico estamos a ver

    public Ver_Historico(Dados data, int jogo) {
        super(data);
        this.jogoHistorico = jogo;
        this.turnoHistorico = 0;;

    }

    @Override
    public IEstado continuaHistorico(ArrayList<ArrayList<Dados>> historico, int num) {
        try{
            copia(historico);
        }catch (IndexOutOfBoundsException e) {
            throw e;
        }
        if(num == AVANCAR) {
            turnoHistorico++;
        }
        else if(num == RECUAR && turnoHistorico != -1) {
            turnoHistorico--;
        }
        return this;
    }

    public void copia(ArrayList<ArrayList<Dados>> historico) {
        data.setJoga(historico.get(jogoHistorico).get(turnoHistorico).getJoga());
        data.setTurno(historico.get(jogoHistorico).get(turnoHistorico).getTurno());
        data.setJogadores(historico.get(jogoHistorico).get(turnoHistorico).getJogadores());
        data.setTabuleiro(historico.get(jogoHistorico).get(turnoHistorico).getTabbuleiro());

    }

    @Override
    public IEstado sairHistorico(Dados newData) {
        return new Inicio(newData);
    }

    @Override
    public Situacao getSituacao() {
        return Situacao.Ver_Historico;
    }
}
