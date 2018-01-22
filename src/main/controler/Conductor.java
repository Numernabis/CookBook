package main.controler;

import main.controler.executor.*;
import main.controler.parser.IParser;
import main.controler.parser.StringParser;
import main.model.category.Category;
import main.model.book.CategoryCollection;
import main.model.recipe.*;
import main.view.Manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class Conductor {
    public IParser parser;
    private CategoryCollection categoryCollection;
    private Manager viewManager;

    public IExecutionStrategy executionStrategy;
    private Object focusedObject; // context of Execution Strategy/
    private Object parentObject;
    private Category lastFocusedCategory;

    public Conductor(IParser parser) {
        this.parser = parser;
        focusedObject = null;
        viewManager = new Manager();
        executionStrategy = new UnfocusedExecutor(this);
        categoryCollection = new CategoryCollection();
    }

    public Object getFocusedObject() {
        return focusedObject;
    }

    public Manager getViewManager() {
        return viewManager;
    }

    public CategoryCollection getCategoryCollection() {
        return categoryCollection;
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

