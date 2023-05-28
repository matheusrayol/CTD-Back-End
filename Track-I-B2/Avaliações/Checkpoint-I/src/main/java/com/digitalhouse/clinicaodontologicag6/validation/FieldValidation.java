package com.digitalhouse.clinicaodontologicag6.validation;

import com.digitalhouse.clinicaodontologicag6.entity.dto.DentistaDTO;
import com.digitalhouse.clinicaodontologicag6.entity.dto.PacienteDTO;
import com.digitalhouse.clinicaodontologicag6.exception.VariableNullException;

import java.util.ArrayList;
import java.util.List;

public class FieldValidation {

    public Boolean validateDentistaVariables(DentistaDTO dentistaDTO) throws VariableNullException {

        List<String> dentistaVariables = new ArrayList<>();

        if (dentistaDTO.getNome() == null || dentistaDTO.getNome().isEmpty()) {
            dentistaVariables.add("nome");
        }
        if (dentistaDTO.getSobrenome() == null || dentistaDTO.getSobrenome().isEmpty()) {
            dentistaVariables.add("sobrenome");
        }
        if (dentistaDTO.getMatricula() == null || dentistaDTO.getMatricula().isEmpty()) {
            dentistaVariables.add("matricula");
        }
        if (dentistaDTO.getUsername() == null || dentistaDTO.getUsername().isEmpty()) {
            dentistaVariables.add("username");
        }
        if (dentistaDTO.getPassword() == null || dentistaDTO.getPassword().isEmpty()) {
            dentistaVariables.add("password");
        }
        if (dentistaDTO.getUserRoles() == null) {
            dentistaVariables.add("userRoles");
        }

        if (!dentistaVariables.isEmpty()) {
            throw new VariableNullException("Verifique as variáveis listadas", dentistaVariables);
        }

        return true;

    }

    public Boolean validatePacienteVariables(PacienteDTO pacienteDTO) throws VariableNullException {

        List<String> pacienteVariables = new ArrayList<>();

        if (pacienteDTO.getNome() == null || pacienteDTO.getNome().isEmpty()) {
            pacienteVariables.add("nome");
        }
        if (pacienteDTO.getSobrenome() == null || pacienteDTO.getSobrenome().isEmpty()) {
            pacienteVariables.add("sobrenome");
        }
        if (pacienteDTO.getRg() == null || pacienteDTO.getRg().isEmpty()) {
            pacienteVariables.add("rg");
        }
        if (pacienteDTO.getUsername() == null || pacienteDTO.getUsername().isEmpty()) {
            pacienteVariables.add("username");
        }
        if (pacienteDTO.getPassword() == null || pacienteDTO.getPassword().isEmpty()) {
            pacienteVariables.add("password");
        }
        if (pacienteDTO.getLogradouro() == null || pacienteDTO.getLogradouro().isEmpty()) {
            pacienteVariables.add("logradouro");
        }
        if (pacienteDTO.getNumero() == null || pacienteDTO.getNumero().isEmpty()) {
            pacienteVariables.add("numero");
        }
        if (pacienteDTO.getComplemento() == null || pacienteDTO.getComplemento().isEmpty()) {
            pacienteVariables.add("complemento");
        }
        if (pacienteDTO.getBairro() == null || pacienteDTO.getBairro().isEmpty()) {
            pacienteVariables.add("bairro");
        }
        if (pacienteDTO.getCidade() == null || pacienteDTO.getCidade().isEmpty()) {
            pacienteVariables.add("cidade");
        }
        if (pacienteDTO.getEstado() == null || pacienteDTO.getEstado().isEmpty()) {
            pacienteVariables.add("estado");
        }
        if (pacienteDTO.getCep() == null || pacienteDTO.getCep().isEmpty()) {
            pacienteVariables.add("cep");
        }
        if (pacienteDTO.getUserRoles() == null) {
            pacienteVariables.add("userRoles");
        }

        if (!pacienteVariables.isEmpty()) {
            throw new VariableNullException("Verifique as variáveis listadas", pacienteVariables);
        }

        return true;

    }

}
