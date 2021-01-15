package tacos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

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
