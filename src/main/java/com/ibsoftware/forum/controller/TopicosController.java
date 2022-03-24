package com.ibsoftware.forum.controller;

import com.ibsoftware.forum.business.TopicosService;
import com.ibsoftware.forum.form.AtualizacaoTopicoForm;
import com.ibsoftware.forum.form.TopicoForm;
import com.ibsoftware.forum.dto.DetalheDoTopicoDto;
import com.ibsoftware.forum.dto.TopicoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicosService topicosService;

    @GetMapping
    @Cacheable(value = "listaDeTopicos")
    public Page<TopicoDto> lista(@RequestParam(required = false) String nomeCurso,@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0,size = 10) Pageable paginacao){

        if(nomeCurso == null){
            return topicosService.listTopicos(paginacao);
        }else{
            return topicosService.listTopicosByCurso(nomeCurso, paginacao);
        }

    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm topicoForm, UriComponentsBuilder uriComponentsBuilder){
        TopicoDto topicoDto = topicosService.saveTopico(topicoForm);

        URI uri = uriComponentsBuilder.path("/topicos").build().toUri();
        return ResponseEntity.created(uri).body(topicoDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalheDoTopicoDto> detalhar(@PathVariable Long id){

        DetalheDoTopicoDto detalheDoTopicoDto = topicosService.detalharTopico(id);

        if(detalheDoTopicoDto != null){
            return ResponseEntity.ok(detalheDoTopicoDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoTopicoForm form){

        TopicoDto topicoDto = topicosService.atualizarTopico(id,form);

        if(topicoDto != null){
            return ResponseEntity.ok(topicoDto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeTopicos", allEntries = true)
    public ResponseEntity<?> remover(@PathVariable Long id){

        Boolean flagDeletou = topicosService.deleteById(id);

        if(flagDeletou){
            return ResponseEntity.ok().build();
        }
        return  ResponseEntity.notFound().build();
    }


}
