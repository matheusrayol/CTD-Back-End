package com.digitalhouse.clinicaodontologicag6.entity.dto;

import com.digitalhouse.clinicaodontologicag6.entity.DentistaEntity;
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
public class DentistaDTO {

    private Long id;
    private String nome;
    private String sobrenome;
    private String username;
    private String password;
    private String matricula;
    private UserRoles userRoles;

    public DentistaDTO(DentistaEntity dentistaEntity) {
        this.id = dentistaEntity.getId();
        this.nome = dentistaEntity.getNome();
        this.username = dentistaEntity.getUsername();
        this.password = dentistaEntity.getPassword();
        this.sobrenome = dentistaEntity.getSobrenome();
        this.matricula = dentistaEntity.getMatricula();
        this.userRoles = dentistaEntity.getUserRoles();
    }

    public UserRoles getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(UserRoles userRoles) {
        this.userRoles = userRoles;
    }

}
