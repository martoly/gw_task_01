import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WordsLoaderUtil {

    private static final String URL_VALUE = "https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt";

    public static List<String> getWords() {
        List<String> result = new ArrayList<>();
        URL words = null;
        try {
            words = new URL(URL_VALUE);
        } catch (MalformedURLException e) {
            System.out.println("Exception occurred while connecting to url!");
            return result;
        }

        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(words.openConnection().getInputStream()))) {
            result = bufferedReader.lines().skip(2).toList();
            System.out.println("Total words collected: " + result.size());

            return result;
        } catch (Exception e) {
            System.out.println(e);
        }

        return result;
    }

}
