package com.ibsoftware.forum.repository;

import com.ibsoftware.forum.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicosRepository extends JpaRepository<Topico,Long> {

    //Topico findByCurso_Nome(String nomeCurso);

    List<Topico> findByCurso_Nome(String nomeCurso);

    @Query("SELECT t from Topico t where t.curso.nome =:nomeCurso")
    List<Topico> listByNomeCurso(@Param("nomeCurso") String nomeCurso);


}
