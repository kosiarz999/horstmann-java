import java.util.Scanner;

// Program wyświetlający stos wywołań rekursywnej metody
public class StackTraceTest {
    /**
     * <p>Oblicza silnię liczby</p>
     *
     * <p>Funkcja wykorzystuje klasę StackWalker,
     * która zwraca strumień egzemplarzy typu StackWalker.StackFrame,
     * każdy opisujący jedną ramkę stosu.</p>
     *
     * <p>Dane ze stosu wywołań przedstawiają listę wszystkich oczekujących wywołań metod
     * w określonym momencie wykonywania programu.</p>
     *
     * @param n nieujemna liczba całkowita
     * @author Cay Horstmann
     */
    public static int factorial(int n) {
        System.out.println("factorial(" + n + "):");
        var walker = StackWalker.getInstance();
        walker.forEach(System.out::println);
        int r;
        if (n <= 1) r = 1;
        else r = n * factorial(n - 1);
        System.out.println("return " + r);
        return r;
    }

    public static void main(String[] args) {
        try (var in = new Scanner(System.in)){
            System.out.print("Wpisz n: ");
            int n = in.nextInt();
            factorial(n);
        }
    }
}
