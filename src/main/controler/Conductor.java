package main.controler;

import main.controler.executor.*;
import main.controler.parser.IParser;
import main.model.book.CookBook;
import main.model.category.Category;
import main.model.recipe.*;
import main.view.ViewManager;

public class Conductor {
    public IParser parser;
    private CookBook cookBook;
    private ViewManager viewManager;

    public IExecutionStrategy executionStrategy;
    private Object focusedObject; // context
    private Object parentObject;
    private Category lastFocusedCategory;

    public Conductor(IParser parser) {
        this.parser = parser;
        focusedObject = null;
        viewManager = new ViewManager();
        executionStrategy = new UnfocusedExecutor(this);
        cookBook = new CookBook();
    }

    public Object getFocusedObject() {
        return focusedObject;
    }

    public ViewManager getViewManager() {
        return viewManager;
    }

    public CookBook getCookBook() {
        return cookBook;
    }

    public Object getParentObject() {
        return parentObject;
    }

    public void setFocusedObject(Object focusedObject) {
        this.parentObject = this.focusedObject;
        this.focusedObject = focusedObject;
        if (focusedObject == null) {
            executionStrategy = new UnfocusedExecutor(this);
        } else if (focusedObject instanceof Category) {
            executionStrategy = new CategoryExecutor(this);
            this.lastFocusedCategory = (Category) focusedObject;
        } else if (focusedObject instanceof Recipe) {
            executionStrategy = new RecipeExecutor(this);
            if (!(parentObject instanceof Category))
                parentObject = this.lastFocusedCategory;
        } else if (focusedObject instanceof RecipeIngredient)
            executionStrategy = new RecipeIngredientExecutor(this);
    }

}

