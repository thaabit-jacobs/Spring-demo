package tacos.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.stream.Stream;


//lOMBOK GENERATES GETTERS AND SETTERS AS WELL AS CONSRUCTOR FOR VARIABLE FIELDS
@Data
@RequiredArgsConstructor
public class Ingredient {
    private final String id;
    private final String name;
    private final Type type;

    public static enum Type{
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }


}
