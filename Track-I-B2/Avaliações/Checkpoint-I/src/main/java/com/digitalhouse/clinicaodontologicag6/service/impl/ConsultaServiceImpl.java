package com.digitalhouse.clinicaodontologicag6.service.impl;

import com.digitalhouse.clinicaodontologicag6.entity.ConsultaEntity;
import com.digitalhouse.clinicaodontologicag6.entity.DentistaEntity;
import com.digitalhouse.clinicaodontologicag6.entity.PacienteEntity;
import com.digitalhouse.clinicaodontologicag6.entity.dto.ConsultaDTO;
import com.digitalhouse.clinicaodontologicag6.exception.NotFoundException;
import com.digitalhouse.clinicaodontologicag6.repository.IConsultaRepository;
import com.digitalhouse.clinicaodontologicag6.service.IClinicaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ConsultaServiceImpl implements IClinicaService<ConsultaDTO> {

    @Autowired
    private IConsultaRepository consultaRepository;

    @Autowired
    private DentistaServiceImpl dentistaService;

    @Autowired
    private PacienteServiceImpl pacienteService;

    @Override
    public ConsultaDTO create(ConsultaDTO consultaDTO) throws NotFoundException {
        DentistaEntity dentista = checkDentista(consultaDTO);
        PacienteEntity paciente = checkPaciente(consultaDTO);

        ConsultaEntity consulta = ConsultaEntity.builder().dentista(dentista.getId()).paciente(paciente.getId()).dataConsulta(consultaDTO.getDataConsulta()).build();

        var consultaCadastrada = new ConsultaDTO(consultaRepository.save(consulta));
        return consultaCadastrada;
    }

    private DentistaEntity checkDentista(ConsultaDTO consultaDTO) throws NotFoundException {
        DentistaEntity dentista = dentistaService.findById(consultaDTO.getDentista());
        if (Objects.isNull(dentista)) {
            throw new NotFoundException("Dentista não encontrado");
        }
        ConsultaEntity consulta = consultaRepository.findByConsultaByDentistaAndDataConsulta(consultaDTO.getDentista(), consultaDTO.getDataConsulta());
        if (Objects.nonNull(consulta)) {
            throw new NotFoundException("O Dentista já possui consulta marcada para esta data");
        }
        return dentista;
    }

    private PacienteEntity checkPaciente(ConsultaDTO consultaDTO) throws NotFoundException {
        PacienteEntity paciente = pacienteService.findById(consultaDTO.getPaciente());
        if (Objects.isNull(paciente)) {
            throw new RuntimeException("Paciente não encontrado");
        }
        return paciente;
    }

    @Override
    public ConsultaDTO getById(Long id) {
        ConsultaEntity consulta = consultaRepository.findById(id).get();
        ConsultaDTO consultaDTO = mapperEntityToDTO(consulta);
        return consultaDTO;
    }

    public List<ConsultaDTO> getByDentista(Long dentista) {
        List<ConsultaEntity> consultas = consultaRepository.getByDentista(dentista);
        return consultas.stream().map(this::mapperEntityToDTO).toList();
    }

    public List<ConsultaDTO> getByPaciente(Long paciente) {
        List<ConsultaEntity> consultas = consultaRepository.getByPaciente(paciente);
        return consultas.stream().map(this::mapperEntityToDTO).toList();
    }

    public List<ConsultaDTO> getByData(String data) {
        List<ConsultaEntity> consultas = consultaRepository.getByData(data);
        return consultas.stream().map(this::mapperEntityToDTO).toList();
    }

    @Override
    public List<ConsultaDTO> getAll() {
        List<ConsultaEntity> consultas = consultaRepository.findAll();
        return consultas.stream().map(this::mapperEntityToDTO).toList();
    }

    @Override
    public String delete(Long id) {
        consultaRepository.deleteById(id);
        return "Consulta excluída (ID: " + id + ")";
    }

    @Override
    public ConsultaDTO update(ConsultaDTO consultaDTO, Long id) {
        ConsultaEntity consultaEntity = consultaRepository.findById(id).get();
        consultaEntity.setPaciente(consultaDTO.getPaciente());
        consultaEntity.setDentista(consultaDTO.getDentista());
        consultaEntity.setDataConsulta(consultaDTO.getDataConsulta());
        consultaRepository.save(consultaEntity);
        return new ConsultaDTO(consultaEntity);
    }

    private ConsultaEntity mapperDTOToEntity(ConsultaDTO consultaDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        ConsultaEntity consulta = objectMapper.convertValue(consultaDTO, ConsultaEntity.class);
        return consulta;
    }

    private ConsultaDTO mapperEntityToDTO(ConsultaEntity consultaEntity) {
        ObjectMapper objectMapper = new ObjectMapper();
        ConsultaDTO consulta = objectMapper.convertValue(consultaEntity, ConsultaDTO.class);
        return consulta;
    }

}
