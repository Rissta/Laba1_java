package org.example.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.Character;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


public class ApiService {
    // URL из задания. final - это константа
    private static final String API_URL = "https://rickandmortyapi.com/api/character";
    private final HttpClient httpClient;

    // Так короче обычно делают когда сервис, репозиторий какой подключают.
    // Сначала объявление его в переменной, а потом определяют в конструкторе
    public ApiService() {
        this.httpClient = HttpClient.newHttpClient();
    }

    // Сам метод который получает JSON строку
    // throws Exception - чтобы писать любое исключение внутри
    public String getAllCharactersJson() throws Exception {
        // Самое сложное. Короче тут мы создаем наш запрос.
        // Используем паттерн Builder для пошагового создания HTTP запроса:
        // HttpRequest.newBuilder() - создает строитель для нового запроса
        // uri(URI.create(API_URL)) - устанавливает целевой URL API
        // GET() - указывает метод HTTP запроса (GET по умолчанию)
        // build() - завершает строительство и возвращает готовый объект HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();

        // метод send блокирующий - текущий поток будет ждать ответа от сервера.
        // Второй параметр, который передаем в метод - это обработчик тела ответа, показываем что нам нужно его в строку обработать
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // статус 200 - это то что все окей.
        if (response.statusCode() != 200) {
            throw new RuntimeException("HTTP error: " + response.statusCode());
        }

        // Возвращаем тело ответа как сырую JSON строку
        // Пример: {"info": {...}, "results": [{...}, {...}]}
        return response.body();
    }

    // Все то же самое только в ретерне конвертация в наш тип данных
    public List<Character> getCharacters() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("HTTP error: " + response.statusCode());
        }

        return parseToCharacterDto(response.body());
    }


    private List<Character> parseToCharacterDto(String json) throws Exception {
        List<Character> characters = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        // Как это внутри работает я не пон.
        // Структура у контейнера это дерево.
        // Он 1 в 1 повторяет json который приходит
        JsonNode root = mapper.readTree(json);
        // Ну тут уже чтобы лучше понять просто гляньте json который приходит.
        // results это ветка со всеми персонажами
        JsonNode results = root.get("results");

        for (JsonNode characterNode : results) {
            Character character = new Character();

            // Тут заполняем поля согласно структуре DTO
            character.setId(characterNode.get("id").asText());
            character.setName(characterNode.get("name").asText());
            character.setStatus(characterNode.get("status").asText());
            character.setSpecies(characterNode.get("species").asText());
            character.setType(characterNode.get("type").asText());
            character.setGender(characterNode.get("gender").asText());

            // А тут вложенные поля поэтому сначала получаем внешние ветки, а потом уже нужные поля которые внутри.
            if (characterNode.has("origin")) {
                character.setOriginName(characterNode.get("origin").get("name").asText());
            }

            if (characterNode.has("location")) {
                character.setLocationName(characterNode.get("location").get("name").asText());
            }

            character.setCreated(characterNode.get("created").asText());

            characters.add(character);
        }

        return characters;
    }

}
