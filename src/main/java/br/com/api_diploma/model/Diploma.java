package br.com.api_diploma.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tb_diploma")
public class Diploma {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "data_conclusao")
    private String dataConclusao;
    @Column(name = "sexo_reitor")
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    @Column(name = "nome_reitor")
    private String nomeReitor;
    @ManyToOne
    @JoinColumn(name = "diplomado_id")
    private Diplomado diplomado;
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
