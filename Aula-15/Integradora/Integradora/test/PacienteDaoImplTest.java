import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pacientes.dao.impl.PacienteDaoImpl;
import pacientes.model.Paciente;
import utils.TestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PacienteDaoImplTest {

    PacienteDaoImpl pacienteDaoImpl = new PacienteDaoImpl();
    Paciente paciente, paciente2;

    @BeforeEach
    public void setup(){
        Paciente newPaciente = TestUtils.newPaciente();
        newPaciente.setNome("Paciente1");
        Paciente newPaciente2 = TestUtils.newPaciente();
        newPaciente2.setNome("Paciente2");

        paciente = pacienteDaoImpl.salvar(newPaciente);
        paciente2 = pacienteDaoImpl.salvar(newPaciente2);
    }

    @Test
    void incluirPaciente() {
        Paciente paciente = TestUtils.newPaciente();

        Paciente insertPaciente = pacienteDaoImpl.salvar(paciente);
        assertEquals(paciente.getNome(), insertPaciente.getNome());
        assertTrue(insertPaciente.getId() != 0 );
    }

    @Test
    void localizarPaciente() {
        Paciente paciente = TestUtils.newPaciente();
        Paciente insertPaciente = pacienteDaoImpl.salvar(paciente);
        Paciente findById = pacienteDaoImpl.encontrarPorId(insertPaciente.getId());
        assertEquals(insertPaciente.getId(), findById.getId());
        assertEquals(insertPaciente.getNome(), findById.getNome());
    }

    @Test
    void excluirPaciente() {
        Paciente paciente = TestUtils.newPaciente();
        Paciente insertPaciente = pacienteDaoImpl.salvar(paciente);
        pacienteDaoImpl.excluirPorId(insertPaciente.getId());
        Paciente findByIdNotExists = pacienteDaoImpl.encontrarPorId(insertPaciente.getId());
        assertNull(findByIdNotExists);
    }

    @Test
    void listarPacientes() {
        Paciente paciente = TestUtils.newPaciente();
        Paciente insertPaciente = pacienteDaoImpl.salvar(paciente);
        List<Paciente> pacienteList = new ArrayList<>(pacienteDaoImpl.localizarTodos());
        System.out.println(pacienteList);
        assertTrue(pacienteList.size() > 2 );
    }

}