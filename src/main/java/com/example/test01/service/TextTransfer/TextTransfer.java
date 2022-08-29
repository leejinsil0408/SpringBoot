package com.example.test01.service.TextTransfer;

import ch.qos.logback.core.net.SyslogOutputStream;

import javax.sound.midi.SysexMessage;
import javax.swing.plaf.synth.SynthUI;

public class TextTransfer {

    public String TransferText3Word(String text) throws Exception {

        //java 문자열 치환 내장메서드 : split, subString..
        String wordFirst3 = text.substring(0, 3);
        System.out.println("앞의 3글자 = " + wordFirst3);
        String wordLast = text.substring(4, text.length());
        System.out.println("뒤의 나머지 글자 = " + wordLast);
        //substring과 split 구조 차이
        //subString : 원본 배열을 참조해서 순번과 길이만 가지고 자름 (객체를 따로 생성해서 관리 x)
        //split : 새로운 인스턴스를 만들어서 문자열을 자르고,
        // 더불어 결과괎을 String 배열로 받아옴 (객체를 따로 생성해서 관리)

        //replaceAll 메서드의 변경 할 값에 "."을 쓰면 모든 문자를 지정
        wordLast = wordLast.replaceAll(".", "*");
        System.out.println(wordFirst3);
        System.out.println(wordLast);

        System.out.println(wordFirst3 + wordLast);
    }
}

