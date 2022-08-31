package com.example.test01.service.TextTransfer;

import com.example.test01.entity.data.ScrapingEntity;
import com.example.test01.repository.data.ScrapingEntityRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//selenium : 동적인 데이터 수집 가능 (직접 브라우저를 통제해서 사람처럼 브라우저 작동을 하여 데이터 수집) : chromeDriver
//jsoup : httpRequest 사용해서 정적인 데이터(HTML, CSS ..)를 수집
@Service
public class SeleniumExample {

    //selenium 드라이버 다운
    private WebDriver driver;
    private static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
    private static final String WEB_DRIVER_PATH = "C:/Users/admin/Downloads/chromedriver_win32/chromedriver.exe";
    //메서드 매개변수로 받아서 스크래핑 동작을 위한 변수 선언
    private String base_url;

    @Autowired
    ScrapingEntityRepository scrapingEntityRepository;

    public void scraping() {
        //윈도우에서 프로그램들을 관리해주는데, 시스템 단계에서 접속을 해서 프로그램 연동시킴.
        //System.io : 개발한 자바 프로그램(런타임)에서 외부 프로그램을 작동하기 위한 객체
        //바이트 프로그램으로 되어있는 특정 프로그램을 기동하게 만드는 객체
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        //프로그램에 아이디와 있는 위치를 입력후 기동
        driver = new ChromeDriver();
        //드라이버 안에 크롬 드라이버 인스턴스를 만듬
        base_url = "http://ncov.mohw.go.kr/bdBoardList_Real.do?brdId=1&brdGubun=13";
        driver.get(base_url);
        //URL 주소 타이핑으로 본격적으로 시스템 구동, 동작.

        try {
            Thread.sleep(5000);
            //사람인 척 하려고 프로그램을 잠시 쉬게함
            //메소드 동작할 때마다 5초동안 쉬게 명령
            //스크래핑할 페이지의 전체 데이터 출력

            //System.out.println(driver.getPageSource());
            //WebElement는 HTML에서 하나의 튜플이라 보면 된다.
            //WebElement element = driver.findElement(By.tagName("button"));
            //List<WebElement> elements_span = driver.findElements(By.tagName("span"));

            //int checkNum = 0;
            //for(WebElement e : elements_span) {
            //System.out.println(checkNum);
            //System.out.println(e.getText());
            //checkNum++; }
            //스크래핑할 페이지의 전체 데이터 출력
            //System.out.println(driver.getPageSource());


            //tag search
            //엘레멘트 이름으로 크롬 드라이버를 통해 (돔 구조) 특정 메서드로 가져옴 (엔티티 개념에선 튜플) 식별자는 태그네임.
            List<WebElement> elements_button = driver.findElements(By.tagName("button"));// 버튼 여러개니 리스트로
            int checkNum = 0;
            //아래 for문에선 button tag를 가져온다.
            for (WebElement e : elements_button) {
                //button tag 안에 있는 span tage를 가져온다
                List<WebElement> elements_span = e.findElements(By.tagName("span"));
                //Entity 인스턴스를 만듬
                ScrapingEntity scrapingEntity = new ScrapingEntity(); //엔티티 하나가 튜플 하나이므로 총 세개 가져옴
                //아래 for문에서는 span tag를 가져온다.
                //포문을 돌며 버튼 태그의 리스트를 하나씩 가져온다
                //스크래핑 엔티티는 버튼태그에서 생성을 해주고 스판  데이터 태그를 순차적으로 저장
                //포문을 통해 적립이 되면 DB에 저장
                checkNum = 0;
                //아래 for문에서는 span tag를 가져온다.
                for (WebElement e_span : elements_span) {
                    if (!e_span.getText().equals("")) {
                        //텍스트가 null일 경우에는 데이터를 넣지 않기 위함
                        System.out.println(checkNum);
                        System.out.println(e_span.getText());
                        System.out.println(e_span.getTagName());
                        //Entity에 스크래핑을 통해 얻은 데이터를 넣기
                        if (checkNum == 0) {
                            scrapingEntity.setCity(e_span.getText());
                        } else if (checkNum == 1) {
                            scrapingEntity.setCovid19_confirmed(e_span.getText());
                        } else if (checkNum == 2) {
                            scrapingEntity.setIncrease_num(e_span.getText());
                        }
                        checkNum++;
                        //버튼 한번 돌아가면 체크넘이 0,1,2 돌아감
                    }
                }
                //CrudRepo 엔티티 저장
                if (!scrapingEntity.getCity().equals("")) {
                    scrapingEntityRepository.save(scrapingEntity);
                }
            }

            //CSS 출력
            List<WebElement> elemants_canvas = driver.findElements(By.tagName("canvas"));
            for (WebElement e : elemants_canvas) {
                System.out.println(e.getCssValue("width"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //공식문서에서는 close()가 아니라 quit() 권장
            driver.quit();
        }
    }
}
