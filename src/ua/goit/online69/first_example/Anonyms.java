package ua.goit.online69.first_example;

import java.util.Comparator;
import java.util.function.Function;

public class Anonyms {
    interface A {
        void doit(int i);
    }

    static class B implements A {
        @Override
        public void doit(int i) {
            System.out.println("Do it in B");
        }
    }

    public static class Holder<T> {
        private T value;

        public Holder(T value) {
            this.value = value;
        }

        public T getValue() {
            return value;
        }

        public Function<T, Long> convert() {
            long a = 10;
            a = 1;
            value = null;
            return new Function<T, Long>() {
                @Override
                public Long apply(T t) {
                    // Holder.this.value
                    return Long.parseLong(value.toString());
                }
            };
        }
    }

    public static void main(String[] args) {
        B b = new B();
        //ua.goit.online69.first_example.Anonyms$B.class
        A a = new A() {
            @Override
            public void doit(int i) {
                System.out.println("DO it in AN");
            }
        };
        //
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };

        int mult = 2;
        Holder<Integer> holder = new Holder<Integer>(10) {
            @Override
            public Integer getValue() {
                return mult * super.getValue();
            }
        };
        //mult = 3;
    }
}
