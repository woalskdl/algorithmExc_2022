package Jan.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

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

    }
}
