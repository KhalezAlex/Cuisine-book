package comklozevitz.cuisinebook.controllers;

import comklozevitz.cuisinebook.entities.Recipe;
import comklozevitz.cuisinebook.utilities.Reader;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

@Controller
public class InitializeBaseController {
    private static LinkedList<Recipe> book = new LinkedList<>();
    private static LinkedList<String> namesList = new LinkedList<>();
    private static Set<String> setOfIngredients = new TreeSet<>();
    private final String[] paths = {"Caucasian.txt", "French.txt", "Mexican.txt", "Russian.txt"};

    public static Set<String> getSetOfIngredients() {
        return setOfIngredients;
    }

    public static LinkedList<String> getNamesList() {
        return namesList;
    }

    public static LinkedList<Recipe> getBook() {
        return book;
    }

    @GetMapping("/")
    public String initializeBase(Authentication auth, Model model) throws IOException {
        flushBase();
        for (String path : paths)
            book.addAll(Reader.getCuisine(path));
        initListNames();
        initSetOfIngredients();
        model.addAttribute("listNames", namesList);
        model.addAttribute("setOfIngredients", setOfIngredients);
        model.addAttribute("allTags", SecondTaskController.getAllTags());
        if (auth != null)
            model.addAttribute("isAdmin", auth.getAuthorities().toString().contains("ROLE_ADMIN"));
//        System.out.println(auth.getAuthorities() + auth.getName());
        return "index";
    }


    static Recipe getInfoRecipe(String name) {
        for (Recipe recipe : book)
            if (recipe.getName().equals(name))
                return recipe;
        return null;
    }

    private void flushBase() {
        while (!book.isEmpty())
            book.removeFirst();
    }

    private void initListNames() {
        if (!book.isEmpty())
            if (getNamesList().isEmpty())
                for (Recipe recipe : book)
                    namesList.add(recipe.getName());
    }

    private void initSetOfIngredients() {
        HashSet<String> temp = new HashSet<>();
        if (!book.isEmpty())
            for (Recipe r : book)
                if (!r.getIngredients().isEmpty())
                    temp.addAll(r.getIngredients());
        setOfIngredients.addAll(temp);
    }
}
