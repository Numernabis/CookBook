package main.controler.executor;

import main.controler.Conductor;
import main.model.category.Category;
import main.model.book.*;
import main.view.Manager;

import java.io.*;
import java.util.List;


public class UnfocusedExecutor implements IExecutionStrategy {
    protected Manager viewManager;
    protected Conductor conductor;


    public UnfocusedExecutor(Conductor conductor) {
        this.viewManager = conductor.getViewManager();
        this.conductor = conductor;
    }

    @Override
    public void execute(List<String> commandLine) {
        if (commandLine.isEmpty()) {
            viewManager.noteViewer.printEmptyCommandLineMessage();
        } else if (commandLine.get(0).substring(0, 1).equals("/")) {
            executeConsoleCommand(commandLine);
        } else executeContextCommand(commandLine);
    }

    private void executeConsoleCommand(List<String> commandLine) {
        switch (commandLine.get(0)) {
            case "/support": {
                viewManager.supportViewer.printSupport();
                showSupport();
                break;
            }
            case "/context": {
                viewManager.noteViewer.printContext(conductor.getFocusedObject());
                break;
            }
            case "/showCategories": {
                viewManager.dataViewer.showCategories(conductor.getCategoryCollection().getTableOfContents());
                break;
            }
            case "/selectCategory": {
                selectCategory(commandLine);
                break;
            }
            case "/import": {
                importCookBook(commandLine);
                break;
            }
            case "/export": {
                exportCookBook(commandLine);
                break;
            }
            case "/newCategory": {
                createCategory(commandLine);
                break;
            }
            case "/setCookBookName": {
                setCookBookName(commandLine);
                break;
            }
            case "/showCookBookName": {
                viewManager.noteViewer.printNote("CookBook name:" + conductor.getCategoryCollection().getCookBookName());
                break;
            }
            case "/unfocus": {
                conductor.setFocusedObject(null);
                break;
            }
            default: {
                viewManager.noteViewer.printErrorNote("Wrong command use /help to get" +
                        " available commands");
                break;
            }
        }
    }
    protected void showSupport() {
        viewManager.supportViewer.printUnfocusedSupport();
    }

    private void selectCategory(List<String> commandLine) {
        try {
            String categoryName = commandLine.get(1);
            Category category = conductor.getCategoryCollection().getCategory(categoryName);
            conductor.setFocusedObject(category);
        } catch (IndexOutOfBoundsException e) {
            viewManager.noteViewer.printErrorNote("Failed to select Category because " +
                    "name was not defined.");
        } catch (NotFoundCategoryException e) {
            viewManager.noteViewer.printErrorNote("Failed to select Category because " +
                    " there is no such category, check name spelling.");
        }
    }

    private void importCookBook(List<String> commandLine) {
        try {
            String path = commandLine.get(1);
            CategoryCollection cookBook = CategoryCollection.deserialize(path);
            cookBook.setCookBookName("imported");
            viewManager.noteViewer.printNote("Successfully imported CookBook from " + path);
        } catch (IndexOutOfBoundsException e) {
            viewManager.noteViewer.printErrorNote("Failed to import CookBook because path was not given.");
        } catch (EOFException e) {
            viewManager.noteViewer.printErrorNote("Failed to import CookBook because program found end of file. " +
                    "Make sure that selected directory is not an empty file.");
        } catch (StreamCorruptedException e) {
            viewManager.noteViewer.printErrorNote("Failed to import CookBook because wrong type of stream. " +
                    "File may have wrong type, make sure the path point at \".ser\" file.");
        } catch (InvalidClassException e) {
            viewManager.noteViewer.printErrorNote("Failed to import CookBook because obsolete version of book file.");
        } catch (IOException e) {
            viewManager.noteViewer.printErrorNote("Failed to import CookBook because of wrong path.");
        } catch (ClassNotFoundException e) {
            viewManager.noteViewer.printErrorNote("Failed to import CookBook because CookBook is not defined.");
        }
    }

    private void exportCookBook(List<String> commandLine) {
        try {
            String path = commandLine.get(1);
            conductor.getCategoryCollection().serialize(path);
            viewManager.noteViewer.printNote("Successfully exported CookBook to " + path);
        } catch (IndexOutOfBoundsException e) {
            viewManager.noteViewer.printErrorNote("Failed to export CookBook because name was not defined.");
        } catch (IOException e) {
            viewManager.noteViewer.printErrorNote("Failed to export CookBook because of wrong path.");
        } catch (UnnamedCookBookException e) {
            viewManager.noteViewer.printErrorNote("Failed to export CookBook because current collection " +
                    "you want to export is unnamed" +
                    " use command /setCookBookName and try again.");
        }
    }

    private void createCategory(List<String> commandLine) {
        try {
            String name = commandLine.get(1);
            conductor.getCategoryCollection().addCategory(new Category(name));
        } catch (IndexOutOfBoundsException e) {
            viewManager.noteViewer.printErrorNote("Failed to create category - name was not defined.");
        } catch (DuplicateCategoryException e) {
            viewManager.noteViewer.printErrorNote("Failed to create category - category with that name already exists.");
        }
    }

    private void setCookBookName(List<String> commandLine) {
        try {
            conductor.getCategoryCollection().setCookBookName(commandLine.get(1));
        } catch (IndexOutOfBoundsException e) {
            viewManager.noteViewer.printErrorNote("Failed to set CookBookName because name was not provided.");
        }
    }

    protected void executeContextCommand(List<String> commandLine) {
        switch (commandLine.get(0)) {
            case "removeCategory": {
                removeCategory(commandLine);
                break;
            }
            default: {
                viewManager.noteViewer.printErrorNote("Wrong command use /help to get available commands");
            }
        }
    }

    private void removeCategory(List<String> commandLine) {
        try {
            conductor.getCategoryCollection().removeCategory(commandLine.get(1));
        } catch (IndexOutOfBoundsException e) {
            viewManager.noteViewer.printErrorNote("Failed to remove category because name was not provided.");
        }
    }
}

