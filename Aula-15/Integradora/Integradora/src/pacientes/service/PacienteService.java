package pacientes.service;

import pacientes.dao.IDao;
import pacientes.dao.impl.PacienteDaoImpl;
import pacientes.model.Paciente;

import java.util.List;

public class PacienteService {

    PacienteDaoImpl pacienteDaoImpl;

    public PacienteService() {
        this.pacienteDaoImpl = new PacienteDaoImpl();
    }

    public Paciente savePaciente(Paciente paciente) {
        return pacienteDaoImpl.salvar(paciente);
    }

    public List<Paciente> localizarTodosPacientes() {
        return pacienteDaoImpl.localizarTodos();
    }

    public void deleteById(int id) {
        pacienteDaoImpl.excluirPorId(id);
    }

    public Paciente encontrarPorId(int id) {
        return pacienteDaoImpl.encontrarPorId(id);
    }
}
