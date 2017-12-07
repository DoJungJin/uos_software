/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.ac.uos.software_project.aeat.view;

import aeat.AEATType;
import aeat.AEAType;
import aeat.LangType;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import kr.ac.uos.software_project.aeat.MyButtonActionListener;

/**
 *
 * @author comkeen
 */
public class AeatextPanel extends JPanel {

    private final MyButtonActionListener buttonActionListener;
    private final Map<String, JTextArea> nameToTextField;
    private final Map<String, JComboBox> nameToComboBox;
    
    public AeatextPanel(MyButtonActionListener buttonActionListener) {
        super();        
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
        this.buttonActionListener = buttonActionListener;
        nameToTextField = new HashMap<>();
        nameToComboBox = new HashMap<>();
        initComponents();
    }
    //메소드명: initComponents
    //입력: 없음
    //출력: 없음
    //부수효과: 각각의 박스를 만들어 현재 클래스(AeatextPanel)에 추가한다.
    private void initComponents() {
        this.add(createTab("AEAText"));
    }
    
    //메소드명: createTab
    //입력: Box의 이름 name
    //출력: Label과 TabbedPane, language ComboBox를 가진 box를 반환
    //부수효과: 입력받은 name으로 만든 Lable과 TabbedPane, language ComboBox를 가진 box를 만든다
    //         TabbedPane은 5개의 탭을 가지도록 만든다.
    private Box createTab(String name){
        Box box = Box.createHorizontalBox();
        box.setBorder(new EmptyBorder(5,10,5,10));
        JLabel label = new JLabel(name, JLabel.CENTER);
        label.setFont(Frame.LABEL_FONT);
        label.setPreferredSize(Frame.LABEL_DIMENSION);
        box.add(label);
        JTabbedPane TabbedPanel = new JTabbedPane(); //탭패널 생성
        for(int i=1; i<=5;i++){ //탭은 5개로 만든다
            Box tmp = Box.createVerticalBox();
            JTextArea textArea = new JTextArea(""); //TextArea 생성
            nameToTextField.put(name+String.valueOf(i),textArea); //입력받은 name+번호를 이름으로 설정
            Box langtype = createComboBoxRecord("lang"+String.valueOf(i));
            
            //langType combobox item
            List<String> langType = new ArrayList();
            langType.add("select your language");
            langType.add("en");
            langType.add("ko");
            langType.add("no");
            langType.add("de");
            langType.add("la");
            langType.add("ru");
            langType.add("ja");
            langType.add("zh");
            ComboBoxModel comboBoxModelAEAType = new DefaultComboBoxModel(langType.toArray());
            nameToComboBox.get("lang"+String.valueOf(i)).setModel(comboBoxModelAEAType);
            
            tmp.add(textArea);
            tmp.add(langtype);
            TabbedPanel.add(name+String.valueOf(i),tmp);// 탭패널에 추가
        }
        TabbedPanel.setPreferredSize(new Dimension(1000,150));
        box.add(TabbedPanel);
        return box;
    }
    
    //메소드명: createComboBoxRecord
    //입력: Box의 이름
    //출력: Label과 ComboBox를 가진 box를 반환
    //부수효과: 입력받은 name으로 만든 Lable과 ComboBox를 가진 box를 만든다
    private Box createComboBoxRecord(String name) {
        Box box = Box.createHorizontalBox();
        box.setBorder(new EmptyBorder(5, 10, 5, 10));
        JLabel label = new JLabel(name, JLabel.CENTER);
        label.setFont(Frame.LABEL_FONT);
        label.setPreferredSize(new Dimension(100,30));
        box.add(label);


        JComboBox comboBox = new JComboBox();
        box.add(comboBox);
        box.add(Box.createHorizontalGlue());

        nameToComboBox.put(name, comboBox);
        return box;
    }
    
    //메소드명: createRecord
    //입력: Box의 이름 name
    //출력: Label과 TextField를 가진 box를 반환
    //부수효과: 입력받은 name으로 만든 Lable과 TextField를 가진 box를 만든다
    private Box createRecord(String name) {
        Box box = Box.createHorizontalBox();
        box.setBorder(new EmptyBorder(5,10,5,10));
        JLabel label = new JLabel(name, JLabel.CENTER);
        label.setFont(Frame.LABEL_FONT);
        label.setPreferredSize(Frame.LABEL_DIMENSION);
        box.add(label);
        
        JTextArea textArea = new JTextArea("");
        textArea.setLineWrap(true);
        box.add(textArea);
        
        nameToTextField.put(name, textArea);
        return box;
    }
    
    //함수이름: loadAeat()
    //입력: 사용자에게 보여줄 내용이 들어 있는 AEATType 객체
    //출력: 없음
    //부수효과: 각 이름을 가진 텍스트필드에 입력으로 받은 AEATType의 알맞은 항목을 꺼내와서 형변환 후, 문자열로 출력한다.
    public void loadAeat(AEATType aeat) {
        for(int i=0;i<5;i++){
            if(i<aeat.getAEA().get(0).getHeader().getEventDesc().size()){
                nameToTextField.get("AEAText"+String.valueOf(i+1)).setText(aeat.getAEA().get(0).getAEAText().get(i).getValue());
                nameToComboBox.get("lang"+String.valueOf(i+1)).setSelectedItem(aeat.getAEA().get(0).getAEAText().get(i).getLang());
            }else{
                nameToTextField.get("AEAText"+String.valueOf(i+1)).setText("");
                nameToComboBox.get("lang"+String.valueOf(i+1)).setSelectedItem("select your language");
            }
        }
    }
    
    //함수이름: clear
    //입력: 없음
    //출력: 없음
    //부수효과: 모든 box를 비운다.
    public void clear(){
        for(int i=0;i<5;i++){
            nameToTextField.get("AEAText"+String.valueOf(i+1)).setText("");
            nameToComboBox.get("lang"+String.valueOf(i+1)).setSelectedItem("select your language");
        }
    }
    
    //함수이름: getAeaText
    //입력: aea 객체
    //출력: 없음
    //부수효과: AEAText TabPane에 있는 각 문자를 입력받은 aea 객체에 추가한다.
    public void getAeaText(AEAType aea){
        for (int i=0;i<5;i++){
            if(!(nameToTextField.get("AEAText"+String.valueOf(i+1)).getText().isEmpty())){
                LangType text = new LangType();
                text.setValue(nameToTextField.get("AEAText"+String.valueOf(i+1)).getText());
                aea.getAEAText().add(text);
            }
        }
    }
    
    //함수이름: getLang
    //입력: aea 객체
    //출력: 없음
    //부수효과: AEAText TabPane에 있는 lang을 입력받은 aea 객체에 추가한다.
    public void getLang(AEAType aea){
        for (int i=0;i<5;i++){
            if(!(nameToTextField.get("AEAText"+String.valueOf(i+1)).getText().isEmpty())){
                String lang =  nameToComboBox.get("lang"+String.valueOf(i+1)).getSelectedItem().toString();
                aea.getAEAText().get(i).setLang(lang);
            }
        }
        
    }
}
