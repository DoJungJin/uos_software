/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.ac.uos.software_project.aeat.view;

import aeat.AEATType;
import aeat.HeaderType;
import aeat.LangType;
import aeat.LocationType;
import aeat.LocationTypeType;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import kr.ac.uos.software_project.aeat.MyButtonActionListener;

/**
 *
 * @author comkeen
 */

public class HeaderPanel extends JPanel {
    private MyButtonActionListener buttonActionListener;
    private Map<String, JTextField> nameToTextField;
    private Map<String, JComboBox> nameToComboBox;
    public HeaderPanel(MyButtonActionListener buttonActionListener) {
        super();        
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        this.buttonActionListener = buttonActionListener;
        nameToTextField = new HashMap<>();
        nameToComboBox = new HashMap<>();
        initComponents();
    }
    
    //메소드명: initComponents
    //입력: 없음
    //출력: 없음
    //부수효과: 각각의 박스를 만들어 현재 클래스(ConfigPanel)에 추가한다.
    private void initComponents() {
        this.add(createRecord("effective"));
        this.add(createRecord("expires"));
        this.add(createRecord("EventCode"));
        this.add(createRecord("EventCodeType"));
        this.add(createTab("EventDesc"));
        this.add(createTab("Location"));
    }
    
    //메소드명: createTab
    //입력: Box의 이름 name
    //출력: Label과 TabbedPane을 가진 box를 반환
    //부수효과: 입력받은 name으로 만든 Lable과 TabbedPane을 가진 box를 만든다
    //         TabbedPane은 5개의 탭을 가지도록 만든다.
    private Box createTab(String name){
        Box box = Box.createHorizontalBox();
        box.setBorder(new EmptyBorder(5,10,5,10));
        JLabel label = new JLabel(name, JLabel.CENTER);
        label.setFont(Frame.LABEL_FONT);
        label.setPreferredSize(Frame.LABEL_DIMENSION);
        box.add(label);
        JTabbedPane TabbedPanel = new JTabbedPane();
        if(name.equals("EventDesc")){
            for(int i=1; i<=5;i++){
                Box tmp = Box.createVerticalBox();
                JTextField textField = new JTextField("");
                nameToTextField.put(name+String.valueOf(i),textField);
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

                tmp.add(textField);
                tmp.add(langtype);
                TabbedPanel.add(name+String.valueOf(i),tmp);
            }
        }else if(name.equals("Location")){
            for(int i=1; i<=5;i++){
                Box tmp = Box.createVerticalBox();
                JTextField textField = new JTextField("");
                nameToTextField.put(name+String.valueOf(i),textField);
                Box typeField = createComboBoxRecord("LocationType"+String.valueOf(i));
          
                //locationType combobox item
                List<String> locationType = new ArrayList();
                locationType.add("FIPS");
                locationType.add("SGC");
                locationType.add("polygon");
                locationType.add("circle");
                
                ComboBoxModel comboBoxModelAEAType = new DefaultComboBoxModel(locationType.toArray());
                nameToComboBox.get("LocationType"+String.valueOf(i)).setModel(comboBoxModelAEAType);  
                
                tmp.add(textField);
                tmp.add(typeField);
                TabbedPanel.add(name+String.valueOf(i),tmp);
            }
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
        
        JTextField textField = new JTextField("");
        box.add(textField);
        
        nameToTextField.put(name, textField);
        return box;
    }
      
    //함수이름: loadAeat()
    //입력: 사용자에게 보여줄 내용이 들어 있는 AEATType 객체
    //출력: 없음
    //부수효과: 각 이름을 가진 텍스트필드에 입력으로 받은 AEATType의 알맞은 항목을 꺼내와서 형변환 후, 문자열로 출력한다.
    public void loadAeat(AEATType aeat) {
        nameToTextField.get("EventCode").setText(aeat.getAEA().get(0).getHeader().getEventCode().getValue());
        //nameToTextField.get("EventDesc").setText(aeat.getAEA().get(0).getHeader().getEventDesc().get(0).getValue());
        for(int i=0;i<5;i++){
            if(i<aeat.getAEA().get(0).getHeader().getEventDesc().size()){
                nameToTextField.get("EventDesc"+String.valueOf(i+1)).setText(aeat.getAEA().get(0).getHeader().getEventDesc().get(i).getValue());
                nameToComboBox.get("lang"+String.valueOf(i+1)).setSelectedItem(aeat.getAEA().get(0).getHeader().getEventDesc().get(i).getLang());
            }else{
                nameToTextField.get("EventDesc"+String.valueOf(i+1)).setText("");
                nameToComboBox.get("lang"+String.valueOf(i+1)).setSelectedItem("select your language");
            }
        }

        //nameToTextField.get("Location"+String.valueOf(1)).setText(aeat.getAEA().get(0).getHeader().getLocation().get(0).getValue());
        for(int i=0;i<5;i++){
            if(i<aeat.getAEA().get(0).getHeader().getLocation().size()){
                nameToTextField.get("Location"+String.valueOf(i+1)).setText(aeat.getAEA().get(0).getHeader().getLocation().get(i).getValue());
                nameToComboBox.get("LocationType"+String.valueOf(i+1)).setSelectedItem(aeat.getAEA().get(0).getHeader().getLocation().get(i).getType().value());
            }else{
                nameToTextField.get("Location"+String.valueOf(i+1)).setText("");
            }
        }
        nameToTextField.get("effective").setText(aeat.getAEA().get(0).getHeader().getEffective().toString());
        nameToTextField.get("expires").setText(aeat.getAEA().get(0).getHeader().getExpires().toString());
        nameToTextField.get("EventCodeType").setText(aeat.getAEA().get(0).getHeader().getEventCode().getType());
        //nameToTextField.get("lang").setText(aeat.getAEA().get(0).getHeader().getEventDesc().get(0).getlang());
    }
    
    //함수이름: clear
    //입력: 없음
    //출력: 없음
    //부수효과: 모든 box를 비운다.
    public void clear(){
        nameToTextField.get("EventCode").setText("");
        for(int i=0;i<5;i++){
            nameToTextField.get("EventDesc"+String.valueOf(i+1)).setText("");
            nameToComboBox.get("lang"+String.valueOf(i+1)).setSelectedItem("select your language");
        }
        for(int i=0;i<5;i++){
            nameToTextField.get("Location"+String.valueOf(i+1)).setText("");
            nameToComboBox.get("LocationType"+String.valueOf(i+1)).setSelectedItem("");
        }
        nameToTextField.get("effective").setText("");
        nameToTextField.get("expires").setText("");
        nameToTextField.get("EventCodeType").setText("");
    }

    //함수이름: getEventCode()
    //입력: 없음
    //출력: 텍스트필드에 입력된 값을 반환한다.
    //부수효과: 없음
    public String getEventCode() {
        return nameToTextField.get("EventCode").getText();
    }
    
    //함수이름: getType()
    //입력: 없음
    //출력: 텍스트필드에 입력된 문자열
    //부수효과: 각 이름을 가진 텍스트필드에 입력된 값을 문자열로 반환한다.
    public String getEventCodeType() {
        return nameToTextField.get("EventCodeType").getText();
    }
    
    //함수이름: getEventDesc()
    //입력: HeaderType 객체
    //출력: 없음
    //부수효과: LangType arraylist를 생성한다.
    //          LangType 객체를 생성한다.
    //          LangType 객체의 값을 텍스트필드에 입력받은 값으로 바꾼다.
    //          LangType arraylist에 LangType 객체를 추가한다.
    //          입력받은 HeaderType 객체에 LangType arraylist를 추가한다.
    public void getEventDesc(HeaderType header){
        for (int i=0;i<5;i++){
            if(!(nameToTextField.get("EventDesc"+String.valueOf(i+1)).getText().isEmpty())){
                LangType text = new LangType();
                text.setValue(nameToTextField.get("EventDesc"+String.valueOf(i+1)).getText());
                header.getEventDesc().add(text);
            }
        }
    }
    
    //함수이름: getlang()
    //입력: HeaderType 객체
    //출력: 없음
    //부수효과: lang 값을 문자열로 받아와 해당 EventDesc에 입력한다.
    public void getLang(HeaderType header){
        for (int i=0;i<5;i++){
            if(!(nameToTextField.get("EventDesc"+String.valueOf(i+1)).getText().isEmpty())){
                String lang =  nameToComboBox.get("lang"+String.valueOf(i+1)).getSelectedItem().toString();
                header.getEventDesc().get(i).setLang(lang);
            }
        }
        
    }
    
    //함수이름: getLocation()
    //입력: HeaderType 객체
    //출력: 없음
    //부수효과: LocationType arraylist를 생성한다.
    //          LocationType 객체를 생성한다.
    //          LocationType 객체의 값을 텍스트필드에 입력받은 값으로 바꾼다.
    //          LocationType arraylist에 LocationType 변수를 추가한다.
    //          입력받은 HeaderType 객체에 LocationType arraylist를 추가한다.
    public void getLocation(HeaderType header){
        for (int i=0;i<5;i++){
            if(!(nameToTextField.get("Location"+String.valueOf(i+1)).getText().isEmpty())){
                LocationType text = new LocationType();
                text.setValue(nameToTextField.get("Location"+String.valueOf(i+1)).getText());
                header.getLocation().add(text);
            }
        }
    }
    
    //함수이름: getLocationType()
    //입력: HeaderType 객체
    //출력: 없음
    //부수효과: 입력받은 header에서 Location에 맞는 Type을 추가한다.
    public void getLocationType(HeaderType header){
        for (int i=0;i<5;i++){
            if(!(nameToTextField.get("Location"+String.valueOf(i+1)).getText().isEmpty())){
                header.getLocation().get(i).setType(LocationTypeType.fromValue(nameToComboBox.get("LocationType"+String.valueOf(i+1)).getSelectedItem().toString()));
            }
        }
    }
    
    //함수이름: getEffective()
    //입력: 없음
    //출력: 텍스트필드에 입력된 문자열
    //부수효과: 각 이름을 가진 텍스트필드에 입력된 값을 문자열로 반환한다.
    public String getEffective() {
        return nameToTextField.get("effective").getText();
    }
    
    //함수이름: getExpries()
    //입력: 없음
    //출력: 텍스트필드에 입력된 문자열
    //부수효과: 각 이름을 가진 텍스트필드에 입력된 값을 문자열로 반환한다.
    public String getExpires() {
        return nameToTextField.get("expires").getText();
    }
    
    
}
