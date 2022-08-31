import org.apache.log4j.Logger;

import java.sql.*;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);

    private static final String criarTabela = "DROP TABLLE IF EXISTS funcionario; CREATE TABLE funcionario "
            + "("
            + "id INT PRIMARY KEY, "
            + "nome varchar(100) NOT NULL, "
            + "sobrenome varchar(100) NOT NULL, "
            + "email varchar(100) NOT NULL, "
            + "idade INT NOT NULL"
            + ")";

    private static final String funcionario1 = "INSERT INTO funcionario VALUES (1, 'Matheus', 'Rayol', 'falecom@matheusrayol.com', 34)";
    private static final String funcionario2 = "INSERT INTO funcionario VALUES (2, 'Matheus', 'Ferreira', 'falecom@matheusferreira.com', 34)";
    private static final String funcionario3 = "INSERT INTO funcionario VALUES (1, 'Matheus', 'Rayol Ferreira', 'falecom@matheusrayolferreira.com', 34)";

    private static final String funcionario4 = "INSERT INTO funcionario VALUES (3, 'Matheus', 'Rayol Ferreira', 'falecom@matheusrayolferreira.com', 34)";

    private static final String excluirPorId = "DELETE FROM funcionario WHERE id=2";
    private static final String excluirPorEmail = "DELETE FROM funcionario WHERE email='falecom@matheusrayol.com'";
    private static final String atualizarPorId = "UPDATE funcionario SET email='falecom@matheusrayol.com' WHERE id=3";

    private static final String localizarPorId = "SELECT * FROM funcionario WHERE id=3";

    public static void main(String[] args) throws Exception {

        Connection connection = null;

        try {
            connection = getConnection();
            Statement statement = connection.createStatement();

            statement.execute(criarTabela);
            logger.info("A tabela funcionario foi criada.");

            statement.execute(funcionario1);
            logger.info("O funcionário 1 foi incluído com sucesso.");

            statement.execute(funcionario2);
            logger.info("O funcionário 2 foi incluído com sucesso.");

            try {
                statement.execute(funcionario3);
                logger.info("O funcionário 3 foi incluído com sucesso.");
            } catch (SQLException ignore) {
                logger.error("A PK para o funcionário não é exclusiva.");
            }

            statement.executeUpdate(atualizarPorId);
            ResultSet busca = statement.executeQuery(localizarPorId);

            while (busca.next()) {
                logger.info("\nId: " + busca.getInt(1)
                        + "\n Nome: " + busca.getString(2)
                        + "\n Sobrenome: " + busca.getString(3)
                        + "\n Email: " + busca.getString(4)
                        + "\n Idade: " + busca.getInt(5));
            }

            logger.debug("Funcionário atualizado: " + busca);

            statement.executeUpdate(excluirPorId);
            logger.info("Funcionário excluído pela ID (Chave primária)");

            statement.executeUpdate(excluirPorEmail);
            logger.info("Funcionário excluído pelo endereço de e-mail");

        } catch (Exception ex) {
            logger.error(ex.getMessage());

        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:~/test", "root", "");
    }

}