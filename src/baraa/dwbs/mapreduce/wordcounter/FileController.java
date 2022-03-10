package baraa.dwbs.mapreduce.wordcounter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FileController {

    public static List<String> cleanLines(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        List<String> cleanLines = new ArrayList<>(lines.size());

        for (String line : lines) {
            line = line.trim().toLowerCase(Locale.ROOT);
            line = line.replaceAll("[,./!@#$%^&*()]","");
            if (!line.isBlank())
                cleanLines.add(line);
        }

        return cleanLines;
    }

    public static List<String> splitWords(List<String> lines, String splitRegex) {
        List<String> words = new ArrayList<>();

        for (String line : lines) {
            String[] wordsInLine = line.split(splitRegex);
            for (String word : wordsInLine)
                words.add(word);
        }

        return words;
    }

}
