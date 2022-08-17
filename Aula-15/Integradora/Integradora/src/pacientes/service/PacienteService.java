package pacientes.service;

import pacientes.dao.IDao;
import pacientes.model.Paciente;

public class PacienteService {

    private IDao<Paciente> pacienteIDao;

    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    public Paciente salvar(Paciente paciente) {
        return pacienteIDao.salvar(paciente);
    }
}
