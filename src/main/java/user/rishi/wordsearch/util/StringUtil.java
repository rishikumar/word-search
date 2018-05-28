package user.rishi.wordsearch.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringUtil {

    /**
     * Parses a comma-separated string into a list
     * @param data a string of comma-separated string data - e.g. "this,is,a,list,of,words"
     * @return a list of strings
     */
    public static List<String> fromCommaSeparatedString(String data) {
        return Stream.of(data.split(",")).map(String::trim).collect(Collectors.toList());
    }

}
