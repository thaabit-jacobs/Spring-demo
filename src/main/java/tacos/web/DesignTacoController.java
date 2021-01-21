package tacos.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos.models.Order;
import tacos.models.Taco;
import tacos.models.Ingredient;
import tacos.models.Ingredient.Type;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import tacos.repos.IngredientRepo;
import tacos.repos.TacoRepo;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepo ingredientRepo;
    private final TacoRepo tacoRepo;

    @Autowired
    public DesignTacoController(IngredientRepo ingredientRepo,TacoRepo tacoRepo){
        this.ingredientRepo = ingredientRepo;
        this.tacoRepo = tacoRepo;
    }

    @GetMapping
    public String showDEsignForm(Model model){
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));

        Type[] types = Ingredient.Type.values();

        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        model.addAttribute("taco", new Taco());

        return "design";
    }

    public static List<Ingredient> filterByType(List<Ingredient> ingredients, Type type){
        List<Ingredient> filteredList = ingredients.stream()
                .filter(i -> i.getType().toString().equalsIgnoreCase(type.toString()))
                .collect(Collectors.toList());

        return filteredList;
    }

    @ModelAttribute(name = "order")
    public Order order(){
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @PostMapping
    public String processDesign(@Valid Taco taco, Errors errors, @ModelAttribute Order order){

        if(errors.hasErrors()){
            System.out.println("Errors ");
            return "design";
        }

        log.info("Process design: " + taco);

        Taco saved = tacoRepo.save(taco);

        log.info("saved design: " + saved);
        log.info("Current order: " + order);

        order.addTaco(saved);

        return "redirect:/orders/current";
    }
}



