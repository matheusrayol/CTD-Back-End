package com.matheusrayol.avaliacaobackend.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfiguracaoJDBC {

    private String jdbcDriver;

    private String dbUrl;

    private String nomeUsuario;

    private String senhaUsuario;

    private Connection connection;

    public ConfiguracaoJDBC() {
        this.jdbcDriver = "org.h2.Driver";
        this.dbUrl = "jdbc:h2:~/test2;DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM 'create.sql'";
        this.nomeUsuario = "sa";
        this.senhaUsuario = "";
    }

    public Connection conectarBD() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(dbUrl, nomeUsuario, senhaUsuario);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return connection;
    }

}
