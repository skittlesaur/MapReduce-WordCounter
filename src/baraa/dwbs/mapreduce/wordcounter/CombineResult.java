package baraa.dwbs.mapreduce.wordcounter;

import baraa.dwbs.mapreduce.utils.Tuple;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class CombineResult {

    public static void combine(List<Tuple<String, Integer>> result, Config config, long startTime) throws IOException {
        Collections.sort(result);

        File outputFile = config.outputFile;

        if (!outputFile.exists())
            outputFile.createNewFile();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile));

        bufferedWriter.write("--- Word Counter ---\n");

        for (Tuple<String, Integer> tuple : result) {
            bufferedWriter.write(String.format("%s: %d time%s\n", tuple.key, tuple.value, tuple.value == 1 ? "" : "s"));
        }

        bufferedWriter.write("\n--- Info ---\n");
        bufferedWriter.write("Running Time: " + (System.currentTimeMillis() - startTime) + "ms\n");
        bufferedWriter.write("Total Machines: " + config.machinesCount);
        bufferedWriter.close();
    }

}
