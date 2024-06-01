package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroDTO(@JsonAlias("title") String titulo,
                       @JsonAlias("download_count") Double numeroDownload,
                       @JsonAlias("languages") List<String> idioma,
                       @JsonAlias("authors") List<AutorDTO> autores) {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Título: ").append(titulo).append("\n");
        sb.append("Autor(es): \n");
        for (AutorDTO autor : autores) {
            sb.append("  - ").append(autor.autor()).append("\n");
        }
        sb.append("Idioma(s): ").append(String.join(", ", idioma)).append("\n");
        sb.append("Downloads: ").append(numeroDownload).append("\n");
        sb.append("----------------------------------------");
        return sb.toString();
    }

}
