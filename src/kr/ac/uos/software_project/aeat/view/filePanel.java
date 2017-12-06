/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.ac.uos.software_project.aeat.view;

import java.awt.Color;
import java.io.File;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import kr.ac.uos.software_project.aeat.MyButtonActionListener;

/**
 *
 * @author B.W.Moon
 */
public class filePanel extends JPanel {
    private final MyButtonActionListener buttonActionListener;
    private final Map<String, JComboBox> nameToTextField;
    String path = "xml/";
    File dirFile=new File(path);
    File []fileList=dirFile.listFiles();
    public filePanel(MyButtonActionListener buttonActionListener) {
        super();        
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        this.buttonActionListener = buttonActionListener;
        nameToTextField = new HashMap<>();
        initComponents();
    }
    
    private void initComponents() {
        this.add(createRecord("file"));
    }
    
    private Box createRecord(String name) {
        Box box = Box.createHorizontalBox();
        box.setBorder(new EmptyBorder(5,10,5,10));
        JLabel label = new JLabel(name, JLabel.CENTER);
        label.setFont(Frame.LABEL_FONT);
        label.setPreferredSize(Frame.LABEL_DIMENSION);
        box.add(label);
        List<String> AEATypeItems = new ArrayList();
        for(File tempFile : fileList){
            
            String tempPath=tempFile.getParent();
            String tempFileName=tempFile.getName();
            AEATypeItems.add(tempFileName);            
        }
        ComboBoxModel comboBoxModelAEAType = new DefaultComboBoxModel(AEATypeItems.toArray());
        JComboBox fileBox = new JComboBox();
        box.add(fileBox);
        fileBox.setModel(comboBoxModelAEAType);  
        nameToTextField.put(name, fileBox);        
        return box;       
    }
    public void refresh(){
        String path = "xml/";
        File dirFile=new File(path);
        File []fileList=dirFile.listFiles();
        List<String> AEATypeItems = new ArrayList();
        for(File tempFile : fileList){
            String tempPath=tempFile.getParent();
            String tempFileName=tempFile.getName();
            AEATypeItems.add(tempFileName);  
        }
        ComboBoxModel comboBoxModelAEAType = new DefaultComboBoxModel(AEATypeItems.toArray());
        (nameToTextField.get("file")).setModel(comboBoxModelAEAType);  
        
    }
    public String getTitleToLoad(){
        return nameToTextField.get("file").getSelectedItem().toString();
    }
    
}
