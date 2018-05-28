package user.rishi.wordsearch;

import org.apache.commons.cli.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import user.rishi.wordsearch.matrix.CharMatrix;
import user.rishi.wordsearch.util.FileUtil;
import user.rishi.wordsearch.util.StringUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordSearch {
    private final static Log log = LogFactory.getLog(WordSearch.class);

    private CharMatrix matrix;

    private WordSearch(Character[][] data) {
        this.matrix = new CharMatrix(data);
    }

    public static void main(String... args) {
        Stream.of(args).forEach(log::debug);

        CommandLine cl = parseArgs(args);
        List<String> words = StringUtil.fromCommaSeparatedString(cl.getOptionValue("words"));
        Character[][] matrixData = FileUtil.getMatrixData(cl.getOptionValue("matrix-path"));

        WordSearch wordSearch = new WordSearch(matrixData);
        log.info("Input Matrix: " + wordSearch.matrix);
        log.info("Input Words: " + words);

        List<String> matchingWords
            = words.stream().filter(wordSearch.matrix::matchByMatrixDistance).collect(Collectors.toList());

        log.info("Matching Words: " + matchingWords);
    }

    private static CommandLine parseArgs(String... args) {
        Options options = getOptions();
        CommandLineParser parser = new DefaultParser();

        try {
             return parser.parse(options, args);
        }
        catch (ParseException e) {
            log.error("Unable to parse input arguments", e);

            // wrap and throw a Runtime exception - we want the application to fail in this case
            throw new RuntimeException(e);
        }

    }

    private static Options getOptions() {
        Options options = new Options();
        options.addRequiredOption("mp", "matrix-path", true,
            "Location of the matrix input file");
        options.addRequiredOption("w", "words", true,
            "Comma-separated list of words to query");

        return options;
    }

}
