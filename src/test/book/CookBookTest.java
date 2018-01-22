package test.book;

import main.model.category.Category;
import main.model.book.*;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CookBookTest {

    @Test
    void renameCategory() throws NotFoundCategoryException, DuplicateCategoryException {
        CookBook collection2 = new CookBook("coll2");
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
