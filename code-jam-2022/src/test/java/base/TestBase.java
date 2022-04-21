package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestBase {

    protected final String readOutput(final String outputFileName) throws FileNotFoundException {
        final Scanner outputScanner = new Scanner(new FileInputStream(outputFileName));
        StringBuilder sb = new StringBuilder();
        while (outputScanner.hasNextLine()) {
            sb.append(outputScanner.nextLine()).append("\n");
        }
        return sb.toString();
    }
}
