package main.controler.executor;

import java.util.List;

public interface IExecutionStrategy {
    void execute(List<String> comandLine);
}