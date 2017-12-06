/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.ac.uos.software_project.aeat.view;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import kr.ac.uos.software_project.aeat.MyButtonActionListener;

/**
 *
 * @author comkeen
 */
public class MessagePanel extends JPanel{

    private final MyButtonActionListener buttonActionListener;
    private JTextArea textArea;
    
    public MessagePanel(MyButtonActionListener buttonActionListener) {
        super();
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        this.buttonActionListener = buttonActionListener;
        initComponents();
    }
    
    public void setTextArea(String text){
        this.textArea.setText(text);
    }
    
    public String getTextArea(){
        return textArea.getText();
    }
    
    private void initComponents() {
        this.textArea = new JTextArea();
        this.add(textArea);
    }

    public void loadAeat(String aeatXML) {
        textArea.setText(aeatXML);
    }

    public void clearTextArea() {
        this.textArea.setText("");
    }
    
    public void setEditable(boolean b){
        this.textArea.setEditable(b);
    }
}
