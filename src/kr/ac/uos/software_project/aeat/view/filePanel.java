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
        super.setBackground(Color.BLACK);
        this.buttonActionListener = buttonActionListener;
        nameToTextField = new HashMap<>();
        initComponents();
    }
    
    //메소드명: initComponents
    //입력: 없음
    //출력: 없음
    //부수효과: 각각의 박스를 만들어 현재 클래스(ConfigPanel)에 추가한다.
    private void initComponents() {
        this.add(createRecord("File To Load"));
    }
    
    //메소드명: createRecord
    //입력: Box의 이름 name
    //출력: xml/ 경로에 있는 파일의 목록을가진 combobox반환
    //부수효과: xml/경로에 있는 파일의 목록을 구하여 이를 combobox의 컨텐츠로 한다.
    private Box createRecord(String name) {
        Box box = Box.createHorizontalBox();
        box.setBorder(new EmptyBorder(5,10,5,10));
        JLabel label = new JLabel(name, JLabel.CENTER);
        label.setFont(Frame.LABEL_FONT);
        label.setForeground(Color.WHITE);
        label.setPreferredSize(Frame.LABEL_DIMENSION);
        box.add(label);
        List<String> fileItems = new ArrayList(); //파일의 목록을 저장하기 위한 배열
        for(File tempFile : fileList){
            String tempFileName=tempFile.getName(); // 파일의 이름을 가져온다.
            fileItems.add(tempFileName);            //파일의 이름을 배열에 추가한다.
        }
        ComboBoxModel comboBoxModelAEAType = new DefaultComboBoxModel(fileItems.toArray());
        JComboBox fileBox = new JComboBox();
        box.add(fileBox);
        fileBox.setModel(comboBoxModelAEAType);  
        nameToTextField.put(name, fileBox);        
        return box;       
    }
    
    //메소드명: refresh
    //입력: 없음
    //출력: 없음
    //부수효과: 새로운 파일을 저장했을때, 이를 업데이트하기 위하여 경로에 있는 파일을 다시 업데이트한다.
    public void refresh(){
        String path = "xml/";
        File dirFile=new File(path);
        File []fileList=dirFile.listFiles();
        List<String> AEATypeItems = new ArrayList();
        for(File tempFile : fileList){
            String tempFileName=tempFile.getName();
            AEATypeItems.add(tempFileName);  
        }
        ComboBoxModel comboBoxModelAEAType = new DefaultComboBoxModel(AEATypeItems.toArray());
        (nameToTextField.get("File To Load")).setModel(comboBoxModelAEAType);  
        
    }
    
    //메소드명: getTitleToLoad
    //입력: 없음
    //출력: 불러올 파일의 이름을 콤보박스를 통해 문자열로 반환
    //부수효과: 없음
    public String getTitleToLoad(){
        return nameToTextField.get("File To Load").getSelectedItem().toString();
    }
    
}
