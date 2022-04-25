package round1.b;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PancakeDequeSolution {

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
            final int pancakeCount = scanner.nextInt();

            final List<Integer> pancakes = new ArrayList<>(pancakeCount);

            for (int i = 0; i < pancakeCount; i++) {
                final int pancakeDeliciousness = scanner.nextInt();
                pancakes.add(pancakeDeliciousness);
            }

            int leftIndex = 0;
            int rightIndex = pancakeCount - 1;

            int sum = 0;
            int max = -1;

            while (leftIndex <= rightIndex) {
                final int leftPancake = pancakes.get(leftIndex);
                final int rightPancake = pancakes.get(rightIndex);

                if (leftPancake > rightPancake) {
                    // Choose right
                    rightIndex--;
                    if (rightPancake >= max) {
                        max = rightPancake;
                        sum++;
                    }
                } else {
                    // Choose left
                    leftIndex++;
                    if (leftPancake >= max) {
                        max = leftPancake;
                        sum++;
                    }
                }
            }

            return String.format("Case #%d: %d", testCaseIndex, sum);
        }
    }
}
