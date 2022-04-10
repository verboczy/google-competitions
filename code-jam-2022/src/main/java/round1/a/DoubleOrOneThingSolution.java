package round1.a;

import java.util.Scanner;

public class DoubleOrOneThingSolution {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(final String[] args) {
        final int T = scanner.nextInt();
        scanner.nextLine();
        for (int testCaseIndex = 1; testCaseIndex <= T; ++testCaseIndex) {
            final String word = scanner.nextLine();
            final String result = handleTestCase("0" + word);
            System.out.printf("Case #%d: %s%n", testCaseIndex, result);
        }
    }

    public static String handleTestCase(final String originalWord) {
        final char[] charArray = originalWord.toCharArray();

        final StringBuilder resultBuilder = new StringBuilder();

        for (int i = 1; i <= charArray.length - 2; i++) {
            final char currentChar = charArray[i];
            final char nextChar = charArray[i + 1];

            resultBuilder.append(currentChar);

            if (currentChar == nextChar) {
                if (shouldDuplicate(charArray, i)) {
                    resultBuilder.append(currentChar);
                }
            } else if (currentChar < nextChar) {
                resultBuilder.append(currentChar);
            }
        }

        resultBuilder.append(charArray[charArray.length - 1]);

        return resultBuilder.toString();
    }

    public static boolean shouldDuplicate(final char[] charArray, final int i) {
        final char currentChar = charArray[i];
        if (i + 1 == charArray.length - 1) {
            // Ending character. Don't duplicate.

            // A (B) B
            return false;
        } else {
            int j = i + 2;
            while (j < charArray.length) {
                if (currentChar < charArray[j]) {
                    // A (B) B C
                    return true;
                } else if (currentChar > charArray[j]) {
                    // A (B) B A
                    return false;
                }
                j++;
            }
        }
        // A (B) B B
        return false;
    }
}
