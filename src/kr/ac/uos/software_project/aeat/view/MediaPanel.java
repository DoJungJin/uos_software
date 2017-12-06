/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.ac.uos.software_project.aeat.view;

import aeat.AEATType;
import java.awt.Color;
import java.math.BigInteger;
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
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import kr.ac.uos.software_project.aeat.MyButtonActionListener;

/**
 *
 * @author B.W.Moon
 */
public class MediaPanel extends JPanel{
    private MyButtonActionListener buttonActionListener;
    private Map<String, JComponent> nameToTextField;

    public MediaPanel(MyButtonActionListener buttonActionListener) {
        super();
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        this.buttonActionListener = buttonActionListener;
        nameToTextField = new HashMap<>();
        initComponents();
    }
    
    private void initComponents() {
        this.add(createComboBoxRecord("MediaType"));
        this.add(createRecord("MediaDesc"));
        this.add(createRecord("Url"));
        this.add(createRecord("AlternateUrl"));
        this.add(createRecord("ContentType"));
        this.add(createRecord("ContentLength"));
        this.add(createRecord("MediaAssoc"));
        
        //MediaType combobox item
        List<String> mediaTypeItems = new ArrayList();
        mediaTypeItems.add("EventDescAudio");
        mediaTypeItems.add("AEAtextAudio");
        mediaTypeItems.add("EventSymbol");
        ComboBoxModel comboBoxModelAEAType = new DefaultComboBoxModel(mediaTypeItems.toArray());
        ((JComboBox) nameToTextField.get("MediaType")).setModel(comboBoxModelAEAType);
    }
    
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
    
    public void loadAeat(AEATType aeat) {
        ((JTextField)nameToTextField.get("MediaDesc")).setText(aeat.getAEA().get(0).getMedia().get(0).getMediaDesc());
        ((JTextField)nameToTextField.get("Url")).setText(aeat.getAEA().get(0).getMedia().get(0).getUrl());
        ((JTextField)nameToTextField.get("AlternateUrl")).setText(aeat.getAEA().get(0).getMedia().get(0).getAlternateUrl());
        ((JTextField)nameToTextField.get("ContentType")).setText(aeat.getAEA().get(0).getMedia().get(0).getContentType());
        ((JTextField)nameToTextField.get("ContentLength")).setText(aeat.getAEA().get(0).getMedia().get(0).getContentLength().toString());
        ((JTextField)nameToTextField.get("MediaAssoc")).setText(aeat.getAEA().get(0).getMedia().get(0).getMediaAssoc());
        ((JComboBox)nameToTextField.get("MediaType")).setSelectedItem(aeat.getAEA().get(0).getMedia().get(0).getMediaType().value());
    }
    
    public void clear(){
        ((JTextField)nameToTextField.get("MediaDesc")).setText("");
        ((JTextField)nameToTextField.get("Url")).setText("");
        ((JTextField)nameToTextField.get("AlternateUrl")).setText("");
        ((JTextField)nameToTextField.get("ContentType")).setText("");
        ((JTextField)nameToTextField.get("ContentLength")).setText("");
        ((JTextField)nameToTextField.get("MediaAssoc")).setText("");
        ((JComboBox)nameToTextField.get("MediaType")).setSelectedItem("");
    
    }
    
    //함수이름: getMediaDesc(), getMediaType(), getUrl(), getAlternateUrl(), getContentType(), getMediaAssoc()
    //입력: 없음
    //출력: 각 텍스트필드에 입력된 문자열
    //부수효과: 각 이름을 가진 텍스트 필드에 입력된 값을 문자열로 반환한다.
    public String getMediaDesc(){
        return ((JTextField)nameToTextField.get("MediaDesc")).getText();
    }
    public String getUrl(){
        return ((JTextField)nameToTextField.get("Url")).getText();
    }
    public String getAlternateUrl(){
        return ((JTextField)nameToTextField.get("AlternateUrl")).getText();
    }
    public String getContentType(){
        return ((JTextField)nameToTextField.get("ContentType")).getText();
    }
    public String getMediaAssoc(){
        return ((JTextField)nameToTextField.get("MediaAssoc")).getText();
    }
    
    //함수이름: getContentLength()
    //입력: 없음
    //출력: 텍스트필드에 입력된 문자열을 변환한 BigInteger형
    //부수효과: 텍스트필드에 입력된 값을 BigInteget형으로 변환하여 출력한다.
    public BigInteger getContentLength(){
        return (new BigInteger(((JTextField)nameToTextField.get("ContentLength")).getText()));
    }
    //함수이름: 
    //입력: 없음
    //출력: 각 텍스트필드에 입력된 문자열
    //부수효과: 각 이름을 가진 텍스트필드에 입력된 값을 문자열로 반환한다.
    
    public String getMediaType(){
        return ((JComboBox)nameToTextField.get("MediaType")).getSelectedItem().toString();
    }
}
