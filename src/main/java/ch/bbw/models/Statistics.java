package ch.bbw.models;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

@Getter
@Setter
public class Statistics {
    public ObjectId _id;
    private String name;
    private int points;
    private long time;
}