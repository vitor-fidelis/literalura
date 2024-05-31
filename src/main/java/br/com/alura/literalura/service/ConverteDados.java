package br.com.alura.literalura.service;

import br.com.alura.literalura.model.LivroDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConverteDados implements IConverteDados {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return objectMapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<LivroDTO> obterListaDeLivros(String json) {
        try {
            CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(List.class, LivroDTO.class);
            return objectMapper.readValue(json, listType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String extraiObjetoJson(String json, String objeto) {
        try {
            JsonNode jsonCompleto = objectMapper.readTree(json);
            JsonNode jsonLivro = jsonCompleto.path(objeto);
            return jsonLivro.toString();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
