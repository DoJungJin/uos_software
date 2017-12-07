/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.ac.uos.software_project.aeat.view;

import aeat.AEATType;
import aeat.AEAType;
import aeat.AEAtypeType;
import aeat.AudienceType;
import aeat.HeaderType;
import aeat.LiveMediaType;
import aeat.MediaType;
import aeat.MediaTypeType;
import aeat.TypeType;
import java.awt.Component;
import kr.ac.uos.software_project.aeat.MyButtonActionListener;
import java.awt.Dimension;
import java.awt.Font;
import java.rmi.ConnectException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import kr.ac.uos.software_project.aeat.Publisher;
import kr.ac.uos.software_project.aeat.network.ActiveMQConsumer;
import kr.ac.uos.software_project.aeat.network.ActiveMQProducer;

/**
 *
 * @author comkeen
 */
public class Frame {
    String DESTIANTION_EXAM = "test.goal"; // 데스티네이션 이름
                                              // sender 와 receiver둘다에게 필요하다. 우체통 이름같은 것.
    String MQ_ADDRESS = "tcp://localhost:61616"; // 메시지 브로커 주소
                                              // 메세지를 전달해주는 놈의 주소
    
    private AeaPanel aeaPanel;
    private HeaderPanel headerPanel;
    private AeatextPanel aeatextPanel;
    private LiveMediaPanel liveMediaPanel;
    private MediaPanel mediaPanel;
    private JPanel buttonPanel;
    private Connection connection;
    private MyButtonActionListener buttonActionListener;
    private TitlePanel titlePanel;
    private filePanel filePanel;
    private ConfigPanel configPanel;
    
    public static final String TITLE = "AEAT Publisher";
    public static final Dimension LABEL_DIMENSION = new Dimension(150, 40);
    public static final Font LABEL_FONT = new Font(Font.DIALOG, Font.PLAIN, 14);
    private MessagePanel messagePanel;
    private MessagePanel receivePanel;
   
    public Frame(MyButtonActionListener buttonActionListener) {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JFrame frame = new JFrame(TITLE);
        this.buttonActionListener = buttonActionListener;
        
        JPanel content = new JPanel();
        
        JTabbedPane mainTabbedPanel = new JTabbedPane() ;
               
        mainTabbedPanel.add("AEAT 편집", initAeatEditTabPanel());
        mainTabbedPanel.add("메시지", initMessageTabPanel());
        mainTabbedPanel.add("설정 및 기타",initConfigTabPanel());
        mainTabbedPanel.setPreferredSize(new Dimension(1200,700));
        content.add(mainTabbedPanel);
        
        
        JPanel myInfo = new JPanel();
        JLabel info = new JLabel("Copyright 2017. BeomwooMoon All rights reserved.");
        myInfo.add(info);
        myInfo.setBorder(new EtchedBorder(EtchedBorder.RAISED));
        myInfo.setPreferredSize(new Dimension(1200,30));
        
        
        JPanel all = new JPanel();
        all.setLayout(new BoxLayout(all,BoxLayout.Y_AXIS));
        all.add(content);
        all.add(myInfo);
        
        frame.getContentPane().add(all);
        
        frame.setPreferredSize(new Dimension(1200, 800));
        frame.setSize(new Dimension(1200, 800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }
    
    //메소드명: initAeatEditTabPanel
    //입력: 없음
    //출력: 첫 화면(AEAT편집)에 대한 필요한 요소를 담은 JPanel
    //부수효과: AEAT편집창에서 필요한 항목을 JPanel에 담는다.
    private JPanel initAeatEditTabPanel() {
        JScrollPane scroll = new JScrollPane();
        JPanel scrollPanel = new JPanel();
        scrollPanel.setLayout(new BoxLayout(scrollPanel,BoxLayout.Y_AXIS));
        
        JPanel aeatEditTabPanel = new JPanel();
        aeatEditTabPanel.setLayout(new BoxLayout(aeatEditTabPanel, BoxLayout.Y_AXIS));
        this.filePanel = new filePanel(buttonActionListener);
        aeatEditTabPanel.add(filePanel);
        this.titlePanel = new TitlePanel(buttonActionListener);
        aeatEditTabPanel.add(titlePanel);
        
        
        this.aeaPanel = new AeaPanel(buttonActionListener);
        scrollPanel.add(aeaPanel);   
        this.aeatextPanel = new AeatextPanel(buttonActionListener);
        scrollPanel.add(aeatextPanel);     
        this.headerPanel = new HeaderPanel(buttonActionListener);
        scrollPanel.add(headerPanel);
        this.liveMediaPanel = new LiveMediaPanel(buttonActionListener);
        scrollPanel.add(liveMediaPanel);
        this.mediaPanel = new MediaPanel(buttonActionListener);
        scrollPanel.add(mediaPanel);
        scroll.setViewportView(scrollPanel);
        scroll.getVerticalScrollBar().setUnitIncrement(12); // 스크롤 속도 조절
        
        
        aeatEditTabPanel.add(scroll);
        
        
        
        this.buttonPanel = initButtonPanel();
        aeatEditTabPanel.add(buttonPanel);
        
        return aeatEditTabPanel;
    }

    //메소드명: initButtonPanel
    //입력: 없음
    //출력: AEAT편집창에서 사용될 버튼을 담은 JPanel
    //부수효과: AEAT편집창에서 필요한 버튼을 JPanel에 담는다.
    private JPanel initButtonPanel() {
        JPanel panel = new JPanel();
        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(buttonActionListener);
        panel.add(loadButton);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(buttonActionListener);
        panel.add(saveButton);
        
        JButton refreshButton = new JButton("Clear");
        refreshButton.addActionListener(buttonActionListener);
        panel.add(refreshButton);

        return panel;
    }
    
    //메소드명: init MessageTabPanel
    //입력: 없음
    //출력: 메세지창에서 필요한 항목을 담은 JPanel
    //부수효과: 메세지 창에서 필요한 항목을 JPanel에 담는다.
    private JPanel initMessageTabPanel() {
        JPanel MessagePanel = new JPanel();
        JScrollPane messageScroll = new JScrollPane();
        JScrollPane receiveScroll = new JScrollPane();
        MessagePanel.setLayout(new BoxLayout(MessagePanel, BoxLayout.Y_AXIS));
               
        this.messagePanel = new MessagePanel(buttonActionListener);
        messageScroll.setViewportView(messagePanel);
        messageScroll.setPreferredSize(new Dimension(600,300));
        MessagePanel.add(messageScroll);
        
        this.buttonPanel = initSendMessageButtonPanel();
        MessagePanel.add(buttonPanel);
        
        this.receivePanel = new MessagePanel(buttonActionListener);
        receivePanel.setEditable(false);
        receivePanel.setTextArea("메세지를 받기를 시작하시려면 '메세지 받기'버튼을 누르세요.");
        receiveScroll.setViewportView(receivePanel);
        receiveScroll.setPreferredSize(new Dimension(1200,300));
        MessagePanel.add(receiveScroll);
        
        this.buttonPanel = initReceiveMessageButtonPanel();
        MessagePanel.add(buttonPanel);
        
        return MessagePanel;
    }
    
    //메소드명: initConfigTabPanel
    //입력: 없음
    //출력: 설정 및 기타창에서 필요한 항목을 담은 JPanel
    //부수효과: 설정 및 기타창에 필요한 항목을 JPanel에 담는다.
    private JPanel initConfigTabPanel(){
        JPanel ConfigPanel = new JPanel();
        ConfigPanel.setLayout(new BoxLayout(ConfigPanel, BoxLayout.Y_AXIS));
        
        this.configPanel = new ConfigPanel(buttonActionListener, this);
        ConfigPanel.add(configPanel);
        return ConfigPanel;
    }
    
    //메소드명: initSendMessageButtonPanel
    //입력: 없음
    //출력: 메세지 창중 입력창에서 필요한 버튼을 담은 JPanel
    //부수효과: 메세지 창에서 필요한 버튼을 구성한다
    private JPanel initSendMessageButtonPanel(){
        JPanel panel = new JPanel();
        JButton sendButton = new JButton("메세지 보내기");
        JButton goToAeatFromSender = new JButton("AEAT편집기로 보내기1");
        JButton clearButton = new JButton("메세지 보내기창 비우기");
        sendButton.addActionListener(buttonActionListener);
        goToAeatFromSender.addActionListener(buttonActionListener);
        clearButton.addActionListener(buttonActionListener);
        panel.add(sendButton);
        panel.add(goToAeatFromSender);
        panel.add(clearButton);
        return panel;
    }
    
    //메소드명: initReceiveMessageButtonPanel
    //입력: 없음
    //출력: 메세지 창중 수진창에서 필요한 버튼을 담은 JPanel
    //부수효과: 메시지 창에서 필요한 버튼을 구성한다
    private JPanel initReceiveMessageButtonPanel(){
        JPanel panel = new JPanel();
        JButton receiveButton = new JButton("메세지 받기");
        JButton goToAeatFromReceiver = new JButton("AEAT편집기로 보내기2");
        JButton clearReceiveButton = new JButton("메세지 받기창 비우기");
        
        receiveButton.addActionListener(buttonActionListener);
        goToAeatFromReceiver.addActionListener(buttonActionListener);
        clearReceiveButton.addActionListener(buttonActionListener);
        panel.add(receiveButton);
        panel.add(goToAeatFromReceiver);
        panel.add(clearReceiveButton);
        
        
        return panel;
    }
    //메소드명:loadAeat()
    //입력:하위 패널들의 텍스트필드에 표출할 데이터인 aeat 객체
    //출력:없음
    //부수효과:하위 패널의 loadAeat() 메소드 호출
    public void loadAeat(AEATType aeat) {
        aeaPanel.loadAeat(aeat);
        headerPanel.loadAeat(aeat);
        aeatextPanel.loadAeat(aeat);
        messagePanel.loadAeat(Publisher.aeatToXml(aeat));
        liveMediaPanel.loadAeat(aeat);
        mediaPanel.loadAeat(aeat);
    }
    
    //메소드명: clear
    //입력: 없음
    //출력: 없음
    //부수효과: 하위 패널의 clear함수를 호출한다.
    public void clear(){
        headerPanel.clear();
        aeaPanel.clear();
        aeatextPanel.clear();
        liveMediaPanel.clear();
        mediaPanel.clear();
    }

    //메소드명:getAeat()
    //입력:없음
    //출력:하위 패널들의 텍스트필드로부터 AEAT 요소 값들을 읽어와서 AEAT 객체 생성
    //부수효과:없음
    public AEATType getAeat() {
        AEATType aeat = new AEATType();
        AEAType aea = new AEAType();
        aea.setAeaId(aeaPanel.getAeaId());
        aea.setIssuer(aeaPanel.getIssuer());
        aea.setAudience(AudienceType.fromValue(aeaPanel.getAudience()));
        aea.setAeaType(AEAtypeType.fromValue(aeaPanel.getAeaType()));
        aea.setRefAEAId(aeaPanel.getAeaId());
        aea.setPriority(aeaPanel.getPriority());
        aea.setWakeup(aeaPanel.getWakeup());
        
        aeatextPanel.getAeaText(aea);
        aeatextPanel.getLang(aea);
        
        HeaderType header = new HeaderType();
        TypeType typeType = new TypeType();
        typeType.setValue(headerPanel.getEventCode());
        header.setEventCode(typeType);
        header.getEventCode().setType(headerPanel.getEventCodeType());
        headerPanel.getEventDesc(header);
        headerPanel.getLang(header);
        headerPanel.getLocation(header);
        headerPanel.getLocationType(header);
        header.setEffective(stringToXMLGregorianCalendar(headerPanel.getEffective()));
        header.setExpires(stringToXMLGregorianCalendar(headerPanel.getExpires()));
        
        
        MediaType media = new MediaType();
        media.setMediaDesc(mediaPanel.getMediaDesc());
        media.setMediaType(MediaTypeType.fromValue(mediaPanel.getMediaType()));
        media.setUrl(mediaPanel.getUrl());
        media.setAlternateUrl(mediaPanel.getAlternateUrl());
        media.setContentType(mediaPanel.getContentType());
        media.setContentLength(mediaPanel.getContentLength());
        media.setMediaAssoc(mediaPanel.getMediaAssoc());
        media.setLang(mediaPanel.getLang());
        
        LiveMediaType liveMedia = new LiveMediaType();
        liveMediaPanel.getBsid(liveMedia);
        liveMedia.setServiceId(liveMediaPanel.getServiceId());
        liveMediaPanel.getServiceName(liveMedia);
        liveMediaPanel.getLang(liveMedia);
        
        aea.setHeader(header);
        aea.getMedia().add(media);
        aea.setLiveMedia(liveMedia);

        aeat.getAEA().add(aea);
        return aeat;
    }
    
    //메소드명: getTitleToSave
    //입력: 없음
    //출력: 저장할 파일의 제목을 반환
    //부수효과: 마샬링하여 xml파일로 저장할 때 파일 제목을 위해 titlePanel의 메소드 호출
    public String getTitleToSave(){
        String title = titlePanel.getTitleToSave();
        return title;
    }
    
    //메소드명: getTitleToLoad
    //입력: 없음
    //출력: 불러올 파일의 제목을 반환
    //부수효과: 언마샬링할 xml파일을 불러올 때 불러올 파일제목을 알기 위해 filePanel의 메소드 호출
    public String getTitleToLoad(){
        String title = filePanel.getTitleToLoad();
        return title;
    }

    //메소드명:stringToXMLGregorianCalendar()
    //입력:XMLGregorianCalendar 타입으로 변환할 문자열(String)
    //출력:XMLGregorianCalendar 객체
    //부수효과:없음
    private XMLGregorianCalendar stringToXMLGregorianCalendar(String input) {
        XMLGregorianCalendar result = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = simpleDateFormat.parse(input);
            GregorianCalendar gregorianCalendar = (GregorianCalendar) GregorianCalendar.getInstance();
            gregorianCalendar.setTime(date);
            result = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        } catch (ParseException | DatatypeConfigurationException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    //메소드명: getMessagePanel
    //입력: 없음
    //출력: MessagePanel의 getTextArea메소드 호출
    //부수효과: 없음
    public String getMesaagePanel(){
        return messagePanel.getTextArea();
    }
    
    //메소드명: getReceivePanel
    //입력: 없음
    //출력: ReceivePanel의 getTextArea메소드 호출
    //부수효과: 없음
    public String getReceivePanel(){
        return receivePanel.getTextArea();
    }
    
    //메소드명: clearMessagePanel
    //입력: 없음
    //출력: 없음
    //부수효과: 메세지패널을 clear하기 위해 메소드 호출
    public void clearMessagePanel() {
        messagePanel.clearTextArea();
    }
    
    //메소드명: clearReceivePanel
    //입력: 없음
    //출력: 없음
    //부수효과: 리시브패널을 clear하기 위해 메소드 호출
    public void clearReceivePanel(){
        receivePanel.clearTextArea();
    }
    
    //메소드명: refresh
    //입력: 없음
    //출력: 없음
    //부수효과: 새로운 파일이 저장되었을때 파일 목록을 새로 불러오기 위한 메소드 호출
    public void refresh(){
        System.out.println("프레임에서 새로고침 실행");
        filePanel.refresh();
    }
    
    //메소드명: sendMessagePanel
    //입력: 없음
    //출력: 없음
    //부수효과: 메세지 패널에 있는 내용을 activeMQ를 통해 메세지를 보내기
    public void sendMessagePanel(){
        try {
            //producer 생성
            ActiveMQProducer producer = new ActiveMQProducer(MQ_ADDRESS,messagePanel);
            // Send Message to Destination
            
            //메세지 패널에 있는 문자열 가져오기
            String text = messagePanel.getTextArea();
            
            producer.sendMessageTo(text, DESTIANTION_EXAM,messagePanel);
            
            Thread.sleep(100); // 필요한가?
            producer.closeConnection();
        } catch (InterruptedException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //메소드명: receiveMessagePanel
    //입력: 없음
    //출력: 없음
    //부수효과: activeMQ를 통해 메세지를 받음
    public void receiveMessagePanel(){
            //consumer 생성
            ActiveMQConsumer consumer = new ActiveMQConsumer(MQ_ADDRESS, receivePanel);

            // Set consumer Destination
            consumer.setConsumerDestination(DESTIANTION_EXAM);
            receivePanel.setTextArea("메세지를 기다립니다...");
        
    }
    
    //메소드명: configDestination
    //입력: 없음
    //출력: 없음
    //부수효과: configPanel에 있는 Destination값을 가져와 DESTIANTION값 설정
    public void configDestination(){
        DESTIANTION_EXAM = configPanel.getDestination();
    }
    
    //메소드명: configBroker
    //입력: 없음
    //출력: 없음
    //부수효과: configPanel에 있는 Broker값을 가져와 MQ_ADDRESS값 설정
    public void configBroker(){
        MQ_ADDRESS = configPanel.getBroker();
    }
}
