package org.example;

import org.example.dto.Character;
import org.example.service.ApiService;
import org.example.service.CsvService;

import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CsvService csvService = new CsvService();
        ApiService apiService = new ApiService();

        String outCsvFile = "src/outCharacters.csv";
        String csvFile = "src/characters.csv";
        List<Character> data = csvService.readCsvFile(csvFile);
//        csvService.printCsvData(data);
        try {
            data = apiService.getCharacters();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Collections.sort(data);
        csvService.printCsvData(data);
        csvService.writeCsvFile(data, outCsvFile);
    }
}