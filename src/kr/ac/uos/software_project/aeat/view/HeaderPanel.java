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
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
    
    public HeaderPanel(MyButtonActionListener buttonActionListener) {
        super();        
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        this.buttonActionListener = buttonActionListener;
        nameToTextField = new HashMap<>();
        initComponents();
    }
    
    private void initComponents() {
        this.add(createRecord("EventCode"));
        this.add(createTab("EventDesc"));
        this.add(createTab("Location"));
        this.add(createRecord("effective"));
    }
    private Box createTab(String name){
        Box box = Box.createHorizontalBox();
        box.setBorder(new EmptyBorder(5,10,5,10));
        JLabel label = new JLabel(name, JLabel.CENTER);
        label.setFont(Frame.LABEL_FONT);
        label.setPreferredSize(Frame.LABEL_DIMENSION);
        box.add(label);
        JTabbedPane TabbedPanel = new JTabbedPane();
        for(int i=1; i<=5;i++){
            JTextField textField = new JTextField("");
            nameToTextField.put(name+String.valueOf(i),textField);
            TabbedPanel.add(name+String.valueOf(i),textField);
        }
        TabbedPanel.setPreferredSize(new Dimension(1000,70));
        box.add(TabbedPanel);
        return box;
    }
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
      
    public void loadAeat(AEATType aeat) {
        nameToTextField.get("EventCode").setText(aeat.getAEA().get(0).getHeader().getEventCode().getValue());
        //nameToTextField.get("EventDesc").setText(aeat.getAEA().get(0).getHeader().getEventDesc().get(0).getValue());
        for(int i=0;i<5;i++){
            if(i<aeat.getAEA().get(0).getHeader().getEventDesc().size()){
                nameToTextField.get("EventDesc"+String.valueOf(i+1)).setText(aeat.getAEA().get(0).getHeader().getEventDesc().get(i).getValue());
            }else{
                nameToTextField.get("EventDesc"+String.valueOf(i+1)).setText("");
            }
        }

        //nameToTextField.get("Location"+String.valueOf(1)).setText(aeat.getAEA().get(0).getHeader().getLocation().get(0).getValue());
        for(int i=0;i<5;i++){
            if(i<aeat.getAEA().get(0).getHeader().getLocation().size()){
                nameToTextField.get("Location"+String.valueOf(i+1)).setText(aeat.getAEA().get(0).getHeader().getLocation().get(i).getValue());
            }else{
                nameToTextField.get("Location"+String.valueOf(i+1)).setText("");
            }
        }
        nameToTextField.get("effective").setText(aeat.getAEA().get(0).getHeader().getEffective().toString());
    }
    
    public void clear(){
        nameToTextField.get("EventCode").setText("");
        for(int i=0;i<5;i++){
            nameToTextField.get("EventDesc"+String.valueOf(i+1)).setText("");
        }
        for(int i=0;i<5;i++){
            nameToTextField.get("Location"+String.valueOf(i+1)).setText("");
        }
        nameToTextField.get("effective").setText("");
    }

    String getEventCode() {
        return nameToTextField.get("EventCode").getText();
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
    
    public String getEffective() {
        return nameToTextField.get("effective").getText();
    }
}
