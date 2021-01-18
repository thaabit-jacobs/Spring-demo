package tacos.models;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class Taco {
    private Long id;

    @NotNull
    @Size(min=6, message="taco name must be at least 6 characters long")
    private String name;
    
    @Size(min=1, message="must select one ingredient")
    private List<String> ingredients;

    private Date createdAt;
    


}
