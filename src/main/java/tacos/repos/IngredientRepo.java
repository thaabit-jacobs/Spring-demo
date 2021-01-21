package tacos.repos;

import tacos.models.Ingredient;

import java.util.List;

public interface IngredientRepo {
    public Ingredient save(Ingredient ingredient);

    public Ingredient findOne(String id);

    public Iterable<Ingredient> findAll();
}
