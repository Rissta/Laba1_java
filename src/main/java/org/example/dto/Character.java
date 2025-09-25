package org.example.dto;

// Вот бы ламбук сюда...

import java.util.Comparator;

public class Character implements Comparable<Character>{
    public String id;
    public String name;
    public String status;
    public String species;
    public String type;
    public String gender;
    public String originName;
    public String locationName;
    public String created;

    public Character() {
    }

    public Character(String[] fields) {
        this.id = fields[0];
        this.name = fields[1];
        this.status = fields[2];
        this.species = fields[3];
        this.type = fields[4];
        this.gender = fields[5];
        this.originName = fields[6];
        this.locationName = fields[7];
        this.created = fields[8];
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    // Тут как в js авто-конвертация типов когда нужно
    @Override
    public String toString() {
        return "Character:{" +
                "\n id:'" + id + '\'' +
                ",\n name:'" + name + '\'' +
                ",\n status:'" + status + '\'' +
                ",\n species:'" + species + '\'' +
                ",\n type:'" + type + '\'' +
                ",\n gender:'" + gender + '\'' +
                ",\n originName:'" + originName + '\'' +
                ",\n locationName:'" + locationName + '\'' +
                ",\n created:'" + created + '\'' + '\n' +
                '}';
    }

    // Это метод для сортировки
    // Если открыть метод сортировки из мейна,
    // то там используется этот метод для получения переменной по которой сортировать
    @Override
    public int compareTo(Character other) {
        return this.name.compareTo(other.name);
    }

    // Это для сортировки (Не обязательно)
    // === СТАТИЧЕСКИЕ КОМПАРАТОРЫ ДЛЯ РАЗНЫХ ПОЛЕЙ ===

    public static Comparator<Character> byId() {
        return Comparator.comparing(Character::getId);
    }

    public static Comparator<Character> byName() {
        return Comparator.comparing(Character::getName);
    }

    public static Comparator<Character> byStatus() {
        return Comparator.comparing(Character::getStatus);
    }

    public static Comparator<Character> bySpecies() {
        return Comparator.comparing(Character::getSpecies);
    }

    public static Comparator<Character> byType() {
        return Comparator.comparing(Character::getType);
    }

    public static Comparator<Character> byGender() {
        return Comparator.comparing(Character::getGender);
    }

    public static Comparator<Character> byOrigin() {
        return Comparator.comparing(Character::getOriginName);
    }

    public static Comparator<Character> byLocation() {
        return Comparator.comparing(Character::getLocationName);
    }

    public static Comparator<Character> byCreated() {
        return Comparator.comparing(Character::getCreated);
    }

    // === КОМБИНИРОВАННЫЕ КОМПАРАТОРЫ ===

    // Сначала по статусу, потом по имени
    public static Comparator<Character> byStatusThenName() {
        return byStatus().thenComparing(byName());
    }

    // Сначала по виду, потом по статусу, потом по имени
    public static Comparator<Character> bySpeciesStatusName() {
        return bySpecies().thenComparing(byStatus()).thenComparing(byName());
    }

    // Обратная сортировка (Z-A)
    public static Comparator<Character> byNameDesc() {
        return byName().reversed();
    }

    // Примеры сортировок таких:
    // Простые сортировки
    //characters.sort(Character.byStatus());
    //characters.sort(Character.bySpecies());
    //
    // Сложные сортировки
    //characters.sort(Character.byStatus().thenComparing(Character.byName()));
    //characters.sort(Character.bySpecies().thenComparing(Character.byStatus()).thenComparing(Character.byName()));
    //
    // Обратный порядок
    //characters.sort(Character.byName().reversed());
}
