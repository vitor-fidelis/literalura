package br.com.alura.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConverteDados implements IConverteDados{
    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> T obterDados(String json, Class<T> tClass) {
        CollectionType lista = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, tClass);
        try {
            return objectMapper.readValue(json, lista);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public String extraiObjetoJson(String json, String objeto){
        try {
            JsonNode jsonCompleto = objectMapper.readTree(json);
            JsonNode jsonLivro = jsonCompleto.path("results");
            return jsonLivro.toString();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

