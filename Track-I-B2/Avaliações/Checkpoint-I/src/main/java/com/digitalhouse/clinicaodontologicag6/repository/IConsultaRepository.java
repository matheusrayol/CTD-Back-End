package com.digitalhouse.clinicaodontologicag6.repository;

import com.digitalhouse.clinicaodontologicag6.entity.ConsultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IConsultaRepository extends JpaRepository<ConsultaEntity, Long> {

    //HQL
    @Query("FROM ConsultaEntity c where c.dentista = :dentista")
    List<ConsultaEntity> getByDentista(Long dentista);

    @Query("FROM ConsultaEntity c where c.paciente = :paciente")
    List<ConsultaEntity> getByPaciente(Long paciente);

    @Query("FROM ConsultaEntity c where c.dataConsulta = :data")
    List<ConsultaEntity> getByData(String data);

    @Query("FROM ConsultaEntity c where c.dentista = :dentista and c.dataConsulta = :dataConsulta")
    ConsultaEntity findByConsultaByDentistaAndDataConsulta(Long dentista, String dataConsulta);
}
