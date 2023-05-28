package com.matheusrayol.avaliacaobackend.dao.impl;

import com.matheusrayol.avaliacaobackend.dao.ConfiguracaoJDBC;
import com.matheusrayol.avaliacaobackend.dao.IDao;
import com.matheusrayol.avaliacaobackend.model.Filial;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class FilialDaoImpl implements IDao<Filial> {

    private ConfiguracaoJDBC configuracaoJDBC;
    final static Logger logger = Logger.getLogger(FilialDaoImpl.class);

    public FilialDaoImpl(ConfiguracaoJDBC configuracaoJDBC) {
        this.configuracaoJDBC = new ConfiguracaoJDBC();
    }

    @Override
    public Filial salvar(Filial filial) {
        Connection connection = configuracaoJDBC.conectarBD();
        Statement statement = null;
        String query = String.format("INSERT INTO filial(nomeFilial,rua,numero,cidade,estado,eCincoEstrelas) VALUES('%s','%s','%s','%s','%s','%s')", filial.getNomeFilial(), filial.getRua(), filial.getNumero(), filial.getCidade(), filial.getEstado(), filial.getECincoEstrelas());
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                filial.setId(resultSet.getInt(1));
            }
            logger.debug("Salvando filial: " + filial.toString());
            connection.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return filial;
    }

}
