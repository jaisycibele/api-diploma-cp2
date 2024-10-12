package br.com.api_diploma.dto;

import br.com.api_diploma.model.Curso;
import br.com.api_diploma.model.Diplomado;
import br.com.api_diploma.model.Sexo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.util.Date;

public class DiplomaRequest {

    @NotNull(message = "A data de conclusão é obrigatória")
    private String dataConclusao;

    @NotNull(message = "O sexo do reitor é obrigatório")
    private Sexo sexo;

    @NotBlank(message = "O nome do reitor é obrigatório")
    private String nomeReitor;

    @NotNull(message = "O diplomado é obrigatório")
    private Diplomado diplomado;

    @NotNull(message = "O curso é obrigatório")
    private Curso curso;

    // Getters e Setters

    public String getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(String dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getNomeReitor() {
        return nomeReitor;
    }

    public void setNomeReitor(String nomeReitor) {
        this.nomeReitor = nomeReitor;
    }

    public Diplomado getDiplomado() {
        return diplomado;
    }

    public void setDiplomado(Diplomado diplomado) {
        this.diplomado = diplomado;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
