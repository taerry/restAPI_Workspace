# HTTP(HyperText Transfer Protocol) 스펙

### 1. HTTP Protocol

* HTTP는 클라이언트와 서버가 서로 통신하는 통신 프로토콜
* 프로토콜은 쉽게 말하면, 메시지를 주고받는 두 곳에서 미리 어떻게 데이터를 교환하자고 정한 규칙, 약속
* HTTP가 전송할 수 있는 데이터 종류도 다양합니다. 초기에는 HTML만 전송할 수 있었지만, 
  현재는 멀티미디어 (이미지, 음성, 파일, 영상)와 JSON, XML 형태의 데이터까지 가능
### 2.HTTP Message

* RFC-7230에 정의된 HTTP 요청 메시지의 형식
    HTTP-message = start-line
                 *( header-field CRLF )
                 CRLF
                 [ message-body ]

### 3.HTTP Request Line

    request-line = method SP request-target SP HTTP-version CRLF 

    ** 참고 : SP(Single Space), OWP(Optional White Space)
       HTTP CRLF는 텍스트 파일에서 줄 바꿈을 나타내는 제어 문자 또는 바이트코드인 CR(\r)과 LF(\n)의 조합을 의미함
       - CR은 Carriage Return의 약자로 타자기 시절 페이퍼 롤을 우측으로 이동시키는 명령어
       - LF는 Line Feed의 약자로, 페이퍼 롤을 한 줄씩 아래로 이동시키는 명령어
       - 윈도우에서는 ASCII의 CR+LF로 새줄을 나타내고, 유닉스에서는 LF로 새줄을 나타냄
         맥 OS은 버전 9까지 CR을 썼지만 버전 10부터 LF를 쓰고 있음
       - HTTP 프로토콜에서는 HEADER 필드를  CRLF로 구분함

  
**[ Request Line에 대한 예시 ]**

     GET /where?q=now HTTP/1.1

**[ request target ]**

    request-target = origin-form	GET /where?q=now HTTP/1.1 Host:www.example.org	
                                    가장 많이 사용하는 방식
                / absolute-form	GET http://www.example.org/pub/WWW/TheProject.html HTTP/1.1	
                                    전체 URI작성, 프록시 연결 시 사용
                / authority-form	CONNECT www.example.com:80 HTTP/1.1										                CONNECT 메서드에서만 사용
                / asterisk-form		OPTIONS * HTTP/1.1 Host:www.example.org:8081			
                                    OPTIONS 메서드에서만 사용. 서버 전체를 나타냄

### 4. HTTP Request Method

* GET : 자원조회
* POST : 자원생성
* PUT/PATCH : 자원수정
* DELETE : 자원삭제
* etc : HEAD, CONNECT, OPTIONS, TRACE

 **[ HTTP Rquest Message SPEC 예시 ]**

     POST /create-developer HTTP/1.1
        Content-Type: application/json
        Accept: application/json

        {
          "developerLevel": "JUNIOR",
          "developerSkillType": "FULL_STACK",
          "experienceYears": 2,
          "memberId": "sunny.flower",
          "name": "sun",
          "age": 36
        }

* 첫째줄: 요청라인. Http메서드들이 들어감(GET, PUT, POST 등)
* 두번째줄부터 줄바꿈 나오기 전까지: Header(User-Agent, Accept 등)
* 헤더에서 줄바꿈 이후: Request Body
### 5. Response Status Line

    status-line = HTTP-version SP status-code SP reason-phase CRLF

 **[ Status Line에 대한 예시 ]**

     HTTP/1.1 200 OK

### 6. Status Code

| 상태코드 |   카테고리    | 설명                                            |
| :------: | :-----------: | :---------------------------------------------- |
|   1xx    | Informational | 요청을 전달 받았고, 현재 진행 중임              |
|   2xx    |  Successful   | 요청이 성공적으로 처리됨                        |
|   3xx    |  Redirection  | 요청을 완수하기 위해 더 다른 작업이 필요함      |
|   4xx    | Client Error  | 클라이언트가 유효하지 않은 요청 전달            |
|   5xx    | Server Error  | 유효한 요청에 대해 서버가 제대로 처리할 수 없음 |

**[ HTTP Response Message SPEC 예시 ]**

    HTTP/1.1 200 OK
        Content-Type: application/json
        Transfer-Encoding: chunked
        Date: Sat, 17 Jul 2021 15:33:34 GMT
        Keep-Alive: timeout=60
        Connection: keep-alive

        {
          "developerLevel": "JUNIOR",
          "developerSkillType": "FULL_STACK",
          "experienceYears": 2,
          "memberId": "sunny.flo1wer",
          "name": "sun",
          "age": 36
        }

* 첫번째줄: 상태라인(200,500,등)
* 두번째줄부터 줄바꿈 나오기전({)까지:Header
* 헤더에서 줄바꿈 이후: Response Body
### 7. HEADER 형식

**[ RFC-7230에 정의된 Header의 형식 ]**

    field-name: OWS field-value OWS

* 클론(:)을 기준으로 왼쪽에는 field name, 오른쪽에는 field value가 옴. 키-밸류 형식
* 주의할 점은 field name 이후에 띄어쓰기 없이 곧바로 콜론이 붙는다는 것. 
     반면 콜론과 field value 사이에는 띄어쓰기를 해도 되고, 안 해도 상관없음
* field name은 대소문자를 구분하지 않지만, field value는 구분함

 **[ 아래는 헤더 예시 ]**

    Host: www.example.com
    Accet-Language: kr

