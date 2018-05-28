package user.rishi.wordsearch.matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CharMatrix extends Matrix<Character> {
    private static Pattern VALID_ELEMENT_PATTERN = Pattern.compile("[a-z]");
    private static Predicate<Character> elementValidator
        = ch -> VALID_ELEMENT_PATTERN.matcher(Character.toString(ch)).matches();

    public CharMatrix(Character[][] data) {
        super(data, elementValidator);
    }

    public boolean matchByMatrixDistance(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }

        char[] letters = word.toCharArray();

        Character current = letters[0];

        // The positions of the first characters become the candidate paths that we want to inspect for each
        // subsequent character
        List<Position> positions = elementIndex.get(current);

        if (positions == null || positions.size() == 0) {
            return false;
        }

        List<Path> candidates = positions.stream().map(Path::new).collect(Collectors.toList());

        // for all subsequent characters
        for (int i = 1; i < letters.length; i++) {
            List<Path> viableCandidates = new ArrayList<>();
            current = letters[i];
            positions = elementIndex.computeIfAbsent(current, k -> new ArrayList<>());

            // loop through all of their positions in the matrix
            for (Position position : positions) {

                // for all path candidates look up their distance to the given position add them to the list of path
                // candidates to move forward with
                for (Path path : candidates) {
                    if (path.distanceTo(position) == 1) {
                        viableCandidates.add(Path.fromPath(path, position));
                    }
                }
            }

            // the viable candidates become the list of candidates to process in the next loop
            candidates = viableCandidates;
        }

        return candidates.size() > 0;
    }
}
