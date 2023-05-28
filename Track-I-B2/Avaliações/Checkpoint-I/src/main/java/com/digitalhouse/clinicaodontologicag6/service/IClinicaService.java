package com.digitalhouse.clinicaodontologicag6.service;

import com.digitalhouse.clinicaodontologicag6.exception.NotFoundException;
import com.digitalhouse.clinicaodontologicag6.exception.UserAlreadyExistsException;
import com.digitalhouse.clinicaodontologicag6.exception.VariableNullException;

import java.util.List;

public interface IClinicaService<T> {

    T create(T t) throws UserAlreadyExistsException, NotFoundException;

    T getById(Long id) throws NotFoundException;

    List<T> getAll() throws NotFoundException;

    String delete(Long id) throws NotFoundException;

    T update(T t, Long id) throws NotFoundException, VariableNullException;
}
