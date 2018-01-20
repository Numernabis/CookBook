package test.book;

import main.model.category.Category;
import main.model.book.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryCollectionTest {
    @Test
    void serialize() throws IOException, UnnamedCookBookException {
        CategoryCollection collection1 = new CategoryCollection();
        assertThrows(UnnamedCookBookException.class, () -> {
            collection1.serialize("coll2.txt");
        });
        CategoryCollection collection2 = new CategoryCollection("coll2");
        collection2.serialize("coll2.txt");
    }

    @Test
    void deserialize() throws IOException, UnnamedCookBookException, ClassNotFoundException {
        String filepath = "coll2.txt";
        CategoryCollection collection2 = new CategoryCollection("coll2");
        // assertThrows(IOException.class, () -> CategoryCollection.deserialize(filepath));
        collection2.serialize(filepath);
        collection2 = CategoryCollection.deserialize(filepath);
    }

    @Test
    void renameCategory() throws NotFoundCategoryException, DuplicateCategoryException {
        CategoryCollection collection2 = new CategoryCollection("coll2");
        Category cat1 = new Category("dinner");
        collection2.addCategory(cat1);
        Category cat2 = new Category("supper");
        collection2.addCategory(cat2);
        assertThrows(DuplicateCategoryException.class, () -> {
            collection2.renameCategory("dinner", "supper");
        });
        assertThrows(NotFoundCategoryException.class, () -> {
            collection2.renameCategory("dinner&more", "supper");
        });
        collection2.renameCategory("dinner", "lunch");
        assertEquals(collection2.getCategory("lunch"), cat1);
    }
}
