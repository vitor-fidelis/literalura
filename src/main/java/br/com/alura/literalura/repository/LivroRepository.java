package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Year;
import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT l FROM Livro l WHERE LOWER(l.titulo) = LOWER(:titulo)")
    List<Livro> findByTitulo(String titulo);

    @Query("SELECT a FROM Autor a WHERE a.anoNascimento <= :ano AND (a.anoFalecimento IS NULL OR a.anoFalecimento >= :ano)")
    List<Autor> findAutoresVivos(@Param("ano") Year ano);

    @Query("SELECT a FROM Autor a WHERE a.anoNascimento = :ano AND (a.anoFalecimento IS NULL OR a.anoFalecimento >= :ano)")
    List<Autor> findAutoresVivosRefinado(@Param("ano") Year ano);

    @Query("SELECT a FROM Autor a WHERE a.anoNascimento <= :ano AND a.anoFalecimento = :ano")
    List<Autor> findAutoresPorAnoDeMorte(@Param("ano") Year ano);

    @Query("SELECT l FROM Livro l WHERE l.idioma LIKE %:idioma%")
    List<Livro> findByIdioma(@Param("idioma") String idioma);

}
