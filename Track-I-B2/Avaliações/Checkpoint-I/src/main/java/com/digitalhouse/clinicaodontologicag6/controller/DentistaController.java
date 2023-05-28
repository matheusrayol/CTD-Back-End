package com.digitalhouse.clinicaodontologicag6.controller;

import com.digitalhouse.clinicaodontologicag6.entity.dto.DentistaDTO;
import com.digitalhouse.clinicaodontologicag6.exception.NotFoundException;
import com.digitalhouse.clinicaodontologicag6.exception.UserAlreadyExistsException;
import com.digitalhouse.clinicaodontologicag6.exception.VariableNullException;
import com.digitalhouse.clinicaodontologicag6.security.AuthenticationResponse;
import com.digitalhouse.clinicaodontologicag6.security.JwtUtil;
import com.digitalhouse.clinicaodontologicag6.service.impl.DentistaServiceImpl;
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
@RequestMapping("/dentista")
public class DentistaController {

    private final FieldValidation fieldValidation = new FieldValidation();
    @Autowired
    private DentistaServiceImpl dentistaService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/cadastrar")
    public ResponseEntity<DentistaDTO> create(@RequestBody DentistaDTO dentistaDTO) throws VariableNullException, UserAlreadyExistsException {
        ResponseEntity responseEntity = null;
        Boolean erro = fieldValidation.validateDentistaVariables(dentistaDTO);
        if (erro) {
            DentistaDTO dentistaDTO1 = dentistaService.create(dentistaDTO);
            responseEntity = new ResponseEntity<>(dentistaDTO1, HttpStatus.CREATED);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/buscar", params = "id", method = RequestMethod.GET)
    public ResponseEntity<DentistaDTO> getById(@RequestParam Long id) throws NotFoundException {
        return new ResponseEntity<>(dentistaService.getById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/buscar", params = "nome", method = RequestMethod.GET)
    public ResponseEntity<List<DentistaDTO>> getByNome(@RequestParam String nome) throws NotFoundException {
        return new ResponseEntity<>(dentistaService.getByNome(nome), HttpStatus.OK);
    }

    @RequestMapping(value = "/buscar", params = "matricula", method = RequestMethod.GET)
    public ResponseEntity<DentistaDTO> getByMatricula(@RequestParam String matricula) throws NotFoundException {
        return new ResponseEntity<>(dentistaService.getByMatricula(matricula), HttpStatus.OK);
    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public ResponseEntity<List<DentistaDTO>> getAll() throws NotFoundException {
        return new ResponseEntity<>(dentistaService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "atualizar", params = "id", method = RequestMethod.PUT)
    public ResponseEntity<DentistaDTO> update(@RequestBody DentistaDTO dentistaDTO, @RequestParam Long id) throws VariableNullException, NotFoundException {
        ResponseEntity responseEntity = null;
        Boolean erro = fieldValidation.validateDentistaVariables(dentistaDTO);
        if (erro) {
            DentistaDTO dentistaDTO1 = dentistaService.update(dentistaDTO, id);
            responseEntity = new ResponseEntity<>(dentistaDTO1, HttpStatus.CREATED);
        }
        return responseEntity;
    }

    @DeleteMapping("/excluir")
    public ResponseEntity<String> delete(@RequestParam Long id) throws NotFoundException {
        return new ResponseEntity<>(dentistaService.delete(id), HttpStatus.OK);
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody DentistaDTO dentistaDTO) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dentistaDTO.getUsername(), dentistaDTO.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = dentistaService.loadUserByUsername(dentistaDTO.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}