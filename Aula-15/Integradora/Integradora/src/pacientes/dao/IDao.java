package pacientes.dao;

public interface IDao<T> {

    public T salvar(T t);
    public T buscar(T t);
    public T atualizar(T t);
    public T excluir(T t);

}
