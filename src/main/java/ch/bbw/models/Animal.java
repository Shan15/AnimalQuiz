package ch.bbw.models;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class Animal {

    private ObjectId _id;
    private String animal;
    private int age;
    private int weight;
    private int spawnrate;
    private int sleepover;
    private int size;
    private String animalSpecies;

    public int getProperty(String property) {
        int value = 0;
        if (property.equals("age")) {
            value = this.age;
        } else if (property.equals("size")) {
            value = this.size;
        } else if (property.equals("sleepover")) {
            value = this.sleepover;
        } else if (property.equals("weight")) {
            value = this.weight;
        } else if (property.equals("spawnrate")) {
            value = this.spawnrate;
        }
        return value;
    }
}