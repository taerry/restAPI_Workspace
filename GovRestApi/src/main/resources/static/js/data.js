console.log("......Called data.js.......");
$(document).ready(function() {
	console.log("......$jquery 함수 실행.......");
	const json='/json/data.json';
	$.getJSON(json, function(data) {
		// 할 일 처리
		let test_data="...$jquery .getJSON 함수 안에서 실행.....";
		console.log(test_data);
		let member_data="";
		$.each(data, function(key, value) {
			console.log(key);
			member_data +="<tr>";
			member_data +="<td>" + value.id + "</td>";
			member_data +="<td>" + value.name + "</td>";
			member_data +="<td>" + value.age + "</td>";
			member_data +="<td>" + value.tel + "</td>";
			member_data +="<td>" + value.homepage + "</td>";
			member_data +="<td>" + value.address + "</td>";
			member_data +="<td>" + value.gender + "</td>";
			member_data +="<td>" + value.job + "</td>";
			member_data +="<td>" + value.hobby + "</td>";
			member_data +="</tr>";
		});
		$("#member_table").append(member_data);
		console.log("......Table에 데이터를 뿌려주었음.......");
	});
});