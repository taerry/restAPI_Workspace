package javaStream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class JavaStreamTest01 {

	public static void main(String[] args) {
		
		List<String> list = Arrays.asList("가", "나", "다", "라");
		Stream<String> stream = list.stream();
		stream.forEach(name -> System.out.print(name + " "));
		System.out.println();
		list.stream().forEach(name -> System.out.print(name + " "));

	}

}
