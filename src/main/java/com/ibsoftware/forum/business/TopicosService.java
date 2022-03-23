package com.ibsoftware.forum.business;

import com.ibsoftware.forum.form.AtualizacaoTopicoForm;
import com.ibsoftware.forum.form.TopicoForm;
import com.ibsoftware.forum.dto.DetalheDoTopicoDto;
import com.ibsoftware.forum.dto.TopicoDto;
import com.ibsoftware.forum.mapper.TopicoMapper;
import com.ibsoftware.forum.modelo.Curso;
import com.ibsoftware.forum.modelo.Topico;
import com.ibsoftware.forum.repository.CursoRepository;
import com.ibsoftware.forum.repository.TopicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicosService {

    @Autowired
    private TopicosRepository topicosRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public Page<TopicoDto> listTopicos(int pagina, int qtd){

        Pageable paginacao = PageRequest.of(pagina,qtd);

        Page<Topico> topicos = topicosRepository.findAll(paginacao);

        return TopicoMapper.INSTANCE.pageEntityToPageDTO(topicos);
    }

    public Page<TopicoDto> listTopicosByCurso(String nomeCurso,int pagina, int qtd){

        Pageable paginacao = PageRequest.of(pagina,qtd);

        Page<Topico> topicos = topicosRepository.findByCurso_Nome(nomeCurso,paginacao);

        return TopicoMapper.INSTANCE.pageEntityToPageDTO(topicos);
    }

    public TopicoDto saveTopico(TopicoForm topicoForm){
        Curso curso = cursoRepository.findByNome(topicoForm.getNomeCurso());
        Topico topico = TopicoMapper.INSTANCE.mapTopico(topicoForm,curso);

        topicosRepository.save(topico);

        return TopicoMapper.INSTANCE.entityToDTO(topico);
    }

    public TopicoDto retrieveById(Long id){
        return TopicoMapper.INSTANCE.entityToDTO(topicosRepository.getById(id));
    }

    public DetalheDoTopicoDto detalharTopico(Long id){
        Optional<Topico> topico = topicosRepository.findById(id);

        return topico.map(value -> TopicoMapper.INSTANCE.mapDetalheByTopico(value)).orElse(null);

    }

    public TopicoDto atualizarTopico(Long id, AtualizacaoTopicoForm formAtt){
        Optional<Topico> topico = topicosRepository.findById(id);

        //System.out.println(topico.getMensagem());

        /*topico.setMensagem(formAtt.getMensagem());
        topico.setTitulo(formAtt.getTitulo());*/

        //System.out.println(topico.getMensagem());

        if(topico.isPresent()){
            TopicoMapper.INSTANCE.attFormToEntity(formAtt,topico.get());
            return TopicoMapper.INSTANCE.entityToDTO(topico.get());
        }
        return null;
    }

    public Boolean deleteById(Long id){
        Optional<Topico> topico = topicosRepository.findById(id);

        if (topico.isPresent()){
            topicosRepository.deleteById(id);
            return true;
        }

        return false;

    }

}
