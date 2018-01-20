package main.model.book;

import main.model.category.Category;

import java.io.*;
import java.util.*;

// https://www.tutorialspoint.com/java/java_serialization.htm

public class CategoryCollection implements Serializable {

    private Map<String, Category> categories;
    private String cookBookName;

    public CategoryCollection(String cookBookName, Map<String, Category> categories) {
        this.categories = categories;
        this.cookBookName = cookBookName;
    }

    public CategoryCollection() {
        this("unnamed", new HashMap<>());
    }

    public CategoryCollection(Map<String, Category> categories) {
        this("unnamed", categories);
    }

    public CategoryCollection(String cookBookName) {
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
            System.out.println("Serialized data is saved in " + outputFilePath);
        } catch (IOException io) {
            System.out.println("Błąd odczytu pliku");
            io.printStackTrace();
        }
    }

    public static CategoryCollection deserialize(String inputFilePath)
            throws ClassNotFoundException, IOException {
        try {
            FileInputStream fileIn = new FileInputStream(inputFilePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            CategoryCollection importedCollection = (CategoryCollection) in.readObject();
            in.close();
            fileIn.close();
            return importedCollection;
        } catch (IOException io) {
            System.out.println("Błąd odczytu pliku");
            io.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("CategoryCollection class not found.");
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

}

