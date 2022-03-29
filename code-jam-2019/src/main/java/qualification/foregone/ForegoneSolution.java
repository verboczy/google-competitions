package qualification.foregone;

import java.util.List;
import java.util.Scanner;

public class ForegoneSolution {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(final String[] args) {
        final int testCaseCount = scanner.nextInt();
        scanner.nextLine();
        for (int testCaseIndex = 1; testCaseIndex <= testCaseCount; ++testCaseIndex) {
            final String number = scanner.nextLine();
            final List<String> result = compute(number);
            System.out.printf("Case #%d: %s %s%n", testCaseIndex, result.get(0), result.get(1));
        }
    }

    public static List<String> compute(final String numberString) {
        final StringBuilder part1 = new StringBuilder();
        final StringBuilder part2 = new StringBuilder();

        for (final char character : numberString.toCharArray()) {
            if (character == '4') {
                part1.append("3");
                part2.append("1");
            } else {
                part1.append(character);
                if (part2.length() > 0) {
                    part2.append("0");
                }
            }
        }

        return List.of(part1.toString(), part2.toString());
    }
}
