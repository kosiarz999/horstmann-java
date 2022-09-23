package optional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class OptionalTest {
    public static void main(String[] args) throws IOException {
        var contents = new String(Files.readAllBytes(
                Paths.get("./lorem.txt")), StandardCharsets.UTF_8);
        List<String> wordList = List.of(contents.split("\\PL+"));

        Optional<String> optionalValue = wordList.stream()
                .filter(s -> s.contains("fringilla"))
                .findFirst();
        System.out.println(optionalValue.orElse("Nie ma") + " zawierających 'fringilla'");

        Optional<String> optionalString = Optional.empty();
        String result = optionalString.orElse("Brak");
        System.out.println("Wynik: " + result);
        try {
            result = optionalString.orElseThrow(IllegalStateException::new);
            System.out.println("Wynik: " + result);
        }
        catch (Throwable t) {
            t.printStackTrace();
        }

        optionalValue = wordList.stream()
                .filter(s -> s.contains("Vivamus"))
                .findFirst();
        optionalValue.ifPresent(s -> System.out.println(s + " zawiera 'vivamus'"));

        var results = new HashSet<String>();
        optionalValue.ifPresent(results::add);
        Optional<Boolean> added = optionalValue.map(results::add);
        System.out.println(added);

        System.out.println(inverse(4.0).flatMap(OptionalTest::squareRoot));
        System.out.println(inverse(-1.0).flatMap(OptionalTest::squareRoot));
        System.out.println(inverse(0.0).flatMap(OptionalTest::squareRoot));
        Optional<Double> result2 = Optional.of(-4.0)
                .flatMap(OptionalTest::inverse).flatMap(OptionalTest::squareRoot);
        System.out.println(result2);
    }

    public static Optional<Double> inverse(Double x) {
        return x == 0 ? Optional.empty() : Optional.of(1 / x);
    }

    public static Optional<Double> squareRoot(Double x) {
        return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
    }
}
