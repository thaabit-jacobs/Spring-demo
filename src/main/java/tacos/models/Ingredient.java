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

    public static Ingredient valueOf(String id) {
        if(id.equalsIgnoreCase("FLTO")){
            return new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP);
        }else if(id.equalsIgnoreCase("COTO")){
            return new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP);
        }else if(id.equalsIgnoreCase("GRBF")){
return new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN);
        }
        else if(id.equalsIgnoreCase("CARN")){
return new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN);
        }
        else if(id.equalsIgnoreCase("TMTO")){
return new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES);
        }else if(id.equalsIgnoreCase("LETC")){
return  new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES);
        }
        else if(id.equalsIgnoreCase("CHED")){
return new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE);
        }
        else if(id.equalsIgnoreCase("JACK")){
            return new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE);
        }
        else if(id.equalsIgnoreCase("SLSA")){
return new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE);
        }
        else if(id.equalsIgnoreCase("SRCR")){
            return new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE);
        } else
            throw new IllegalArgumentException();
    }



}

/*                ,
                ,
                ,
                ,
                ,
                ,
                ,
                ,
                ,
                */