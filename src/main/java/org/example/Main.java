package org.example;

import org.example.Interface.GameInterface;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        GameInterface gameInterface = new GameInterface();
        gameInterface.showMenu();
    }
}
