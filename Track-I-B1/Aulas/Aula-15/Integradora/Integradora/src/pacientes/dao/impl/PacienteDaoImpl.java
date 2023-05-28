package pacientes.dao.impl;

import org.apache.log4j.Logger;
import pacientes.dao.ConfiguracaoJDBC;
import pacientes.dao.IDao;
import pacientes.model.Paciente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class PacienteDaoImpl implements IDao<Paciente> {

    private ConfiguracaoJDBC configuracaoJDBC;

    public PacienteDaoImpl() {
        this.configuracaoJDBC = new ConfiguracaoJDBC();
    }

    @Override
    public Paciente salvar(Paciente paciente) {
        Connection connection = configuracaoJDBC.conectarDb();
        String queryPaciente = "INSERT INTO paciente(nome, sobrenome, rg, data, endereco) VALUES(?,?,?,?,?)";
        String queryEndereco = "INSERT INTO endereco(rua, numero, cidade, bairro) VALUES(?,?,?,?)";

        try {
            PreparedStatement SQLInsertEndereco = connection.prepareStatement(queryEndereco, Statement.RETURN_GENERATED_KEYS);

            SQLInsertEndereco.setString(1, paciente.getEndereco().getRua());
            SQLInsertEndereco.setString(2, paciente.getEndereco().getNumero());
            SQLInsertEndereco.setString(3, paciente.getEndereco().getCidade());
            SQLInsertEndereco.setString(4, paciente.getEndereco().getBairro());
            SQLInsertEndereco.execute();

            ResultSet keys = SQLInsertEndereco.getGeneratedKeys();
            int id = 0;

            while (keys.next()) {
                id = keys.getInt(1);
            }

            PreparedStatement SQLInsert = connection.prepareStatement(queryPaciente, Statement.RETURN_GENERATED_KEYS);

            SQLInsert.setString(1, paciente.getNome());
            SQLInsert.setString(2, paciente.getSobrenome());
            SQLInsert.setString(3, paciente.getRg());
            SQLInsert.setObject(4, paciente.getData());
            SQLInsert.setInt(5, id);
            SQLInsert.execute();

            ResultSet key = SQLInsert.getGeneratedKeys();

            if (key.next()) {
                paciente.setId(key.getInt(1));
            }

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return paciente;
    }

    @Override
    public List<Paciente> localizarTodos() {
        Connection connection = configuracaoJDBC.conectarDb();
        Statement statement = null;
        String query = String.format("SELECT * FROM paciente");
        List<Paciente> pacienteList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                pacienteList.add(new Paciente(resultSet.getInt("id"), resultSet.getString("nome")));
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return pacienteList;
    }

    @Override
    public void excluirPorId(int id) {
        Connection connection = configuracaoJDBC.conectarDb();
        String query = "DELETE FROM paciente WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            System.out.println("Deletado com sucesso!");

        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public Paciente encontrarPorId(int id) {
        Connection connection = configuracaoJDBC.conectarDb();
        String query = "SELECT * FROM paciente WHERE id = ?";
        Paciente paciente = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()){
                paciente = new Paciente(resultSet.getInt("id"), resultSet.getString("nome"));
            }
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return paciente;
    }

}

