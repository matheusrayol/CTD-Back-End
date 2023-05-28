package com.digitalhouse.clinicaodontologicag6.service.impl;

import com.digitalhouse.clinicaodontologicag6.entity.PacienteEntity;
import com.digitalhouse.clinicaodontologicag6.entity.dto.PacienteDTO;
import com.digitalhouse.clinicaodontologicag6.exception.NotFoundException;
import com.digitalhouse.clinicaodontologicag6.exception.UserAlreadyExistsException;
import com.digitalhouse.clinicaodontologicag6.repository.IDentistaRepository;
import com.digitalhouse.clinicaodontologicag6.repository.IPacienteRepository;
import com.digitalhouse.clinicaodontologicag6.service.IClinicaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteServiceImpl implements IClinicaService<PacienteDTO>, UserDetailsService {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private IPacienteRepository pacienteRepository;
    @Autowired
    private IDentistaRepository dentistaRepository;

    @Override
    public PacienteDTO create(PacienteDTO pacienteDTO) throws UserAlreadyExistsException {
        try {
            PacienteEntity pacienteEntity = mapperDTOToEntity(pacienteDTO);
            String password = passwordEncoder.encode(pacienteEntity.getPassword());
            pacienteEntity.setPassword(password);
            pacienteEntity = pacienteRepository.save(pacienteEntity);
            pacienteDTO = mapperEntityToDTO(pacienteEntity);
        } catch (Exception e) {
            throw new UserAlreadyExistsException("Usuário já cadastrado");
        }
        return pacienteDTO;
    }

    @Override
    public PacienteDTO getById(Long id) throws NotFoundException {
        PacienteEntity paciente = pacienteRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Paciente não encontrado"));
        PacienteDTO pacienteDTO = mapperEntityToDTO(paciente);
        return pacienteDTO;
    }

    public List<PacienteDTO> getByNome(String nome) throws NotFoundException {
        List<PacienteEntity> pacientes = pacienteRepository.getByNome(nome);
        if (pacientes.isEmpty()) {
            throw new NotFoundException("Paciente(s) de nome " + nome + " não encontrado(s)");
        }
        return pacientes.stream().map(this::mapperEntityToDTO).toList();
    }

    public PacienteDTO getByRg(String rg) throws NotFoundException {
        PacienteEntity paciente = pacienteRepository.getByRg(rg);
        if (paciente == null) {
            throw new NotFoundException("Paciente de RG " + rg + " não encontrado");
        }
        PacienteDTO pacienteDTO = mapperEntityToDTO(paciente);
        return pacienteDTO;
    }

    public List<PacienteDTO> getByCidade(String cidade) throws NotFoundException {
        List<PacienteEntity> pacientes = pacienteRepository.getByCidade(cidade);
        if (pacientes.isEmpty()) {
            throw new NotFoundException("Paciente(s) na cidade " + cidade + " não encontrado(s)");
        }
        return pacientes.stream().map(this::mapperEntityToDTO).toList();
    }

    @Override
    public List<PacienteDTO> getAll() throws NotFoundException {
        List<PacienteEntity> pacientes = pacienteRepository.findAll();
        if (pacientes.isEmpty()) {
            throw new NotFoundException("Nenhum paciente encontrado");
        }
        return pacientes.stream().map(this::mapperEntityToDTO).toList();
    }

    @Override
    public PacienteDTO update(PacienteDTO pacienteDTO, Long id) throws NotFoundException {
        PacienteEntity pacienteEntity = pacienteRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Paciente não encontrado"));
        pacienteEntity.setNome(pacienteDTO.getNome());
        pacienteEntity.setSobrenome(pacienteDTO.getSobrenome());
        pacienteEntity.setRg(pacienteDTO.getRg());
        pacienteEntity.setUsername(pacienteDTO.getUsername());
        pacienteEntity.setPassword(pacienteDTO.getPassword());
        pacienteEntity.setUserRoles(pacienteDTO.getUserRoles());
        pacienteEntity.setLogradouro(pacienteDTO.getLogradouro());
        pacienteEntity.setNumero(pacienteDTO.getNumero());
        pacienteEntity.setComplemento(pacienteDTO.getComplemento());
        pacienteEntity.setCidade(pacienteDTO.getCidade());
        pacienteEntity.setEstado(pacienteDTO.getEstado());
        pacienteEntity.setCep(pacienteDTO.getCep());
        pacienteRepository.save(pacienteEntity);
        return new PacienteDTO(pacienteEntity);
    }

    @Override
    public String delete(Long id) throws NotFoundException {
        PacienteEntity pacienteEntity = pacienteRepository.findById(id).get();
        if (pacienteEntity == null) {
            throw new NotFoundException("Paciente não encontrado");
        }
        pacienteRepository.deleteById(id);
        return "Paciente excluído! (ID: " + id + ")";
    }

    private PacienteEntity mapperDTOToEntity(PacienteDTO pacienteDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        PacienteEntity paciente = objectMapper.convertValue(
                pacienteDTO,
                PacienteEntity.class
        );
        return paciente;
    }

    private PacienteDTO mapperEntityToDTO(PacienteEntity pacienteEntity) {
        ObjectMapper objectMapper = new ObjectMapper();
        PacienteDTO paciente = objectMapper.convertValue(
                pacienteEntity,
                PacienteDTO.class
        );
        return paciente;
    }

    public PacienteEntity findById(Long paciente) throws NotFoundException {
        PacienteEntity pacienteEntity = pacienteRepository.findById(paciente).orElseThrow(
                () -> new NotFoundException("Paciente não encontrado"));
        return pacienteEntity;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (pacienteRepository.findByUsername(username).isPresent()) {
            return pacienteRepository.findByUsername(username).orElseThrow(
                    () -> new UsernameNotFoundException("Paciente não encontrado"));
        }
        if (dentistaRepository.findByUsername(username).isPresent()) {
            return dentistaRepository.findByUsername(username).orElseThrow(
                    () -> new UsernameNotFoundException("Dentista não encontrado"));
        }
        return null;
    }

}
