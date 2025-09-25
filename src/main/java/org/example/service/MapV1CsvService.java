package org.example.service;

import org.example.dto.Character;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.regex.Pattern;

// Смотрите есть 2 идеи как ассоциативные коллекции в лабе могут использоваться
// Первая это типа мы используем так же персон, ток как ключ там айди еще пихаем
// Вторая это мы вместо объекта персон делаем контейнер у которого ключ будет как названия поля,
// например айди а велью это само значение

// ТУт первая идея. Тогда поле айди надо убрать из персонажей


public class MapV1CsvService {

    public Map<Integer, Character> readCsvFile(String csvFile) {
        Map<Integer, Character> data = new HashMap<>();
        try (Scanner scanner = new Scanner(new File(csvFile))) {
            System.out.println(scanner);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                if (Pattern.matches("\\d+", values[0])){
                    Character character = new Character(values);
                    data.put(Integer.parseInt(values[0]), character);
                }
            }

        }
        catch (FileNotFoundException e) {
            System.err.println("CSV file not found: " + e.getMessage());
            return new HashMap<>();
        }

        return data;
    }


    // Тут если что get() не по индексу а по ключу
    public void writeCsvFile(Map<Integer, Character> data, String csvFile) {
        try (PrintWriter out = new PrintWriter(csvFile)) {
            for(int i = 0; i < data.size(); i++) {
                out.println(data.get(i));
            }
        }
        catch (FileNotFoundException e){
            System.err.println("CSV file not found: " + e.getMessage());
        }
    }

    public void printCsvData(Map<Integer, Character> data){
        for(int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i));
        }
    }

}
