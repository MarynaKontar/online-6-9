package ua.goit.online69.first_example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Examples for lambda usages in java 8.
 * <p/>
 * Created by andreymi on 3/28/2017.
 */
public class FirstLambda {
    // Lambda is anonymous function. Lambda has no name, only signature and type.
    // Signature of lambda function is parameters in brackets (when one parameter used - this can be ommited),
    //  '->' , and lambda body.
    private Function<Integer, String> lambda = x -> Integer.toString(x);

    // Lambda exists in many modern languages (called functional languages) and help write code in functional style.
    // What does it means: you can operate lambdas as parameters to methods and constructors, pass lambda into lambdas and soon.
    // And last but not least - less and more readable code.
    // How lambdas are implemented in java?
    // Each lambda in java is converted into so called Functional interface.
    // Functional interface is interface which has 1 method only, except defaults.
    private interface A {
        int method(int values);
    }
    // And lambda type of it looks like.
    private A lambda2 = x -> x * x;
    // 1. Lambda?
    // 2. return int, param size 1, int
    // 3. 'method' -> 2.
    // This is examcty same as, but less code.x
    private A lambda3 = new A() {
        @Override
        public int method(int values) {
            return values * values;
        }
    };
    // You can annotate your interface using @FunctionalInterface annotation and mark interface as functional (but this is optional)
    @FunctionalInterface
    private interface B {
        int method(int values);
    }

    public static void main(String[] args) {
        // There are lots of functional interfaces exists in java already.
        // 1. Predicate - took element and return boolean
        // 2. Consumer - took 1 argument to process, return void
        // 3. Function - took 1 argument and transform it into another
        // 4. BiFunction - took 2 argument and transform it into another
        // 5. Operator - took 1 argument and transform it into same type
        // 6. BinaryOperator - took 2 arguments and transform it into same type
        // 7. Runnable - used in concurrent java and soon.
        //
        // Took 1 element and then return true in case this element less then zero.
        Predicate<Integer> negativeNumbers = x -> x < 0;
        // Example to usage with different filters.
        Collection<Integer> initial = Arrays.asList(-1, -2, 0, 1, 2, 3);
        System.out.println(filterNumbers(initial, x -> x < 0));
        System.out.println(filterNumbers(initial, x -> x >= 0));
        System.out.println(filterNumbers(initial, x -> x % 2 == 0));
    }

    // Write method which is filter elements
    private static Collection<Integer> filterNumbers(Collection<Integer> collection, Predicate<Integer> predicate) {
        Collection<Integer> result = new ArrayList<>();
        for (Integer e : collection) {
            if (predicate.test(e)) {
                result.add(e);
            }
        }
        return result;
    }
}
