package com.game.src.main;

public class Assertion {

    private static boolean isAssertionOn = true;

    private static void printStack(String why) {
        Throwable t = new Throwable(why);
        t.printStackTrace();
        System.exit(1);
    }

    public static void _assert(boolean expression, String why) {
        if (isAssertionOn && !expression) {
            printStack(why);
        }
    }

    public static void setIsAssertionOn(boolean isAssertionOn) {
        Assertion.isAssertionOn = isAssertionOn;
    }
}
