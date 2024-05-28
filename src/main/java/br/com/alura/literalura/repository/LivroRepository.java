package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT DISTINCT a.nome FROM Autor a")
    List<String> findAutores();

    @Query("SELECT DISTINCT a FROM Autor a WHERE a.birthYear <= :ano AND (a.mortAno IS NULL OR a.mortAno >= :ano)")
    List<Autor> findAutoresEmDeterminadoAno(@Param("ano") Integer ano);

    @Query("SELECT l FROM Livro l WHERE l.idiomas LIKE %:language%")
    List<Livro> findByIdioma(String idioma);
}
