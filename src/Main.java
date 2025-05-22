import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<String> words = WordsLoaderUtil.getWords();

        long start = System.currentTimeMillis();
        Set<String> wordsSet = new HashSet<>(words);

        HashMap<String, List<String>> wordsMap = new HashMap<>(words.size()); //TODO: final 9 letter words would be less
        for (String word : words) {
            if (word.length() != 9) {
                continue;
            }

            List<String> newWords = new ArrayList<>();
            word = "STARTLING";
            boolean hasMatch = true;
            String temp = word;
            int m = 1;
            int i = 1;
            while (hasMatch || i < word.length()) {
                String candidate = getCandidate(temp, m);
                if (wordsSet.contains(candidate)) {
                    temp = candidate;
                    m = 1;
                    newWords.add(candidate);
                } else {
                    m++;
                }

                if (candidate.isEmpty()) {
                    i++;
                    m = i;
                    hasMatch = false;
                    temp = word;
                }
            }

            if (newWords.size() == 8) {
                wordsMap.put(word, newWords);
            }
        }

        prettyPrint(wordsMap);
        System.out.println("All 9 letter words that form new words: " + wordsMap.keySet().size());
        System.out.println("Total running time: " + (System.currentTimeMillis() - start) + "ms.");
    }

    private static String getCandidate(String originalWord, int i) {
        if (i > originalWord.length()) {
            return "";
        }

        String firstPart = originalWord.substring(0, i - 1);
        String secondPart = originalWord.substring(i);
        return firstPart + secondPart;
    }

    private static void prettyPrint(HashMap<String, List<String>> wordsMap) {
        for (String word : wordsMap.keySet()) {
            System.out.print(word + " -> [");

            List<String> newWords = wordsMap.get(word);
            for (String newWord : newWords) {
                System.out.print(" ");
                System.out.print(newWord);
            }
            System.out.println(" ]");
        }
    }
}