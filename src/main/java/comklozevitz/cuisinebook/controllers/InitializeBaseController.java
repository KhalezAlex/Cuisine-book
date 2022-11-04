package comklozevitz.cuisinebook.controllers;

import comklozevitz.cuisinebook.entities.Recipe;
import comklozevitz.cuisinebook.utilities.Reader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

@Controller
public class InitializeBaseController {
    LinkedList<Recipe> book = new LinkedList<>();
    private final LinkedList<String> listNames = new LinkedList<>();
    private Set<String> setOfIngredients = new HashSet<>();
    private final String[] paths = {"Caucasian.txt", "French.txt", "Mexican.txt", "Russian.txt"};
    @GetMapping("/")
    public String initializeBase(Model model) throws IOException {
        System.out.println("initialiseBase start");
        flushBase();
        for (String path: paths)
            book.addAll(Reader.getCuisine(path));
        initListNames();
        initSetOfIngredients();
        model.addAttribute("listNames", listNames);
        model.addAttribute("setOfIngredients", setOfIngredients);
        return "index";
    }

    @GetMapping("/firstTask")
    public String infoRecipe(Model model, @RequestParam(required = false) String name){
        Recipe recipe = getInfoRecipe(name);
        if(recipe != null) {
            model.addAttribute("name", recipe.getName());
            model.addAttribute("cuisine", recipe.getCuisine());
            model.addAttribute("type", recipe.getType());
            model.addAttribute("ingredients", recipe.getIngredients());
            model.addAttribute("listNames", listNames);
        }
        return "index";
    }
    private Recipe getInfoRecipe(String name) {
        for (Recipe recipe : book)
            if (recipe.getName().equals(name))
                return recipe;
        return null;
    }

    private void flushBase() {
        while (!book.isEmpty())
            book.removeFirst();
    }

    private void initListNames(){
        if(!book.isEmpty())
            for(Recipe r : book)
                listNames.add(r.getName());
    }

    private void initSetOfIngredients() {
        if (!book.isEmpty())
            for (Recipe r: book)
                if (!r.getIngredients().isEmpty())
                    setOfIngredients.addAll(r.getIngredients());
    }

    @GetMapping("/secondTask")
    public String linkRecipe(Model model, @RequestParam(required = false) String name) {
        Recipe recipe = getInfoRecipe(name);
        if(recipe != null) {
            model.addAttribute("name", recipe.getName());
            model.addAttribute("technology", recipe.getTechnology());
            model.addAttribute("link" , recipe.getLink());
            model.addAttribute("listNames", listNames);
        }
        return "index";
    }
}
