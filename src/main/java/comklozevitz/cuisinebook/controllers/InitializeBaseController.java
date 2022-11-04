package comklozevitz.cuisinebook.controllers;

import comklozevitz.cuisinebook.entities.Recipe;
import comklozevitz.cuisinebook.utilities.Reader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.LinkedList;

@Controller
public class InitializeBaseController {
    LinkedList<Recipe> book = new LinkedList<>();
    private String[] paths = {"Caucasian.txt", "French.txt", "Mexican.txt", "Russian.txt"};
    @GetMapping("/")
    public String initializeBase() throws IOException {
        System.out.println("initialiseBase start");
        flushBase();
        for (String path: paths)
            book.addAll(Reader.getCuisine(path));
        for (Recipe r: book) {
            r.printRecipe();
        }
        return "index";
    }

    private void flushBase() {
        while (!book.isEmpty())
            book.removeFirst();
    }
}
