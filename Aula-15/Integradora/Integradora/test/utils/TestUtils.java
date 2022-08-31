package utils;

import pacientes.model.Endereco;
import pacientes.model.Paciente;

import java.time.LocalDate;

public class TestUtils {

    public static Paciente newPaciente(){
        Endereco endereco = new Endereco("rua", "numero", "cidade", "bairro");
        Paciente paciente = new Paciente("Paciente", "1", "1232", LocalDate.now(), endereco );

        return paciente;
    }
}
