package br.com.api_diploma.dto;

import br.com.api_diploma.model.TipoCurso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CursoRequest {

    @NotBlank(message = "O nome do curso é obrigatório")
    private String nome;

    @NotNull(message = "O tipo do curso é obrigatório")
    private TipoCurso tipo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoCurso getTipo() {
        return tipo;
    }

    public void setTipo(TipoCurso tipo) {
        this.tipo = tipo;
    }
}
