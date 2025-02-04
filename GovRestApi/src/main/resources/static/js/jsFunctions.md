## Document.querySelector()  querySelector() 함수
  - 괄호 속에 제공한 선택자와 일치하는 문서 내 첫 번째 Element를 반환
    즉, HTML 문서나 특정한 요소 내에서 지정한 CSS 선택자와 일치하는 첫 번째 요소 객체를 반환
  - 일치하는 요소가 없다면 null 반환
  
  ** 사용 예시
  const selected = document.querySelector("h1");
  
  - 문서 내의 첫 번째 h1 태그를 찾아 selected에 반환해줍니다.
  
  ** 사용 방법 4가지
  1. 태그 select
  2. 클래스 select
  3. id select
  4. 복합 select
  
  
## addEventListener
  - syntax
    * eventTarget.addEventListener('eventType', function)
    * 	이벤트를실행할타겟.addEventListener('이벤트타입', 실행할함수)
      - eventTarget(이벤트 타겟)은 해당 이벤트를 적용할 DOM을 가져와 준다.
	   - eventType(이벤트 타입)은 말 그대로 어떤 타입의 이벤트를 적용할 것인지 써주면 된다. 
	     대표적으로 click DOMContentLoad, scroll submit등등이 있다.
	   - function(실행할함수)에는 이벤트를 발생시켰을 시 실행할 동작을 가져와 준다.
	   
	 * ex) document.querySelector('#temp-save').addEventListener('click', function() {}
	 
	   
## $(document).ready(function(){}
  - jQuery를 사용한 모든 웹페이지는 다음 코드로 시작합니다.
    <script>
		$(document).ready(function(){	});
	</script>
	
  - $(document).ready()는 문서가 준비되면 매개변수로 넣은 콜백 함수를 실행하라는 의미입니다.
  - jquery를 쓰지 않고 순수 javascript로 ready()를 대체하는 코드.
    <script>
		document.addEventListener("DOMContentLoaded", function(){
		 // Handler when the DOM is fully loaded
		});
	</script>


	
