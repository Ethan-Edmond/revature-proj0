package com.ethanedmond.util;

import java.util.Scanner;

public class Dialog {
    public static String runDialog(String prompt) {
        Scanner sc = new Scanner(System.in);
        System.out.print(prompt);
        String input = sc.nextLine();
        return input;
    }
}
