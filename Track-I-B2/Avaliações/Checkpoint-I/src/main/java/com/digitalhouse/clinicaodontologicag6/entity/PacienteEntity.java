package com.digitalhouse.clinicaodontologicag6.entity;

import com.digitalhouse.clinicaodontologicag6.enums.UserRoles;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Pacientes")
@SecondaryTable(name = "Enderecos")
public class PacienteEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false)
    @NotNull
    private String nome;

    @Column(name = "sobrenome", nullable = false)
    @NotNull
    private String sobrenome;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoles userRoles;

    @Column(name = "rg", unique = true, nullable = false)
    @NotNull
    private String rg;

    @Column(name = "dataAlta")
    private String dataAlta;

    @Column(table = "Enderecos", name = "logradouro", nullable = false)
    @NotNull
    private String logradouro;

    @Column(table = "Enderecos", name = "numero", nullable = false)
    @NotNull
    private String numero;

    @Column(table = "Enderecos", name = "complemento")
    private String complemento;

    @Column(table = "Enderecos", name = "bairro", nullable = false)
    @NotNull
    private String bairro;

    @Column(table = "Enderecos", name = "cidade", nullable = false)
    @NotNull
    private String cidade;

    @Column(table = "Enderecos", name = "estado", nullable = false)
    @NotNull
    private String estado;

    @Column(table = "Enderecos", name = "cep", nullable = false)
    @NotNull
    private String cep;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userRoles.name());
        return Collections.singleton(grantedAuthority);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
