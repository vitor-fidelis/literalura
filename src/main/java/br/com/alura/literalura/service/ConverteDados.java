package br.com.alura.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class ConverteDados {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return objectMapper.readValue(json, classe);
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
