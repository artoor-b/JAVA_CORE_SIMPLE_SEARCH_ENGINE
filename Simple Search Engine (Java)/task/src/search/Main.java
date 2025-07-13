package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.lang.reflect.Array;
import java.util.*;

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
        ArrayList<Integer> stringLinesIndexes = getAllLinesIndexes(stringLines);
        Map<String, ArrayList<Integer>> invertedIndex = createInvertedIndex(stringLines);

        boolean activeMenu = true;
        int activeOption = -1;
        // Open CLI menu:

        while (activeMenu) {
            meneuCli();
            activeOption = scanner.nextInt();
            scanner.nextLine();
            switch (activeOption) {
                case 1:
                    findQuery(stringLines, invertedIndex, scanner, stringLinesIndexes);
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

    static ArrayList<Integer> getStrategy(Scanner scanner, Map<String, ArrayList<Integer>> invertedIndex, ArrayList<Integer> allLinesIndexes) {
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        String strategyName = scanner.nextLine();
        String query = scanner.nextLine();

        switch (strategyName) {
            case "ALL":
                return allStrategy(invertedIndex, query);
            case "NONE":
                return noneStrategy(invertedIndex, query, allLinesIndexes);
            case "ANY":
                return anyStrategy(invertedIndex, query);
            default:
                return new ArrayList<>(0);
        }
    }

    static ArrayList<Integer> anyStrategy(Map<String, ArrayList<Integer>> invertedIndex, String query) {
        List<String> queryWords = List.of(query.split(" "));
        Set<Integer> allResult = new HashSet<>();

        for (String word : queryWords) {
            List<Integer> indexes = invertedIndex.get(word);

            if (indexes != null) {
                allResult.addAll(indexes);
            }
        }

        return new ArrayList<>(allResult);
    }

    static ArrayList<Integer> allStrategy(Map<String, ArrayList<Integer>> invertedIndex, String query) {
        List<String> queryWords = List.of(query.split(" "));
        Set<Integer> allResult = new HashSet<>();

        for (String word : queryWords) {
            List<Integer> indexes = invertedIndex.get(word);

            if (indexes != null) {
                if (allResult.isEmpty()) {
                    allResult.addAll(indexes);
                } else {
                    allResult.retainAll(indexes);
                }
            } else {
                return new ArrayList<>(0);
            }
        }

        return new ArrayList<>(allResult);
    }

    static ArrayList<Integer> noneStrategy(Map<String, ArrayList<Integer>> invertedIndex, String query, ArrayList<Integer> allLinesIndexes) {
        ArrayList<Integer> anyResult = anyStrategy(invertedIndex, query);

        ArrayList<Integer> allIndexesCopy = new ArrayList<>(allLinesIndexes);
        allIndexesCopy.removeAll(anyResult);

        return allIndexesCopy;
    }

    private static void findQuery(ArrayList<String> data, Map<String, ArrayList<Integer>> invertedIndex, Scanner scanner, ArrayList<Integer> stringLinesIndexes) {
        ArrayList<Integer> indexes = getStrategy(scanner, invertedIndex, stringLinesIndexes);

        if (indexes.isEmpty()) {
            System.out.println("No matches found");
        } else {
            for (Integer wordIndex : indexes) {
                System.out.println(data.get(wordIndex));
            }
        }
    }

    private static void printAllData(ArrayList<String> data) {
        for (String line : data) {
            System.out.println(line);
        }
    }

    private static ArrayList<String> readFile(File inputFile) {
        ArrayList<String> dataLines = new ArrayList<>(5);

        try (Scanner scanner = new Scanner(inputFile)) {
            while (scanner.hasNext()) {
                dataLines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        return dataLines;
    }

    static Map<String, ArrayList<Integer>> createInvertedIndex(ArrayList<String> dataLines) {
        Map<String, ArrayList<Integer>> dataLinesMap = new HashMap<>();

        for (int i = 0; i < dataLines.size(); i++) {

            for (String word : dataLines.get(i).split(" ")) {
                String wordLowerCase = word.toLowerCase();
                if (dataLinesMap.get(wordLowerCase) == null) {
                    ArrayList<Integer> indexList = new ArrayList<>();
                    indexList.add(i);
                    dataLinesMap.put(wordLowerCase, indexList);
                } else {
                    dataLinesMap.get(wordLowerCase).add(i);
                }
            }
        }

        return dataLinesMap;
    }

    static ArrayList<Integer> getAllLinesIndexes(ArrayList<String> stringLines) {
        ArrayList<Integer> allLinesIndexes = new ArrayList<>(stringLines.size());

        for (int i = 0; i < stringLines.size(); i++) {
            allLinesIndexes.add(i);
        }

        return allLinesIndexes;
    }
}
