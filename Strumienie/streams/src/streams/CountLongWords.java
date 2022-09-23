package streams;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CountLongWords {
    public static void main(String[] args) throws IOException {
        var contents = new String(Files.readAllBytes(
                Paths.get("./lorem.txt")), StandardCharsets.UTF_8);

        List<String> words = List.of(contents.split("\\PL+"));

        long count = 0;
        for (String w : words) {
            if (w.length() > 10) count++;
        }
        System.out.println("Zliczanie po pętli foreach: " + count);

        count = words.stream().filter(w -> w.length() > 10).count();
        System.out.println("Zliczanie po stream(): " + count);

        count = words.parallelStream().filter(w -> w.length() > 10).count();
        System.out.println("Zliczanie po parallelStream(): " + count);
    }
}
