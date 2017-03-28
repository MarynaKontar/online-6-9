package ua.goit.online69.third_example;

import java.util.Random;
import java.util.function.Function;

/**
 * Example for methods.
 * <p>
 * Created by andreymi on 3/28/2017.
 */
public class MethodsExamples {
    // You know that interface is abstraction which has methods that all abstract..
    // Until...
    // .... java 8 came.
    // And default methods introduced.
    interface A {
        // To create default method in interface you must specify it as 'default'
        default int someMethod(int value) {
            return value * value;
        }
    }

    // Yes, it breaks abstraction in some cases but in Oracle do not care..
    // One of possible options to use.
    //
    interface B {
        default Object action() {
            return action(null);
        }

        default Object action(Object a) {
            return action(a, a);
        }

        default Object action(Object a, Object b) {
            return action(a, a, b);
        }

        int action(Object a, Object b, Object c);
    }
    // As you see interface provides 4 methods as API, but some of them have default implementations.
    // Same as in abstract class, but... but cannot extend more then 1 class but can implements are many interfaces as possible.

    public static void main(String[] args) {
        // For example you have lambla that calles some method that have same paremeters as lambda.
        Function<Integer, Integer> squareL = x -> square(x);
        // Then this function can be shorted to method reference.
        Function<Integer, Integer> squareLX = MethodsExamples::square;
        // First here is class name if method is static or variable.
        Random r = new Random();
        // This will return some random int bounded by provided values.
        Function<Integer, Integer> randomBounded = r::nextInt;
    }

    private static int square(int x) {
        return x * x;
    }

}
