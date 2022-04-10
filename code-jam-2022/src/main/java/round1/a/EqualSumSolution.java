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
        if (N == -1) {
            throw new IllegalStateException("Something went wrong, the judge responded with -1.");
        }

        // Phase 2
        final List<Long> ownNumbers = new ArrayList<>(N);
        final StringBuilder numbersResponse = new StringBuilder();
        for (long number = 0; number < N; number++) {
            final long value;
            if (number < 30) {
                value = (long) Math.pow(2.0, number);
            } else {
                value = getRandomValue(ownNumbers);
            }
            numbersResponse.append(value).append(" ");
            ownNumbers.add(value);
        }
        System.out.println(numbersResponse);

        // Phase 3
        final Partition partition1 = new Partition(new LinkedList<>());
        final Partition partition2 = new Partition(new LinkedList<>());

        for (int i = 1; i <= N; i++) {
            final int number = scanner.nextInt();
            addToPartition(partition1, partition2, number);
        }

        for (final Long number : ownNumbers) {
            addToPartition(partition1, partition2, number);
        }

        while (partition1.getSum() != partition2.getSum()) {
            if (partition1.getSum() > partition2.getSum()) {
                final long minElement = partition1.removeFirstElement();
                partition2.addElement(minElement);
            } else {
                final long minElement = partition2.removeFirstElement();
                partition1.addElement(minElement);
            }
        }

        System.out.println(partition1.print());
    }

    private static void addToPartition(final Partition partition1, final Partition partition2, final long number) {
        if (partition1.getSum() <= partition2.getSum()) {
            partition1.addElement(number);
        } else {
            partition2.addElement(number);
        }
    }

    public static long getRandomValue(final List<Long> list) {
        final Random random = new Random();
        long randomValue = Math.abs(random.nextLong()) % 1_000_000_000L;
        while (list.contains(randomValue)) {
            randomValue = Math.abs(random.nextLong()) % 1_000_000_000L;
        }
        return randomValue;
    }

    public static class Partition {
        long sum;
        final List<Long> elements;

        public Partition(List<Long> elements) {
            this.sum = 0L;
            this.elements = elements;
        }

        public void addElement(final long element) {
            sum += element;
            elements.add(element);
            Collections.sort(elements);
        }

        public long removeFirstElement() {
            final int firstIndex = 0;
            final Long firstElement = elements.get(firstIndex);
            sum -= firstElement;
            elements.remove(firstIndex);
            return firstElement;
        }

        public long getSum() {
            return sum;
        }

        public String print() {
            return elements.stream().map(Objects::toString).collect(Collectors.joining(" "));
        }
    }
}
