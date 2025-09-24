package org.example.dto;

// Вот бы ламбук сюда...

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

}
