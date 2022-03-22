package com.ibsoftware.forum.controller;

import com.ibsoftware.forum.business.TopicosService;
import com.ibsoftware.forum.controller.form.TopicoForm;
import com.ibsoftware.forum.dto.DetalheDoTopicoDto;
import com.ibsoftware.forum.dto.TopicoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm topicoForm, UriComponentsBuilder uriComponentsBuilder){
        TopicoDto topicoDto = topicosService.saveTopico(topicoForm);

        URI uri = uriComponentsBuilder.path("/topicos").build().toUri();
        return ResponseEntity.created(uri).body(topicoDto);
    }

    @GetMapping("/{id}")
    public DetalheDoTopicoDto detalhar(@PathVariable Long id){
        return topicosService.detalharTopico(id);
    }
}
