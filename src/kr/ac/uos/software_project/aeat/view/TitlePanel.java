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
        
        this.buttonActionListener = buttonActionListener;
        nameToTextField = new HashMap<>();
        nameToComboBox = new HashMap<>();
        initComponents();
    }
    
    private void initComponents() {
        this.add(createRecord("Title"));
    }
    
    private Box createRecord(String name) {
        Box box = Box.createHorizontalBox();
        box.setBorder(new EmptyBorder(5,10,5,10));
        JLabel label = new JLabel(name, JLabel.CENTER);
        label.setFont(Frame.LABEL_FONT);
        label.setPreferredSize(Frame.LABEL_DIMENSION);
        box.add(label);
        
        JTextField textField = new JTextField("title.xml");
        box.add(textField);
        
        nameToTextField.put(name, textField);
        return box;
    }
    
    public String getTitleToSave(){
        return nameToTextField.get("Title").getText();
    }
    
}
