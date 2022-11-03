package comklozevitz.cuisinebook;

import java.util.LinkedList;

public class Recipe {
    private String name;
    private String cuisine;
    private String ingredients;
    private LinkedList<String> tags;
    private String technology;
    private String link;
    private int rating;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public LinkedList<String> getTags() {
        return tags;
    }

    public void setTags(LinkedList<String> tags) {
        this.tags = tags;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Recipe(String name, String cuisine, String ingredients,
                  LinkedList<String> tags, String technology, int rating) {
        this.name = name;
        this.cuisine = cuisine;
        this.ingredients = ingredients;
        this.tags = tags;
        this.technology = technology;
        this.rating = rating;
        this.link = "youtube.com";
    }

    public Recipe(String name, String cuisine, String ingredients, LinkedList<String> tags,
                  String technology, String link, int rating) {
        this.name = name;
        this.cuisine = cuisine;
        this.ingredients = ingredients;
        this.tags = tags;
        this.technology = technology;
        this.link = link;
        this.rating = rating;
    }
}
