/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.ac.uos.software_project.aeat.view;

import aeat.AEATType;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import kr.ac.uos.software_project.aeat.MyButtonActionListener;
/**
 *
 * @author B.W.Moon
 */
public class TitlePanel extends JPanel {
    private final MyButtonActionListener buttonActionListener;
    private final Map<String, JTextField> nameToTextField;
    private final Map<String, JComboBox> nameToComboBox;

    public TitlePanel(MyButtonActionListener buttonActionListener) {
        super();        
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        super.setBackground(Color.BLACK);
        this.buttonActionListener = buttonActionListener;
        nameToTextField = new HashMap<>();
        nameToComboBox = new HashMap<>();
        initComponents();
    }
    
    //함수이름: initComponents
    //입력: 없음
    //출력: 없음
    //부수효과: 필요한 박스를 만들어서 패널에 추가한다.
    private void initComponents() {
        this.add(createRecord("Title To Save"));
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
        label.setForeground(Color.WHITE);
        label.setPreferredSize(Frame.LABEL_DIMENSION);
        box.add(label);
        
        JTextField textField = new JTextField("title.xml");
        box.add(textField);
        
        nameToTextField.put(name, textField);
        return box;
    }
    
    //메소드명: getTitleToSave
    //입력: 없음
    //출력: 저장될 파일의 이름을 반환한다.
    //부수효과: 없음
    public String getTitleToSave(){
        return nameToTextField.get("Title To Save").getText();
    }
    
}
