package com.digitalhouse.clinicaodontologicag6.entity.dto;

import com.digitalhouse.clinicaodontologicag6.entity.ConsultaEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultaDTO {

    private Long id;
    private Long dentista;
    private Long paciente;
    private String dataConsulta;

    public ConsultaDTO(ConsultaEntity consultaEntity) {
        this.id = consultaEntity.getId();
        this.dentista = consultaEntity.getDentista();
        this.paciente = consultaEntity.getPaciente();
        this.dataConsulta = consultaEntity.getDataConsulta();
    }

}
