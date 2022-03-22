package com.ibsoftware.forum.form;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class TopicoForm {

    @NotNull @NotEmpty @Length(min = 5, max = 10)
    private String titulo;
    @NotNull @NotEmpty @Length(min = 5, max = 10)
    private String mensagem;
    @NotNull @NotEmpty
    private String nomeCurso;
}
