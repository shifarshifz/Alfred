package com.shifz.alfred;

import com.shifz.alfred.reporters.APIStabilizer;

import java.util.Scanner;

/**
 * Created by Shifar Shifz on 8/22/2015.
 */
public class Main {

    private static final Class[] programsArr = new Class[]{
            APIStabilizer.class
    };

    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        System.out.println("Alfred at your service, Sir.");
        System.out.println("Options:");
        for (int i = 0; i < programsArr.length; i++) {
            System.out.println(i + " to " + programsArr[i].getSimpleName());
        }

        Class[] noparam = {};
        int choice;
        System.out.println("Choose a number to start the program: ");
        while ((choice = scanner.nextInt()) < programsArr.length && choice!=-1) {
            programsArr[choice].getMethod("start", noparam).invoke(null, null);
            System.out.println("Next choice :");
        }

        scanner.close();
        System.out.println("Exiting program...");
        System.exit(0);
    }
}
