import java.util.ArrayList;
import java.util.List;

public class StringPermutations {

    // Main method for testing
    public static void main(String[] args) {
        String input = "qwerty";
        try {
            List<String> permutations = generatePermutations(input);
            System.out.println("Permutations of " + input + ": " + permutations);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<String> generatePermutations(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty.");
        }

        List<String> permutations = new ArrayList<>();
        permute(str.toCharArray(), 0, permutations);
        return permutations;
    }

    private static void permute(char[] chars, int currentIndex, List<String> result) {
        if (currentIndex == chars.length - 1) {
            result.add(new String(chars));
            return;
        }

        for (int i = currentIndex; i < chars.length; i++) {
            swap(chars, currentIndex, i); // Swap characters
            permute(chars, currentIndex + 1, result); // Recur with next index
            swap(chars, currentIndex, i); // Swap back (backtracking)
        }
    }

    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
