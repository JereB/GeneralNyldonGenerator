package nyldons;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.logging.Logger;

public class FileUtil {

    private static final Logger logger = Logger.getLogger(FileUtil.class.getName());

    public static void writeToFile(List<String> nyldon, Path path) {

        logger.info("Writing to file " + path);
        try {
            Files.createDirectories(path.getParent());

            try (var writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
                for (String word : nyldon) {
                    writer.write(word);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> readFromFile(Path path) {
        logger.info("Reading from file " + path);
        try {
            return Files.readAllLines(path)
                    .stream()
                    .map(String::trim)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
