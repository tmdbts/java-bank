package org.academiadecodigo.javabank.application.operations;

public class Quit implements Operation {
    @Override
    public void execute() {
        System.exit(0);
    }
}
