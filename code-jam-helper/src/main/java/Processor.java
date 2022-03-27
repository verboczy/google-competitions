import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Processor {

    public String process(final String path) throws IOException {
        final File file = new File(path);
        final Scanner scanner = new Scanner(file);
        scanner.useDelimiter("\n");

        final StringBuilder sb = new StringBuilder();
        while (scanner.hasNext()) {
            final String line = scanner.next();
            sb.append(processLine(line));
        }
        scanner.close();

        return sb.toString();
    }

    private String processLine(final String line) {
        if (line.trim().startsWith("//") || line.trim().startsWith("package ") || line.contains("log.") || line.contains("@Slf4j") || line.contains("lombok")) {
            return "";
        } else {
            return line + "\n";
        }
    }

    public void writeFile(final String fileName, final String content) throws IOException {
        final BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(content);
        writer.close();
    }
}
