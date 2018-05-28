package user.rishi.wordsearch.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtil {
    /**
     * Takes a given matrix file location path and converts it to a two dimensional character array.
     * Each row in the file is represented by a separate line and all elements are stored in a comma-separated format
     * within each line. For example:
     *
     *      a,b,c,d
     *      q,r,a,b
     *      w,o,r,l
     *      t,z,e,b
     *
     * @param path the path of the file
     * @return two dimensional Character Matrix
     */
    public static Character[][] getMatrixData(String path) {
        List<String> lines;

        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            lines = stream.filter(line -> !line.isEmpty()).collect(Collectors.toList());
        }
        catch (IOException e) {
            // fatal error, re-raise it as a RuntimeException
            throw new RuntimeException(e);
        }

        Character[][] data = new Character[lines.size()][];

        for (int i=0; i < lines.size(); i++) {
            String line = lines.get(i);

            String[] splits = line.trim().split(",");
            Character[] rowData = new Character[splits.length];
            data[i] = rowData;

            for (int j=0; j < splits.length; j++) {
                String split = splits[j];
                rowData[j] = split.trim().charAt(0);
            }

        }

        return data;
    }

}
