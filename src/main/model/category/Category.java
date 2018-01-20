package main.model.category;

import main.model.recipe.Recipe;

import java.io.Serializable;
import java.util.*;
import java.util.function.Predicate;

public class Category implements Serializable {
    private String categoryName;
    private Map<String, Recipe> recipes;

    public Category(String categoryName, Map<String, Recipe> recipes) {
        if (recipes == null)
            throw new IllegalArgumentException("Unable to create object with null as a Map");
        this.categoryName = categoryName;
        this.recipes = recipes;
    }

    public Category(String categoryName) {
        this(categoryName, new HashMap<>());
    }

    public void addRecipe(Recipe recipe) throws DuplicateRecipeException {
        if (recipes.get(recipe.getRecipeName()) != null)
            throw new DuplicateRecipeException();
        recipes.put(recipe.getRecipeName(), recipe);
    }

    public void removeRecipe(String recipeName) {
        recipes.remove(recipeName);
    }

    public void renameRecipe(String oldName, String newName)
            throws NotFoundRecipeException, DuplicateRecipeException {
        Recipe recipe = recipes.get(oldName);
        if (recipe == null)
            throw new NotFoundRecipeException();
        if (recipes.get(newName) != null)
            throw new DuplicateRecipeException();
        recipes.remove(oldName);
        recipe.setRecipeName(newName);
        recipes.put(newName, recipe);
    }

    public Map<String, Recipe> getRecipes(Predicate<Recipe> checker) {
        Map<String, Recipe> recipeMap = new HashMap<>();
        for (Recipe recipe : recipes.values()) {
            if (checker.test(recipe))
                recipeMap.put(recipe.getRecipeName(), recipe);
        }
        return recipeMap;
    }

    public Recipe getRecipe(String recipeName) {
        return recipes.get(recipeName);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<String> getTableOfContents() {
        List<String> tableOfContents = new ArrayList<>(recipes.keySet());
        Collections.sort(tableOfContents);
        return tableOfContents;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Category))
            return false;
        if (!this.toString().equals(obj.toString()))
            return false;
        if (this.recipes.size() != ((Category) obj).recipes.size())
            return false;
        for (String recipeName : this.recipes.keySet()) {
            if (((Category) obj).recipes.get(recipeName) == null)
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return categoryName;
    }

}
