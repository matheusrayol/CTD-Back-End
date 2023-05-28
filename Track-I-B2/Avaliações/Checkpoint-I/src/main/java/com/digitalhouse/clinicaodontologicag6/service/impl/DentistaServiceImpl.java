package com.digitalhouse.clinicaodontologicag6.service.impl;

import com.digitalhouse.clinicaodontologicag6.entity.DentistaEntity;
import com.digitalhouse.clinicaodontologicag6.entity.dto.DentistaDTO;
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
public class DentistaServiceImpl implements IClinicaService<DentistaDTO>, UserDetailsService {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private IDentistaRepository dentistaRepository;
    @Autowired
    private IPacienteRepository pacienteRepository;

    @Override
    public DentistaDTO create(DentistaDTO dentistaDTO) throws UserAlreadyExistsException {
        try {
            DentistaEntity dentistaEntity = mapperDTOToEntity(dentistaDTO);
            String password = passwordEncoder.encode(dentistaEntity.getPassword());
            dentistaEntity.setPassword(password);
            dentistaEntity = dentistaRepository.save(dentistaEntity);
            dentistaDTO = mapperEntityToDTO(dentistaEntity);
        } catch (Exception e) {
            throw new UserAlreadyExistsException("Usuário já cadastrado");
        }
        return dentistaDTO;
    }

    @Override
    public DentistaDTO getById(Long id) throws NotFoundException {
        DentistaEntity dentista = dentistaRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Dentista não encontrado"));
        DentistaDTO dentistaDTO = mapperEntityToDTO(dentista);
        return dentistaDTO;
    }

    public List<DentistaDTO> getByNome(String nome) throws NotFoundException {
        List<DentistaEntity> dentistas = dentistaRepository.getByNome(nome);
        if (dentistas.isEmpty()) {
            throw new NotFoundException("Dentista(s) de nome " + nome + " não encontrado(s)");
        }
        return dentistas.stream().map(this::mapperEntityToDTO).toList();
    }

    public DentistaDTO getByMatricula(String matricula) throws NotFoundException {
        DentistaEntity dentista = dentistaRepository.getByMatricula(matricula);
        if (dentista == null) {
            throw new NotFoundException("Dentista de matrícula " + matricula + " não encontrado");
        }
        DentistaDTO dentistaDTO = mapperEntityToDTO(dentista);
        return dentistaDTO;
    }

    @Override
    public List<DentistaDTO> getAll() throws NotFoundException {
        List<DentistaEntity> dentistas = dentistaRepository.findAll();
        if (dentistas.isEmpty()) {
            throw new NotFoundException("Nenhum dentista cadastrado");
        }
        return dentistas.stream().map(this::mapperEntityToDTO).toList();
    }

    @Override
    public DentistaDTO update(DentistaDTO dentistaDTO, Long id) throws NotFoundException {
        DentistaEntity dentistaEntity = dentistaRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Dentista não encontrado"));
        dentistaEntity.setNome(dentistaDTO.getNome());
        dentistaEntity.setSobrenome(dentistaDTO.getSobrenome());
        dentistaEntity.setUsername(dentistaDTO.getUsername());
        dentistaEntity.setPassword(dentistaDTO.getPassword());
        dentistaEntity.setUserRoles(dentistaDTO.getUserRoles());
        dentistaEntity.setMatricula(dentistaDTO.getMatricula());
        dentistaRepository.save(dentistaEntity);
        return new DentistaDTO(dentistaEntity);
    }

    @Override
    public String delete(Long id) throws NotFoundException {
        DentistaEntity dentista = dentistaRepository.findById(id).get();
        if (dentista == null) {
            throw new NotFoundException("Dentista não encontrado");
        }
        dentistaRepository.deleteById(id);
        return "Dentista ID " + id + " excluido com sucesso";
    }

    private DentistaEntity mapperDTOToEntity(DentistaDTO dentistaDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        DentistaEntity dentista = objectMapper.convertValue(
                dentistaDTO,
                DentistaEntity.class
        );
        return dentista;
    }

    private DentistaDTO mapperEntityToDTO(DentistaEntity dentistaEntity) {
        ObjectMapper objectMapper = new ObjectMapper();
        DentistaDTO dentista = objectMapper.convertValue(
                dentistaEntity,
                DentistaDTO.class
        );
        return dentista;
    }

    public DentistaEntity findById(Long dentista) throws NotFoundException {
        DentistaEntity dentistaEntity = dentistaRepository.findById(dentista).orElseThrow(
                () -> new NotFoundException("Dentista não encontrado"));
        return dentistaEntity;
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