package comklozevitz.cuisinebook.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ForthTaskController {
    @GetMapping("/addRecipe")
    public String forthA(Authentication auth) {
        if (auth.getPrincipal() != null)
            return "addRecipe";
        else
            return "index";
    }
}
