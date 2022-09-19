package f;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

public class WaterContainerSystemSolution {


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
            final int containerCount = scanner.nextInt();
            final int queryCount = scanner.nextInt();

            Map<Integer, Set<Integer>> connections = new HashMap<>();
            // We always have container1.
            connections.put(1, new HashSet<>());

            for (int i = 0; i < containerCount - 1; i++) {
                int container1 = scanner.nextInt();
                int container2 = scanner.nextInt();
                addToMap(connections, container1, container2);
            }

            for (int i = 0; i < queryCount; i++) {
                // It doesn't matter, does it?
                scanner.nextInt();
            }

            Set<Integer> actualLevel = new HashSet<>();
            actualLevel.add(1);

            Set<Integer> previousLevel = new HashSet<>();

            Map<Integer, Integer> containerCountByLevel = new HashMap<>();
            int level = 1;
            containerCountByLevel.put(level, 1);

            boolean hasNextLevel = true;
            while (hasNextLevel) {
                level++;
                Set<Integer> nextLevel = getNextLevel(actualLevel, previousLevel, connections);
                if (nextLevel.isEmpty()) {
                    hasNextLevel = false;
                } else {
                    containerCountByLevel.put(level, nextLevel.size());
                }
                previousLevel = actualLevel;
                actualLevel = nextLevel;
            }

            int result = computeFullContainerCount(containerCountByLevel, queryCount);

            return String.format("Case #%d: %d", testCaseIndex, result);
        }

        private void addToMap(Map<Integer, Set<Integer>> connections, int container1, int container2) {
            if (!connections.containsKey(container1)) {
                addNewElementToList(connections, container1, container2);
            } else {
                addElementToList(connections, container1, container2);
            }

            if (!connections.containsKey(container2)) {
                addNewElementToList(connections, container2, container1);
            } else {
                addElementToList(connections, container2, container1);
            }
        }

        private void addNewElementToList(Map<Integer, Set<Integer>> connections, int key, int value) {
            Set<Integer> list = new HashSet<>();
            list.add(value);
            connections.put(key, list);
        }

        private void addElementToList(Map<Integer, Set<Integer>> connections, int key, int value) {
            connections.get(key).add(value);
        }

        private Set<Integer> getNextLevel(Set<Integer> actualLevel, Set<Integer> previousLevel, Map<Integer, Set<Integer>> connections) {
            Set<Integer> nextLevel = new HashSet<>();

            actualLevel.forEach((container) -> {
                Set<Integer> adjacentContainers = connections.get(container);
                adjacentContainers.removeAll(previousLevel);
                nextLevel.addAll(adjacentContainers);
            });

            return nextLevel;
        }

        private int computeFullContainerCount(Map<Integer, Integer> containerCountByLevel, int liter) {
            int count = 0;
            int level = 1;
            while (liter > 0) {
                Integer containerCount = containerCountByLevel.get(level);
                if (containerCount <= liter) {
                    count += containerCount;
                }
                liter -= containerCount;
                level++;
            }
            return count;
        }
    }
}
