package com.ibsoftware.forum.dto;

import com.ibsoftware.forum.modelo.Resposta;
import com.ibsoftware.forum.modelo.StatusTopico;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class DetalheDoTopicoDto {

    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String nomeAutor;
    private StatusTopico status;
    private List<RespostaDTO> respostas;


}
