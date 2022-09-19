package f;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SortTheFabricsSolution {

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
            final int fabricCount = scanner.nextInt();
            scanner.nextLine();

            List<String> lines = new ArrayList<>(fabricCount);

            for (int i = 0; i < fabricCount; i++) {
                lines.add(scanner.nextLine());
            }

            lines.sort(Comparator.comparingInt((line) -> Integer.parseInt(line.split(" ")[2])));
            List<String> adaList = new ArrayList<>(lines);
            List<String> charlesList = new ArrayList<>(lines);

            adaList.sort(Comparator.comparingInt((line) -> Integer.parseInt(line.split(" ")[1])));
            charlesList.sort(Comparator.comparing((line) -> line.split(" ")[0]));

            int samePositionCount = 0;
            for (int i = 0; i < fabricCount; i++) {
                if (adaList.get(i).equals(charlesList.get(i))) {
                    samePositionCount++;
                }
            }

            return String.format("Case #%d: %s", testCaseIndex, samePositionCount);
        }
    }
}
