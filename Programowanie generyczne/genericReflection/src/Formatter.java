import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Formatuje obiekty przy użyciu reguł wiążących typy z funkcjami formatującymi.
 */
public class Formatter {
    private Map<TypeLiteral<?>, Function<?, String>> rules = new HashMap<>();

    /**
     * Dodaje regułę formatowania do tego formatera.
     *
     * @param type             typ, którego dotyczy ta reguła
     * @param formatterForType funkcja  formatująca obiekty tego typu
     */
    public <T> void forType(TypeLiteral<T> type, Function<T, String> formatterForType) {
        rules.put(type, formatterForType);
    }

    /**
     * Formatuje wszystkie pola obiektu przy użyciu reguł tego formatera
     *
     * @param o obiekt
     * @return łańcuch zawierający wszystkie nazwy pól i sformatowane wartości
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public String formatFields(Object o) throws IllegalArgumentException, IllegalAccessException {
        var result = new StringBuilder();
        for (Field f : o.getClass().getDeclaredFields()) {
            result.append(f.getName());
            result.append("=");
            f.setAccessible(true);
            Function<?, String> formatterForType = rules.get(TypeLiteral.of(f.getGenericType()));
            if (formatterForType != null) {
                /**
                 * formatterForType ma typ parametru ?. Nic nie można przekazać do metody apply.
                 * Rzutowanie zamienia typ parametru na Object, aby można było wykonać wywołanie.
                 */
                Function<Object, String> objectFormatter = (Function<Object, String>) formatterForType;
                result.append(objectFormatter.apply(f.get(o)));
            } else result.append((f.get(o).toString()));
            result.append("\n");
        }
        return result.toString();
    }
}
