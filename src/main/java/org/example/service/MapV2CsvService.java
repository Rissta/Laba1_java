package org.example.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

// Вторая идея: вместо объектов Character используем Map<String, String>
// где ключ - название поля (например, "name", "status"), значение - данные
// Это более гибкий подход для обработки CSV
public class MapV2CsvService {

    public List<Map<String, String>> readCsvFile(String csvFile) {
        List<Map<String, String>> data = new ArrayList<>();
        List<String> headers = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(csvFile))) {
            boolean isFirstLine = true;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(","); // Улучшенный парсинг

                if (isFirstLine) {
                    // Первая строка - заголовки
                    headers = Arrays.asList(values);
                    isFirstLine = false;
                } else {
                    // Создаем Map для каждой строки
                    Map<String, String> row = new HashMap<>();
                    for (int i = 0; i < Math.min(headers.size(), values.length); i++) {
                        row.put(headers.get(i), values[i]);
                    }
                    data.add(row);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("CSV file not found: " + e.getMessage());
            return new ArrayList<>();
        }

        return data;
    }


    // Запись с поддержкой разных форматов вывода
    public void writeCsvFile(List<Map<String, String>> data, String csvFile) {
        if (data.isEmpty()) return;

        try (PrintWriter out = new PrintWriter(csvFile)) {
            // Записываем заголовки (ключи из первой Map)
            Set<String> headers = data.get(0).keySet();
            String headerLine = String.join(",", headers);
            out.println(headerLine);

            // Записываем данные
            for (Map<String, String> row : data) {
                List<String> values = new ArrayList<>();
                for (String header : headers) {
                    String value = row.get(header);
                    values.add(value != null ? value : "");
                }
                out.println(String.join(",", values));
            }
        } catch (FileNotFoundException e) {
            System.err.println("CSV file not found: " + e.getMessage());
        }
    }

    // Вывод в консоль в читаемом формате
    public void printCsvData(List<Map<String, String>> data) {
        for (int i = 0; i < data.size(); i++) {
            System.out.println("Record " + (i + 1) + ":");
            Map<String, String> row = data.get(i);
            for (Map.Entry<String, String> entry : row.entrySet()) {
                System.out.println("  " + entry.getKey() + ": " + entry.getValue());
            }
            System.out.println();
        }
    }

    // === ПРОСТЫЕ МЕТОДЫ ДЛЯ ОБРАБОТКИ ===

    // Группировка по полю (например, по статусу)
    public Map<String, List<Map<String, String>>> groupByField(List<Map<String, String>> data, String fieldName) {
        Map<String, List<Map<String, String>>> result = new HashMap<>();

        for (Map<String, String> row : data) {
            String key = row.get(fieldName);
            if (key == null) key = "Unknown";

            result.computeIfAbsent(key, k -> new ArrayList<>()).add(row);
        }
        return result;
    }

    // Фильтрация (например, только живые персонажи)
    public List<Map<String, String>> filterByField(List<Map<String, String>> data, String fieldName, String value) {
        List<Map<String, String>> result = new ArrayList<>();

        for (Map<String, String> row : data) {
            if (value.equals(row.get(fieldName))) {
                result.add(row);
            }
        }
        return result;
    }

    // Сортировка по полю
    public List<Map<String, String>> sortByField(List<Map<String, String>> data, String fieldName) {
        List<Map<String, String>> sorted = new ArrayList<>(data);

        sorted.sort((a, b) -> {
            String valA = a.get(fieldName);
            String valB = b.get(fieldName);
            if (valA == null) valA = "";
            if (valB == null) valB = "";
            return valA.compareTo(valB);
        });

        return sorted;
    }

    // Подсчет статистики (сколько каждого значения)
    public Map<String, Integer> countByField(List<Map<String, String>> data, String fieldName) {
        Map<String, Integer> result = new HashMap<>();

        for (Map<String, String> row : data) {
            String value = row.get(fieldName);
            if (value == null) value = "Unknown";

            result.put(value, result.getOrDefault(value, 0) + 1);
        }
        return result;
    }

    // Уникальные значения поля
    public Set<String> getUniqueValues(List<Map<String, String>> data, String fieldName) {
        Set<String> result = new HashSet<>();

        for (Map<String, String> row : data) {
            String value = row.get(fieldName);
            if (value != null) {
                result.add(value);
            }
        }
        return result;
    }

    // Топ-N по алфавиту
    public List<Map<String, String>> getTopN(List<Map<String, String>> data, String sortField, int n) {
        List<Map<String, String>> sorted = sortByField(data, sortField);
        return sorted.subList(0, Math.min(n, sorted.size()));
    }

}