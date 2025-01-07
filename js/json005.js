// JSON 데이터 객체와 문자열로 변환하기
// JSON.parse() : JSON 데이터를 객체로 변환
// JSON.stringify() : JSON 데이터를 문자열로 변환

// [1] : JSON.parse(jsonText) --> JSON 형식의 텍스트를 객체로 변환

let jsonTextStr='{"name":"홍길동", "age":22, "nationality":"대한민국"}';
let jsonTextObj={"name":"홍길동", "age":22, "nationality":"대한민국"};

console.log("변환전 : " + typeof jsonTextStr);	//String
console.log("변환전 : " + typeof jsonTextObj);	//Object
console.log("성명 : " + jsonTextObj.name);	//홍길동

let jsonObj=JSON.parse(jsonTextStr);
console.log('------JSON 데이터를 객체로 변환 확인----------------------');
console.log(jsonObj);
console.log("성명 : " + jsonObj.name);	//홍길동
console.log("이름과 나이는 홍길동(22)이며, 국적은 대한민국입니다.");
console.log(jsonObj.name + " " + jsonObj.age + " " + jsonObj.nationality);
console.log('이름은 ${jsonObj.name}');
console.log('이름과 나이는 ${jsonObj.name}(${jsonObj.age})이며, 국적은 ${jsonObj.nationality}입니다.');

// [1] : JSON.stringify(jsonObj) --> 데이터 객체를 JSON 형식의 텍스트로 변환
console.log('');
console.log('------데이터 객체를 JSON 형식의 텍스트로 변환------------------');
let jsonStr=JSON.stringify(jsonObj);
console.log("jsonObj의 객체 타입은 : " + typeof jsonObj);
console.log("jsonStr의 객체 타입은 : " + typeof jsonStr);
console.log(jsonStr);
// JSON 객체를 JSON 문자열 형식으로 깔끔하게 표시함
console.log('------JSON 객체를 JSON 문자열 형식으로 깔끔하게 표시함---------');
console.log(JSON.stringify(jsonObj, null, 2));