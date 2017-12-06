/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.ac.uos.software_project.aeat;

import kr.ac.uos.software_project.aeat.view.Frame;
import aeat.AEATType;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import kr.ac.uos.software_project.aeat.view.MessagePanel;
import kr.ac.uos.software_project.aeat.view.TitlePanel;

/**
 *
 * @author comkeen
 */
public class Publisher {

    private Frame frame;
    private TitlePanel titlePanel;
    public static final String AEAT_SAMPLE = "xml/AEAT-Example-20170920.xml"; // 샘플 aeat xml 경로
    public static final String AEAT_XML_SCHEMA = "xmlSchema/AEAT-1.0-20170920.xsd"; // aeat schema 경로
    public static final String AEAT_OUTPUT = "xml/output.xml"; // 저장되는 파일 경로
    public static final String MQ_ADDRESS = "tcp://172.16.162.203:61616";
    public static final String DESTIANTION_EXAM = "lecture.goal";

    public Publisher() {
        MyButtonActionListener buttonActionListener = new MyButtonActionListener(this); // 버튼액션리스너 생성
        this.frame = new Frame(buttonActionListener); // 메인 프레임 생성
    }

    //메소드명:aeatMarshalling()
    //입력:aeat 루트엘리먼트 객체(AEATType), 마샬링할 파일 경로 및 이름(String)
    //출력:없음
    //부수효과:없음
    private void aeatMarshalling(AEATType aeat, String path) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(AEATType.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //AEATType 객체를 "path" 경로에 파일로 저장            
            marshaller.marshal(aeat, new File(path));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    //메소드명: aeatUnmarshalling()
    //입력:불러올 파일 경로 및 이름(String)
    //출력:언마샬링한 xml 파일 루트엘리먼트 객체
    //부수효과:없음
    private AEATType aeatUnmarshalling(String path) {
        AEATType aeat = null;
        try {
            File file = new File(path);
            JAXBContext jaxbContext = JAXBContext.newInstance(AEATType.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            //XML 파일로부터 AEATTYPE 객체 반환
            aeat = (AEATType) ((JAXBElement) jaxbUnmarshaller.unmarshal(file)).getValue();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return aeat;
    }

    public static String aeatToXml(AEATType aeat) {
        String result = "";
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(AEATType.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //AEATType 객체를 "path" 경로에 파일로 저장
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(aeat, stringWriter);
            result = stringWriter.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    private AEATType goToAeatFromSender(){
        AEATType aeat = null;
        try {
            String content = frame.getMesaagePanel();
            JAXBContext jaxbContext = JAXBContext.newInstance(AEATType.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            //XML 문자열로부터 AEATTYPE 객체 반환
            StringReader reader = new StringReader(content);
            aeat = (AEATType) ((JAXBElement) jaxbUnmarshaller.unmarshal(reader)).getValue();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return aeat;
    }
    private AEATType goToAeatFromReceiver(){
        AEATType aeat = null;
        try {
            String content = frame.getReceivePanel();
            JAXBContext jaxbContext = JAXBContext.newInstance(AEATType.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            //XML 문자열로부터 AEATTYPE 객체 반환
            StringReader reader = new StringReader(content);
            aeat = (AEATType) ((JAXBElement) jaxbUnmarshaller.unmarshal(reader)).getValue();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return aeat;
    }
    public void onClickedGoToAeat1(){
        frame.loadAeat(this.goToAeatFromSender());
        frame.clearMessagePanel();
    }
    public void onClickedGoToAeat2(){
        frame.loadAeat(this.goToAeatFromReceiver());
        frame.clearReceivePanel();
    }
    
    public void onClickedLoadButton() {
        //String path = AEAT_SAMPLE;
        String path = "xml/";
        path = path+(frame.getTitleToLoad());
        frame.loadAeat(this.aeatUnmarshalling(path));
        System.out.println("Load xml from: " + path);
    }

    public void onClickedSaveButton() {
        //String path = AEAT_OUTPUT;
        String path = "xml/";
        path = path+(frame.getTitleToSave());
        this.aeatMarshalling(frame.getAeat(), path);
        System.out.println("Save xml to: " + path);
        frame.refresh();
    }
    
    public void onClickedxmlClearButton(){
        frame.clear();
    }

    public void onClickedClearButton() {
        frame.clearMessagePanel();
    }
    
    public void onClickedClearReceiverButton() {
        frame.clearReceivePanel();
    }
    
    public void onClickedSendButton(){
        frame.sendMessagePanel(); 
    }
    
    public void onClickedReceiveButton(){
        frame.receiveMessagePanel();
    }
    
    public void onClickedConfigDestination(){
        
        frame.configDestination();
    }
    
    public void onClickedConfigBroker(){
        frame.configBroker();
    }

}
