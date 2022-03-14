package com.ibsoftware.forum.controller;

import com.ibsoftware.forum.business.TopicosService;
import com.ibsoftware.forum.controller.form.TopicoForm;
import com.ibsoftware.forum.dto.TopicoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicosService topicosService;

    @GetMapping
    public List<TopicoDto> lista(String nomeCurso){
        if(nomeCurso == null){
            return topicosService.listTopicos();
        }else{
            return topicosService.listTopicosByCurso(nomeCurso);
        }
    }

    @PostMapping
    public void cadastrar(@RequestBody TopicoForm topicoForm){
        topicosService.saveTopico(topicoForm);
    }
}
