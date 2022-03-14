package com.ibsoftware.forum.business;

import com.ibsoftware.forum.modelo.Curso;
import com.ibsoftware.forum.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Curso retrieveByNome(String nome){
        return cursoRepository.findByNome(nome);
    }

}
