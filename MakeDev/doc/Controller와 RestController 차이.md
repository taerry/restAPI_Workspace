## @Controller와 @RestController 차이 : 참조 https://mangkyu.tistory.com/49

* 전통적인 Spring MVC의 컨트롤러인 @Controller와 Restuful 웹서비스의 컨트롤러인 @RestController의 주요한 차이점은 HTTP Response Body가 생성되는 방식
  
### 1. @Controller
   - @Controller는 주로 View를 반환하기 위해 사용. 즉, Controller는 요청을 처리한 후에 ViewName을 반환함
   - Controller가 반환환 뷰의 이름으로부터 View를 렌더링하기 위해서는 ViewResolver가 사용되며, 
     ViewResolver 설정에 맞게 View를 찾아 렌더링함

   **[ Controller로 Data 반환하기 ]**

   - 컨트롤러를 사용하면서 Data를 반환해야 하는 경우도 있음
   - 컨트롤러에서는 데이터를 반환하기 위해 @ResponseBody 어노테이션을 활용해주어야 하며, 이를 통해 Controller도 Json 형태로 데이터를 반환할 수 있음
   - Controller가 요청을 처리한 후에 객체를 반환하는 경우 반환되는 객체는 Json으로 Serialize되어 사용자에게 반환됨

   - 컨트롤러를 통해 객체를 반환할 때에는 일반적으로 ResponseEntity로 감싸서 반환함
   - 그리고 객체를 반환하기 위해서는 viewResolver 대신에 HttpMessageConverter가 동작합니다. 
     HttpMessageConverter에는 여러 Converter가 등록되어 있고, 반환해야 하는 데이터에 따라 사용되는 Converter가 달라짐
   - 단순 문자열인 경우에는 StringHttpMessageConverter가 사용되고, 객체인 경우에는 MappingJackson2HttpMessageConverter가 사용되며, 데이터 종류에 따라 서로 다른 MessageConverter가 작동하게 됨.
   - Spring은 클라이언트의 HTTP Accept 헤더와 서버의 컨트롤러 반환 타입 정보 둘을 조합해 적합한 HttpMessageConverter를 선택하여 이를 처리함.
   - MessageConverter가 동작하는 시점은 HandlerAdapter와 Controller가 요청을 주고 받는 시점임
	
	**[ Controller 예제코드 ]** 
	
	```
	@Controller
	@RequiredArgsConstructor
	public class UserController {
	
		private final UserService userService;
	
		@GetMapping(value = "/users")
		@ResponseBody
		public ResponseEntity<User> findUser(@RequestParam String userName){
			return ResponseEntity.ok(userService.findUser(userName));
		}
		
		@GetMapping(value = "/users/detailView")
		public String detailView(Model model, @RequestParam String userName){
			User user = userService.findUser(userName);
			model.addAttribute("user", user);
			return "/users/detailView";
		}
	}
	```
	* 위 예제의 findUser는 User 객체를 ResponseEntity로 감싸서 반환하고 있고, 
	  User를 json으로 반환하기 위해 @ResponseBody라는 어노테이션을 붙여주고 있습니다. 
	  
	  detailView 함수에서는 View를 전달해주고 있기 때문에 String을 반환값으로 설정해주었습니다. 
	  (물론 이렇게 데이터를 반환하는 RestController와 View를 반환하는 Controller를 분리하여 작성하는 것이 좋습니다.)

### 2. @RestController
   - @RestController는 @Controller에 @ResponseBody가 추가된 것
   - 당연하게도 RestController의 주용도는 Json 형태로 객체 데이터를 반환하는 것
   - 최근에 데이터를 응답으로 제공하는 REST API를 개발할 때 주로 사용하며 
     객체를 ResponseEntity로 감싸서 반환합니다. 
	 이러한 이유로 동작 과정 역시 @Controller에 @ReponseBody를 붙인 것과 완벽히 동일합니다.

	**[ RestController 예제코드 ]** 
	
	```
	@RestController
	@RequiredArgsConstructor
	public class UserController {
	
		private final UserService userService;
	
		@GetMapping(value = "/users")
		public User findUser(@RequestParam String userName){
			return userService.findUser(userName);
		}
	
		@GetMapping(value = "/users")
		public ResponseEntity<User> findUserWithResponseEntity(@RequestParam String userName){
			return ResponseEntity.ok(userService.findUser(userName));
		}
	}
	```
     * 위 예제의 findUser는 User 객체를 그대로 반환
	 이러한 경우의 문제는 클라이언트가 예상하는 HttpStatus를 설정해줄 수 없다는 것입니다. 
	  예를 들어 어떤 객체의 생성 요청이라면 201 CREATED를 기대할 것이지만 객체를 그대로 반환하면 
	  HttpStatus를 설정해줄 수 없습니다. 그래서 객체를 상황에 맞는 ResponseEntity로 감싸서 반환해주어야 합니다.

### 3. ResponseEntity  : 참조 https://velog.io/@2jjong/Spring-Boot-s6xmqo77
   - ResponseEntity는 HTTP 응답을 나타내는 Spring Framework의 클래스
   - 이 클래스는 요청에 대한 응답의 HttpHeader, HttpBody 및 Status Code를 포함하여 클라이언트에게 전달할 수 있는 다양한 기능을 제공

   **[ ResponseEntity 사용 이유 ]**
   1. HTTP 상태 코드 제어: ResponseEntity를 사용하면 응답에 대한 HTTP 상태 코드를 명시적으로 지정할 수 있습니다. 
      이는 클라이언트에게 정확한 상태 정보를 제공하는 데 도움이 됩니다.
   2. 응답 본문 및 헤더 제어: ResponseEntity를 통해 응답 본문과 헤더를 세밀하게 제어할 수 있습니다.
   3. 유연성: ResponseEntity를 사용하면 일반적인 객체 또는 커스텀 클래스를 응답으로 반환할 수 있으며, 
      Spring은 자동으로 해당 객체를 적절한 형식으로 변환합니다.

   **[ 자주 사용되는 HTTP 상태 코드 ]**  참조 : https://restfulapi.net/http-status-codes/
	- HttpStatus.OK: 200 OK
	- HttpStatus.CREATED: 201 Created
	- HttpStatus.NO_CONTENT: 204 No Content
	- HttpStatus.BAD_REQUEST: 400 Bad Request
	- HttpStatus.UNAUTHORIZED: 401 Unauthorized
	- HttpStatus.FORBIDDEN: 403 Forbidden
	- HttpStatus.NOT_FOUND: 404 Not Found
	- HttpStatus.INTERNAL_SERVER_ERROR: 500 Internal Server Error

   **[ ResponseEntity 예제코드 ]** 

	@RestController
	@RequestMapping("/api/books")
	public class BookController {
	
		@Autowired
		private BookService bookService;
	
		@GetMapping("/{id}")
		public ResponseEntity<Book> getBookById(@PathVariable Long id) {
			Book book = bookService.getBookById(id);
	
			if (book != null) {
				return ResponseEntity.ok().body(book);
			} else {
				return ResponseEntity.notFound().build();
			}
		}
	
		// 나머지 메서드들
	}
	```



   **[ 직접적인 Ststus Code를 사용한 ResponseEntity 예제코드 ]** 	

	@RestController
	@RequestMapping("/api/books")
	public class BookController {
	
		@GetMapping("/{id}")
		public ResponseEntity<Book> getBookById(@PathVariable Long id) {
			// 예시로 임의의 책을 반환
			Book book = new Book(id, "Sample Book", "Sample Author");
			
			// 상태 코드를 지정하여 ResponseEntity를 생성
			return ResponseEntity.status(HttpStatus.OK).body(book);
		}
	
		@PostMapping
		public ResponseEntity<Book> createBook(@RequestBody Book book) {
			// 예시로 생성된 책을 반환
			Book savedBook = new Book(1, book.getTitle(), book.getAuthor());
	
			// 상태 코드를 지정하여 ResponseEntity를 생성
			return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
		}
	
		// 나머지 메서드들
	}
	
	* 위와 같이 ResponseEntity.status(HttpStatus)를 사용하여 HTTP 상태 코드를 지정하고 .body로 body 값을 지정하여
	  Response를 사용할 수 있음
	  예시로는 Get 요청에 대한 Status 200 ResponseEntity, Create 요청에 대한 Status 201 ResponseEntity를 사용하였음


​	  
### 4. [Spring] 스프링 에러 처리 (Error Handling for REST with Spring)
   * 참조 : https://velog.io/@6v6/Spring-%EC%8A%A4%ED%94%84%EB%A7%81-%EC%97%90%EB%9F%AC-%EC%B2%98%EB%A6%AC-Error-Handling-for-REST-with-Spring