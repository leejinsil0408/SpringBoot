package com.example.test01.service.ApiTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonParser;
import org.springframework.boot.autoconfigure.gson.GsonBuilderCustomizer;
import org.springframework.stereotype.Service;



//@Service: 컨테이너에 bean을 등록하여 controller가 불러올 수 있도록 선언
@Service
public class ApiTest {

    //파싱 받아서 가공하는 라이브러리 JAVA
    // 1. SimpleJson : 대용량 데이터 처리 속도가 빠름
    // 2. Jeckson : 평균적으로 빠름
    // 3. Gson : 간단한 데이터 처리 속도가 빠름
    public void testAPI() {
        String result = readAPI();

//        String jsonStr = gson.toJson(result);
//        System.out.println(jsonStr);

        Gson pretty = new GsonBuilder().setPrettyPrinting().create();
        String element = pretty.toJson(result);

        System.out.println(element);

    }


    public String readAPI() {
        //인증 키
        String key = "%2Fk49W4UhNTuGlvyhZ6NCaHVhV1%2BBp0wbhWy0YjmvKgHQSFbVPwQqzw4ppSYg8O9ubHyLPYi8N%2F0e4yGvEQKGug%3D%3D";

        //파싱할 데이터를 저장할 변수
        String bfResult = "";
        String brResult = "";
        StringBuilder sb = new StringBuilder();

        //JSON API라는 것은 네트워크 통신을 통해 데이터를 다운 받아 서비스 할 수 있도록 가공하기 위한 데이터
        //네트워크 통신이 끊기거나 예외적인 상황을 상정
        try {

            //https는 인증서가 필요하므로 http로 데이터 다운
            URL url = new URL("https://apis.data.go.kr/B551177/BusInformation/getBusInfo?serviceKey=" +
                    key + "&numOfRows=10&pageNo=1&area=1&type=json");

            //인증서가 필요한 객체
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader br =
                    new BufferedReader
                            (new InputStreamReader(con.getInputStream(), "UTF-8"));

            while ((brResult = br.readLine()) != null) {
                sb.append(brResult);
                System.out.println(brResult);
            }
            br.close();
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
