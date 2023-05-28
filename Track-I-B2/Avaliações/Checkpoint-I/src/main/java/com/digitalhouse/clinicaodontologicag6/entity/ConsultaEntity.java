package com.digitalhouse.clinicaodontologicag6.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Consultas")
public class ConsultaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "dentista", nullable = false)
    @NotNull
    private Long dentista;

    @Column(name = "paciente", nullable = false)
    @NotNull
    private Long paciente;

    @Column(name = "dataConsulta", nullable = false)
    @NotNull
    private String dataConsulta;

}
