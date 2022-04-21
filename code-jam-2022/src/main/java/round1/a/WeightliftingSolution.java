package round1.a;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

public class WeightliftingSolution {

    public static void main(final String[] args) {
        Solver solver = new Solver(new Scanner(System.in));
        solver.solve();
    }

    @Slf4j
    public static class Solver {
        private final Scanner scanner;

        public Solver(final Scanner scanner) {
            this.scanner = scanner;
        }

        public String solve() {
            final int testCaseCount = scanner.nextInt();
            log.info("Test case count: {}", testCaseCount);
            final StringBuilder resultBuilder = new StringBuilder();
            for (int testCaseIndex = 1; testCaseIndex <= testCaseCount; ++testCaseIndex) {
                final String testCaseResult = handleTestCase(testCaseIndex);
                System.out.println(testCaseResult);
                resultBuilder.append(testCaseResult).append("\n");
            }
            return resultBuilder.toString();
        }

        private String handleTestCase(final int testCaseIndex) {
            final int solution = 1;

            return String.format("Case #%d: %d", testCaseIndex, solution);
        }
    }
}
