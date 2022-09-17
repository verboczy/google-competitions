package round1.c;

import base.TestBase;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Scanner;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

public class LetterBlocksTest extends TestBase {
    private static final String BASE_DIR = "src/test/resources/round1/c/letter-blocks/";

    private static Stream<Arguments> params() {
        return Stream.of(
                Arguments.of("Sample", BASE_DIR + "sample_test_set_1/sample_ts1_input.txt", BASE_DIR + "sample_test_set_1/sample_ts1_output.txt")
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("params")
    void test(@SuppressWarnings("unused") final String testCaseName, final String inputFileName, final String outputFileName) throws FileNotFoundException {
        final String expectedResult = readOutput(outputFileName);

        final Scanner inputScanner = new Scanner(new FileInputStream(inputFileName));
        final LetterBlocksSolution.Solver solver = new LetterBlocksSolution.Solver(inputScanner);

        assertTimeoutPreemptively(Duration.ofSeconds(20), () -> {
            final String result = solver.solve();
            assertEquals(expectedResult, result);
        });
    }
}
