package Flashcard;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

class Flashcards {
    Map<String, String> flashcards;
    Map<String, Integer> mistakeCards;
    Menu menu;
    Scanner scanner;
    char ch = '"';
    String term, definition;

    Flashcards() {
        flashcards = new LinkedHashMap<>();
        mistakeCards = new HashMap<>();
        menu = new Menu();
        scanner = new Scanner(System.in);
    }

    void addCard() {
        String temp = "";
        System.out.println("The card:");
        temp += ("The card:" + "\n");
        term = scanner.nextLine();
        temp += (term + "\n");
        if (flashcards.containsKey(term)) {
            System.out.println("The card " + ch + term + ch + " already exists.");
            temp += ("The card " + ch + term + ch + " already exists." + "\n");
        } else {
            System.out.println("The definition of the card:");
            temp += ("The definition of the card:" + "\n");
            definition = scanner.nextLine();
            temp += (definition + "\n");
            if (flashcards.containsValue(definition)) {
                System.out.println("The definition " + ch + definition + ch + " already exists.");
                temp += ("The definition " + ch + definition + ch + " already exists." + "\n");
            } else {
                flashcards.put(term, definition);
                mistakeCards.put(term, 0);
                System.out.println("The pair (" + ch + term + ch + ":" + ch + definition + ch + ") has been added.");
                temp += ("The pair (" + ch + term + ch + ":" + ch + definition + ch + ") has been added." + "\n");
            }
        }
        Menu.receiveLog(temp);
    }

    void removeCard() {
        String temp = "";
        System.out.println("Which card?");
        temp += ("Which card?" + "\n");
        term = scanner.nextLine();
        temp += (term + "\n");
        if (flashcards.containsKey(term)) {
            flashcards.remove(term);
            mistakeCards.remove(term);
            System.out.println("The card has been removed.");
            temp += ("The card has been removed." + "\n");
        } else {
            System.out.println("Can't remove " + ch + term + ch + ": there is no such card.");
            temp += ("Can't remove " + ch + term + ch + ": there is no such card." + "\n");
        }
        Menu.receiveLog(temp);
    }

    void fileOperation(int choice, int status, String str) {
        String temp ="";
        if (status == 0) {
            System.out.println("File name:");
            temp += ("File name:" + "\n");
            term = scanner.nextLine();
            temp += (term + "\n");
            Menu.receiveLog(temp);
        } else {
            term = str;
        }
        File file = new File(term);
        temp = "";
        if (choice == 3) {
            try {
                menu.readFromFile(file, flashcards, mistakeCards);
            } catch (IOException e) {
                System.out.println("File not found.");
                temp += ("File not found." + "\n");
            }
        } else if (choice == 4){
            try {
                menu.writeToFile(term, flashcards, mistakeCards);
            }catch (IOException e) {
                System.out.println("Failed to create a file in the directory.");
            }
        }
        else {
            try {
                menu.saveLog(term);
            }catch (IOException e) {
                System.out.println("Failed to create a file in the directory.");
            }
        }
        Menu.receiveLog(temp);
    }

    void recallCards() {
        String temp = "", answer;
        System.out.println("How many times to ask?");
        temp += ("How many times to ask?" + "\n");
        int n = Integer.parseInt(scanner.nextLine());
        temp += (n + "\n");
        for (Map.Entry<String, String> var1 : flashcards.entrySet()) {
            if (n == 0) {
                break;
            }
            System.out.println("Print the definition of " + ch + var1.getKey() + ch + ":");
            temp += ("Print the definition of " + ch + var1.getKey() + ch + ":" + "\n");
            answer = scanner.nextLine();
            temp += (answer + "\n");
            if (var1.getValue().equals(answer)) {
                System.out.println("Correct!");
                temp += ("Correct!" + "\n");
            } else {
                mistakeCards.put(var1.getKey(), mistakeCards.get(var1.getKey())+1);
                System.out.print("Wrong. The right answer is " + ch + var1.getValue() + ch);
                temp += ("Wrong. The right answer is " + ch + var1.getValue() + ch);
                if (flashcards.containsValue(answer)) {
                    for (String key : flashcards.keySet()) {
                        if (flashcards.get(key).equals(answer)) {
                            System.out.println(", but your definition is correct for " + ch + key + ch + ".");
                            temp += (", but your definition is correct for " + ch + key + ch + "." + "\n");
                            break;
                        }
                    }
                } else {
                    System.out.println(".");
                    temp += ("." + "\n");
                }
            }
            n--;
        }
        Menu.receiveLog(temp);
    }

    void hardestCard() {
        String temp = "";
        int hardest = Integer.MIN_VALUE, num = 0;
        for (Map.Entry<String, Integer> var1 : mistakeCards.entrySet()) {
            if (hardest < var1.getValue()) {
                hardest = var1.getValue();
                num = 1;
            } else if (hardest == var1.getValue()) {
                num++;
            }
        }
        if (hardest > 0) {
            System.out.print((num == 1) ? "The hardest card is " : "The hardest cards are ");
            temp += (((num == 1) ? "The hardest card is " : "The hardest cards are "));
            String end = (num == 1) ? "it." : "them.";
            for (Map.Entry<String, Integer> var1 : mistakeCards.entrySet()) {
                if (hardest == var1.getValue()) {
                    if (num == 1) {
                        System.out.println(ch + var1.getKey() + ch + ". You have " + var1.getValue() + " errors answering " + end);
                        temp += (ch + var1.getKey() + ch + ". You have " + var1.getValue() + " errors answering " + end + "\n");
                    } else {
                        System.out.print(ch + var1.getKey() + ch + ",");
                        temp += (ch + var1.getKey() + ch + ", ");
                        num--;
                    }
                }
            }
        } else {
            System.out.println("There are no cards with errors.");
            temp += ("There are no cards with errors." + "\n");
        }
        Menu.receiveLog(temp);
    }

    void resetStats() {
        String temp = "";
        for (Map.Entry<String, Integer> var1 : mistakeCards.entrySet()) {
            var1.setValue(0);
        }
        System.out.println("Card statistics have been reset.");
        temp += ("Card statistics have been reset." + "\n");
        Menu.receiveLog(temp);
    }

}
