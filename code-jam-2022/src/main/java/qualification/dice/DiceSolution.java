package qualification.dice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DiceSolution {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(final String[] args) {
        final int T = scanner.nextInt();
        for (int testCaseIndex = 1; testCaseIndex <= T; ++testCaseIndex) {
            final int result = handleTestCase();
            System.out.printf("Case #%d: %d%n", testCaseIndex, result);
        }
    }

    public static int handleTestCase() {
        final int N = scanner.nextInt();
        final List<Integer> diceList = new ArrayList<>(N);
        for (int i = 1; i <= N; i++) {
            final int dice = scanner.nextInt();
            diceList.add(dice);
        }
        Collections.sort(diceList);

        int length = 0;
        for (int i = 0; i < diceList.size(); i++) {
            final int element = diceList.get(i);
            if (element > length) {
                length++;
            }
        }
        return length;
    }

}
