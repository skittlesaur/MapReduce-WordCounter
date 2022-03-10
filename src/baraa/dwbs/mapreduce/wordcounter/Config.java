package baraa.dwbs.mapreduce.wordcounter;

import java.io.File;

public class Config {

    public int machinesCount;
    public File inputFile;
    public File outputFile;
    public String splitRegex;

    public Config() {
        machinesCount = 5;
        inputFile = new File("src/baraa/dwbs/mapreduce/data/input.txt");
        outputFile = new File("src/baraa/dwbs/mapreduce/data/output.txt");
        splitRegex = "[ ]+";
    }

    @Override
    public String toString() {
        return "Config{" +
                "machinesCount=" + machinesCount +
                ", inputFile=" + inputFile +
                ", outputFile=" + outputFile +
                ", splitRegex='" + splitRegex + '\'' +
                '}';
    }
}
