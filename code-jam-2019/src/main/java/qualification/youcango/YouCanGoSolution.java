package qualification.youcango;

import java.util.Scanner;

public class YouCanGoSolution {

    private static final char EAST = 'E';
    private static final char SOUTH = 'S';

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(final String[] args) {
        final int testCaseCount = scanner.nextInt();
        scanner.nextLine();
        for (int testCaseIndex = 1; testCaseIndex <= testCaseCount; ++testCaseIndex) {
            scanner.nextInt();
            scanner.nextLine();
            final String lydiaDirections = scanner.nextLine();
            final String result = compute(lydiaDirections);
            System.out.printf("Case #%d: %s%n", testCaseIndex, result);
        }
    }

    public static String compute(final String lydiaDirections) {
        final StringBuilder ownDirections = new StringBuilder();
        for (char lydiaDirection : lydiaDirections.toCharArray()) {
            if (lydiaDirection == EAST) {
                ownDirections.append(SOUTH);
            } else {
                ownDirections.append(EAST);
            }
        }

        return ownDirections.toString();
    }
}
