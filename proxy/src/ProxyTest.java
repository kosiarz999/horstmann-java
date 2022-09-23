import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/**
 * Program wykorzystujący klasy pośredniczące (dynamic proxies).
 * Nie mam bladego pojęcia co to robi ani tym bardziej jak to działa :)
 */
public class ProxyTest {
    public static void main(String[] args) {
        var elements = new Object[1000];

        // wstawianie do tablicy obiektów pośredniczących liczb całkowitych z przedziału 1 - 1000
        for (int i = 0; i < elements.length; i++) {
            Integer value = i + 1;
            var handler = new TraceHandler(value);
            Object proxy = Proxy.newProxyInstance(
                    ClassLoader.getSystemClassLoader(),
                    new Class[]{Comparable.class}, handler
            );
            elements[i] = proxy;
        }
        // Tworzenie losowej liczby całkowitej
        Integer key = new Random().nextInt(elements.length) + 1;

        // Szukanie liczby
        int result = Arrays.binarySearch(elements, key);

        // Drukowanie dopasowanej wartości, jeśli zostanie znaleziona
        if (result >= 0) System.out.println(elements[result]);
    }
}
