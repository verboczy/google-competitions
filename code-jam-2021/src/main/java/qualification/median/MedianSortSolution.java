package qualification.median;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

// Test Set 1: Passed
// Test Set 2: Passed
// Test Set 3: Passed
public class MedianSortSolution {

    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    private static int N; // Size of the list to be ordered.
    private static int Q; // Number of possible questions.
    private static List<Integer> list;

    public static void main(String[] args) {
        final int testCaseCount = scanner.nextInt();
        N = scanner.nextInt();
        Q = scanner.nextInt();
        // Initialize list with the known capacity. +1 because of the padding in the beginning.
        list = new ArrayList<>(N);
        for (int testCaseIndex = 1; testCaseIndex <= testCaseCount; ++testCaseIndex) {
            final String result = handleTestCase();
            System.out.println(result);
            int judgement = scanner.nextInt();
            if (judgement == -1) {
                System.exit(-1);
            }
            list.clear(); // Reset the list between test cases.
        }
    }

    private static String handleTestCase() {
        final int startA = 1;
        final int startB = 2;
        final int startC = 3;
        final int startMedian = question(startA, startB, startC);
        switch (startMedian) {
            case startA:
                list.add(startB);
                list.add(startA);
                list.add(startC);
                break;
            case startB:
                list.add(startA);
                list.add(startB);
                list.add(startC);
                break;
            case startC:
                list.add(startA);
                list.add(startC);
                list.add(startB);
                break;
            default:
                System.exit(-1);
        }

        for (int element = 4; element <= N; ++element) {
            final int middleIndex = (list.size() - 1) / 2;

            final int aIndex = middleIndex;
            final int bIndex = middleIndex + 1;
            final int a = list.get(aIndex);
            final int b = list.get(bIndex);
            final int c = element;
            final int median = question(a, b, c);

            if (median == a) {
                // The new value is the smallest of the three.
                // E.g.: 4123; 123 + 234->2 => 1423 or 4123
                final int lowerBound = 0;
                final int upperBound = middleIndex - 1;
                binarySearch(element, lowerBound, upperBound);
            } else if (median == b) {
                // The new value is the greatest of the three.
                // E.g.: 1234; 123 + 234->3 => 1234
                final int lowerBound = middleIndex + 2;
                final int upperBound = list.size() - 1; // -1 not to overindex the list
                if (lowerBound > upperBound) {
                    // Only possible if it is the last element.
                    list.add(element);
                } else {
                    binarySearch(element, lowerBound, upperBound);
                }
            } else if (median == c) {
                // The new value is in between.
                // E.g.: 1243; 123 + 234->4 => 1243
                list.add(bIndex, element);
            } else {
                System.exit(-2);
            }
        }

        return list.stream().map(Object::toString).collect(Collectors.joining(" "));
    }

    private static void binarySearch(final int element, final int lowerBound, final int upperBound) {
        if (lowerBound == upperBound) {
            // Meaning we only have to check one element.
            final int bound = lowerBound;
            // We are at the end of the list, careful with indexes
            if (bound == list.size() - 1) {
                final int aIndex = list.size() - 2;
                final int bIndex = list.size() - 1;
                final int a = list.get(aIndex);
                final int b = list.get(bIndex);
                final int c = element;
                final int median = question(a, b, c);
                if (median == a) {
                    list.add(aIndex, element);
                } else if(median == b) {
                    list.add(element);
                } else if (median == c) {
                    list.add(bIndex, element);
                } else {
                    System.exit(-10);
                }
            } else {
                final int aIndex = bound;
                final int bIndex = bound + 1;
                final int a = list.get(aIndex);
                final int b = list.get(bIndex);
                final int c = element;
                final int median = question(a, b, c);
                if (median == a) {
                    list.add(aIndex, element);
                } else if(median == b) {
                    list.add(bIndex + 1, element);
                } else if (median == c) {
                    list.add(bIndex, element);
                } else {
                    System.exit(-11);
                }
            }
        } else if (upperBound - lowerBound == 1) {
            // No more iteration is needed, just check these 3 elements
            final int a = list.get(lowerBound);
            final int b = list.get(upperBound);
            final int c = element;
            final int median = question(a, b, c);
            if (median == a) {
                list.add(lowerBound, element);
            } else if (median == b) {
                list.add(upperBound + 1, element);
            } else if (median == c) {
                list.add(upperBound, element);
            } else {
                System.exit(-12);
            }
        } else {
            final int middleIndex = (lowerBound + upperBound) / 2;

            final int a = list.get(middleIndex);
            final int b = list.get(middleIndex + 1);
            final int c = element;
            final int median = question(a, b, c);

            if (median == a) {
                binarySearch(element, lowerBound, middleIndex - 1);
            } else if (median == b) {
                if (middleIndex + 2 > upperBound) {
                    list.add(middleIndex + 2, element);
                } else {
                    binarySearch(element, middleIndex + 2, upperBound);
                }
            } else if (median == c) {
                list.add(middleIndex + 1, element);
            } else {
                System.exit(-13);
            }
        }
    }

    private static int question(int a, int b, int c) {
        System.out.printf("%d %d %d%n", a, b, c);
        int i = scanner.nextInt();
        if (i == -1) {
            System.exit(-1);
        }
        return i;
    }
}

