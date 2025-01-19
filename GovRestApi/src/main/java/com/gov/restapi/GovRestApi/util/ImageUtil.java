package com.gov.restapi.GovRestApi.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ImageUtil {
	
	public static String makePath(String uploadPath, String fileName, Long book_id) throws IOException {
		
		String path=uploadPath+book_id;
		Files.createDirectories(Paths.get(path));
		
		return new File(path).getAbsolutePath()+"\\"+fileName;	//업로드할 파일의 절대경로 만들기
	}

}


/*
 * Java Files 클래스 : 자바에서는 파일과 디렉토리에 대한 정보를 가지고있는 java.io.File, java.nio.file.Files 
 *                   클래스를 제공하고 있습니다. Files는 File을 개선한 클래스입니다.
 *  
 *  File 객체 생성은 다음과 같이 합니다. : File file = new File("파일 경로");
 *  위와 같이 객체를 생성했다고 해서 없던 파일이 바로 생기거나, 바로 작업을 할 수 있는 것은 아닙니다. 
 *  exists() 메소드를 통해서 지정한 파일이 존재하고 있는지 아닌지를 판별하고 그 결과에 따라서 추가적인 작업을 수행하게 됩니다.
 *  
 *  Files 클래스는 정적 메소드로만 구성되어 있는 클래스이기에 File 처럼 객체를 생성해서 이용할 필요가 없습니다.
 *  Files의 정적 메소드들의 공통점으로는 매개변수로 Path 객체를 사용한다는 점 입니다. 
 *  Path 객체는 파일이나 디렉토리의 경로에 대한 정보를 가지고 있는 객체입니다. 
 *  경로 정보는 정적 메소드 get()을 통해서 얻을 수 있습니다.
 *  Path path = Paths.get("파일/디렉토리 경로");
 */