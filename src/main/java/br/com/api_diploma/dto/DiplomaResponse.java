package br.com.api_diploma.dto;

import br.com.api_diploma.model.Sexo;
import org.springframework.hateoas.Link;

import java.util.Date;

public class DiplomaResponse {

    private String nomeDiplomado;
    private String nacionalidade;
    private String naturalidade;
    private String numeroRg;
    private String nomeCurso;
    private String tipoCurso;
    private String dataConclusao;
    private String nomeReitor;
    private Sexo sexoReitor;
    private Link link;

    // Getters e Setters


    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public String getNomeDiplomado() {
        return nomeDiplomado;
    }

    public void setNomeDiplomado(String nomeDiplomado) {
        this.nomeDiplomado = nomeDiplomado;
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

    public String getNumeroRg() {
        return numeroRg;
    }

    public void setNumeroRg(String numeroRg) {
        this.numeroRg = numeroRg;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String getTipoCurso() {
        return tipoCurso;
    }

    public void setTipoCurso(String tipoCurso) {
        this.tipoCurso = tipoCurso;
    }

    public String getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(String dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public String getNomeReitor() {
        return nomeReitor;
    }

    public void setNomeReitor(String nomeReitor) {
        this.nomeReitor = nomeReitor;
    }

    public Sexo getSexoReitor() {
        return sexoReitor;
    }

    public void setSexoReitor(Sexo sexoReitor) {
        this.sexoReitor = sexoReitor;
    }
}
