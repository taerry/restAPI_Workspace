### ```JSON File Upload RESTAPI with Springboot```
##### 1. 개요
* `JSON 파일을 전송할 수 있는 Spring Boot 기반의 RESP API`

##### 2. RESTAPI
* `Upload API : /api/json/upload`
	+ 파일 업로드 구현을 위하여 MultipartFile 인터페이스 사용
* `Download API : /api/json/download/{fileName}`

##### 3. RESTAPI 테스트
1. HTTP 클라이언트 사용: Apache HttpClient 라이브러리를 사용하여 REST API에 POST 요청
2. 멀티파트 요청 구성
	- MultipartEntityBuilder를 사용해 파일 데이터를 요청 본문에 추가합니다.
	- ContentType.APPLICATION_JSON을 지정하여 JSON 파일임을 명시합니다.
3. REST API 업로드 URL:
	- UPLOAD_URL 변수에 업로드 엔드포인트 경로를 설정합니다.
		+ 예: http://localhost:8080/api/json/upload
4. Apache HttpClient 라이브러리

```
<!-- https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient -->
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpclient</artifactId>
    <version>4.5.14</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.apache.httpcomponents/httpmime -->
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpmime</artifactId>
    <version>4.5.14</version>
</dependency>
```

