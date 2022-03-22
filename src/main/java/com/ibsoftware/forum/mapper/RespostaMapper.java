package com.ibsoftware.forum.mapper;

import com.ibsoftware.forum.dto.RespostaDTO;
import com.ibsoftware.forum.modelo.Resposta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RespostaMapper {

    RespostaMapper INSTANCE = Mappers.getMapper(RespostaMapper.class);

    @Mapping(target = "nomeAutor", source = "autor.nome")
    RespostaDTO entityToDTO(Resposta source);

    @Mapping(target = "nomeAutor", source = "autor.nome")
    List<RespostaDTO> listEntityToListDTO(List<Resposta> source);

}
