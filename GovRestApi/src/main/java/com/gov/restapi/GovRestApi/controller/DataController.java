package com.gov.restapi.GovRestApi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DataController {

	@PostMapping("/submitData")
	@ResponseBody
	public String submitData(@RequestBody String data) {
	// 서버에서 데이터를 처리하는 로직
	System.out.println("Received data: " + data);
	return "Data received successfully!";
	}
	
	@PostMapping("/submitData2")
	@ResponseBody
	public String submitData2(@RequestBody String data) {
	// 서버에서 데이터를 처리하는 로직
	System.out.println("Received data2: " + data);
	return "Data received successfully2!";
	}
	
	@GetMapping("/fetchTest")
    public String fetchTest(){
		return "fetchTest";
//        return "fetchAwait"; // fetchAwait.html
    }
	
	// JsonPlanceHolder의 RestApi를 이용하여 JSON Data 검색을 위한 HTML Page 호출
	@GetMapping("/jsonPlace")
	public String jsonPlace() {
		return "jsonPlace/jsonPlaceTest01";
	}
	
	@GetMapping("/jsonPosts")
	public String jsonPosts() {
		return "jsonPlace/jsonPlaceTest02";
	}
	
}
