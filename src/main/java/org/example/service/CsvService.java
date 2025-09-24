package org.example.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.example.dto.Character;


// Ну сервис это чисто когда методы отсюда берут, и они не хранят в себе переменные
public class CsvService {

    // Сам метод чтения. Получился очень простым
    public List<Character> readCsvFile(String csvFile) {
        //Лист тут считайте как вектор.
        List<Character> data = new ArrayList<>();
        // Тут короче происходит чтение самого файл.
        // Как работает бибиблиотека сканер с файлами в целом.
        // Он короче не читает файл полность, там есть буфер на 1024 символа.
        // У него есть метод next - возвращает одно слово до пробела, буфер переходит на следующее слово после пробела
        // Так же можно двигать по типам например nextInt в строке "нюхай беберу 322" он перескочит на 322 и вернет 322 в виде инта.
        // В моем случае я читаю каждую строку. И записываю ее в стринг.
        try (Scanner scanner = new Scanner(new File(csvFile))) {
            System.out.println(scanner);
            // hasNextLine - возращает бул, ну я думаю и так понятно, не тупые
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // Так как записи в CSV файлике хранятся в одной линии через запятую,
                // то мы разбиваем линию по запятой с помощью split(",")
                // на массив из слов, а после конвертим массив в лист
                String[] values = line.split(",");
                Character character = new Character(values);

                // Добовляем лист который получился в другой лист который
                data.add(character);
            }

        }
        // Встроенное исключение
        catch (FileNotFoundException e) {
            System.err.println("CSV file not found: " + e.getMessage());
            return new ArrayList<>();
        }

        return data;
    }

    // Тут PrintWriter базовый класс джавы который данные в поток вывода выводит.
    public void writeCsvFile(List<Character> data, String csvFile) {
        try (PrintWriter out = new PrintWriter(csvFile)) {
            for(int i = 0; i < data.size(); i++) {
                // Ну записывает просто куда надо конкретный объект.
                // Ну и автоматом строчку конвертит методом toString
                out.println(data.get(i));
            }
        }
        catch (FileNotFoundException e){
            System.err.println("CSV file not found: " + e.getMessage());
        }
    }

    public void printCsvData(List<Character> data){
        for(int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i));
        }
    }
}

