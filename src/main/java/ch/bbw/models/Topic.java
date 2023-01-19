package ch.bbw.models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Topic {
    private String topic;
    private List<Question> questions;
}