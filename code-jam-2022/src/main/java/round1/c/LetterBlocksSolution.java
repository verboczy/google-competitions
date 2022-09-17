package round1.c;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

public class LetterBlocksSolution {

    public static void main(final String[] args) {
        Solver solver = new Solver(new Scanner(System.in));
        solver.solve();
    }

    enum Position {
        BEFORE, AFTER, BOTH, DONT_CARE
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
            final int wordCount = scanner.nextInt();
            scanner.nextLine();
            final String[] line = scanner.nextLine().split(" ");

            List<String> wordList = new ArrayList<>(wordCount);
            wordList.add(line[0]);
            for (int wordIndex = 1; wordIndex < wordCount; wordIndex++) {
                final String newWord = line[wordIndex];
                try {
                    wordList = buildMegatower(wordList, newWord);
                } catch (IllegalStateException e) {
                    return String.format("Case #%d: IMPOSSIBLE", testCaseIndex);
                }
            }

            final String result = String.join("", wordList);

            return String.format("Case #%d: %s", testCaseIndex, result);
        }

        private List<String> buildMegatower(final List<String> wordList, final String newWord) {
            // 1. Check if it fits with all
            final int wordListSize = wordList.size();
            List<Position> checkResults = new ArrayList<>(wordListSize);
            for (String oldWord : wordList) {
                final Position checkResult = check(oldWord, newWord);
                if (checkResult != Position.DONT_CARE && checkResults.contains(checkResult)) {
                    throw new IllegalStateException("IMPOSSIBLE");
                }
                checkResults.add(checkResult);
            }

            final List<String> megatower = new ArrayList<>();
            String result = newWord;
            for (int i = 0; i < wordListSize; i++) {
                final String oldWord = wordList.get(i);
                if (checkResults.get(i) == Position.BEFORE) {
                    result = result + oldWord;
                } else if (checkResults.get(i) == Position.AFTER) {
                    result = oldWord + result;
                } else if (checkResults.get(i) == Position.BOTH) {
                    result = oldWord + result;
                } else {
                    megatower.add(oldWord);
                }
            }
            megatower.add(result);
            return megatower;
        }

        // Tells where the new word has to be compared to the old one.
        private Position check(final String oldWorld, final String newWorld) {
            if (oldWorld.startsWith(newWorld.substring(newWorld.length() - 1)) && newWorld.startsWith(oldWorld.substring(oldWorld.length() - 1))) {
                if (!check(newWorld + oldWorld)) {
                    throw new IllegalStateException("IMPOSSIBLE");
                }
                return Position.BOTH;
            }
            if (oldWorld.startsWith(newWorld.substring(newWorld.length() - 1))) {
                if (!check(newWorld + oldWorld)) {
                    throw new IllegalStateException("IMPOSSIBLE");
                }
                return Position.BEFORE;
            }
            if (newWorld.startsWith(oldWorld.substring(oldWorld.length() - 1))) {
                if (!check(oldWorld + newWorld)) {
                    throw new IllegalStateException("IMPOSSIBLE");
                }
                return Position.AFTER;
            }

            // Doesn't matter which way.
            if (!check(oldWorld + newWorld)) {
                throw new IllegalStateException("IMPOSSIBLE");
            }
            return Position.DONT_CARE;
        }

        private boolean check(final String word) {
            final Set<Character> charsAlready = new HashSet<>();
            char previousChar = word.charAt(0);
            charsAlready.add(previousChar);
            for (int i = 1; i < word.length(); i++) {
                final char actualCharacter = word.charAt(i);
                if (actualCharacter == previousChar) {
                    continue;
                }
                if (charsAlready.contains(actualCharacter)) {
                    return false;
                }
                charsAlready.add(actualCharacter);
                previousChar = actualCharacter;
            }
            return true;
        }
    }
}
