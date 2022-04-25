package round1.b;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

public class ControlledInflationSolution {

    public static void main(final String[] args) {
        Solver solver = new Solver(new Scanner(System.in));
        solver.solve();
    }

    @Slf4j
    public static class Solver {
        private final Scanner scanner;
        private final Map<Node, Long> cache;

        public Solver(final Scanner scanner) {
            this.scanner = scanner;
            this.cache = new HashMap<>();
        }

        public String solve() {
            final int testCaseCount = scanner.nextInt();
            log.info("Test case count: {}", testCaseCount);
            final StringBuilder resultBuilder = new StringBuilder();
            for (int testCaseIndex = 1; testCaseIndex <= testCaseCount; ++testCaseIndex) {
                final String testCaseResult = handleTestCase(testCaseIndex);
                System.out.println(testCaseResult);
                resultBuilder.append(testCaseResult).append("\n");
                cache.clear();
            }
            return resultBuilder.toString();
        }

        private String handleTestCase(final int testCaseIndex) {
            final int customerCount = scanner.nextInt();
            final int productCount = scanner.nextInt();

            // Read first customer
            int firstProduct = 0;
            for (int productIndex = 0; productIndex < productCount; productIndex++) {
                final int product = scanner.nextInt();
                if (product > firstProduct) {
                    firstProduct = product;
                }
            }

            final List<Integer> products = new ArrayList<>();
            for (int customerIndex = 1; customerIndex < customerCount; customerIndex++) {
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;
                for (int productIndex = 0; productIndex < productCount; productIndex++) {
                    final int product = scanner.nextInt();
                    if (product > max) {
                        max = product;
                    }
                    if (product < min) {
                        min = product;
                    }
                }
                products.add(min);
                products.add(max);
            }

            final long result = compute(firstProduct, products, 0);

            return String.format("Case #%d: %d", testCaseIndex, result + firstProduct);
        }

        private long compute(final int root, final List<Integer> products, final int level) {
            if (level >= products.size()) {
                return 0;
            }

            final Integer leftProduct = products.get(level);
            final Integer rightProduct = products.get(level + 1);

            final Node node = new Node(level, root, leftProduct, rightProduct);
            if (cache.containsKey(node)) {
                return cache.get(node);
            } else {
                final int nextLevel = level + 2;

                final long leftComputed = compute(leftProduct, products, nextLevel);
                final long rightComputed = compute(rightProduct, products, nextLevel);

                final long leftCost = Math.abs(root - rightProduct) + leftComputed;
                final long rightCost = Math.abs(root - leftProduct) + rightComputed;

                final long result = Math.min(leftCost, rightCost) + Math.abs(rightProduct - leftProduct);

                cache.put(node, result);
                return result;
            }
        }
    }

    public static class Node {
        final int level;
        final int root;
        final int left;
        final int right;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return level == node.level && root == node.root && left == node.left && right == node.right;
        }

        @Override
        public int hashCode() {
            return Objects.hash(level, root, left, right);
        }

        public Node(int level, int root, int left, int right) {
            this.level = level;
            this.root = root;
            this.left = left;
            this.right = right;
        }
    }
}