/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.ac.uos.software_project.aeat.view;

import aeat.AEATType;
import aeat.AEAType;
import aeat.AudienceType;
import kr.ac.uos.software_project.aeat.MyButtonActionListener;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author comkeen
 */
public class AeaPanel extends JPanel {

    private MyButtonActionListener buttonActionListener;
    private Map<String, JComponent> nameToTextField;

    public AeaPanel(MyButtonActionListener buttonActionListener) {
        super();
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        this.buttonActionListener = buttonActionListener;
        nameToTextField = new HashMap<>();
        initComponents();
    }
    //메소드명: initComponents
    //입력: 없음
    //출력: 없음
    //부수효과: 각각의 박스를 만들어 현재 클래스(AeaPanel)에 추가한다.
    private void initComponents() {
        this.add(createRecord("aeaId"));
        this.add(createRecord("issuer"));
        this.add(createComboBoxRecord("audience"));
        this.add(createComboBoxRecord("aeaType"));
        this.add(createRecord("refAEAId"));
        this.add(createRadioButtonRecord("priority"));
        this.add(createRadioButtonRecord("wakeup"));

        //audience combobox item
        List<String> audienceItems = new ArrayList();
        for (AudienceType value : AudienceType.values()) {
            audienceItems.add(value.value());
        }
        ComboBoxModel comboBoxModelAudience = new DefaultComboBoxModel(audienceItems.toArray());
        ((JComboBox) nameToTextField.get("audience")).setModel(comboBoxModelAudience);
        
        //aeaType combobox item
        List<String> AEATypeItems = new ArrayList();
        AEATypeItems.add("alert");
        AEATypeItems.add("update");
        AEATypeItems.add("cancel");
        ComboBoxModel comboBoxModelAEAType = new DefaultComboBoxModel(AEATypeItems.toArray());
        ((JComboBox) nameToTextField.get("aeaType")).setModel(comboBoxModelAEAType);        
    }
    
    //메소드명: createRecord
    //입력: Box의 이름 name
    //출력: Label과 TextField를 가진 box를 반환
    //부수효과: 입력받은 name으로 만든 Lable과 TextField를 가진 box를 만든다
    private Box createRecord(String name) {
        Box box = Box.createHorizontalBox();
        box.setBorder(new EmptyBorder(5, 10, 5, 10));
        JLabel label = new JLabel(name, JLabel.CENTER);
        label.setFont(Frame.LABEL_FONT);
        label.setPreferredSize(Frame.LABEL_DIMENSION);
        box.add(label);

        box.add(Box.createHorizontalGlue());

        JTextField textField = new JTextField("");
        box.add(textField);

        nameToTextField.put(name, textField);
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
        label.setPreferredSize(Frame.LABEL_DIMENSION);
        box.add(label);


        JComboBox comboBox = new JComboBox();
        box.add(comboBox);
        box.add(Box.createHorizontalGlue());

        nameToTextField.put(name, comboBox);
        return box;
    }
    
    //메소드명: createRadioButtonRecord
    //입력: Box의 이름 name
    //출력: Label과 RadioButton을 가진 Box를 반환
    //부수효과: 입력받은 name으로 만든 Label과 RadioButton을 가진 Box를 만든다.
    private Box createRadioButtonRecord(String name){
        Box box = Box.createHorizontalBox();
        box.setBorder(new EmptyBorder(5, 10, 5, 10));
        JLabel label = new JLabel(name, JLabel.CENTER);
        label.setFont(Frame.LABEL_FONT);
        label.setPreferredSize(Frame.LABEL_DIMENSION);
        box.add(label);

        
        if (name.equals("priority")){
            JRadioButton radiobutton5 = new JRadioButton("VeryHigh");
            radiobutton5.setForeground(Color.RED);
            JRadioButton radiobutton4 = new JRadioButton("High");
            radiobutton4.setForeground(Color.MAGENTA);
            JRadioButton radiobutton3 = new JRadioButton("medium");
            JRadioButton radiobutton2 = new JRadioButton("Low");
            radiobutton2.setForeground(Color.cyan);
            JRadioButton radiobutton1 = new JRadioButton("VeryLow");
            radiobutton1.setForeground(Color.blue);
            //만들어진 라디오버튼을 하나의 그룹으로 묶어야 한다.
            ButtonGroup radioGroup = new ButtonGroup();
            radioGroup.add(radiobutton1);
            radioGroup.add(radiobutton2);
            radioGroup.add(radiobutton3);
            radioGroup.add(radiobutton4);
            radioGroup.add(radiobutton5);
            box.add(radiobutton1);
            box.add(radiobutton2);
            box.add(radiobutton3);
            box.add(radiobutton4);
            box.add(radiobutton5);
            nameToTextField.put("priority5",radiobutton5);
            nameToTextField.put("priority4",radiobutton4);
            nameToTextField.put("priority3",radiobutton3);
            nameToTextField.put("priority2",radiobutton2);
            nameToTextField.put("priority1",radiobutton1);
            box.add(Box.createHorizontalGlue());
        }
        else if(name.equals("wakeup")){
            JRadioButton wakeup1 = new JRadioButton("TRUE");
            JRadioButton wakeup0 = new JRadioButton("FALSE");
            ButtonGroup wakeupGroup = new ButtonGroup();
            wakeupGroup.add(wakeup1);
            wakeupGroup.add(wakeup0);
            box.add(wakeup1);
            box.add(wakeup0);
            nameToTextField.put("wakeup1",wakeup1);
            nameToTextField.put("wakeup0",wakeup0);
            box.add(Box.createHorizontalGlue());
        }
       
        
        return box;
    }
    
    //함수이름: loadAeat()
    //입력: 사용자에게 보여줄 내용이 들어 있는 AEATType 객체
    //출력: 없음
    //부수효과: 각 이름을 가진 텍스트필드에 입력으로 받은 AEATType의 알맞은 항목을 꺼내와서 형변환 후, 문자열로 출력한다.
    public void loadAeat(AEATType aeat) {
        ((JTextField) nameToTextField.get("aeaId")).setText(aeat.getAEA().get(0).getAeaId());
        ((JTextField) nameToTextField.get("issuer")).setText(aeat.getAEA().get(0).getIssuer());
        ((JComboBox) nameToTextField.get("audience")).setSelectedItem((String)aeat.getAEA().get(0).getAudience().value());
        ((JComboBox) nameToTextField.get("aeaType")).setSelectedItem(aeat.getAEA().get(0).getAeaType().value());
        ((JTextField) nameToTextField.get("refAEAId")).setText(aeat.getAEA().get(0).getRefAEAId());
        
//Radio버튼을 이용한 두 항목은 switch문 또는 if문으로 각각하나씩 확인해보는 방식으로 체크를 표시한다.
        String tmp = aeat.getAEA().get(0).getPriority().toString();
        switch(tmp){
            case "0":((JRadioButton)nameToTextField.get("priority1")).setSelected(true);break;
            case "1":((JRadioButton)nameToTextField.get("priority2")).setSelected(true);break;
            case "2":((JRadioButton)nameToTextField.get("priority3")).setSelected(true);break;
            case "3":((JRadioButton)nameToTextField.get("priority4")).setSelected(true);break;
            case "4":((JRadioButton)nameToTextField.get("priority5")).setSelected(true);break;
        }
        String tmp2 = String.valueOf(aeat.getAEA().get(0).isWakeup());
        if (tmp2.equals("true")){
            ((JRadioButton)nameToTextField.get("wakeup1")).setSelected(true);
        }else{
            ((JRadioButton)nameToTextField.get("wakeup0")).setSelected(true);
        }
    }
    
    //함수이름: clear
    //입력: 없음
    //출력: 없음
    //부수효과: 모든 box를 비운다.
    public void clear(){
        ((JTextField) nameToTextField.get("aeaId")).setText("");
        ((JTextField) nameToTextField.get("issuer")).setText("");
        ((JComboBox) nameToTextField.get("audience")).setSelectedItem("");
        ((JComboBox) nameToTextField.get("aeaType")).setSelectedItem("");
        ((JTextField) nameToTextField.get("refAEAId")).setText("");
        ((JRadioButton)nameToTextField.get("priority1")).setSelected(true);
        ((JRadioButton)nameToTextField.get("wakeup1")).setSelected(true);
    }
    
    //함수이름: getAeaId(), getIssuer(), getAudience(), getAeaType(), getPriority(), getWakeup()
    //입력: 없음
    //출력: 각 box에 입력되어 있는 것
    //부수효과: 각 이름을 가진 JComponent에 입력된 값을 문자열또는 short, boolean으로 반환한다.
    public String getAeaId() {
        return ((JTextField) nameToTextField.get("aeaId")).getText();
    }

    public String getIssuer() {
        return ((JTextField) nameToTextField.get("issuer")).getText();
    }

    public String getAudience() {
        return (String) ((JComboBox) nameToTextField.get("audience")).getSelectedItem();
    }

    public String getAeaType() {
        return (String)((JComboBox) nameToTextField.get("aeaType")).getSelectedItem();
    }
    public Short getPriority() {
        if (((JRadioButton)nameToTextField.get("priority1")).isSelected()) return 0;
        else if (((JRadioButton)nameToTextField.get("priority2")).isSelected()) return 1;
        else if (((JRadioButton)nameToTextField.get("priority3")).isSelected()) return 2;
        else if (((JRadioButton)nameToTextField.get("priority4")).isSelected()) return 3;
        else if (((JRadioButton)nameToTextField.get("priority5")).isSelected()) return 4;
        else return null;
    }
    public Boolean getWakeup(){
        if (((JRadioButton)nameToTextField.get("wakeup1")).isSelected()) return true;
        else return false;
    }
}
