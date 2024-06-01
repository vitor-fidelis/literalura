package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;


public record AutorDTO(@JsonAlias("name") String autor,
                       @JsonAlias("birth_year") Integer anoNascimento,
                       @JsonAlias("death_year") Integer anoFalecimento){
    @Override
    public String toString() {
        return "Autor: " + autor;
    }

}



