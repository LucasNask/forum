package com.ibsoftware.forum.mapper;

import com.ibsoftware.forum.form.AtualizacaoTopicoForm;
import com.ibsoftware.forum.form.TopicoForm;
import com.ibsoftware.forum.dto.DetalheDoTopicoDto;
import com.ibsoftware.forum.dto.TopicoDto;
import com.ibsoftware.forum.modelo.Curso;
import com.ibsoftware.forum.modelo.Topico;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class TopicoMapper {

    public static TopicoMapper INSTANCE = Mappers.getMapper(TopicoMapper.class);

    public abstract TopicoDto entityToDTO(Topico source);

    public abstract List<TopicoDto> listEntityToListDTO(List<Topico> source);

    public abstract Topico formToEntity(TopicoForm source);

    public Topico mapTopico(TopicoForm source, Curso curso){

        Topico topico = formToEntity(source);
        topico = addCurso(curso,topico);

        return topico;
    };

    @Mapping(target = "curso.nome", source = "nome")
    @Mapping(target = "curso.categoria", source = "categoria")
    @Mapping(target = "curso.id", source = "id")
    @Mapping(target = "id", ignore = true)
    public abstract Topico addCurso(Curso source, @MappingTarget Topico target);

    @Mapping(target = "nomeAutor", source = "autor.nome")
    public abstract DetalheDoTopicoDto detalheByTopico(Topico source);

    public DetalheDoTopicoDto mapDetalheByTopico(Topico source){

        DetalheDoTopicoDto detalheDoTopicoDto = detalheByTopico(source);
        detalheDoTopicoDto.setRespostas(RespostaMapper.INSTANCE.listEntityToListDTO(source.getRespostas()));

        return detalheDoTopicoDto;
    };

    public Topico attFormToEntity(AtualizacaoTopicoForm source, Topico target){
        target.setMensagem(source.getMensagem());
        target.setTitulo(source.getTitulo());

        return target;
    }

}
