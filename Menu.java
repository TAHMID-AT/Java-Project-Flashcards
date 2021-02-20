package Flashcard;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Menu {
    String input;
    Map<String, Integer> choice = new HashMap<>();
    static String stringBuilder = "";


    void createHashMap() {
        choice.put("add", 1);
        choice.put("remove", 2);
        choice.put("import", 3);
        choice.put("export", 4);
        choice.put("ask", 5);
        choice.put("exit", 6);
        choice.put("log", 7);
        choice.put("hardest card", 8);
        choice.put("reset stats", 9);
    }

    int message() {
        Scanner scanner = new Scanner(System.in);
        String str = "Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):";
        System.out.println(str);
        stringBuilder += (str + "\n");
        input = scanner.nextLine();
        stringBuilder += (input + "\n");
        return choice.get(input);
    }

    public void readFromFile(File file, Map<String, String> flashcards, Map<String, Integer> mistakeCards) throws IOException {
        Scanner fileScanner = new Scanner(file);
        String line;
        int cards = 0;
        while (fileScanner.hasNextLine()) {
            line = fileScanner.nextLine();
            cards++;
            String[] arr = line.split("(?<=\\D)(?=\\d)");
            String[] city = arr[0].split("\t");
            if (city.length != 2) {
                city = arr[0].split("   ");
            }
            city[0]=city[0].trim();
            city[1] = city[1].trim();
            flashcards.put(city[0], city[1]);
            mistakeCards.put(city[0], Integer.parseInt(arr[1]));
        }
        fileScanner.close();
        System.out.println(cards + " cards have been loaded.");
        stringBuilder += (cards + " cards have been loaded." + "\n");
    }

    public void writeToFile(String file, Map<String, String> flashcards, Map<String, Integer> mistakeCards) throws IOException {
        int cards = 0;
        PrintWriter printWriter = new PrintWriter(file);
        for (Map.Entry<String, String > var1 : flashcards.entrySet()) {
            printWriter.printf("%s\t%s\t%d\n", var1.getKey(), var1.getValue(), mistakeCards.get(var1.getKey()));
            cards++;
        }
        printWriter.close();
        System.out.println(cards + " cards have been saved.");
        stringBuilder += (cards + " cards have been saved." + "\n");
    }

    void saveLog(String file) throws IOException{
        PrintWriter printWriter = new PrintWriter(file);
        System.out.println("The log has been saved.");
        stringBuilder += ("The log has been saved.");
        printWriter.printf("%s", stringBuilder);
        printWriter.close();
    }

    static void receiveLog(String str) {
        stringBuilder += (str);
    }
}
