package com.digitalhouse.clinicaodontologicag6.entity.dto;

import com.digitalhouse.clinicaodontologicag6.entity.PacienteEntity;
import com.digitalhouse.clinicaodontologicag6.enums.UserRoles;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PacienteDTO {

    private Long id;
    private String nome;
    private String sobrenome;
    private String username;
    private String password;
    private String rg;
    private String dataAlta;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private UserRoles userRoles;

    public PacienteDTO(PacienteEntity pacienteEntity) {
        this.id = pacienteEntity.getId();
        this.nome = pacienteEntity.getNome();
        this.sobrenome = pacienteEntity.getSobrenome();
        this.username = pacienteEntity.getUsername();
        this.password = pacienteEntity.getPassword();
        this.rg = pacienteEntity.getRg();
        this.dataAlta = pacienteEntity.getDataAlta();
        this.logradouro = pacienteEntity.getLogradouro();
        this.numero = pacienteEntity.getNumero();
        this.complemento = pacienteEntity.getComplemento();
        this.bairro = pacienteEntity.getBairro();
        this.cidade = pacienteEntity.getCidade();
        this.estado = pacienteEntity.getEstado();
        this.cep = pacienteEntity.getCep();
        this.userRoles = pacienteEntity.getUserRoles();
    }

    public UserRoles getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(UserRoles userRoles) {
        this.userRoles = userRoles;
    }
}
