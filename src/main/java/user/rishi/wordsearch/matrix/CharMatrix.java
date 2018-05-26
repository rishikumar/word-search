package user.rishi.wordsearch.matrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CharMatrix {
    private char[][] data;

    // This map is used to keep track of the positions of each letter in the matrix. this is used to query the matrix
    // for the positions of letters
    private Map<Character, List<Position>> characterIndex;

    public CharMatrix(char[][] data) {
        this.data = validateData(data);
        this.characterIndex = buildCharacterIndex(data);
    }

    public boolean isWordFound(String word) {
        List<Path> paths = findPaths(word);

        return paths.size() > 0;
    }

    private char[][] validateData(char[][] data) {
        // TODO: Validate that the data contains consistent values for rows and columns.
        // TODO: Validate that all characters are lowecase letters - e.g. [a-z]
        return data;
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


    private List<Path> findPaths(String word) {
        if (word == null || word.length() == 0) {
            return new ArrayList<>();
        }

        char[] letters = word.toCharArray();

        char current = letters[0];

        // The positions of the first characters become the candidate paths that we want to inspect for each
        // subsequent character
        List<Position> positions = characterIndex.get(current);

        if (positions == null || positions.size() == 0) {
            return new ArrayList<>();
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

        return candidates;
    }

}
