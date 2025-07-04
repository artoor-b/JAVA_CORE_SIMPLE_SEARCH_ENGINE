package search;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

//        String firstLine = scanner.nextLine();
//        String searchWord = scanner.nextLine();

        System.out.println("Enter number of lines: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        String[] stringLines = new String[n];

        System.out.println("Enter all lines: ");
        for (int i = 0; i < n; i++) {
            String inputLine = scanner.nextLine();
            stringLines[i] = inputLine;
        }

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
//        String[] dataLineIndexed = firstLine.split(" ");
//
//        int searchIndex = -1;
//        for (int i = 0; i< dataLineIndexed.length; i++) {
//            if (dataLineIndexed[i].equals(searchWord)) {
//                searchIndex = i + 1;
//                break;
//            }
//        }
//
//        if (searchIndex != -1) {
//            System.out.println(searchIndex);
//        } else {
//            System.out.println("Not found");
//        }
    }

    private static void meneuCli() {
        System.out.println("=== Menu ===");
        System.out.println("1. Type query");
        System.out.println("2. Print all data");
        System.out.println("0. Exit");
    }

    private static void findQuery(String[] data, Scanner scanner) {
        String query = scanner.nextLine();

        ArrayList<String> results = new ArrayList<>(data.length);

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

    private static void printAllData(String[] data) {
        for (String line : data) {
            System.out.println(line);
        }
    }
}
