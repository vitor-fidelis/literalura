package br.com.alura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToOne(cascade = CascadeType.ALL)
    private Autor autor;

    private String idioma;

    private Integer anoNascimentoAutor;

    private Integer anoFalecimentoAutor;

    private Double numeroDownloads;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getAnoNascimentoAutor() {
        return anoNascimentoAutor;
    }

    public void setAnoNascimentoAutor(Integer anoNascimentoAutor) {
        this.anoNascimentoAutor = anoNascimentoAutor;
    }

    public Integer getAnoFalecimentoAutor() {
        return anoFalecimentoAutor;
    }

    public void setAnoFalecimentoAutor(Integer anoFalecimentoAutor) {
        this.anoFalecimentoAutor = anoFalecimentoAutor;
    }

    public Double getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(Double numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    // Construtores
    public Livro() {}

    public Livro(LivroDTO livroDTO) {
        this.titulo = livroDTO.titulo();
        Autor autor = new Autor(livroDTO.autores().get(0));
        this.autor = autor;
        this.idioma = livroDTO.idioma().get(0);
        this.numeroDownloads = livroDTO.numeroDownload();
    }

    public Livro(Long idApi, String titulo, Autor autor, String idioma, Double numeroDownload) {
        this.titulo = titulo;
        this.autor = autor;
        this.idioma = idioma;
        this.numeroDownloads = numeroDownload;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + "\n" +
                "Autor: " + autor + "\n" +
                "Idioma: " + idioma + "\n" +
                "Downloads: " + numeroDownloads + "\n" +
                "----------------------------------------";
    }
}
