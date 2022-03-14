package com.ibsoftware.forum.business;

import com.ibsoftware.forum.controller.form.TopicoForm;
import com.ibsoftware.forum.dto.TopicoDto;
import com.ibsoftware.forum.mapper.TopicoMapper;
import com.ibsoftware.forum.modelo.Curso;
import com.ibsoftware.forum.repository.CursoRepository;
import com.ibsoftware.forum.repository.TopicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicosService {

    @Autowired
    private TopicosRepository topicosRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public List<TopicoDto> listTopicos(){
        return TopicoMapper.INSTANCE.listEntityToListDTO(topicosRepository.findAll());
    }

    public List<TopicoDto> listTopicosByCurso(String nomeCurso){
        return TopicoMapper.INSTANCE.listEntityToListDTO(topicosRepository.findByCurso_Nome(nomeCurso));
    }

    public void saveTopico(TopicoForm topicoForm){
        Curso curso = cursoRepository.findByNome(topicoForm.getNomeCurso());

        topicosRepository.save(TopicoMapper.INSTANCE.formToDTO(topicoForm));
    }

}
