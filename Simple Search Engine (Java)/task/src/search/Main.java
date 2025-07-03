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

        System.out.println("Enter number of queries: ");
        int searchQueryNumber = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < searchQueryNumber; i++) {
            System.out.println("Enter search query: ");
            String query = scanner.nextLine();

            ArrayList<String> searchResult = findQuery(query, stringLines);

            System.out.println("Found results: ");

            if (searchResult.isEmpty()) {
                System.out.println("No matches found");
            } else {
                for (String line : searchResult) {
                    System.out.println(line);
                }
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

    private static ArrayList<String> findQuery(String query, String[] data) {
        ArrayList<String> results = new ArrayList<>(data.length);

        for (String line : data) {
            if (line.toLowerCase().contains(query.toLowerCase())) {
                results.add(line);
            }
        }

        return results;
    }
}
