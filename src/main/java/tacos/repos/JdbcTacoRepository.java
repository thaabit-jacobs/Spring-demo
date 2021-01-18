package tacos.repos;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import tacos.models.Ingredient;
import tacos.models.Taco;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

public class JdbcTacoRepository {

    private JdbcTemplate jdbc;

    public JdbcTacoRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    public Taco save(Taco taco) {
        long tacoId = saveTacoInfo(taco);

        taco.setId(tacoId);
        for(Ingredient ingredient: taco.getIngredients()){
            saveIngredientToTaco(ingredient, tacoId);
        }

        return null;
    }

    private long saveTacoInfo(Taco taco){
        taco.setCreatedAt(new Date());

        PreparedStatementCreator psc = new PreparedStatementCreatorFactory(
                "insert into Taco (name, createdAt) values (?, ?)",
                Types.VARCHAR, Types.TIMESTAMP
        ).newPreparedStatementCreator(Arrays.asList(
                taco.getName(), new Timestamp(taco.getCreatedAt().getTime())));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);

        return keyHolder.getKey().longValue();
    }

    private void saveIngredientToTaco(Ingredient ingredient, Long tacoId){
        jdbc.update("insert into Taco_ingredients (taco, ingredient)" +
                "values (?, ?)",
                tacoId, ingredient.getId());
    }
}
