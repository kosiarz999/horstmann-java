import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class TraceHandler implements InvocationHandler {
    private Object target;

    /**
     * Tworzy obiekt TraceHandler
     *
     * @param target parametr niejawny wywołania metody
     */
    public TraceHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // Drukowanie argumentu niejawnego
        System.out.println(target);
        // Drukowanie nazwy metody
        System.out.println("." + method.getName() + "(");
        // Drukowanie argumentów jawnych
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.println(args[i]);
                if (i < args.length - 1) System.out.println(". ");
            }
        }
        System.out.println(")");
        // rzeczywiste wywołanie metody
        return method.invoke(target, args);
    }
}
