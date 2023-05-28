import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    private static final String sqlCreateTable = "DROP TABLE IF EXISTS Usuario; CREATE TABLE Usuario "
            + "("
            + "ID INT PRIMARY KEY, "
            + "PrimeiroNome varchar(100) NOT NULL, "
            + "Sobrenome varchar(100) NOT NULL, "
            + "Idade INT NOT NULLLL, "
            + ")";

    private static final String sqlInsert1 = "INSERT INTO Usuario (ID, PrimeiroNome, Sobrenome, Idade) VALUES (1, 'João', 'Silva', 20)";
    private static final String sqlInsert2 = "INSERT INTO Usuario (ID, PrimeiroNome, Sobrenome, Idade) VALUES (2, 'Maria', 'Santos', 25)";
    private static final String sqlInsert3 = "INSERT INTO Usuario (ID, PrimeiroNome, Sobrenome, Idade) VALUES (3, 'Pedro', 'Santos', 30)";

    private static final String sqlDelete = "DELETE FROM Usuario WHERE ID=2";

    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws Exception {

        Connection connection = null;

        try {

            connection = getConnection();

        } catch (Exception e) {

        } finally {
            if (connection != null) connection.close();
        }

    }

    public static Connection getConnection() throws Exception {
        Class.forName("org.h2.Driver").newInstance();
        return DriverManager.getConnection("jdbc:h2:~/test", "sa", "");

    }
}