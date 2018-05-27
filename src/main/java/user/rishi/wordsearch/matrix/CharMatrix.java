package user.rishi.wordsearch.matrix;

import user.rishi.wordsearch.exception.InvalidDataException;
import user.rishi.wordsearch.util.Functional;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class CharMatrix {
    private Pattern VALID_ELEMENT_PATTERN = Pattern.compile("[a-z]");

    private char[][] data;

    // This map is used to keep track of the positions of each letter in the matrix. this is used to query the matrix
    // for the positions of letters
    private Map<Character, List<Position>> characterIndex;

    public CharMatrix(char[][] data) {
        validateData(data);

        this.data = data;
        this.characterIndex = buildCharacterIndex(data);
    }

    public boolean matchByMatrixDistance(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }

        char[] letters = word.toCharArray();

        char current = letters[0];

        // The positions of the first characters become the candidate paths that we want to inspect for each
        // subsequent character
        List<Position> positions = characterIndex.get(current);

        if (positions == null || positions.size() == 0) {
            return false;
        }

        List<Path> candidates = positions.stream().map(Path::new).collect(Collectors.toList());

        // for all subsequent characters
        for (int i = 1; i < letters.length; i++) {
            List<Path> viableCandidates = new ArrayList<>();
            current = letters[i];
            positions = characterIndex.computeIfAbsent(current, k -> new ArrayList<>());

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

    private void validateData(char[][] data) {
        if (data == null || data.length == 0) {
            return;
        }

        int numColumns = data[0].length;

        // check for mismatch in number of columns on each row
        boolean isMismatch = Arrays.stream(data).anyMatch(a -> a.length != numColumns);
        if (isMismatch) {
            throw new InvalidDataException("Dimension mismatch: Found an inconsistent number of columns");
        }

        boolean allMatchPattern =
            Functional.toFlatCharStream(data).allMatch(e -> VALID_ELEMENT_PATTERN.matcher(Character.toString(e)).matches());

        if (!allMatchPattern) {
            throw new InvalidDataException("Invalid character passed into the CharMatrix - only supports [a-z].");
        }

    }

    private Map<Character, List<Position>> buildCharacterIndex(char[][] data) {
        Map<Character, List<Position>> result = new HashMap<>();

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                Position position = new Position(i, j);
                result.computeIfAbsent(data[i][j], k -> new ArrayList<>()).add(position);
            }
        }

        return result;
    }


    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder("\n");

        for (char[] row : data) {
            for (char ch : row) {
                msg.append(String.format("%5s", ch));
            }
            msg.append("\n");
        }

        return msg.toString();
    }
}
