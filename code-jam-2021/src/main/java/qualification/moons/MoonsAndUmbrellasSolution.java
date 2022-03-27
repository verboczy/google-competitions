package qualification.moons;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Samples: Passed
// Test Set 1: Passed
// Test Set 2: Passed
// Test Set 3: Passed
public class MoonsAndUmbrellasSolution {
    private static final Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

    private static final char CODY = 'C';
    private static final char JAMAL = 'J';
    private static final char UNDEFINED = '?';
    private static final char SPEC_CHAR = 'X';

    private static int X = 0; // CJ copyright
    private static int Y = 0; // JC copyright

    public static void main(String[] args) {
        final int testCaseCount = Integer.parseInt(scanner.nextLine());
        for (int testCaseIndex = 1; testCaseIndex <= testCaseCount; ++testCaseIndex) {
            final int result = handleTestCase2();
            System.out.printf("Case #%d: %d%n", testCaseIndex, result);
        }
    }

    private static int handleTestCase2() {
        final String line = scanner.nextLine();
        final String[] words = line.split(" ");
        X = Integer.parseInt(words[0]);
        Y = Integer.parseInt(words[1]);
        final String mural = words[2];

        final char[] characters = new char[mural.length() + 2];
        characters[0] = SPEC_CHAR;
        characters[characters.length - 1] = SPEC_CHAR;

        final char[] chars = mural.toCharArray();
        System.arraycopy(chars, 0, characters, 1, chars.length);

        int sum = 0;
        for (int i = 1; i < characters.length - 1; ++i) {
            if (characters[i] == UNDEFINED) {
                int nextNonUndefined = findNextNonUndefined(i + 1, characters);
                sum += calculateMinimum(i - 1, nextNonUndefined, characters);
                i = nextNonUndefined;
            } else {
                sum += copyrightAmount(characters[i - 1], characters[i]);
            }
        }

        return sum;
    }

    private static int calculateMinimum(int start, int end, char[] characters) {
        List<Integer> amounts = new ArrayList<>();

        // Do nothing
        amounts.add(copyrightAmount(characters[start], characters[end]));
        // Alternate
        amounts.add(calculateAlternate(start, end, characters));
        // Add one
        amounts.add(calculateAddOne(start, end, characters));

        return amounts.stream().min(Integer::compareTo).get();
    }

    private static int calculateAddOne(int start, int end, char[] characters) {
        if (characters[start] == SPEC_CHAR && characters[end] == SPEC_CHAR) {
            if (end - start > 2) {
                // X??+X
                return Math.min(X, Y);
            } else {
                // X?X
                return 0;
            }
        } else if (characters[start] == SPEC_CHAR) {
            // X?+C || X?+J
            return characters[end] == CODY ? Y : X;
        } else if (characters[end] == SPEC_CHAR) {
            // C?+X || J?+X
            return characters[start] == CODY ? X : Y;
        } else {
            // Only both can be added in this case. That is like alternating.
            // C?+C || J?+J || C?+J || J?+C
            return copyrightAmount(characters[start], characters[end]);
        }
    }

    private static int calculateAlternate(int start, int end, char[] characters) {
        // [0]..[2] - length = 3, because its all inclusive
        final int length = end - start + 1;
        final int halfLength = length / 2;
        if (characters[start] == SPEC_CHAR && characters[end] == SPEC_CHAR) {
            if (length == 3) {
                // X?X
                return 0;
            } else if (length == 4) {
                // X??X
                return Math.min(X, Y);
            } else {
                if (length % 2 != 0) {
                    // X(??)+?X
                    final int skipAlternate = Math.min((halfLength - 2) * X + (halfLength - 1) * Y, (halfLength - 1) * X + (halfLength - 2) * Y);
                    final int notSkip = (halfLength - 1) * X + (halfLength - 1) * Y;
                    return Math.min(notSkip, skipAlternate);
                } else {
                    // X??(??)+X
                    final int skipAlternate = (halfLength - 2) * X + (halfLength - 2) * Y;
                    final int notSkip = Math.min((halfLength - 2) * X + (halfLength - 1) * Y, (halfLength - 1) * X + (halfLength - 2) * Y);
                    return Math.min(notSkip, skipAlternate);
                }
            }
        } else if (characters[start] == SPEC_CHAR) {
            if (characters[end] == CODY) {
                if (length == 3) {
                    return Math.min(0, Y);
                } else if (length % 2 != 0) {
                    // (??)+?C
                    int countX = halfLength - 1;
                    return Math.min(countX * X + halfLength * Y, countX * X + (halfLength - 1) * Y);
                } else {
                    // (??)+C
                    int countY = halfLength - 1;
                    return Math.min((halfLength - 1) * X + countY * Y, (halfLength - 2) * X + countY * Y);
                }
            } else if (characters[end] == JAMAL) {
                if (length == 3) {
                    return Math.min(0, X);
                } else if (length % 2 != 0) {
                    // (??)+?J
                    int countY = halfLength - 1;
                    return Math.min(halfLength * X + countY * Y, (halfLength - 1) * X + countY * Y);
                } else {
                    // (??)+J
                    int countX = halfLength - 1;
                    return Math.min(countX * X + (halfLength - 1) * Y, countX * X + (halfLength - 2) * Y);
                }
            }
        } else if (characters[end] == SPEC_CHAR) {
            if (characters[start] == CODY) {
                if (length == 3) {
                    return Math.min(0, X);
                } else if (length % 2 != 0) {
                    // C(??)+?
                    int countY = halfLength - 1;
                    return Math.min((halfLength - 1) * X + countY * Y, halfLength * X + countY * Y);
                } else {
                    // C(??)+
                    int countX = halfLength - 1;
                    return Math.min(countX * X + (halfLength - 2) * Y, countX * X + (halfLength - 1) * Y);
                }
            } else if (characters[start] == JAMAL) {
                if (length == 3) {
                    return Math.min(0, Y);
                } else if (length % 2 != 0) {
                    // J(??)+?
                    int countX = halfLength - 1;
                    return Math.min(countX * X + (halfLength - 1) * Y, countX * X + halfLength * Y);
                } else {
                    // J(??)+
                    int countY = halfLength - 1;
                    return Math.min((halfLength - 2) * X + countY * Y, (halfLength - 1) * X + countY * Y);
                }
            }
        } else {
            if (characters[start] == characters[end]) {
                if (length % 2 != 0) {
                    // C(??)*?C || J(??)*?J
                    return halfLength * X + halfLength * Y;
                } else {
                    // C(??)+C || J(??)+J
                    return (halfLength - 1) * X + (halfLength - 1) * Y;
                }
            } else {
                if (characters[start] == CODY) {
                    // C[??]*?J || C(??)+J
                    return halfLength * X + (halfLength - 1) * Y;
                } else {
                    // J(??)*?C || J(??)+C
                    return (halfLength - 1) * X + halfLength * Y;
                }
            }
        }

        return 0;
    }

    private static int copyrightAmount(final char previousChar, final char actualChar) {
        // No copyright on CC nor on JJ.
        if (previousChar == actualChar) {
            return 0;
        }

        // CJ
        if (previousChar == CODY && actualChar == JAMAL) {
            return X;
        }

        // JC
        if (previousChar == JAMAL && actualChar == CODY) {
            return Y;
        }

        // In the beginning this will happen.
        return 0;
    }

    private static int findNextNonUndefined(int start, char[] array) {
        for (int i = start; i < array.length; ++i) {
            if (array[i] != UNDEFINED) {
                return i;
            }
        }
        // Not possible
        System.exit(-1);
        return -1;
    }
}
