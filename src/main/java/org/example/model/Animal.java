package org.example.model;

public class Animal {

    private String name;
    private String color;
    private String animalType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public Animal(String name, String color, String animalType) {
        this.name = name;
        this.color = color;
        this.animalType = animalType;
    }

    public Animal() {
    }
}
