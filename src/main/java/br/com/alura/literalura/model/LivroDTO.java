package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LivroDTO(
        @JsonAlias("title") String titulo,
        @JsonAlias("download_count") Double numeroDownload,
        @JsonAlias("languages") List<String> idioma,
        @JsonAlias("authors") List<AutorDTO> autores
) {}
