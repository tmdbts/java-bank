package org.academiadecodigo.javabank.views;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.javabank.application.UserOptions;
import org.academiadecodigo.javabank.controller.application.operations.Operation;

import java.util.Map;

public class MenuView {

    public void menuLoop(MenuInputScanner mainMenu, Map<Integer, Operation> operationsMap, Prompt prompt) {

        int userChoice = prompt.getUserInput(mainMenu);

        if (userChoice == UserOptions.QUIT.getOption()) {
            return;
        }

        operationsMap.get(userChoice).execute();
        menuLoop(mainMenu, operationsMap, prompt);
    }
}
