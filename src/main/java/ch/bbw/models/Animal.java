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
}