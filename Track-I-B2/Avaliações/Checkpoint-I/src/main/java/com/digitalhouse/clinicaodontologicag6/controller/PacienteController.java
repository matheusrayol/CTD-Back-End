package com.digitalhouse.clinicaodontologicag6.controller;

import com.digitalhouse.clinicaodontologicag6.entity.dto.PacienteDTO;
import com.digitalhouse.clinicaodontologicag6.exception.NotFoundException;
import com.digitalhouse.clinicaodontologicag6.exception.UserAlreadyExistsException;
import com.digitalhouse.clinicaodontologicag6.exception.VariableNullException;
import com.digitalhouse.clinicaodontologicag6.security.AuthenticationResponse;
import com.digitalhouse.clinicaodontologicag6.security.JwtUtil;
import com.digitalhouse.clinicaodontologicag6.service.impl.PacienteServiceImpl;
import com.digitalhouse.clinicaodontologicag6.validation.FieldValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private final FieldValidation fieldValidation = new FieldValidation();
    @Autowired
    private PacienteServiceImpl pacienteService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/cadastrar")
    public ResponseEntity<PacienteDTO> create(@RequestBody PacienteDTO pacienteDTO) throws VariableNullException, UserAlreadyExistsException {
        ResponseEntity responseEntity = null;
        Boolean erro = fieldValidation.validatePacienteVariables(pacienteDTO);
        if (erro) {
            PacienteDTO pacienteDTO1 = pacienteService.create(pacienteDTO);
            responseEntity = new ResponseEntity<>(pacienteDTO1, HttpStatus.CREATED);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/buscar", params = "id", method = RequestMethod.GET)
    public ResponseEntity<PacienteDTO> getById(@RequestParam Long id) throws NotFoundException {
        return new ResponseEntity<>(pacienteService.getById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/buscar", params = "nome", method = RequestMethod.GET)
    public ResponseEntity<List<PacienteDTO>> getByNome(@RequestParam String nome) throws NotFoundException {
        return new ResponseEntity<>(pacienteService.getByNome(nome), HttpStatus.OK);
    }

    @RequestMapping(value = "/buscar", params = "cidade", method = RequestMethod.GET)
    public ResponseEntity<List<PacienteDTO>> getByCidade(@RequestParam String cidade) throws NotFoundException {
        return new ResponseEntity<>(pacienteService.getByCidade(cidade), HttpStatus.OK);
    }

    @RequestMapping(value = "/buscar", params = "rg", method = RequestMethod.GET)
    public ResponseEntity<PacienteDTO> getByRg(@RequestParam String rg) throws NotFoundException {
        return new ResponseEntity<>(pacienteService.getByRg(rg), HttpStatus.OK);
    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public ResponseEntity<List<PacienteDTO>> getAll() throws NotFoundException {
        return new ResponseEntity<>(pacienteService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "atualizar", params = "id", method = RequestMethod.PUT)
    public ResponseEntity<PacienteDTO> update(@RequestBody PacienteDTO pacienteDTO, @RequestParam Long id) throws VariableNullException, NotFoundException {
        ResponseEntity responseEntity = null;
        Boolean erro = fieldValidation.validatePacienteVariables(pacienteDTO);
        if (erro) {
            PacienteDTO pacienteDTO1 = pacienteService.update(pacienteDTO, id);
            responseEntity = new ResponseEntity<>(pacienteDTO1, HttpStatus.CREATED);
        }
        return responseEntity;
    }

    @DeleteMapping("/excluir")
    public ResponseEntity<String> delete(@RequestParam Long id) throws NotFoundException {
        return new ResponseEntity<>(pacienteService.delete(id), HttpStatus.OK);
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody PacienteDTO pacienteDTO) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(pacienteDTO.getUsername(), pacienteDTO.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = pacienteService.loadUserByUsername(pacienteDTO.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}