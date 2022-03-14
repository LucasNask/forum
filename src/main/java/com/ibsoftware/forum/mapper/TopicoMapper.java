package com.ibsoftware.forum.mapper;

import com.ibsoftware.forum.controller.form.TopicoForm;
import com.ibsoftware.forum.dto.TopicoDto;
import com.ibsoftware.forum.modelo.Topico;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TopicoMapper {

    TopicoMapper INSTANCE = Mappers.getMapper(TopicoMapper.class);

    TopicoDto entityToDTO(Topico source);

    List<TopicoDto> listEntityToListDTO(List<Topico> source);

    TopicoForm dtoToForm(Topico source);

    Topico formToDTO(TopicoForm source);

}
