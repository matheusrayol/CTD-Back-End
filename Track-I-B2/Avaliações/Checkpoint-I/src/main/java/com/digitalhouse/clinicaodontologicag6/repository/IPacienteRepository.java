package com.digitalhouse.clinicaodontologicag6.repository;

import com.digitalhouse.clinicaodontologicag6.entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface IPacienteRepository extends JpaRepository<PacienteEntity, Long> {

    //HQL
    @Query("FROM PacienteEntity p where p.nome = :nome")
    List<PacienteEntity> getByNome(String nome);

    @Query("FROM PacienteEntity p where p.rg = :rg")
    PacienteEntity getByRg(String rg);

    @Query("FROM PacienteEntity p where p.cidade = :cidade")
    List<PacienteEntity> getByCidade(String cidade);

    Optional<PacienteEntity> findByUsername(String sobrenome);
}
