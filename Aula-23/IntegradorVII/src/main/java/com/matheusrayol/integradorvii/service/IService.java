package com.matheusrayol.integradorvii.service;

import java.util.List;

public interface IService<T> {

    T salvar(T t);
    List<T> buscarTodos();
    String excluir(Integer id);

}
