package com.example.test01.service.ApiTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.autoconfigure.gson.GsonBuilderCustomizer;
import org.springframework.stereotype.Service;



//@Service: 컨테이너에 bean을 등록하여 controller가 불러올 수 있도록 선언
@Service
public class ApiTest {

    public void testAPI() {
        String result = readAPI();

        //파싱 받아서 가공하는 라이브러리 JAVA
        // 1. SimpleJson : 대용량 데이터 처리 속도가 빠름 (골고루 빠름)
        // 2. Jeckson : 평균적으로 빠름 (빅데이터 등 큰 사이즈의 json 처리)
        // 3. Gson : 간단한 데이터 처리 속도가 빠름 (분산 아키텍처 설정 등 작은 용량의 json)

        //json방식은 http 프로토콜을 통해서 데이터 전송규악 (클라이언트 백엔트 통신)
        //백엔드와 백엔드 통신도 json방식 > grpc 통신의 등장으로 다수의 백엔드 통신은 grpc 변경
        //앱 통신도 grpc

        //Gson transfer
        Gson pretty = new GsonBuilder().setPrettyPrinting().create();
        String element = pretty.toJson(result);
        System.out.println("----------testAPI-----------");
        System.out.println(element);

        //String 문자열을 dto 객체로 변환
        //fromJson(문자열, DTO 객체. class (런타임 시점 객체))
        BusDTO busdto = pretty.fromJson(result, BusDTO.class);
        for (int i =0; i< busdto.getResponse().getBody().getNumOfRows(); i++) {
            System.out.println(busdto.getResponse().getBody().getItems().get(i).getCpname());
        }

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

