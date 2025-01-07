// JSON 데이터를 웹페이지로 출력하기
// Parsing : 다른 형식으로 저장된 데이터를 목적에 맞는 형식으로 변환하는 것
// JSON Parsing : JSON 형식의 텍스트 문자열을 목적에 맞는 객체로 변환
// window.load() 함수 : 자바스크립트에서 페이지가 로드되면 자동으로 실행되는 전역 콜백함수
// 웹 페이지의 모든 요소들이 로드된 후 호출되며, 웹 페이지에서 여러번 사용해도 window.onload() 함수는 하나만 적용

window.onload=()=>{
	document.getElementById('btn').addEventListener('click',function() {
		console.log("....test.....");
		//-----------------------------------------------------------
		let json={
			"book":[{
				"isbn":"123-456-789",
				"author":{
					"name":"홍길동",
					"email":"gildong.hong@naver.com"
				},
				"editor":{
					"name":"홍길녀",
					"email":"gilnei.hong@naver.com"
				},
				"title":"대한민국 정치에 정의는 있는가?",
				"category":[
					"Non-Fiction", "IT", "Politics"
				],
				"picture":"images/joe.jpg",
				"description":"홍길동 저자의 야심작! 절찬리에 판매중~~~",
				"comment":[{
					"id":"정이봉",
					"text":"대한민국 정치의 문제점을 알 수 있게 되었습니다."
				},
				{
					"id":"김진경",
					"text":"대한민국 정치가 가야햘 방향을 제시해 주었습니다."
				},
				{
					"id":"홍수범",
					"text":"대한민국 정치인의 무능력을 신날하게 보여주었습니다."
				},
				{
					"id":"이정길",
					"text":"너무 잘 읽었습니다. 감사합니다.."
				},
				{
					"id":"유명준",
					"text":"암울한 대한민국의 정치에 새로운 바람이 불었으면 좋겠습니다."
				},
				{
					"id":"신재원",
					"text":"대한민국 국민의 필독서라 생각합니다."
				}],
				"comwinner":["김진경", "이정길", "신재원"],
				"add1":"false",
				"add2":"true"
			}]
		}
		
		json=json["book"];
		console.log("json Length : " + json.length);
		console.log(json);
		console.log("isbn number : " + json[0].isbn);
		console.log(json[0].editor);
		console.log(json[0].category);
		console.log(json[0].comment);
		console.log(json[0].comment[0]);		//첫번쨰 값
		console.log(json[0].comment[json[0].comment.length-1]);		//마지막 값
		
		console.log('----------------------------------------------');
		for(let i=0;i<json.length;i++) {
			console.log(json[i].comment);
			console.log(json[i].comwinner);
		}
		
		console.log('----------------------------------------------');
		//ul 태그노드 생성
		let ul=document.createElement('ul');
		let bookList=document.getElementById('bookList');
		bookList.appendChild(ul);
		
		for(let i=0;i<json.length;i++) {
			for(let j=0;j<json[i].comment.length;j++){
				//댓글 참가자
				let bookComment=json[i].comment[j];
				console.log(bookComment.id + " : " + bookComment.text);
				let bookComments=bookComment.id + " : " + bookComment.text;
				// console.log(json[i].comment[j]);
				//-------------------------------------------------------
				// li 태그 노드 생성
				let li=document.createElement('li');

				// 텍스트 노드 생성
				// let textNode=document.createTextNode("테스트..");
				// let textNode=document.createTextNode(bookComment.id + " : " + bookComment.text);
				let textNode=document.createTextNode(bookComments);
				li.appendChild(textNode);
				ul.appendChild(li);
				
				//List에 붙이기
				// let bookList=document.getElementById('bookList');
				// bookList.appendChild(li);
				//-------------------------------------------------------
			}
			//당첨자
			console.log(json[i].comwinner.join(","));
			
			document.getElementById('noti').innerHTML="댓글 이벤트 당첨자는 아래와 같습니다.";
			document.getElementById('bookListWinner').innerHTML=json[i].comwinner.join(",");
		}
		//-----------------------------------------------------------
	});
};


