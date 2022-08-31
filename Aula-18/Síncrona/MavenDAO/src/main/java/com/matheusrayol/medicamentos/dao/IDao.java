package com.matheusrayol.medicamentos.dao;

public interface IDao<T> {

    public T salvar(T t);

}