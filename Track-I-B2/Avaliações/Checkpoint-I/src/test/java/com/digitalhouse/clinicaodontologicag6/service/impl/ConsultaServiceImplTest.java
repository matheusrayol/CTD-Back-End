package com.digitalhouse.clinicaodontologicag6.service.impl;

import com.digitalhouse.clinicaodontologicag6.entity.ConsultaEntity;
import com.digitalhouse.clinicaodontologicag6.entity.DentistaEntity;
import com.digitalhouse.clinicaodontologicag6.entity.dto.ConsultaDTO;
import com.digitalhouse.clinicaodontologicag6.enums.UserRoles;
import com.digitalhouse.clinicaodontologicag6.exception.NotFoundException;
import com.digitalhouse.clinicaodontologicag6.repository.IConsultaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultaServiceImplTest {

    @Mock
    private IConsultaRepository mockConsultaRepository;
    @Mock
    private DentistaServiceImpl mockDentistaService;
    @Mock
    private PacienteServiceImpl mockPacienteService;

    @InjectMocks
    private ConsultaServiceImpl consultaServiceImplUnderTest;

    @Test
    @DisplayName("Consulta: Dentista não encontrado")
    void testCreate_DentistaServiceImplThrowsNotFoundException() throws Exception {
        final ConsultaDTO consultaDTO = new ConsultaDTO(0L, 0L, 0L, "dataConsulta");
        when(mockDentistaService.findById(0L)).thenThrow(NotFoundException.class);
        assertThatThrownBy(() -> consultaServiceImplUnderTest.create(consultaDTO)).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DisplayName("Consulta: Paciente não encontrado")
    void testCreate_PacienteServiceImplThrowsNotFoundException() throws Exception {
        final ConsultaDTO consultaDTO = new ConsultaDTO(0L, 0L, 0L, "dataConsulta");
        final DentistaEntity dentistaEntity = new DentistaEntity(0L, "nome", "sobrenome", "username", "password", UserRoles.ROLE_USER, "matricula");
        when(mockDentistaService.findById(0L)).thenReturn(dentistaEntity);
        final ConsultaEntity consultaEntity = new ConsultaEntity(0L, 0L, 0L, "dataConsulta");
        when(mockConsultaRepository.findByConsultaByDentistaAndDataConsulta(0L, "dataConsulta")).thenReturn(consultaEntity);
        assertThatThrownBy(() -> consultaServiceImplUnderTest.create(consultaDTO)).isInstanceOf(NotFoundException.class);
    }

    @Test
    @DisplayName("Consulta: Obter por ID")
    void testGetById() {
        final ConsultaDTO expectedResult = new ConsultaDTO(0L, 0L, 0L, "dataConsulta");
        final Optional<ConsultaEntity> consultaEntity = Optional.of(new ConsultaEntity(0L, 0L, 0L, "dataConsulta"));
        when(mockConsultaRepository.findById(0L)).thenReturn(consultaEntity);
        final ConsultaDTO result = consultaServiceImplUnderTest.getById(0L);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Consulta: Obter por ID - Não encontrado")
    void testGetById_IConsultaRepositoryReturnsAbsent() {
        when(mockConsultaRepository.findById(0L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> consultaServiceImplUnderTest.getById(0L)).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("Consulta: Obter por dentista")
    void testGetByDentista() {
        final List<ConsultaDTO> expectedResult = List.of(new ConsultaDTO(0L, 0L, 0L, "dataConsulta"));
        final List<ConsultaEntity> consultaEntities = List.of(new ConsultaEntity(0L, 0L, 0L, "dataConsulta"));
        when(mockConsultaRepository.getByDentista(0L)).thenReturn(consultaEntities);
        final List<ConsultaDTO> result = consultaServiceImplUnderTest.getByDentista(0L);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Consulta: Obter por dentista - Não encontrado")
    void testGetByDentista_IConsultaRepositoryReturnsNoItems() {
        when(mockConsultaRepository.getByDentista(0L)).thenReturn(Collections.emptyList());
        final List<ConsultaDTO> result = consultaServiceImplUnderTest.getByDentista(0L);
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    @DisplayName("Consulta: Obter por paciente")
    void testGetByPaciente() {
        final List<ConsultaDTO> expectedResult = List.of(new ConsultaDTO(0L, 0L, 0L, "dataConsulta"));
        final List<ConsultaEntity> consultaEntities = List.of(new ConsultaEntity(0L, 0L, 0L, "dataConsulta"));
        when(mockConsultaRepository.getByPaciente(0L)).thenReturn(consultaEntities);
        final List<ConsultaDTO> result = consultaServiceImplUnderTest.getByPaciente(0L);
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Consulta: Obter por paciente - Não encontrado")
    void testGetByPaciente_IConsultaRepositoryReturnsNoItems() {
        when(mockConsultaRepository.getByPaciente(0L)).thenReturn(Collections.emptyList());
        final List<ConsultaDTO> result = consultaServiceImplUnderTest.getByPaciente(0L);
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    @DisplayName("Consulta: Obter por data")
    void testGetByData() {
        final List<ConsultaDTO> expectedResult = List.of(new ConsultaDTO(0L, 0L, 0L, "dataConsulta"));
        final List<ConsultaEntity> consultaEntities = List.of(new ConsultaEntity(0L, 0L, 0L, "dataConsulta"));
        when(mockConsultaRepository.getByData("data")).thenReturn(consultaEntities);
        final List<ConsultaDTO> result = consultaServiceImplUnderTest.getByData("data");
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Consulta: Obter por data - Não encontrado")
    void testGetByData_IConsultaRepositoryReturnsNoItems() {
        when(mockConsultaRepository.getByData("data")).thenReturn(Collections.emptyList());
        final List<ConsultaDTO> result = consultaServiceImplUnderTest.getByData("data");
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    @DisplayName("Consulta: Obter todos")
    void testGetAll() {
        final List<ConsultaDTO> expectedResult = List.of(new ConsultaDTO(0L, 0L, 0L, "dataConsulta"));
        final List<ConsultaEntity> consultaEntities = List.of(new ConsultaEntity(0L, 0L, 0L, "dataConsulta"));
        when(mockConsultaRepository.findAll()).thenReturn(consultaEntities);
        final List<ConsultaDTO> result = consultaServiceImplUnderTest.getAll();
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Consulta: Obter todos - Não encontrado")
    void testGetAll_IConsultaRepositoryReturnsNoItems() {
        when(mockConsultaRepository.findAll()).thenReturn(Collections.emptyList());
        final List<ConsultaDTO> result = consultaServiceImplUnderTest.getAll();
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    @DisplayName("Consulta: Excluir consulta")
    void testDelete() {
        final String result = consultaServiceImplUnderTest.delete(0L);
        assertThat(result).isEqualTo("Consulta excluída (ID: 0)");
        verify(mockConsultaRepository).deleteById(0L);
    }

    @Test
    @DisplayName("Consulta: Atualizar consulta")
    void testUpdate() {
        final ConsultaDTO consultaDTO = new ConsultaDTO(0L, 0L, 0L, "dataConsulta");
        final ConsultaDTO expectedResult = new ConsultaDTO(0L, 0L, 0L, "dataConsulta");
        final Optional<ConsultaEntity> consultaEntity = Optional.of(new ConsultaEntity(0L, 0L, 0L, "dataConsulta"));
        when(mockConsultaRepository.findById(0L)).thenReturn(consultaEntity);
        final ConsultaEntity consultaEntity1 = new ConsultaEntity(0L, 0L, 0L, "dataConsulta");
        when(mockConsultaRepository.save(new ConsultaEntity(0L, 0L, 0L, "dataConsulta"))).thenReturn(consultaEntity1);
        final ConsultaDTO result = consultaServiceImplUnderTest.update(consultaDTO, 0L);
        assertThat(result).isEqualTo(expectedResult);
        verify(mockConsultaRepository).save(new ConsultaEntity(0L, 0L, 0L, "dataConsulta"));
    }

    @Test
    @DisplayName("Consulta: Buscar por ID - Não encontrado")
    void testUpdate_IConsultaRepositoryFindByIdReturnsAbsent() {
        final ConsultaDTO consultaDTO = new ConsultaDTO(0L, 0L, 0L, "dataConsulta");
        when(mockConsultaRepository.findById(0L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> consultaServiceImplUnderTest.update(consultaDTO, 0L)).isInstanceOf(NoSuchElementException.class);
    }
}
