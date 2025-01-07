window.onload=()=> {
	const book={
		"isbn":"123-456-789",
		"author":{
			"name":"홍길동",
			"email":"dong.hong@nave.com"
		},
		"editor":{
			"name":"홍길녀",
			"email":"gil.hong@naver.com"
		},
		"title":"대한민국은 정의로운 사회인가?",
		"category":[
			"Non-Fiction","IT","Politics"	
		]
	}

console.log(book["author"].name);
console.log(book["editor"].name);
console.log(book["isbn"]);
console.log(book.isbn);
console.log(book.title);
console.log(book.category);
console.log(book.category[0]);

// 개별 엑세스
let val="";
val=book.category[0];
document.getElementById("aaa").innerText=val;

// 반복문을 이용한 엑세스
let category_value="";
for(let i=0;i<book.category.length;i++) {
	// category_value+=book.category[i] + "<br>";
	category_value+=`${book["category"][i]} <br>`;
}
document.getElementById("bbb").innerHTML = category_value;

// for in
let cat_val="";
for(let i in book.category) {	// i in의 i는 배열 index
	cat_val+=book.category[i] + "<br>";
}
document.getElementById("ccc").innerHTML = cat_val;

// for of
let cat_val2="";
for(let value of book.category) {	// value of의 i는 배열 index
	cat_val2+=value + "<br>";
}
document.getElementById("ddd").innerHTML = cat_val2;

const el1=document.getElementById("a1");
console.log(el1.innerHTML);

const el2=document.getElementById("example");
console.log(el2.innerHTML);

//메뉴 변경하기 : innerHTML 사용
const food = document.getElementById("food");
const btn = document.getElementById("btn");

btn.addEventListener("click", () => {
    food.innerHTML = `
        <li>짜장면</li>
        <li>짬뽕</li>
        <li>우동</li>
    `;
});

//문자열 변경하기 : innerTEXT 사용
const ele = document.getElementById("str");
const btn2 = document.getElementById("btn2");

btn2.addEventListener("click", () => {
    ele.innerText = "반갑습니다!";
});

};	
