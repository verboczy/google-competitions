package qualification.punchedcards;

import java.util.Scanner;

public class PunchedCardsSolution {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(final String[] args) {
        final int T = scanner.nextInt();
        for (int testCaseIndex = 1; testCaseIndex <= T; ++testCaseIndex) {
            final String result = handleTestCase();
            System.out.printf("Case #%d: \n%s%n", testCaseIndex, result);
        }
    }

    public static String handleTestCase() {
        final int R = scanner.nextInt();
        final int C = scanner.nextInt();

        final String firstSeparatorRow = createFirstSeparatorRow(C);
        final String firstRow = createFirstRow(C);

        final String separatorRow = createSeparatorRow(C);
        final String row = createRow(C);

        final StringBuilder sb = new StringBuilder();
        sb.append(firstSeparatorRow).append("\n").append(firstRow).append("\n");

        for (int i = 1; i <= R - 1; i++) {
            sb.append(separatorRow).append("\n");
            sb.append(row).append("\n");
        }
        sb.append(separatorRow);
        return sb.toString();
    }

    public static String createFirstSeparatorRow(final int C) {
        return ".." + createSeparatorRow(C - 1);
    }

    public static String createSeparatorRow(final int C) {
        final StringBuilder sb = new StringBuilder("+");
        for (int i = 1; i <= C; i++) {
            sb.append("-+");
        }
        return sb.toString();
    }

    public static String createFirstRow(final int C) {
        return ".." + createRow(C - 1);
    }

    public static String createRow(final int C) {
        final StringBuilder sb = new StringBuilder("|");
        for (int i = 1; i <= C; i++) {
            sb.append(".|");
        }
        return sb.toString();
    }

}
