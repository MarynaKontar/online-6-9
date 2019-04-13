package ua.goit.online69.fourth_example;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Example for java streams
 * <p/>
 * Created by andreymi on 3/28/2017.
 */
public class StreamsExamples {
    public static void main(String[] args) {
        // In java8 was introduced some abstraction called stream.
        Stream<Integer> s = Stream.empty();
        // Stream some likely can be compared to iterator, but stream has much more possibilities.
        // It firstly user as extension to collections as to provide
        // more flexible way to work with existed collections.
        // But it can be extended to many other ways.
        //
        // Assume we have list of elements
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        // To create stream from collection you can call stream() method on java.util.Collection object.
        Stream<Integer> stream = list.stream();
//        // As I said - it can be compared to iterators, because streams as single-shot.
//        // While stream executed, it cannot be returned to initial position.
//        //
//        // There are two types of operations in stream: intermidiate and terminal.
//        // Intermediate operation is applied to stream, but not immediately executed and gives possibility to apply other operations.
//        // Terminate operations are those which terminate the stream and return some result.
//        // NOTE! Stream intermidiate operations are lazy and terminal - eager, so whole stream will be executed when terminal operation applied.
//        //
//        // Intermidiate operations are: filter, sort, map, flatMap, peek, limit, skip, distinct, sequential and parallel
//        // Terminal are: collect, count, matchesAll, matchesAny, noneMatch, findFirst, findAny, min, max, reduce
//        //
//        // Examples.
//        // Lets select first element of stream.
        Optional<Integer> first = stream.findFirst();
//        System.out.println(first);
//        // Optional - it is wrapper over possible null-type and can provide more flexible interface to work with.
//        System.out.println(first.isPresent());
//        // When you call get and there is inside null - you will receive NPE.
//        System.out.println(first.get());
//        //String a = null;
//        //Object b = Optional.ofNullable(a).orElse("de");
//        // As I said streams are single-shot
//        // Another invocation will give you java.lang.IllegalStateException: stream has already been operated upon or closed
//        // Select only odd elements and collect them into some list.
        List<Integer> odds = list.stream().filter(i -> i % 2 == 1).collect(Collectors.toList());
//        System.out.println(odds);
//        // Now collect all elements that are odd and bigger then 4
//        Predicate<Integer> oddOr = i -> i % 2 == 1;
//        oddOr = oddOr.or(i -> i < 4);
//        odds = list.stream().filter(oddOr).collect(Collectors.toList());
//        odds = list.stream().filter(i -> i % 2 == 1).filter(i -> i > 4).collect(Collectors.toList());
//        System.out.println(odds);
//        // Now collect all elements that are odd and bigger then 4 and convert them into Strings
        List<String> oddStr = list.stream().filter(i -> i % 2 == 1).filter(i -> i > 4).map(i -> "Test number:" + i).collect(Collectors.toList());
//        System.out.println(oddStr);
//        // And now I want to combine them into 1 line
//        String out = list.stream().filter(i -> i % 2 == 1).filter(i -> i > 4).map(i -> "Test number:" + i).collect(Collectors.joining(","));
//        System.out.println(out);
//        // How to find is element exists in collection?
//        int element = 7;
//        Optional<Integer> result = list.stream().filter(i -> i == element).findFirst();
//        System.out.println(result.isPresent());
//        // Not found....
//        int another = 17;
//        result = list.stream().filter(i -> i == another).findFirst();
//        System.out.println(result.isPresent());
//        // As example before lets sum all numbers
        int sum = list.stream().reduce(0, (x, y) -> x + y);
//        System.out.println(sum);
//        // Lest select top element.
//        // It can be done in two ways
//        // 1. Sort in desc and take first
//        // 2. Get max in streams
//        Optional<Integer> max1 = list.stream().max(Integer::compareTo);
//        Optional<Integer> max2 = list.stream().sorted((a, b) -> -a.compareTo(b)).findFirst();
//        System.out.println(max1 + " -> " + max2);
//        // Assume we want to limit number of elements to 3
//        List<Integer> limited = list.stream().limit(3).collect(Collectors.toList());
//        System.out.println(limited);
//        // Top 3
//        limited = list.stream().sorted((a, b) -> -a.compareTo(b)).limit(3).collect(Collectors.toList());
//        System.out.println(limited);
//        // You can collect data into other collections: set, lists
//        Set<Integer> set = list.stream().limit(3).collect(Collectors.toCollection(TreeSet::new));
//        System.out.println(set);
//        // Special streams exists for primitives: IntStream, LongStream, DoubleStream...
//        // For example we want to generate collection of 5 elements
//        List<A> example = IntStream.range(0, 5).boxed().map(i -> new A(i % 2, "Test:" + i)).collect(Collectors.toList());
//        System.out.println(example);
//        // And group examples by type
//        Map<Integer, List<A>> grouped = example.stream().collect(Collectors.groupingBy(v -> v.type));
//        System.out.println(grouped);
//        //
//        // And many, many, many more opportunities...
        Random random = new Random();
//        IntStream stream1 = random.ints(10);
//        //
//        int[] a = {1, 2, 3};
//        IntStream intA = Arrays.stream(a);
//        int[] b = {4, 5, 6};
//        IntStream intB = Arrays.stream(b);
//        //
//        Stream<Integer> c = Stream.concat(intA.boxed(), intB.boxed());
        Stream<Long> infinite = Stream.generate(random::nextLong).limit(10);
//        infinite.forEach(i -> System.out.println(i));
//        //
//        LongStream longStream = infinite.mapToLong(Long::longValue);

        //peek return the same steam but uses function to each element of the stream
        List<String> lists = Stream.of("one", "two", "three", "four")
                         .filter(e -> e.length() > 3)
                         .peek(e -> System.out.println("Filtered value: " + e))
                         .map(String::toUpperCase)
                         .peek(e -> System.out.println("Mapped value: " + e))
                         .collect(Collectors.toList());
//        Filtered value: three
//        Mapped value: THREE
//        Filtered value: four
//        Mapped value: FOUR

        //FLATMAP (collection in collection)
        List<String> lists1 = new ArrayList<>();
        lists1.add("// !!!!!!!!!!!!!!!--FRONTEND--!!!!!!!!!!!!!!!!!!!");
        lists1.add("// PROMISE: synchronized receiving data from the server for saving value-compatibility area arrays; change modal author-card size; replace value-compatibility colors to separate file; change background color(to rgba(245,245,245,0.4)) for ValueProfilesMatching figure for noPrincipal user; add redirect after registration; fix bug in RegistrationService: consider the case when in httpOptions there isnt token; fix bug in RegistrationComponent and LoginComponent: reload home page after redirect from register/login page (use Promise);");
        lists1.add("done scroll-spy for match component (scrolling by page with navbar); add login-error notification; fix bag in send-tokens - not visible if there isnt links");
        lists1.add("NOT COMMITED add possibility to show inviteTokenList only 2 times per session on value-profile component, than shows only value-profile without links; scale sending-tokens for all device sizes;");
        lists1.add("NOT COMMITED add user-account component and write update method for UserAccount");
        Stream<String> lines =  lists1.stream();
          List<String> words = lines.flatMap(line -> Stream.of(line.split(" +"))).peek(word -> System.out.println("@@" + word)).collect(Collectors.toList());
        System.out.println(words);
    }

    private static class A {
        int type;
        String name;

        A(int type, String name) {
            this.type = type;
            this.name = name;
        }

        @Override
        public String toString() {
            return "A{" +
                    "type=" + type +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
