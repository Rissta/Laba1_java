package org.example;

import org.example.dto.Character;
import org.example.service.ApiService;
import org.example.service.CsvService;
import org.example.service.MapV1CsvService;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        CsvService csvService = new CsvService();
        ApiService apiService = new ApiService();

        String outCsvFile = "src/outCharacters.csv";
        String csvFile = "src/characters.csv";
        List<Character> data = csvService.readCsvFile(csvFile);
//        try {
//            data = apiService.getCharacters();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Collections.sort(data);
        csvService.printCsvData(data);
        csvService.writeCsvFile(data, outCsvFile);

        // Примеры действий
        // Только живые персонажи
        //List<Character> aliveCharacters = data.stream()
        //    .filter(c -> "Alive".equals(c.getStatus()))
        //    .collect(Collectors.toList());
        // Поиск по имени (частичное совпадение)
//        List<Character> ricks = characters.stream()
//                .filter(c -> c.getName().contains("Rick"))
//                .collect(Collectors.toList());
        // Поиск первого подходящего
        //Optional<Character> firstAlien = characters.stream()
        //    .filter(c -> "Alien".equals(c.getSpecies()))
        //    .findFirst();
        //
        // Проверка существования
        //boolean hasDeadCharacters = characters.stream()
        //    .anyMatch(c -> "Dead".equals(c.getStatus()));
        //
        // Проверка что все удовлетворяют условию
        // boolean allHaveNames = characters.stream()
        //    .allMatch(c -> c.getName() != null && !c.getName().isEmpty());
        // Получить только имена
        //List<String> names = characters.stream()
        //    .map(Character::getName)
        //    .collect(Collectors.toList());
        //
        // Получить пары имя-статус
        //List<String> nameStatusPairs = characters.stream()
        //    .map(c -> c.getName() + " - " + c.getStatus())
        //    .collect(Collectors.toList());
        //
        // Создать Map из List (id -> имя)
        // Map<String, String> idToName = characters.stream()
        //    .collect(Collectors.toMap(Character::getId, Character::getName));
        // // Подсчет количества
        //long aliveCount = characters.stream()
        //    .filter(c -> "Alive".equals(c.getStatus()))
        //    .count();
        //
        // Группировка по статусу
        //Map<String, List<Character>> byStatus = characters.stream()
        //    .collect(Collectors.groupingBy(Character::getStatus));
        //
        // Группировка по виду с подсчетом
        //Map<String, Long> speciesCount = characters.stream()
        //    .collect(Collectors.groupingBy(Character::getSpecies, Collectors.counting()));
        //
        // Найти самый популярный вид
        // String mostCommonSpecies = speciesCount.entrySet().stream()
        //    .max(Map.Entry.comparingByValue())
        //    .map(Map.Entry::getKey)
        //    .orElse("Unknown");
        // Топ-10 персонажей
        //List<Character> top10 = characters.stream()
        //    .limit(10)
        //    .collect(Collectors.toList());
        //
        // Пропустить первых 5, взять следующие 10
        // List<Character> page2 = characters.stream()
        //    .skip(5)
        //    .limit(10)
        //    .collect(Collectors.toList());
        // Ну короче чезе дипсик или меня сделаете. Либо сами без stream() а простыми хуйнями
    }
}