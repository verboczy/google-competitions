package round1.a;

import java.util.*;

public class EqualSumSolution {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final int testCaseCount = scanner.nextInt();
        for (int testCaseIndex = 1; testCaseIndex <= testCaseCount; ++testCaseIndex) {
            handleTestCase();
        }
    }

    public static void handleTestCase() {
        // Phase 1
        final int N = scanner.nextInt();

        // Phase 2
        long sum = 0;
        final List<Long> numbers = new ArrayList<>(2 * N);

        final StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sum += i;
            numbers.add((long) i);
            sb.append(i).append(" ");
        }
        System.out.println(sb.toString().trim());

        // Phase 3
        for (int i = 1; i <= N; i++) {
            final int number = scanner.nextInt();
            if (number == -1) {
                throw new IllegalStateException("Something went wrong, the judge responded with -1.");
            }
            sum += number;
            numbers.add((long) number);
        }

        if (sum % 2 != 0) {
            throw new IllegalStateException("Something went wrong, the sum is not even.");
        }

        final long halfSum = sum / 2;

        Collections.sort(numbers);

        final Map<Long, Sum> sums = new HashMap<>();

        for (final Long number : numbers) {
            final Map<Long, Sum> dummySums = new HashMap<>();
            if (sums.isEmpty()) {
                dummySums.put(number, new Sum(number, null, null));
            }

            for (Map.Entry<Long, Sum> entry : sums.entrySet()) {
                final Long previousSum = entry.getKey();
                final long newSum = number + previousSum;
                if (!sums.containsKey(newSum)) {
                    final Sum previousSubSum = entry.getValue();
                    dummySums.put(newSum, new Sum(newSum, previousSubSum, new Sum(number, null, null)));
                }

                if (newSum == halfSum) {
                    System.out.println(dummySums.get(newSum).print());
                    return;
                }
            }

            sums.putAll(dummySums);
        }
    }

    public static class Sum {
        private final long value;
        private final Sum left;
        private final Sum right;

        public Sum(final long value, final Sum left, final Sum right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public String print() {
            if (left == null || right == null) {
                return String.valueOf(value);
            }

            return String.format("%s %s", left.print(), right.print());
        }
    }
}
