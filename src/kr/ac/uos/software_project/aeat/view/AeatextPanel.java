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
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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

    public AeatextPanel(MyButtonActionListener buttonActionListener) {
        super();        
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
        
        this.buttonActionListener = buttonActionListener;
        nameToTextField = new HashMap<>();
        initComponents();
    }
    
    private void initComponents() {
        this.add(createTab("AEAText"));
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
            JTextArea textArea = new JTextArea("");
            nameToTextField.put(name+String.valueOf(i),textArea);
            TabbedPanel.add(name+String.valueOf(i),textArea);
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
        
        JTextArea textArea = new JTextArea("");
        textArea.setLineWrap(true);
        box.add(textArea);
        
        nameToTextField.put(name, textArea);
        return box;
    }
    
    public void loadAeat(AEATType aeat) {
        for(int i=0;i<5;i++){
            if(i<aeat.getAEA().get(0).getHeader().getEventDesc().size()){
                nameToTextField.get("AEAText"+String.valueOf(i+1)).setText(aeat.getAEA().get(0).getAEAText().get(i).getValue());
            }else{
                nameToTextField.get("AEAText"+String.valueOf(i+1)).setText("");
            }
        }
    }
    
    public void clear(){
        for(int i=0;i<5;i++){
            nameToTextField.get("AEAText"+String.valueOf(i+1)).setText("");
        }
    }
    
    public void getAeaText(AEAType aea){
        for (int i=0;i<5;i++){
            if(!(nameToTextField.get("AEAText"+String.valueOf(i+1)).getText().isEmpty())){
                LangType text = new LangType();
                text.setValue(nameToTextField.get("AEAText"+String.valueOf(i+1)).getText());
                aea.getAEAText().add(text);
            }
        }
    }
}
