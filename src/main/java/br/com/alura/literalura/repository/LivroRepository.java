package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT DISTINCT a.autor FROM Livro l JOIN l.autor a")
    List<String> findAllAuthors();

    @Query("SELECT a FROM Autor a WHERE a.anoNascimento <= :ano AND (a.anoFalecimento IS NULL OR a.anoFalecimento >= :ano)")
    List<Autor> findAutoresVivos(@Param("ano") Integer ano);

    @Query("SELECT l FROM Livro l WHERE l.idioma LIKE %:idioma%")
    List<Livro> findByIdioma(@Param("idioma") String idioma);

    @Query("SELECT a FROM Autor a WHERE a.autor = :nome")
    Autor findAByName(@Param("nome") String nome);
}
