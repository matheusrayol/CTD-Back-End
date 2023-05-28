package pacientes.dao;

import pacientes.model.Paciente;

import java.util.List;

public interface IDao<T> {
    public T salvar(T t);
    public List<T> localizarTodos();
    public void excluirPorId(int id);
    public Paciente encontrarPorId(int id);

}
