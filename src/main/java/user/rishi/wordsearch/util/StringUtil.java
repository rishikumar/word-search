package user.rishi.wordsearch.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringUtil {

    public static List<String> fromCommaSeparatedString(String data) {
        return Stream.of(data.split(",")).map(String::trim).collect(Collectors.toList());
    }

}
