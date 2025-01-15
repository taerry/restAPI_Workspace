$(document).ready(function() {
	$.ajax({
		
		//ajsx 홉션 설정
		url:"https://apis.data.go.kr/B552061/jaywalking/getRestJaywalking?serviceKey=6%2BSoZrtz9bE%2F4NqwrzS7aD60NwLBIQ%2B%2BhEMzWAe%2BMl%2BG5ETMqp0u4IVMYPBje7AQ02wfi7PpEnE9jMp4y1OKWQ%3D%3D&searchYearCd=2017&siDo=11&guGun=680&type=json&numOfRows=10&pageNo=1",
		type:"GET",
		dataType:"JSON",
		
		//요청 성공 시 할일 처리
		success:function(data) {
			
			console.log(data.items.item, typeof data)
			
			data = JSON.stringify(data)	//data가 object -> string으로 변경됨
			
			data = JSON.parse(data)	//data가 string -> object로 변경됨
			
			//할일 처리
			let api_data = "";
			$.each(data.items.item, function(key, value) {
						api_data +="<tr>";
						api_data +="<td>" + key + "</td>";
						api_data +="<td>" + value.sido_sgg_nm + "</td>";
						api_data +="<td>" + value.spot_nm + "</td>";
						api_data +="<td>" + value.dth_dnv_cnt + "</td>";
						api_data +="<td>" + value.se_dnv_cnt + "</td>";
						api_data +="<td>" + value.sl_dnv_cnt + "</td>";
						api_data +="</tr>";
					});
			
			//페이지 하단에 붙이기
			$('#member_table').append(api_data);
		}
	});
});

$(document).ready(function(){
	alert("사고발생 다발지역 표시를 완료하였습니다.");
});
$(document).ready(function(){
	alert("Second READY");
});