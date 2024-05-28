package br.com.alura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer nascAno;
    private Integer mortAno;

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutor() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNascAno() {
        return nascAno;
    }

    public void setNascAno(Integer nascAno) {
        this.nascAno = nascAno;
    }

    public Integer getMortAno() {
        return mortAno;
    }

    public void setMortAno(Integer mortAno) {
        this.mortAno = mortAno;
    }
}
