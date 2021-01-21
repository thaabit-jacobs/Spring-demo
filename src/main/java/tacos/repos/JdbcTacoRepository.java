package tacos.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import tacos.models.Ingredient;
import tacos.models.Taco;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcTacoRepository implements TacoRepo{

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcTacoRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    public Taco save(Taco taco) {
        long tacoId = saveTacoInfo(taco);

        taco.setId(tacoId);

        List<Ingredient> ingredientList = new ArrayList<>();

        taco.getIngredients().stream()
                .forEach(i -> ingredientList.add(Ingredient.valueOf(i)));

        for(Ingredient ingredient: ingredientList){
            saveIngredientToTaco(ingredient, tacoId);
        }

        return taco;
    }

    private long saveTacoInfo(Taco taco) {
        taco.setCreatedAt(new Date());

        PreparedStatementCreatorFactory preparedStatementCreatorFactory = new PreparedStatementCreatorFactory(
                "insert into Taco (name, createdAt) values (?, ?)",
                Types.VARCHAR, Types.TIMESTAMP
        );

        preparedStatementCreatorFactory.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = preparedStatementCreatorFactory.newPreparedStatementCreator(
                Arrays.asList(
                        taco.getName(),
                        new Timestamp(taco.getCreatedAt().getTime())));

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(psc, keyHolder);

        return keyHolder.getKey().longValue();
    }

    private void saveIngredientToTaco(Ingredient ingredient, Long tacoId){
        jdbc.update("insert into Taco_Ingredients (taco, ingredient)" +
                "values (?, ?)",
                tacoId, ingredient.getId());
    }
}
