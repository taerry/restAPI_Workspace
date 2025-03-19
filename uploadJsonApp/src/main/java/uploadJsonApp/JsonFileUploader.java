package uploadJsonApp;

import java.io.File;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class JsonFileUploader {
	
	private static final String UPLOAD_URL = "http://localhost:9210/api/json/upload"; // REST API 업로드 URL
	
	public static void main(String[] args) {
        File jsonFile = new File("example.json"); // 전송할 JSON 파일 경로

        if (!jsonFile.exists()) {
            System.out.println("파일이 존재하지 않습니다: " + jsonFile.getAbsolutePath());
            return;
        }

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost uploadFile = new HttpPost(UPLOAD_URL);

            // 파일을 포함한 멀티파트 엔티티 생성
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody(
                    "file",
                    jsonFile,
                    ContentType.APPLICATION_JSON,
                    jsonFile.getName()
            );

            HttpEntity multipart = builder.build();
            uploadFile.setEntity(multipart);

            // 요청 실행 및 응답 처리
            HttpResponse response = httpClient.execute(uploadFile);
            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {
                String responseString = EntityUtils.toString(responseEntity);
                System.out.println("서버 응답: " + responseString);
            }
        } catch (Exception e) {
            System.err.println("파일 업로드 중 오류 발생: " + e.getMessage());
        }
    }

}
