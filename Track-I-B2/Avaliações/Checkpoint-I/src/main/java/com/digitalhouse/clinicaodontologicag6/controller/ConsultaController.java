package com.digitalhouse.clinicaodontologicag6.controller;

import com.digitalhouse.clinicaodontologicag6.entity.dto.ConsultaDTO;
import com.digitalhouse.clinicaodontologicag6.service.impl.ConsultaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaServiceImpl consultaService;

    @PostMapping("/cadastrar")
    public ResponseEntity<ConsultaDTO> create(@RequestBody ConsultaDTO consultaDTO) {
        ResponseEntity responseEntity = null;
        try {
            ConsultaDTO consultaDTO1 = consultaService.create(consultaDTO);
            responseEntity = new ResponseEntity<>(consultaDTO1, HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/buscar", params = "id", method = RequestMethod.GET)
    public ResponseEntity<ConsultaDTO> getById(@RequestParam Long id) {
        ResponseEntity responseEntity = null;
        ConsultaDTO consultaDTO = consultaService.getById(id);
        if (consultaDTO != null) {
            responseEntity = new ResponseEntity<>(consultaDTO, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>("ID não encontrada", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/buscar", params = "dentista", method = RequestMethod.GET)
    public ResponseEntity<List<ConsultaDTO>> getByDentista(@RequestParam Long dentista) {
        ResponseEntity responseEntity = null;
        List<ConsultaDTO> consultaDTO = consultaService.getByDentista(dentista);
        if (consultaDTO != null) {
            responseEntity = new ResponseEntity<>(consultaDTO, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>("Não há consultas agendadas para o dentista informado.", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/buscar", params = "paciente", method = RequestMethod.GET)
    public ResponseEntity<List<ConsultaDTO>> getByPaciente(@RequestParam Long paciente) {
        ResponseEntity responseEntity = null;
        List<ConsultaDTO> consultaDTO = consultaService.getByPaciente(paciente);
        if (consultaDTO != null) {
            responseEntity = new ResponseEntity<>(consultaDTO, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>("Não há consultas agendadas para o paciente informado.", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/buscar", params = "data", method = RequestMethod.GET)
    public ResponseEntity<List<ConsultaDTO>> getByData(@RequestParam String data) {
        ResponseEntity responseEntity = null;
        List<ConsultaDTO> consultaDTO = consultaService.getByData(data);
        if (consultaDTO != null) {
            responseEntity = new ResponseEntity<>(consultaDTO, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>("Não há consultas agendadas para a data informada.", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/listar", method = RequestMethod.GET)
    public ResponseEntity<List<ConsultaDTO>> getAll() {
        ResponseEntity responseEntity = null;
        List<ConsultaDTO> consultaDTOList = consultaService.getAll();
        if (consultaDTOList != null) {
            responseEntity = new ResponseEntity<>(consultaDTOList, HttpStatus.OK);
        } else {
            responseEntity = new ResponseEntity<>("Nenhum dentista encontrado", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @RequestMapping(value = "atualizar", params = "id", method = RequestMethod.PUT)
    public ResponseEntity<ConsultaDTO> update(@RequestBody ConsultaDTO consultaDTO, @RequestParam Long id) {
        ResponseEntity responseEntity = null;
        try {
            ConsultaDTO consultaDTO1 = consultaService.update(consultaDTO, id);
            responseEntity = new ResponseEntity<>(consultaDTO1, HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("ID não encontrada", HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @DeleteMapping("/excluir")
    public ResponseEntity<String> delete(@RequestParam Long id) {
        ResponseEntity responseEntity = null;
        try {
            consultaService.delete(id);
            responseEntity = new ResponseEntity<>("Consulta excluída com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Consulta não encontrada", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


}
