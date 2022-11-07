package comklozevitz.cuisinebook.controllers;

import comklozevitz.cuisinebook.entities.Recipe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.LinkedList;

@Controller
public class ThirdTaskController {
    @GetMapping("/thirdA")
    public String thirdA(Model model, @RequestParam String cuisine) {
        LinkedList<Recipe> list =getRecipesByCuisine(cuisine);
        list.sort(new SecondTaskController.SortByRating());
        while(list.size() > 3)
            list.removeLast();
        model.addAttribute("recipes", list);
        return "thirdA";
    }

    private LinkedList<Recipe> getRecipesByCuisine(String cuisine) {
        LinkedList<Recipe> list = new LinkedList<>();
        for (Recipe recipe: InitializeBaseController.getBook())
            if (recipe.getCuisine().equals(cuisine))
                list.add(recipe);
        return list;
    }

    @GetMapping("/thirdB")
    public String thirdB(Model model) {
        LinkedList<String> cuisines = new LinkedList<>();
        cuisines.add("кавказская кухня");
        cuisines.add("мексиканская кухня");
        cuisines.add("русская кухня");
        cuisines.add("французская кухня");

        int[] ratings = new int[4];
        for (int i = 0; i < cuisines.size(); i++)
            ratings[i] = cuisineRating(cuisines.get(i));

        int index = 0;
        for (int i = 1; i < ratings.length; i++)
            if(ratings[i] < ratings[index])
                index = i;

        cuisines.remove(index);
        model.addAttribute("top3cuisines", cuisines);
        return "thirdB";
    }

    private int cuisineRating(String cuisine) {
        int rating = 0;
        for(Recipe recipe: InitializeBaseController.getBook())
            if (recipe.getCuisine().equals(cuisine))
                rating += recipe.getRating();
        return rating;
    }
}
