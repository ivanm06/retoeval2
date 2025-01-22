package me.ivanmart.plaiaundi;


import java.io.Console;
import java.util.Arrays;

public class test {
    public static void main(String[] args) {
        Console console = System.console();
        String pass = Arrays.toString(console.readPassword("Enter your password: "));
    }
}
