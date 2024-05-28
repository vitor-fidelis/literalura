package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.service.ConsumoAPI;
import br.com.alura.literalura.repository.LivroRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class Principal {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private ConsumoAPI consumoAPI;

    private final Scanner leitura = new Scanner(System.in);

    private Livro livro;
    private List<Livro> livros;


    public Principal() {
        this.livroRepository = livroRepository;
        this.consumoAPI = consumoAPI;
        this.livro = livro;
    }



    public void executar() {
        boolean running = true;
        while (running) {
            exibirMenu();
            var opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivrosPeloTitulo();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 6:
                    System.out.println("Encerrando a LiterAlura!");
                    running = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
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
        this.livros = livros;
        for (Livro livro : livros){
            livroRepository.save(livro);
        }
    }

    private void buscarLivrosPeloTitulo() {
        try {
            System.out.println("Digite o título do livro: ");
            String titulo = leitura.nextLine();
            var baseURL = "https://gutendex.com/books?search=";
            String endereco = baseURL + titulo.replace(" ", "%20");
            String jsonResponse = consumoAPI.obterDados(endereco);

            // Processar a resposta JSON e converter para uma lista de objetos Livro
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(jsonResponse).get("results");

            List<Livro> livros = StreamSupport.stream(root.spliterator(), false)
                    .map(bookNode -> {
                        Livro livro = new Livro();
                        livro.setTitulo(bookNode.get("title").asText());
                        livro.setAutor(bookNode.get("authors").get(0).get("name").asText());
                        livro.setIdioma(bookNode.get("languages").get(0).asText());
                        // Verificar se o ano de nascimento do autor está disponível e definir no livro
                        if (bookNode.get("authors").get(0).has("birth_year")) {
                            livro.setAnoNascimentoAutor(bookNode.get("authors").get(0).get("birth_year").asInt());
                        }
                        // Verificar se o ano de falecimento do autor está disponível e definir no livro
                        if (bookNode.get("authors").get(0).has("death_year")) {
                            livro.setAnoFalecimentoAutor(bookNode.get("authors").get(0).get("death_year").asInt());
                        }
                        return livro;
                    })
                    .collect(Collectors.toList());

            // Salvar a lista de livros no repositório
            salvarLivros(livros);
            System.out.println("Livros salvos com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao buscar livros: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private void listarLivrosRegistrados() {
        List<Livro> livros = livroRepository.findAll();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro registrado.");
        } else {
            for (Livro livro : livros) {
                System.out.println(livro);
            }
        }
    }

    private void listarAutoresRegistrados() {
        List<Livro> livros = livroRepository.findAll();
        if (livros.isEmpty()) {
            System.out.println("Nenhum autor registrado.");
        } else {
            Set<String> autores = new HashSet<>();
            for (Livro livro : livros) {
                String autor = livro.getAutor();
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
        List<Livro> livros = livroRepository.findAll();
        Set<String> autoresVivos = new HashSet<>();
        for (Livro livro : livros) {
            if (livro.getAnoNascimentoAutor() <= ano && (livro.getAnoFalecimentoAutor() == null || livro.getAnoFalecimentoAutor() > ano)) {
                autoresVivos.add(livro.getAutor());
            }
        }
        if (autoresVivos.isEmpty()) {
            System.out.println("Nenhum autor vivo encontrado.");
        } else {
            for (String autor : autoresVivos) {
                System.out.println(autor);
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
            for (Livro livro : livros) {
                System.out.println(livro);
            }
        }
    }

}






