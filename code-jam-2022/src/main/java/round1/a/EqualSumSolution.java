package round1.a;

import java.util.*;
import java.util.stream.Collectors;

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

//        final Map<Long, List<Long>> sums = new HashMap<>();
//        sums.put(0L, Collections.emptyList());

        Collections.sort(numbers);


//        for (Long number : numbers) {
//            final Map<Long, List<Long>> dummySums = new HashMap<>();
//
//            for (Map.Entry<Long, List<Long>> entry : sums.entrySet()) {
//                final long newValue = number + entry.getKey();
//                if (!sums.containsKey(newValue)) {
//                    final List<Long> subSums = new ArrayList<>(entry.getValue());
//                    subSums.add(number);
//                    dummySums.put(newValue, subSums);
//                }
//
//                if (newValue == halfSum) {
//                    final String result = dummySums.get(newValue).stream().map(Object::toString).collect(Collectors.joining(" "));
//                    System.out.println(result);
//                    return;
//                }
//            }
//
//            sums.putAll(dummySums);
//        }
        final Map<Long, Sum> sums = new HashMap<>();
        sums.put(0L, new Sum(null, 0L));
        for (final Long number : numbers) {
            final Map<Long, Sum> dummySums = new HashMap<>();
            for (Map.Entry<Long, Sum> entry : sums.entrySet()) {
                final long newValue = number + entry.getKey();
                if (!sums.containsKey(newValue)) {
                    dummySums.put(newValue, new Sum(entry.getValue(), number));
                }

                if (newValue == halfSum) {
                    final String result = String.format("%d %s", number, entry.getValue().print());
                    System.out.println(result);
                    return;
                }
            }

            sums.putAll(dummySums);
        }
    }

    public static class Sum {
        private final long sum;
        private final Sum previousSubSum;

        public Sum(final Sum previousSubSum, final long newElement) {
            if (previousSubSum == null) {
                this.sum = newElement;
            } else {
                this.sum = previousSubSum.sum + newElement;
            }
            this.previousSubSum = previousSubSum;
        }

        public String print() {
            if (previousSubSum == null) {
                return String.format("%d", sum);
            } else {
                return String.format("%d %s", sum, previousSubSum.print());
            }
        }
    }
}
