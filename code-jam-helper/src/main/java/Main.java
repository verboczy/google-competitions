import java.io.IOException;

public class Main {

    public static void main(final String[] args) throws IOException {
        final Processor processor = new Processor();
        final String result = processor.process("src/main/resources/input/Solution.java");
        processor.writeFile("src/main/resources/output/Solution.java", result);
    }
}
