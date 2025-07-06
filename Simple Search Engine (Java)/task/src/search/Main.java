package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        System.out.println("Enter number of lines: ");
//        int n = scanner.nextInt();
//        scanner.nextLine();
//
//        String[] stringLines = new String[n];
//
//        System.out.println("Enter all lines: ");
//        for (int i = 0; i < n; i++) {
//            String inputLine = scanner.nextLine();
//            stringLines[i] = inputLine;
//        }
        String inputFileName = "";

        for (int i = 0; i < args.length; i++) {
            if ("--data".equals(args[i]) & i + 1 < args.length) {
                inputFileName = args[i+1];
                break;
            }
        }

        File inputFile = new File(inputFileName);

        ArrayList<String> stringLines = readFile(inputFile);

        boolean activeMenu = true;
        int activeOption = -1;
        // Open CLI menu:

        while (activeMenu) {
            meneuCli();
            activeOption = scanner.nextInt();
            scanner.nextLine();
            switch (activeOption) {
                case 1:
                    findQuery(stringLines, scanner);
                    break;
                case 2:
                    printAllData(stringLines);
                    break;
                case 0:
                    activeMenu = false;
                    break;
            }
        }
    }

    private static void meneuCli() {
        System.out.println("=== Menu ===");
        System.out.println("1. Type query");
        System.out.println("2. Print all data");
        System.out.println("0. Exit");
    }

    private static void findQuery(ArrayList<String> data, Scanner scanner) {
        String query = scanner.nextLine();

        ArrayList<String> results = new ArrayList<>(data.size());

        for (String line : data) {
            if (line.toLowerCase().contains(query.toLowerCase())) {
                results.add(line);
            }
        }

        if (results.isEmpty()) {
            System.out.println("No matches found");
        } else {
            for (String line : results) {
                System.out.println(line);
            }
        }
    }

    private static void printAllData(ArrayList<String> data) {
        for (String line : data) {
            System.out.println(line);
        }
    }

    private static ArrayList<String> readFile(File inputFile) {
        ArrayList<String> dataLines = new ArrayList<String>(5);

        try (Scanner scanner = new Scanner(inputFile)) {
            while (scanner.hasNext()) {
                dataLines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        return dataLines;
    }
}
