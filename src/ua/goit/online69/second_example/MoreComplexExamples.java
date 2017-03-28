package ua.goit.online69.second_example;

import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * More complex lambda examples
 * <p>
 * Created by andreymi on 3/28/2017.
 */
public class MoreComplexExamples {
    public static void main(String[] args) {
        // Assume that logic inside lambda is complicated and it requires more lines.
        // Then you can extend lambda by adding '{}' as in anonymous class.
        Predicate<Integer> negativeMore = x -> {
            if (x < 0) {
                return true;
            } else if (x > 10) {
                return true;
            }
            return false;
        };
        // Lets create example with two input numbers and calcute its sum.
        // This operator took 2 elements as input parameters and then return result type.
        // NOTE: types are calculated dynamically.
        BinaryOperator<Integer> summing = (x, y) -> x + y;
        Integer[] array = {1, 2, 3, 4, 5};
        System.out.println(reduce(array, 0, summing));
        // Multiplication of all numbers..
        System.out.println(reduce(array, 1, (x, y) -> x * y));
        // Familliar to something?
        // Comparators.
        Arrays.sort(array, (a, b) -> -Integer.compare(a, b));
        System.out.println(Arrays.toString(array));
        // Visibility. Inside lambla you can access local variables and class elements.
        // Local variables must be exclusively final: not change its reference value.
        int a = 20;
        Function<Integer, Integer> multi = x -> a * x;
        // But if java will know that a might change state - it wont compile.
        //a = 10;
        // You cannot shade variable as it possible for anonymous classes lambda variables visibility is same
        // as method or class or place where you call it.
        //int x = 10;
        //Function<Integer, Integer> multi2 = x -> a * x;
        int x = 10;
        Function<Integer, Integer> multi3 = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return x * x;
            }
        };
        System.out.println(multi3.apply(2));
        // So lambda is small functional possibility to makes like more easier.
    }

    // Function with reduces some array into number
    private static <T> T reduce(T[] array, T initialValue, BinaryOperator<T> operator) {
        T result = initialValue;
        for (T element : array) {
            result = operator.apply(result, element);
        }
        return result;
    }
}
