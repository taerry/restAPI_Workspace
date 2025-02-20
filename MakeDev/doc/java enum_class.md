## Java enum Class

  * enum 클래스는 열거체(enumeration type)으로 JDK 1.5 이상의 버전에서만 사용이 가능
  * 클래스처럼 보이게 하는 상수
  * 서로 관련있는 상수들끼리 모아 상수들을 정의하는것
  * enum 클래스 형을 기반으로 한 클래스형 선언

###  1. enum Class 특징
  1. 열거형으로 선언된 순서에 따라 0부터 index 값을 가진다.(순차적으로 증가)
  2. enum 열거형으로 지정된 상수들은 모두 대문자로 선언한다.
  3. 열거형 변수들을 선언한 후 마지막에 세미콜론(;)을 찍지 않는다.
  4. 상수와 특정 값을 연결시킬경우 마지막에 세미콜론(;)을 붙여줘야한다.

###  2. enum Class 문법
  * 문법 : enum 열거체 이름 {상수1, 상수2, ...}
  * 코드 : enum Company {SK, LG, KT, SAMSUNG, APPLE}
  * 사용법 : Company.APPLE

###  3. enum Class 예
	```
	@Getter
	@AllArgsConstructor
	public enum StatusCode {
		
		EMPLOYED("고용"),
		RETIRED("퇴직");
		
		private final String description;
	}
	``` 

###  4. enum Class Method
   ```
   메소드						내용
   compareTo(E e)			매개 변수로 받은 enum 객체 상수와의 순서차이를 리턴한다.
   getDeclaringClass()	클래스 타입의 enum을 리턴한다.
   name()						상수의 이름을 리턴한다.
   ordinal()					상수의 순서를 리턴한다.
   valueOf()					static 메소드로 enum에 선언된 모든 상수를 배열로 리턴한다.
   ```