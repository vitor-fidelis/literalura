package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroDTO(@JsonAlias("title") String titulo,
                       @JsonAlias("authors") List<AutorDTO> authors,
                       @JsonAlias("languages") List<String> idioma,
                       @JsonAlias("download_count") Double numeroDownload) {
}
