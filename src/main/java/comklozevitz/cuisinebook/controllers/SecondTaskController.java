package comklozevitz.cuisinebook.controllers;

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
}
