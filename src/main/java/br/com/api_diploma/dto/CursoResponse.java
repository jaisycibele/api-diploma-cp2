package br.com.api_diploma.dto;

import br.com.api_diploma.model.TipoCurso;
import org.springframework.hateoas.Link;

public class CursoResponse {

    private Long id;  // ID do curso
    private String nome;
    private TipoCurso tipo;
    private Link link;

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
