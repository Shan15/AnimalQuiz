package ch.bbw.models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Thema {
    private String name;
    private List<Question> questions;
}