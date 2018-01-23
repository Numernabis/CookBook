package main.view;

public class SupportViewer {

    public void printSupport() {
        System.out.println("Available global functions: " +
                "\n - .support" +
                "\n - .context" +
                "\n - .showCategories" +
                "\n - .newCategory categoryName" +
                "\n - .selectCategory categoryName" +
                "\n - .import filePath " +
                "\n - .export filePath" +
                "\n - .setCookBookName cookBookName" +
                "\n - .showCookBookName" +
                "\n - .unfocus" +
                "\n - .quit");
    }

    public void printCategorySupport() {
        System.out.println("Available context functions: " +
                "\n - showTableOfContents" +
                "\n - setCategoryName" +
                "\n - addRecipe" +
                "\n - removeRecipe" +
                "\n - selectRecipe" +
                "\n - showVegetarianMeals" +
                "\n - showIngredients");
    }

    public void printRecipeSupport() {
        System.out.println("Available context functions: " +
                "\n - setRecipeName" +
                "\n - showRecipe portions" +
                "\n - addDirection [Direction]" +
                "\n - removeDirection index" +
                "\n - addIngredient quantity measure ingredient" +
                "\n - removeIngredient index" +
                "\n - selectIngredient index" +
                "\n - setPreparationTime hours minutes (int)" +
                "\n - setCookingTime hours minutes (int)" +
                "\n - showIngredients" +
                "\n - showUnits" +
                "\n - return" );
    }

    public void printRecipeIngredientSupport() {
        System.out.println("Available context functions: " +
                "\n - setQuantity quantity" +
                "\n - convertTo unit" +
                "\n - return" );
    }

    public void printUnfocusedSupport() {
        System.out.println("Available context functions: " +
                "\n - removeCategory");
    }
}
