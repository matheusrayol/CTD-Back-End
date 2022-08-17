package pacientes.dao.impl;

import org.apache.log4j.Logger;
import pacientes.ConfiguracaoJdbc;
import pacientes.dao.IDao;
import pacientes.model.Paciente;

import java.sql.Connection;
import java.sql.Statement;


public class PacienteDaoImpl implements IDao<Paciente> {

    private ConfiguracaoJdbc configuracaoJdbc;

    final static Logger log = Logger.getLogger(PacienteDaoImpl.class);

    public PacienteDaoImpl(ConfiguracaoJdbc configuracaoJdbc) {
        this.configuracaoJdbc = configuracaoJdbc;
    }

    @Override
    public Paciente salvar(Paciente paciente) {
        log.debug("Cadastrando novo paciente: " + paciente.toString());
        Connection connection = configuracaoJdbc.conectarDb();
        Statement statement = null;
        String query = String.format("INSERT INTO endereco(rua,numero,bairro,cidade) VALUES('%s','%s','%s','%s')",


    }

    @Override
    public Paciente buscar(Paciente paciente) {
        return null;
    }

    @Override
    public Paciente atualizar(Paciente paciente) {
        return null;
    }

    @Override
    public Paciente excluir(Paciente paciente) {
        return null;
    }
}

