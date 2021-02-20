package Flashcard;

public class Main {
    public static void main(String[] args) {
        int action, status = 0;
        String saveFile = "";
        Menu menu = new Menu();
        Flashcards flashcards = new Flashcards();
        for (int i = 0; i < args.length; i+=2) {
            if ("-import".equals(args[i])) {
                flashcards.fileOperation(3, 1, args[i+1]);
            } else {
                status = 1;
                saveFile = args[i+1];
            }
        }
        boolean bool = true;
        menu.createHashMap();
        while (bool) {
            action = menu.message();
            switch (action) {
                case 1 : {
                    flashcards.addCard();
                    break;
                }
                case 2 : {
                    flashcards.removeCard();
                    break;
                }
                case 3 :
                case 4 :
                case 7 : {
                    flashcards.fileOperation(action, 0, "");
                    break;
                }
                case 5 : {
                    flashcards.recallCards();
                    break;
                }
                case 6 : {
                    System.out.println("Bye bye!");
                    if (status == 1) {
                        flashcards.fileOperation(4,1, saveFile);
                    }
                    bool = false;
                    break;
                }
                case 8 : {
                    flashcards.hardestCard();
                    break;
                }
                case 9 : {
                    flashcards.resetStats();
                    break;
                }
            }
        }
    }
}

