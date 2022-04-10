package qualification.printing;

import java.util.Scanner;

public class PrintingSolution {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(final String[] args) {
        final int T = scanner.nextInt();
        for (int testCaseIndex = 1; testCaseIndex <= T; ++testCaseIndex) {
            final String result = handleTestCase();
            System.out.printf("Case #%d: %s%n", testCaseIndex, result);
        }
    }

    public static String handleTestCase() {
        final int c1 = scanner.nextInt();
        final int m1 = scanner.nextInt();
        final int y1 = scanner.nextInt();
        final int k1 = scanner.nextInt();

        final int c2 = scanner.nextInt();
        final int m2 = scanner.nextInt();
        final int y2 = scanner.nextInt();
        final int k2 = scanner.nextInt();

        final int c3 = scanner.nextInt();
        final int m3 = scanner.nextInt();
        final int y3 = scanner.nextInt();
        final int k3 = scanner.nextInt();

        final int minC = Math.min(c1, Math.min(c2, c3));
        final int minM = Math.min(m1, Math.min(m2, m3));
        final int minY = Math.min(y1, Math.min(y2, y3));
        final int minK = Math.min(k1, Math.min(k2, k3));

        if (minC + minM + minY + minK < 1_000_000) {
            return "IMPOSSIBLE";
        }

        int remaining = 1_000_000;
        final StringBuilder sb = new StringBuilder();
        sb.append(minC);
        remaining -= minC;
        if (minM >= remaining) {
            sb.append(" ").append(remaining).append(" 0 0");
        } else {
            sb.append(" ").append(minM);
            remaining -= minM;
            if (minY >= remaining) {
                sb.append(" ").append(remaining).append(" 0");
            } else {
                sb.append(" ").append(minY);
                remaining -= minY;
                sb.append(" ").append(remaining);
            }
        }
        return sb.toString();
    }
}
