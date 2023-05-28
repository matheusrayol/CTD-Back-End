package com.digitalhouse.clinicaodontologicag6.service.impl;

import com.digitalhouse.clinicaodontologicag6.entity.DentistaEntity;
import com.digitalhouse.clinicaodontologicag6.entity.PacienteEntity;
import com.digitalhouse.clinicaodontologicag6.entity.dto.PacienteDTO;
import com.digitalhouse.clinicaodontologicag6.enums.UserRoles;
import com.digitalhouse.clinicaodontologicag6.exception.NotFoundException;
import com.digitalhouse.clinicaodontologicag6.exception.UserAlreadyExistsException;
import com.digitalhouse.clinicaodontologicag6.repository.IDentistaRepository;
import com.digitalhouse.clinicaodontologicag6.repository.IPacienteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PacienteServiceImplTest {

    @Mock
    private IPacienteRepository mockPacienteRepository;
    @Mock
    private BCryptPasswordEncoder mockPasswordEncoder;

    @InjectMocks
    private PacienteServiceImpl pacienteServiceImplUnderTest;

    @Test
    @DisplayName("Paciente: Cadastrar")
    void testCreate() throws Exception {
        final PacienteDTO pacienteDTO = new PacienteDTO(0L, "nome", "sobrenome", "username", "password", "rg", "dataAlta", "logradouro", "numero", "complemento", "bairro", "cidade", "estado", "cep", UserRoles.ROLE_USER);
        final PacienteDTO expectedResult = new PacienteDTO(0L, "nome", "sobrenome", "username", "password", "rg", "dataAlta", "logradouro", "numero", "complemento", "bairro", "cidade", "estado", "cep", UserRoles.ROLE_USER);
        when(mockPasswordEncoder.encode("password")).thenReturn("password");
        final PacienteEntity pacienteEntity = new PacienteEntity(0L, "nome", "sobrenome", "username", "password", UserRoles.ROLE_USER, "rg", "dataAlta", "logradouro", "numero", "complemento", "bairro", "cidade", "estado", "cep");
        when(mockPacienteRepository.save(new PacienteEntity(0L, "nome", "sobrenome", "username", "password", UserRoles.ROLE_USER, "rg", "dataAlta", "logradouro", "numero", "complemento", "bairro", "cidade", "estado", "cep"))).thenReturn(pacienteEntity);
        final PacienteDTO result = pacienteServiceImplUnderTest.create(pacienteDTO);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Paciente: Obter por ID")
    void testGetById() throws Exception {
        final PacienteDTO expectedResult = new PacienteDTO(0L, "nome", "sobrenome", "username", "password", "rg", "dataAlta", "logradouro", "numero", "complemento", "bairro", "cidade", "estado", "cep", UserRoles.ROLE_USER);
        final Optional<PacienteEntity> pacienteEntity = Optional.of(new PacienteEntity(0L, "nome", "sobrenome", "username", "password", UserRoles.ROLE_USER, "rg", "dataAlta", "logradouro", "numero", "complemento", "bairro", "cidade", "estado", "cep"));
        when(mockPacienteRepository.findById(0L)).thenReturn(pacienteEntity);
        final PacienteDTO result = pacienteServiceImplUnderTest.getById(0L);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Paciente: Obter por nome")
    void testGetByNome() throws Exception {
        final List<PacienteDTO> expectedResult = List.of(new PacienteDTO(0L, "nome", "sobrenome", "username", "password", "rg", "dataAlta", "logradouro", "numero", "complemento", "bairro", "cidade", "estado", "cep", UserRoles.ROLE_USER));
        final List<PacienteEntity> pacienteEntities = List.of(new PacienteEntity(0L, "nome", "sobrenome", "username", "password", UserRoles.ROLE_USER, "rg", "dataAlta", "logradouro", "numero", "complemento", "bairro", "cidade", "estado", "cep"));
        when(mockPacienteRepository.getByNome("nome")).thenReturn(pacienteEntities);
        final List<PacienteDTO> result = pacienteServiceImplUnderTest.getByNome("nome");
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Paciente: Obter por RG")
    void testGetByRg() throws Exception {
        final PacienteDTO expectedResult = new PacienteDTO(0L, "nome", "sobrenome", "username", "password", "rg", "dataAlta", "logradouro", "numero", "complemento", "bairro", "cidade", "estado", "cep", UserRoles.ROLE_USER);
        final PacienteEntity pacienteEntity = new PacienteEntity(0L, "nome", "sobrenome", "username", "password", UserRoles.ROLE_USER, "rg", "dataAlta", "logradouro", "numero", "complemento", "bairro", "cidade", "estado", "cep");
        when(mockPacienteRepository.getByRg("rg")).thenReturn(pacienteEntity);
        final PacienteDTO result = pacienteServiceImplUnderTest.getByRg("rg");
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Paciente: Obter por Cidade")
    void testGetByCidade() throws Exception {
        final List<PacienteDTO> expectedResult = List.of(new PacienteDTO(0L, "nome", "sobrenome", "username", "password", "rg", "dataAlta", "logradouro", "numero", "complemento", "bairro", "cidade", "estado", "cep", UserRoles.ROLE_USER));
        final List<PacienteEntity> pacienteEntities = List.of(new PacienteEntity(0L, "nome", "sobrenome", "username", "password", UserRoles.ROLE_USER, "rg", "dataAlta", "logradouro", "numero", "complemento", "bairro", "cidade", "estado", "cep"));
        when(mockPacienteRepository.getByCidade("cidade")).thenReturn(pacienteEntities);
        final List<PacienteDTO> result = pacienteServiceImplUnderTest.getByCidade("cidade");
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Paciente: Obter todos")
    void testGetAll() throws Exception {
        final List<PacienteDTO> expectedResult = List.of(new PacienteDTO(0L, "nome", "sobrenome", "username", "password", "rg", "dataAlta", "logradouro", "numero", "complemento", "bairro", "cidade", "estado", "cep", UserRoles.ROLE_USER));
        final List<PacienteEntity> pacienteEntities = List.of(new PacienteEntity(0L, "nome", "sobrenome", "username", "password", UserRoles.ROLE_USER, "rg", "dataAlta", "logradouro", "numero", "complemento", "bairro", "cidade", "estado", "cep"));
        when(mockPacienteRepository.findAll()).thenReturn(pacienteEntities);
        final List<PacienteDTO> result = pacienteServiceImplUnderTest.getAll();
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Paciente: Atualizar")
    void testUpdate() throws Exception {
        final PacienteDTO pacienteDTO = new PacienteDTO(0L, "nome", "sobrenome", "username", "password", "rg", "dataAlta", "logradouro", "numero", "complemento", "bairro", "cidade", "estado", "cep", UserRoles.ROLE_USER);
        final PacienteDTO expectedResult = new PacienteDTO(0L, "nome", "sobrenome", "username", "password", "rg", "dataAlta", "logradouro", "numero", "complemento", "bairro", "cidade", "estado", "cep", UserRoles.ROLE_USER);
        final Optional<PacienteEntity> pacienteEntity = Optional.of(new PacienteEntity(0L, "nome", "sobrenome", "username", "password", UserRoles.ROLE_USER, "rg", "dataAlta", "logradouro", "numero", "complemento", "bairro", "cidade", "estado", "cep"));
        when(mockPacienteRepository.findById(0L)).thenReturn(pacienteEntity);
        final PacienteEntity pacienteEntity1 = new PacienteEntity(0L, "nome", "sobrenome", "username", "password", UserRoles.ROLE_USER, "rg", "dataAlta", "logradouro", "numero", "complemento", "bairro", "cidade", "estado", "cep");
        when(mockPacienteRepository.save(new PacienteEntity(0L, "nome", "sobrenome", "username", "password", UserRoles.ROLE_USER, "rg", "dataAlta", "logradouro", "numero", "complemento", "bairro", "cidade", "estado", "cep"))).thenReturn(pacienteEntity1);
        final PacienteDTO result = pacienteServiceImplUnderTest.update(pacienteDTO, 0L);
        assertThat(result).isEqualTo(expectedResult);
        verify(mockPacienteRepository).save(new PacienteEntity(0L, "nome", "sobrenome", "username", "password", UserRoles.ROLE_USER, "rg", "dataAlta", "logradouro", "numero", "complemento", "bairro", "cidade", "estado", "cep"));
    }

    @Test
    @DisplayName("Paciente: Excluir")
    void testDelete() throws Exception {
        final Optional<PacienteEntity> pacienteEntity = Optional.of(new PacienteEntity(0L, "nome", "sobrenome", "username", "password", UserRoles.ROLE_USER, "rg", "dataAlta", "logradouro", "numero", "complemento", "bairro", "cidade", "estado", "cep"));
        when(mockPacienteRepository.findById(0L)).thenReturn(pacienteEntity);
        final String result = pacienteServiceImplUnderTest.delete(0L);
        assertThat(result).isEqualTo("Paciente excluído! (ID: 0)");
        verify(mockPacienteRepository).deleteById(0L);
    }
}
