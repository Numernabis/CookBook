package main.model.book;

import main.model.category.Category;
import main.model.category.DuplicateRecipeException;
import main.model.recipe.Recipe;

import java.io.*;
import java.util.*;

// https://www.tutorialspoint.com/java/java_serialization.htm

public class CookBook implements Serializable {

    private Map<String, Category> categories;
    private String cookBookName;

    public CookBook(String cookBookName, Map<String, Category> categories) {
        this.categories = categories;
        this.cookBookName = cookBookName;
    }

    public CookBook() {
        this("unnamed", new HashMap<>());
    }

    public CookBook(Map<String, Category> categories) {
        this("unnamed", categories);
    }

    public CookBook(String cookBookName) {
        this(cookBookName, new HashMap<>());
    }

    public void addCategory(Category category) throws DuplicateCategoryException {
        if (categories.get(category.getCategoryName()) != null)
            throw new DuplicateCategoryException();
        else categories.put(category.getCategoryName(), category);
    }

    public Category getCategory(String categoryName) throws NotFoundCategoryException {
        Category category = categories.get(categoryName);
        if (category == null)
            throw new NotFoundCategoryException();
        else
            return category;
    }

    public void removeCategory(String categoryName) {
        categories.remove(categoryName);
    }

    public void renameCategory(String oldName, String newName)
            throws NotFoundCategoryException, DuplicateCategoryException {
        Category category = categories.get(oldName);
        if (category == null)
            throw new NotFoundCategoryException();
        if (categories.get(newName) != null)
            throw new DuplicateCategoryException();
        categories.remove(oldName);
        category.setCategoryName(newName);
        categories.put(newName, category);
    }

    public void serialize(String outputFilePath) throws IOException, UnnamedCookBookException {
        if (this.cookBookName.equals("unnamed"))
            throw new UnnamedCookBookException();
        try {
            File file = new File(outputFilePath);
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException io) {
            System.out.println("Unable to read file.");
            io.printStackTrace();
        }
    }

    public static CookBook deserialize(String inputFilePath)
            throws ClassNotFoundException, IOException {
        try {
            FileInputStream fileIn = new FileInputStream(inputFilePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            CookBook importedCollection = (CookBook) in.readObject();
            in.close();
            fileIn.close();
            return importedCollection;
        } catch (IOException io) {
            System.out.println("Unable to read file.");
            io.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("CookBook class not found.");
            c.printStackTrace();
        }
        return null;
    }


    public List<String> getTableOfContents() {
        List<String> tableOfContents = new ArrayList<>(categories.keySet());
        Collections.sort(tableOfContents);
        return tableOfContents;
    }

    public String getCookBookName() {
        return cookBookName;
    }

    public void setCookBookName(String cookBookName) {
        this.cookBookName = cookBookName;
    }

    public void merge(CookBook other) {
        for (Category category : other.categories.values()) {
            Category temp = categories.get(category.getCategoryName());
            if (temp == null)
                categories.put(category.getCategoryName(), category);
            else {
                if (!category.equals(temp)) {
                    category.setCategoryName(other.getCookBookName() + "." + category.getCategoryName());
                    temp.setCategoryName(this.getCookBookName() + "." + temp.getCategoryName());
                    categories.put(category.getCategoryName(), category);
                }
            }
        }
    }
}

