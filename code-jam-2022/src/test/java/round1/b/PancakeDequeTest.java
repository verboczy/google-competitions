package round1.b;

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

public class PancakeDequeTest extends TestBase {

    private static final String BASE_DIR = "src/test/resources/round1/b/pancake/";

    private static Stream<Arguments> params() {
        return Stream.of(
                Arguments.of("Sample", BASE_DIR + "sample_test_set_1/sample_ts1_input.txt", BASE_DIR + "sample_test_set_1/sample_ts1_output.txt"),
                Arguments.of("Test set 1", BASE_DIR + "test_set_1/ts1_input.txt", BASE_DIR + "test_set_1/ts1_output.txt"),
                Arguments.of("Test set 2", BASE_DIR + "test_set_2/ts2_input.txt", BASE_DIR + "test_set_2/ts2_output.txt"),
                Arguments.of("Test set 3", BASE_DIR + "test_set_3/ts3_input.txt", BASE_DIR + "test_set_3/ts3_output.txt")
        );
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("params")
    void test(@SuppressWarnings("unused") final String testCaseName, final String inputFileName, final String outputFileName) throws FileNotFoundException {
        final String expectedResult = readOutput(outputFileName);

        final Scanner inputScanner = new Scanner(new FileInputStream(inputFileName));
        final PancakeDequeSolution.Solver solver = new PancakeDequeSolution.Solver(inputScanner);

        assertTimeoutPreemptively(Duration.ofSeconds(20), () -> {
            final String result = solver.solve();
            assertEquals(expectedResult, result);
        });
    }
}
