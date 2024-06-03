# Literalura
![Badge](https://github.com/vitor-fidelis/literalura/blob/main/imagens/badge%20literalura.png)


Literalura é uma aplicação Java/Spring Boot para amantes de livros. Esta aplicação permite buscar livros, listar livros registrados, listar autores, e muitas outras funcionalidades relacionadas à leitura e organização de livros.

## Funcionalidades

1. **Buscar livros pelo título**: Consulta a API Gutendex para buscar livros pelo título.
2. **Listar livros registrados**: Exibe todos os livros registrados no banco de dados.
3. **Listar autores registrados**: Exibe todos os autores dos livros registrados.
4. **Listar autores vivos em um determinado ano**: Lista autores que estavam vivos em um ano especificado.
5. **Listar autores nascidos em determinado ano**: Lista autores que nasceram em um ano especificado.
6. **Listar autores por ano de sua morte**: Lista autores que morreram em um ano especificado.
7. **Listar livros em um determinado idioma**: Lista livros registrados no banco de dados em um idioma especificado.
8. **Encerrar a aplicação**: Encerra o programa.

## Tecnologias Utilizadas

- **Java 17** 
- **Spring Boot 2.7**
- **Hibernate**
- **PostgreSQL**
- **Gutendex API**
- **Maven**

## Configuração do Projeto

### Pré-requisitos

- Java 17 ou superior
- Maven
- PostgreSQL

### Instalação

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/literalura.git
   cd literalura
   ```

2. Configure o banco de dados no arquivo `application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
   spring.datasource.username=seu-usuario
   spring.datasource.password=sua-senha
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

3. Execute o projeto:
   ```bash
   mvn spring-boot:run
   ```

## Estrutura do Projeto

- `br.com.alura.literalura`: Pacote principal do projeto.
  - `principal`: Contém a classe `Principal`, que gerencia a execução da aplicação.
  - `model`: Contém as classes de modelo (`Livro`, `Autor`, `LivroDTO`, `AutorDTO`).
  - `repository`: Contém as interfaces de repositório Spring Data JPA.
  - `service`: Contém as classes de serviço (`ConsumoAPI`, `ConverteDados`).

## Uso

Ao iniciar a aplicação, o menu principal será exibido com as opções disponíveis. Basta seguir as instruções na tela para navegar pelas funcionalidades.

### Exemplo de Uso

1. **Buscar livros pelo título**:
   - Digite `1` e pressione Enter.
   - Insira o título do livro que deseja buscar.
   - A aplicação fará uma consulta à API Gutendex e exibirá os resultados encontrados.

2. **Listar livros registrados**:
   - Digite `2` e pressione Enter.
   - A aplicação listará todos os livros registrados no banco de dados.

3. **Listar autores registrados**:
   - Digite `3` e pressione Enter.
   - A aplicação listará todos os autores dos livros registrados.

4. **Listar autores vivos em um determinado ano**:
   - Digite `4` e pressione Enter.
   - Insira o ano desejado.
   - A aplicação listará os autores que estavam vivos naquele ano.

5. **Listar autores nascidos em determinado ano**:
   - Digite `5` e pressione Enter.
   - Insira o ano desejado.
   - A aplicação listará os autores que nasceram naquele ano.

6. **Listar autores por ano de sua morte**:
   - Digite `6` e pressione Enter.
   - Insira o ano desejado.
   - A aplicação listará os autores que morreram naquele ano.

7. **Listar livros em um determinado idioma**:
   - Digite `7` e pressione Enter.
   - Insira o código do idioma desejado (por exemplo, `en` para Inglês, `pt` para Português).
   - A aplicação listará todos os livros registrados no banco de dados naquele idioma.

8. **Encerrar a aplicação**:
   - Digite `0` e pressione Enter.
   - A aplicação será encerrada.

## Contribuição

Se você deseja contribuir para o projeto, siga os passos abaixo:

1. Fork o repositório.
2. Crie uma nova branch: `git checkout -b minha-feature`.
3. Faça suas alterações e commite-as: `git commit -m 'Minha nova feature'`.
4. Envie para o repositório original: `git push origin minha-feature`.
5. Abra um Pull Request.

## Licença

Este projeto está licenciado sob a MIT License. Veja o arquivo `LICENSE` para mais detalhes.

## Contato

Se você tiver alguma dúvida ou sugestão, sinta-se à vontade para entrar em contato.

---

## Imagens da aplicação em funcionamento

![img](https://github.com/vitor-fidelis/literalura/blob/94bde8826b6be879fd89b3866bd793f065572c9d/imagens/imagens/Captura%20de%20ecr%C3%A3%202024-06-03%20155941.png)
![img](https://github.com/vitor-fidelis/literalura/blob/94bde8826b6be879fd89b3866bd793f065572c9d/imagens/imagens/Captura%20de%20ecr%C3%A3%202024-06-01%20195448.png)
![img](https://github.com/vitor-fidelis/literalura/blob/94bde8826b6be879fd89b3866bd793f065572c9d/imagens/imagens/Captura%20de%20ecr%C3%A3%202024-06-01%20195525.png)
![img](https://github.com/vitor-fidelis/literalura/blob/main/imagens/imagens/Captura%20de%20ecr%C3%A3%202024-06-01%20195615.png)
![img](https://github.com/vitor-fidelis/literalura/blob/94bde8826b6be879fd89b3866bd793f065572c9d/imagens/imagens/Captura%20de%20ecr%C3%A3%202024-06-03%20174736.png)



Espero que você aproveite esta aplicação e que ela seja útil para suas necessidades literárias!
