package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.model.LivroDTO;
import br.com.alura.literalura.service.ConsumoAPI;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class Principal {

    private static final Logger logger = LoggerFactory.getLogger(Principal.class);

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private ConsumoAPI consumoAPI;

    @Autowired
    private ConverteDados converteDados;

    private final Scanner leitura = new Scanner(System.in);

    public void executar() {
        boolean running = true;
        while (running) {
            exibirMenu();
            var opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1 -> buscarLivrosPeloTitulo();
                case 2 -> listarLivrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresVivos();
                case 5 -> listarLivrosPorIdioma();
                case 6 -> {
                    System.out.println("Encerrando a LiterAlura!");
                    running = false;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void exibirMenu() {
        System.out.println("""
                ===========================================================
                                       LITERALURA
                      Uma aplicação para você que gosta de livros !
                      Escolha um número no menu abaixo:
                -----------------------------------------------------------
                                          Menu
                           1- Buscar livros pelo título
                           2- Listar livros registrados
                           3- Listar autores registrados
                           4- Listar autores vivos em um determinado ano
                           5- Listar livros em um determinado idioma
                           6- Sair
                """);
    }

    private void salvarLivros(List<Livro> livros) {
        livros.forEach(livroRepository::save);
    }

    private void buscarLivrosPeloTitulo() {
        try {
            System.out.println("Digite o título do livro: ");
            String titulo = leitura.nextLine();
            var baseURL = "https://gutendex.com/books?search=";
            String endereco = baseURL + titulo.replace(" ", "%20");

            String jsonResponse = consumoAPI.obterDados(endereco);
            String jsonLivro = converteDados.extraiObjetoJson(jsonResponse, "results");

            List<LivroDTO> livrosDTO = converteDados.obterLista(jsonLivro, LivroDTO.class);

            if (!livrosDTO.isEmpty()) {
                List<Livro> livros = livrosDTO.stream().map(Livro::new).collect(Collectors.toList());
                salvarLivros(livros);
                System.out.println("Livros salvos com sucesso!");
            } else {
                System.out.println("Não foi possível encontrar livro buscado");
            }
        } catch (Exception e) {
            logger.error("Erro ao buscar livros: {}", e.getMessage());
        }
    }

    private void listarLivrosRegistrados() {
        List<Livro> livros = livroRepository.findAll();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro registrado.");
        } else {
            livros.forEach(System.out::println);
        }
    }

    private void listarAutoresRegistrados() {
        List<Livro> livros = livroRepository.findAll();
        if (livros.isEmpty()) {
            System.out.println("Nenhum autor registrado.");
        } else {
            Set<String> autores = new HashSet<>();
            for (Livro livro : livros) {
                String autor = livro.getAutor().getAutor();
                if (autor != null && !autor.isEmpty()) {
                    autores.add(autor);
                }
            }
            if (autores.isEmpty()) {
                System.out.println("Nenhum autor registrado.");
            } else {
                System.out.println("Autores registrados:");
                for (String autor : autores) {
                    System.out.println(autor);
                }
            }
        }
    }

    private void listarAutoresVivos() {
        System.out.println("Digite o ano: ");
        int ano = leitura.nextInt();
        leitura.nextLine();
        List<Autor> autores = livroRepository.findAutoresVivos(ano);
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor vivo encontrado.");
        } else {
            for (Autor autor : autores) {
                System.out.println(autor.getAutor());
            }
        }
    }
    private void listarLivrosPorIdioma() {
        System.out.println("Digite o idioma: ");
        String idioma = leitura.nextLine();
        List<Livro> livros = livroRepository.findByIdioma(idioma);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado no idioma especificado.");
        } else {
            livros.forEach(System.out::println);
        }
    }
}
