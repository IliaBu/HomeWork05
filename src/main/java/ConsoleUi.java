import java.util.Scanner;

public class ConsoleUi {
    public static final int SIZE_LINE = 110;
    public static final Scanner scanner = new Scanner(System.in,"Cp866");
    private static final Menu menu = new MainMenu();
    private final MainMenu m = new MainMenu();
    private static boolean isWork = true;

    public static void start() {
        clearConsole();
        printLineWithSymbol("=", SIZE_LINE, Colors.YELLOW);
        printCaption("*** МОЙ 3D РЕДАКТОР ***", " ", Colors.GREEN);
        printLineWithSymbol("=", SIZE_LINE, Colors.YELLOW);
        printLineWithSymbol("-", SIZE_LINE, Colors.YELLOW);
        while (isWork) {
            print(menu.toString(), Colors.BLUE);
            menu.execute(getIntInput() - 1);
        }
    }

    public static void close() {
        scanner.close();
        isWork = false;
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[J");
    }

    private static void printLineWithSymbol(String symbol, int sizeLine, Colors colors) {
        printResetln(colors + symbol.repeat(sizeLine) + Colors.RESET);
    }

    private static void printCaption(String caption, String padSymb, Colors colors) {
        int spaceSize = (SIZE_LINE - caption.length()) / 2;
        String captionLine = padSymb.repeat(spaceSize) + caption + padSymb.repeat(spaceSize);
        printResetln(colors + captionLine + Colors.RESET);
    }

    public static void printResetln(String message) {
        System.out.println(message);
    }

    public static void println(String message, Colors colors) {
        System.out.println(colors + message + Colors.RESET);
    }

    public static void printf(String message1, Object message2, Colors colors) {
        System.out.printf(colors + message1 + Colors.RESET, message2);
    }

    public static void printf(String message1, Object message2, Object message3, Colors colors) {
        System.out.printf(colors + message1 + Colors.RESET, message2, message3);
    }

    public static void print(String message, Colors colors) {
        System.out.print(colors + message + Colors.RESET);
    }

    public static int getIntInput() {
        String input;
        do {
            print("> ", Colors.BLUE);
            input = scanner.nextLine();
            if (input.matches("\\d+")) {
                int number = Integer.parseInt(input);
                if (number >= 1 && number <= menu.size()) {
                    return number;
                }
            }
        } while (true);
    }
}
