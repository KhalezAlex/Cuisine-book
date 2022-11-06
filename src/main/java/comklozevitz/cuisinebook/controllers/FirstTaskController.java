package comklozevitz.cuisinebook.controllers;

import comklozevitz.cuisinebook.entities.Recipe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.Objects;

import static comklozevitz.cuisinebook.controllers.InitializeBaseController.getInfoRecipe;

@Controller
public class FirstTaskController {
    @GetMapping("/firstA")
    public String firstA(Model model, @RequestParam(required = false) String name){
        Recipe recipe = getInfoRecipe(name);
        if(recipe != null) {
            model.addAttribute("name", recipe.getName());
            model.addAttribute("cuisine", recipe.getCuisine());
            model.addAttribute("type", recipe.getType());
            model.addAttribute("ingredients", recipe.getIngredients());
        }
        return "firstAB";
    }

    @GetMapping("/firstB")
    public String firstB(Model model, @RequestParam(required = false) String name) {
        Recipe recipe = getInfoRecipe(name);
        if(recipe != null) {
            model.addAttribute("name", recipe.getName());
            model.addAttribute("technology", recipe.getTechnology());
            model.addAttribute("link" , recipe.getLink());
        }
        return "firstAB";
    }

    @GetMapping("/firstC")
    public String ingredientsRecipe(Model model, @RequestParam String ingr1,
                                    @RequestParam(required = false) String ingr2,
                                    @RequestParam(required = false) String ingr3,
                                    @RequestParam(required = false) String ingr4) {
        LinkedList<String> list = new LinkedList<>();
        list.add(ingr1);
        if (!Objects.equals(ingr2, ""))
            list.add(ingr2);
        if (!Objects.equals(ingr3, ""))
            list.add(ingr3);
        if (!Objects.equals(ingr4, ""))
            list.add(ingr4);
        LinkedList<Recipe> recipesList = getRecipesByIngredients(list);
        if (!recipesList.isEmpty()) {
            model.addAttribute("recipesList", recipesList);
        }
        return "firstC";
    }

    private static LinkedList<Recipe> getRecipesByIngredients(LinkedList<String> ingr) {
        LinkedList<Recipe> list = new LinkedList<>();
        boolean contains;
        for (Recipe recipe : InitializeBaseController.getBook()) {
            contains = true;
            for (String ingredient : ingr) {
                if (!recipe.getIngredients().contains(ingredient)) {
                    contains = false;
                    break;
                }
            }
            if (contains)
                list.add(recipe);
        }
        return list;
    }

    @GetMapping("/firstD")
    public String randomRecipe(Model model) {
        Recipe recipe = getRandomRecipe();
        model.addAttribute("recipe", recipe);
        return "firstD";
    }

    private static Recipe getRandomRecipe() {
        int randomIndex = (int) (Math.random() * InitializeBaseController.getBook().size()) + 1;
        return InitializeBaseController.getBook().get(randomIndex);
    }
}
