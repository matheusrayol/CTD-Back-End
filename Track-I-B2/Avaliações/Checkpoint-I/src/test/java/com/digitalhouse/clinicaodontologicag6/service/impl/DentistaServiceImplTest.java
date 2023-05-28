package com.digitalhouse.clinicaodontologicag6.service.impl;

import com.digitalhouse.clinicaodontologicag6.entity.DentistaEntity;
import com.digitalhouse.clinicaodontologicag6.entity.PacienteEntity;
import com.digitalhouse.clinicaodontologicag6.entity.dto.DentistaDTO;
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
class DentistaServiceImplTest {

    @Mock
    private IDentistaRepository mockDentistaRepository;
    @Mock
    private IPacienteRepository mockPacienteRepository;
    @Mock
    private BCryptPasswordEncoder mockPasswordEncoder;

    @InjectMocks
    private DentistaServiceImpl dentistaServiceImplUnderTest;

    @Test
    @DisplayName("Dentista: Criar")
    void testCreate() throws Exception {
        final DentistaDTO dentistaDTO = new DentistaDTO(0L, "nome", "sobrenome", "username", "password", "matricula", UserRoles.ROLE_USER);
        final DentistaDTO expectedResult = new DentistaDTO(0L, "nome", "sobrenome", "username", "password", "matricula", UserRoles.ROLE_USER);
        when(mockPasswordEncoder.encode("password")).thenReturn("password");
        final DentistaEntity dentistaEntity = new DentistaEntity(0L, "nome", "sobrenome", "username", "password", UserRoles.ROLE_USER, "matricula");
        when(mockDentistaRepository.save(new DentistaEntity(0L, "nome", "sobrenome", "username", "password", UserRoles.ROLE_USER, "matricula"))).thenReturn(dentistaEntity);
        final DentistaDTO result = dentistaServiceImplUnderTest.create(dentistaDTO);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Dentista: Obter por ID")
    void testGetById() throws Exception {
        final DentistaDTO expectedResult = new DentistaDTO(0L, "nome", "sobrenome", "username", "password", "matricula", UserRoles.ROLE_USER);
        final Optional<DentistaEntity> dentistaEntity = Optional.of(new DentistaEntity(0L, "nome", "sobrenome", "username", "password", UserRoles.ROLE_USER, "matricula"));
        when(mockDentistaRepository.findById(0L)).thenReturn(dentistaEntity);
        final DentistaDTO result = dentistaServiceImplUnderTest.getById(0L);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Dentista: Obter por ID - Não Encontrado")
    void testGetById_IDentistaRepositoryReturnsAbsent() {
        when(mockDentistaRepository.findById(0L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> dentistaServiceImplUnderTest.getById(0L)).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DisplayName("Dentista: Obter por Nome")
    void testGetByNome() throws Exception {
        final List<DentistaDTO> expectedResult = List.of(new DentistaDTO(0L, "nome", "sobrenome", "username", "password", "matricula", UserRoles.ROLE_USER));
        final List<DentistaEntity> dentistaEntities = List.of(new DentistaEntity(0L, "nome", "sobrenome", "username", "password", UserRoles.ROLE_USER, "matricula"));
        when(mockDentistaRepository.getByNome("nome")).thenReturn(dentistaEntities);
        final List<DentistaDTO> result = dentistaServiceImplUnderTest.getByNome("nome");
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Dentista: Obter por Nome - Não Encontrado")
    void testGetByNome_IDentistaRepositoryReturnsNoItems() {
        when(mockDentistaRepository.getByNome("nome")).thenReturn(Collections.emptyList());
        assertThatThrownBy(() -> dentistaServiceImplUnderTest.getByNome("nome")).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DisplayName("Dentista: Obter por Matrícula")
    void testGetByMatricula() throws Exception {
        final DentistaDTO expectedResult = new DentistaDTO(0L, "nome", "sobrenome", "username", "password", "matricula", UserRoles.ROLE_USER);
        final DentistaEntity dentistaEntity = new DentistaEntity(0L, "nome", "sobrenome", "username", "password", UserRoles.ROLE_USER, "matricula");
        when(mockDentistaRepository.getByMatricula("matricula")).thenReturn(dentistaEntity);
        final DentistaDTO result = dentistaServiceImplUnderTest.getByMatricula("matricula");
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Dentista: Obter por Matrícula - Não Encontrado")
    void testGetByMatricula_IDentistaRepositoryReturnsNull() {
        when(mockDentistaRepository.getByMatricula("matricula")).thenReturn(null);
        assertThatThrownBy(() -> dentistaServiceImplUnderTest.getByMatricula("matricula")).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DisplayName("Dentista: Obter todos")
    void testGetAll() throws Exception {
        final List<DentistaDTO> expectedResult = List.of(new DentistaDTO(0L, "nome", "sobrenome", "username", "password", "matricula", UserRoles.ROLE_USER));
        final List<DentistaEntity> dentistaEntities = List.of(new DentistaEntity(0L, "nome", "sobrenome", "username", "password", UserRoles.ROLE_USER, "matricula"));
        when(mockDentistaRepository.findAll()).thenReturn(dentistaEntities);
        final List<DentistaDTO> result = dentistaServiceImplUnderTest.getAll();
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Dentista: Obter todos - Não Encontrado")
    void testGetAll_IDentistaRepositoryReturnsNoItems() {
        when(mockDentistaRepository.findAll()).thenReturn(Collections.emptyList());
        assertThatThrownBy(() -> dentistaServiceImplUnderTest.getAll()).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DisplayName("Dentista: Atualizar")
    void testUpdate() throws Exception {
        final DentistaDTO dentistaDTO = new DentistaDTO(0L, "nome", "sobrenome", "username", "password", "matricula", UserRoles.ROLE_USER);
        final DentistaDTO expectedResult = new DentistaDTO(0L, "nome", "sobrenome", "username", "password", "matricula", UserRoles.ROLE_USER);
        final Optional<DentistaEntity> dentistaEntity = Optional.of(new DentistaEntity(0L, "nome", "sobrenome", "username", "password", UserRoles.ROLE_USER, "matricula"));
        when(mockDentistaRepository.findById(0L)).thenReturn(dentistaEntity);
        final DentistaEntity dentistaEntity1 = new DentistaEntity(0L, "nome", "sobrenome", "username", "password", UserRoles.ROLE_USER, "matricula");
        when(mockDentistaRepository.save(new DentistaEntity(0L, "nome", "sobrenome", "username", "password", UserRoles.ROLE_USER, "matricula"))).thenReturn(dentistaEntity1);
        final DentistaDTO result = dentistaServiceImplUnderTest.update(dentistaDTO, 0L);
        assertThat(result).isEqualTo(expectedResult);
        verify(mockDentistaRepository).save(new DentistaEntity(0L, "nome", "sobrenome", "username", "password", UserRoles.ROLE_USER, "matricula"));
    }

    @Test
    @DisplayName("Dentista: Atualizar - Não Encontrado")
    void testUpdate_IDentistaRepositoryFindByIdReturnsAbsent() {
        final DentistaDTO dentistaDTO = new DentistaDTO(0L, "nome", "sobrenome", "username", "password", "matricula", UserRoles.ROLE_USER);
        when(mockDentistaRepository.findById(0L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> dentistaServiceImplUnderTest.update(dentistaDTO, 0L)).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DisplayName("Dentista: Excluir")
    void testDelete() throws Exception {
        final Optional<DentistaEntity> dentistaEntity = Optional.of(new DentistaEntity(0L, "nome", "sobrenome", "username", "password", UserRoles.ROLE_USER, "matricula"));
        when(mockDentistaRepository.findById(0L)).thenReturn(dentistaEntity);
        final String result = dentistaServiceImplUnderTest.delete(0L);
        assertThat(result).isEqualTo("Dentista ID 0 excluido com sucesso");
        verify(mockDentistaRepository).deleteById(0L);
    }

    @Test
    @DisplayName("Dentista: Excluir - Não Encontrado")
    void testDelete_IDentistaRepositoryFindByIdReturnsAbsent() {
        when(mockDentistaRepository.findById(0L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> dentistaServiceImplUnderTest.delete(0L)).isInstanceOf(NoSuchElementException.class);
    }
}
