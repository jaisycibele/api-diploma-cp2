package br.com.api_diploma.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DiplomadoRequest {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "A nacionalidade é obrigatória")
    private String nacionalidade;

    @NotBlank(message = "A naturalidade é obrigatória")
    private String naturalidade;

    @NotBlank(message = "O RG é obrigatório")
    @Size(min = 9, max = 12, message = "O RG deve ter entre 9 e 12 caracteres")
    private String rg;

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }
}
