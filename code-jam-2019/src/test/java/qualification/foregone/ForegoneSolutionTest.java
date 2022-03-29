package qualification.foregone;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ForegoneSolutionTest {

    private static Stream<Arguments> params() {
        return Stream.of(
                Arguments.of("4", "3", "1"),
                Arguments.of("40", "30", "10"),
                Arguments.of("44", "33", "11"),
                Arguments.of("104", "103", "1"),
                Arguments.of("940", "930", "10"),
                Arguments.of("4444", "3333", "1111"),
                Arguments.of("41234", "31233", "10001"),
                Arguments.of("123456789", "123356789", "100000"),
                Arguments.of("40000000000000000000000000000000000000000000000000", "30000000000000000000000000000000000000000000000000", "10000000000000000000000000000000000000000000000000")
        );
    }

    @ParameterizedTest(name = "Case{index} - {0} = {1} + {2}")
    @MethodSource("params")
    void foregone_solution(final String inputNumber, final String partOne, final String partTwo) {
        final List<String> result = ForegoneSolution.compute(inputNumber);

        assertEquals(2, result.size());
        assertTrue(result.contains(partOne));
        assertTrue(result.contains(partTwo));
    }
}
