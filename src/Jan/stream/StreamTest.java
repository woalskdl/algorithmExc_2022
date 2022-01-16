package Jan.stream;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.*;

public class StreamTest {
    public static void main(String[] args) {
        String[] arr = new String[]{"a", "b", "c"};
        Stream<String> stream = Arrays.stream(arr);
        Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3);

        stream.forEach(e -> System.out.println(e));
        System.out.println(">>>>>");
        streamOfArrayPart.forEach(e -> System.out.println(e));

        System.out.println("=====================");
        List<String> list = Arrays.asList("aa", "bb", "cc");
        Stream<String> listStream = list.stream();
        Stream<String> parListStream = list.parallelStream();

        listStream.forEach(e -> System.out.println(e));
        System.out.println(">>>>>");
        parListStream.forEach(e -> System.out.println(e));

        System.out.println("=====================");
        Stream<String> generateStream = Stream.generate(() -> "gen").limit(5);
        generateStream.forEach(e -> System.out.println(e));

        System.out.println("=====================");
        Stream<Integer> iterateStream = Stream.iterate(30, e -> e + 2).limit(5);
        iterateStream.forEach(e -> System.out.println(e));

        System.out.println("=====================");
        IntStream intStream = IntStream.range(1, 5);
        LongStream longStream = LongStream.rangeClosed(1, 5);
        intStream.forEach(e -> System.out.println(e));
        System.out.println(">>>>>");
        longStream.forEach(e -> System.out.println(e));

        // boxing ??
        Stream<Integer> boxedIntStream = IntStream.range(1,5).boxed();

        System.out.println("=====================");
        DoubleStream randomStream = new Random().doubles(3);
        randomStream.forEach(e -> System.out.println(e));

        System.out.println("=====================");
        IntStream charsStream = "Stream".chars();
        charsStream.forEach(e -> System.out.println(e));

        System.out.println("=====================");
        Stream<String> stringStream = Pattern.compile(", ").splitAsStream("Eric, Elena, Java");
        stringStream.forEach(e -> System.out.println(e));

        // Parallel Stream
        System.out.println("=====================");
        Stream<String> parallelStream = list.parallelStream();
        boolean isParallel = parallelStream.isParallel();
        System.out.println(isParallel);

        Stream<String> generatedParStream = Arrays.stream(arr).parallel();      // 배열 to parallel stream
        Stream<String> sequenceStream = generatedParStream.sequential();        // parallel to sequential

        System.out.println("=====================");
//        Stream<String> concatStream = Stream.concat(stream, listStream);  // stream 은 일종의 인스턴스 >> 재활용 불가
        Stream<String> stream1 = Stream.of("1","2","3");
        Stream<String> stream2 = Stream.of("4","5","6");
        Stream<String> concatStream = Stream.concat(stream1, stream2);
        concatStream.forEach(e -> System.out.println(e));

        // Filter
        System.out.println("=====================");
        List<String> names = Arrays.asList("Eric", "Elena", "Java");
        Stream<String> filteredStream = names.stream().filter(e -> e.contains("a"));
        filteredStream.forEach(e -> System.out.println(e));

        // Mapping
        System.out.println("=====================");
        Stream<String> mappedStream1 = names.stream().map(String::toUpperCase);
        mappedStream1.forEach(e -> System.out.println(e));
        System.out.println(">>>>>");
        Stream<Integer> mappedStream2 = names.stream().map(String::length);
        mappedStream2.forEach(e -> System.out.println(e));
        // getter, setter 사용 가능

        System.out.println("=====================");
        List<List<String>> compList = Arrays.asList(Arrays.asList("a"), Arrays.asList("b"));
        // >>
        List<String> flatList = compList.stream().flatMap(Collection::stream).collect(Collectors.toList());
        flatList.forEach(e -> System.out.println(e));

        // Sorting
        System.out.println("=====================");
        IntStream.of(14, 11, 20, 39, 23).sorted().boxed().collect(Collectors.toList())
                .forEach(e -> System.out.println(e));

        List<String> lang = Arrays.asList("Java", "Scala", "Groovy", "Python", "Go", "Swift");
        lang.stream().sorted().collect(Collectors.toList()).forEach(e -> System.out.println(e));
        System.out.println(">>>>>");
        lang.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()).forEach(e -> System.out.println(e));
        System.out.println(">>>>>");
        lang.forEach(e -> System.out.println(e)); // 원 데이터는 안변함

        System.out.println(">>>>>");
        lang.stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList()).forEach(e -> System.out.println(e));
        lang.stream().sorted((s1, s2) -> s2.length() - s1.length()).collect(Collectors.toList()).forEach(e -> System.out.println(e));

        // iterating / calculating
        System.out.println("=====================");
//        int sum = IntStream.of(1,3,5,7,9).peek(System.out::println).sum();
        int count = (int) IntStream.of(1,3,5,7,9).peek(System.out::println).count();
//        System.out.println(sum);
        System.out.println(count);

        OptionalInt min = IntStream.of(1,3,5,7,9).min();
        OptionalInt max = IntStream.of(1,3,5,7,9).max();

        System.out.println("min : " + min.getAsInt() + " / " + "max : " + max.getAsInt());
        DoubleStream.of(1.1, 2.2, 3.3, 4.4, 5.5).average().ifPresent(System.out::println);      // 평균

        OptionalInt reduced = IntStream.range(1,4).reduce((a,b) -> {
            return Integer.sum(a,b); // 1+2+3
        });
        System.out.println(reduced);

        int reducedTwoParams = IntStream.range(1,4).reduce(10, Integer::sum);
        System.out.println(reducedTwoParams);

        Integer reducedParams = Stream.of(1,2,3)
                .reduce(10,
                        Integer::sum,
                        (a,b) -> {
                            System.out.println("combiner called");
                            return a+b;
                        });
        System.out.println(reducedParams); // combiner는 실행되지 않음. 각각 쓰레드로 실행되기 때문에 마지막에 합치지 않아 연산에 포함되지 않음.

        Integer reducedParallel = Arrays.asList(1,2,3)
                .parallelStream()
                .reduce(10,
                        Integer::sum,
                        (a,b) -> {
                            System.out.println("combiner called");
                            return a+b;
                        });
        System.out.println(reducedParallel);    // 10 + 1 / 10 + 2 / 10 + 3 >> 12 + 13 / 25 + 11 (두번 호출됨)

        // Collecting
        class Product{
            int no;
            String name;

            public Product(int no, String name) {
                this.no = no;
                this.name = name;
            }

            public int getNo() {
                return no;
            }

            public void setNo(int no) {
                this.no = no;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        List<Product> productList =
                Arrays.asList(new Product(23, "potatoes"),
                        new Product(14, "orange"),
                        new Product(13, "lemon"),
                        new Product(23, "bread"),
                        new Product(13, "sugar"));

        List<String> collectorCollection = productList.stream()
                .map(Product::getName)
                .collect(Collectors.toList());

        collectorCollection.forEach(System.out::println);

        String listToString = productList.stream()
                .map(Product::getName)
                .collect(Collectors.joining());

        System.out.println(listToString);

        String listToString2 = productList.stream()
                .map(Product::getName)
                .collect(Collectors.joining(", ", "<", ">"));

        System.out.println(listToString2);

        Integer summingAmount = productList.stream().collect(Collectors.summingInt(Product::getNo));
        System.out.println(summingAmount);

        Integer summingAmount2 = productList.stream().mapToInt(Product::getNo).sum();
        System.out.println(summingAmount2);

        IntSummaryStatistics statistics = productList.stream().collect(Collectors.summarizingInt(Product::getNo));
        System.out.println(statistics.getMax());
        System.out.println(statistics.getMin());
        System.out.println(statistics.getCount());
        System.out.println(statistics.getAverage());
        System.out.println(statistics.getSum());

        // groupingBy
        System.out.println("=====================");
        Map<Integer, List<Product>> collectorMapOfLists = productList.stream()
                .collect(Collectors.groupingBy(Product::getNo));
        // 같은 수량끼리 묶어서 List 로 Map 형성

        // Partition
        Map<Boolean, List<Product>> mapPartitioned = productList.stream()
                .collect(Collectors.partitioningBy(el -> el.getNo() > 15));
        // true / false 인 두 분류로 나눔

        // matching
        System.out.println("=====================");
        List<String> names2 = Arrays.asList("Eric", "Elena", "Java");

        boolean anyMatch = names2.stream().anyMatch(e -> e.contains("a"));
        System.out.println(anyMatch);
        boolean allMatch = names2.stream().allMatch(e -> e.length() > 4);
        System.out.println(allMatch);
        boolean noneMatch = names2.stream().noneMatch(e -> e.endsWith("a"));
        System.out.println(noneMatch);

        /////////////////// stream 고급

        // 동작 순서서
       System.out.println("=====================");
        names2.stream()
                .filter(e -> {
                    System.out.println("filter() was called.");
                    return e.contains("a");
                })
                .map(e -> {
                    System.out.println("map() was called.");
                    return e.toUpperCase();
                })
                .findFirst();

        //처음 요소인 “Eric” 은 “a” 문자열을 가지고 있지 않기 때문에 다음 요소로 넘어갑니다. 이 때 “filter() was called.” 가 한 번 출력됩니다.
        //다음 요소인 “Elena” 에서 "filter() was called."가 한 번 더 출력됩니다. "Elena"는 "a"를 가지고 있기 때문에 다음 연산으로 넘어갈 수 있습니다.
        //다음 연산인 map 에서 toUpperCase 메소드가 호출됩니다. 이 때 "map() was called"가 출력됩니다.
        //마지막 연산인 findFirst 는 첫 번째 요소만을 반환하는 연산입니다. 따라서 최종 결과는 “ELENA” 이고 다음 연산은 수행할 필요가 없어 종료됩니다

        System.out.println("=====================");
        Stream<String> streams = Stream.of("Eric", "Elena", "Java")
               .filter(e -> e.contains("a"));

        Optional<String> firstElement = streams.findFirst();
//        Optional<String> anyElement = streams.findAny(); // 여기서 에러 터짐. 왜냐면 stream 은 재사용 불가

        // >>>>
        List<String> names3 = Stream.of("Eric", "Elena", "Java")
                .filter(e -> e.contains("a"))
                .collect(Collectors.toList());



        Optional<String> firstElement2 = names3.stream().findFirst();
        Optional<String> anyElement2 = names3.stream().findAny();

        System.out.println(firstElement2);
        System.out.println(anyElement2);

        // null-safe
        List<String> nullList = null;

        collectionToStream(nullList)
                .filter(str -> str.contains("a"))
                .map(String::length)
                .forEach(System.out::println);
    }

    private static <T> Stream<T> collectionToStream(Collection<T> collection){
        return Optional
                .ofNullable(collection)
                .map(Collection::stream)
                .orElseGet(Stream::empty);
    }
}
