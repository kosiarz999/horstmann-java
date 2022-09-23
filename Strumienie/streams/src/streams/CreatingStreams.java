package streams;

import jdk.swing.interop.SwingInterOpUtils;

import javax.imageio.event.IIOReadProgressListener;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Array;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CreatingStreams {
    public static <T> void show(String title, Stream<T> stream) {
        final int SIZE = 10;
        List<T> firstElements = stream
                .limit(SIZE + 1)
                .collect(Collectors.toList());
        System.out.println(title + ": ");
        for (int i = 0; i < firstElements.size(); i++) {
            if (i > 0) System.out.print(", ");
            if (i < SIZE) System.out.print(firstElements.get(i));
            else System.out.print("...");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("./lorem.txt");
        var contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);

        Stream<String> words = Stream.of(contents.split("\\PL+"));
        show("Stream.of", words);
        Stream<String> beginning  = Stream.of("lorem", "ipsum", "blandit", "viverra");
        show("Stream.of(Wybrane słowa)", beginning);
        Stream<String> silence = Stream.empty();
        show("Stream.empty", silence);

        Stream<String> echos = Stream.generate(() -> "Echo");
        show("Stream.generate(Echo)", echos);

        Stream<Double> randoms = Stream.generate(Math::random);
        show("Randomy", randoms);

        Stream<BigInteger> integers = Stream.iterate(BigInteger.ONE, n -> n.add(BigInteger.ONE));
        show("Liczby całkowite", integers);

        Stream<String> wordsAnotherWay = Pattern.compile("\\PL+").splitAsStream(contents);
        show("Pattern.compile.splitAsStream", wordsAnotherWay);
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            show("Files.lines", lines);
        }

        Iterable<Path> iterable = FileSystems.getDefault().getRootDirectories();
        Stream<Path> rootDirectories = StreamSupport.stream(iterable.spliterator(), false);
        show("Dyski. iterable = FileSystems.getDefault().getRootDirectories() -> StreamSupport.stream(iterable.spliterator()", rootDirectories);

        Iterator<Path> iterator = Paths.get("D:\\Programowanie\\Java\\Horstmann\\Strumienie\\streams\\src").iterator();
        Stream<Path> pathComponents = StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                iterator, Spliterator.ORDERED), false);
        show("Foldery (StreamSupport.stream(Spliterators)", pathComponents);
    }
}
