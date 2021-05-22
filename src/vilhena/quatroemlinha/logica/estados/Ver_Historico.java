package vilhena.quatroemlinha.logica.estados;

import vilhena.quatroemlinha.logica.Situacao;
import vilhena.quatroemlinha.logica.dados.Dados;

public class Ver_Historico extends EstadoAdapter {
    public Ver_Historico(Dados data) {
        super(data);
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
