package comklozevitz.cuisinebook.controllers;

import com.sun.security.auth.module.NTLoginModule;
import comklozevitz.cuisinebook.entities.Recipe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class SecondTaskController {

    static class SortByRating implements Comparator<Recipe>
    {
        public int compare(Recipe a, Recipe b)
        {
            return b.getRating() - a.getRating();
        }
    }

    @GetMapping("/secondA")
    public String secondA(Model model) {
        model.addAttribute("allTags", getAllTags());
        return "secondA";
    }

    public static Set<String> getAllTags() {
        Set<String> tagsSet = new TreeSet<>();
        for (Recipe recipe : InitializeBaseController.getBook())
            tagsSet.addAll(recipe.getTags());
        return tagsSet;
    }

    @GetMapping("/secondB")
    public String secondB(Model model, @RequestParam String tag) {
        LinkedList<Recipe> recipesByTag = new LinkedList<>();
        for (Recipe recipe : InitializeBaseController.getBook())
            if (recipe.getTags().contains(tag))
                recipesByTag.add(recipe);
        recipesByTag.sort(new SortByRating());
        while (recipesByTag.size() > 3)
            recipesByTag.removeLast();
        model.addAttribute("top3", recipesByTag);
        return "secondB";
    }


    @GetMapping("/secondC")
    public String secondC(Model model) {
        String[] tags = {"первые блюда", "вторые блюда", "десерты"};
        LinkedList<Recipe> recipes = new LinkedList<>();
        for (String tag : tags)
            recipes.add(getRandomDishByTag(tag));
        model.addAttribute("dinner", recipes);
        return "secondC";
    }

    @GetMapping("/secondD")
    public String secondD(Model model) {
        model.addAttribute("recipe", getRandomDishByTag("завтраки"));
        return "secondD";
    }

    private static Recipe getRandomDishByTag(String tag){
        LinkedList<Recipe> recipesByTag = new LinkedList<>();
        for (Recipe recipe : InitializeBaseController.getBook())
            if(recipe.getTags().contains(tag))
                recipesByTag.add(recipe);
        int randomIndex = (int) (Math.random()*recipesByTag.size());
        return recipesByTag.get(randomIndex);
    }

    @GetMapping("/secondE")
    public String secondE(Model model, @RequestParam String ingr1, @RequestParam String ingr2,
                          @RequestParam String ingr3, @RequestParam String ingr4) {
        LinkedList<String> list = new LinkedList<>();
        list.add(ingr1);
        if (!Objects.equals(ingr2, ""))
            list.add(ingr2);
        if (!Objects.equals(ingr3, ""))
            list.add(ingr3);
        if (!Objects.equals(ingr4, ""))
            list.add(ingr4);
        LinkedList<Recipe> recipes = getRecipesWithoutIngredients(list);
        if (!recipes.isEmpty()) {
            while (recipes.size() > 10)
                recipes.removeLast();
            model.addAttribute("recipes",recipes);
        }
        return "secondE";
    }

    private static LinkedList<Recipe> getRecipesWithoutIngredients(LinkedList<String> ingr) {
        LinkedList<Recipe> list = new LinkedList<>();
        boolean notContains;
        for (Recipe recipe : InitializeBaseController.getBook()) {
            notContains = true;
            for (String ingredient : ingr) {
                if (recipe.getIngredients().contains(ingredient)) {
                    notContains = false;
                    break;
                }
            }
            if (notContains)
                list.add(recipe);
        }
        return list;
    }
}
