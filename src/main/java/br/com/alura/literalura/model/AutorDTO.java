package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record AutorDTO(@JsonAlias("nome") String autor,
                       @JsonAlias("birth_year") Integer anoNascimento,
                       @JsonAlias("death_year") Integer anoFalecimento){
}
