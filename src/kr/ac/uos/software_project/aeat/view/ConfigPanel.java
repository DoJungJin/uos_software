/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.ac.uos.software_project.aeat.view;

import java.awt.Cursor;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import kr.ac.uos.software_project.aeat.MyButtonActionListener;

/**
 *
 * @author B.W.Moon
 */


public class ConfigPanel extends JPanel{
    private final MyButtonActionListener buttonActionListener;
    private JTextArea textArea;
    private Map<String, JComponent> nameToTextField;
    private Frame frame;
    public ConfigPanel(MyButtonActionListener buttonActionListener,Frame frame) {
        super();
        
        super.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        this.buttonActionListener = buttonActionListener;
        this.frame = frame;
        nameToTextField = new HashMap<>();
        initComponents();
    }
    private void initComponents() {
        Box Destination = createRecord("Destination 설정");
        Destination.setPreferredSize(new Dimension(600,60));
        this.add(Destination);
        
        Box Broker = createRecord("Broker 설정");
        Broker.setPreferredSize(new Dimension(600,60));
        this.add(Broker);
        
        Box intro = introBox();
        intro.setPreferredSize(new Dimension(1000,500));
        this.add(intro);
        
    }
    
    private Box introBox(){
        Box box = Box.createVerticalBox();
        box.setBorder(new EmptyBorder(5, 10, 5, 10));
        JLabel label = new JLabel("", JLabel.LEFT);
        label.setFont(Frame.LABEL_FONT);
        label.setPreferredSize(new Dimension(500,500));
        label.setText("<html><h2>프로그램 제작자 소개</h2><h4>소속:</h4> 서울시립대학교<br/><h4>전공:</h4> 전자전기컴퓨터공학부<br/>"
                + "<h4>이름: 문범우</h4><h4>프로그램 소개:</h4> AEAT편집기를 통해서 저장된 xml파일을 marshalling하거나 unmarshalling할 수 있습니다.<br/>"
                + "또한 위의 설정 탭을 이용하여 Broker와 Destination을 올바르게 설정하여 메세지를 주고 받을 수 있습니다.<br/>"
                + "<br/>더 많은 정보를 얻고 싶다면 아래 링크를 통해 블로그를 확인하세요.</html>");
        box.add(label);
        
        JLabel blog = new JLabel("<html><h1><a href=''>블로그</a></h1></html>");
        
        blog.setCursor(new Cursor(Cursor.HAND_CURSOR));
        box.add(blog);
        goWebsite(blog);
        return box;
    }
    private void goWebsite(JLabel website) {
        website.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("http://doorbw.tistory.com/"));
                } catch (URISyntaxException | IOException ex) {
                    //It looks like there's a problem
                }
            }
        });
    }
    
    private Box createRecord(String name) {
        Box box = Box.createHorizontalBox();
        box.setBorder(new EmptyBorder(5, 10, 5, 10));
        JLabel label = new JLabel(name, JLabel.CENTER);
        label.setFont(Frame.LABEL_FONT);
        label.setPreferredSize(new Dimension(120,60));
        box.add(label);

        box.add(Box.createHorizontalGlue());
        JTextField textField = new JTextField();
        
        if (name.equals("Destination 설정")){
            textField.setText(frame.DESTIANTION_EXAM);
        }else if(name.equals("Broker 설정")){
            textField.setText(frame.MQ_ADDRESS);
        }
        
        box.add(textField);
        
        JButton config = new JButton(name);
        config.addActionListener(buttonActionListener);
        box.add(config);
        nameToTextField.put(name, textField);
        return box;
    }
    
    public String getDestination(){
        return ((JTextField)nameToTextField.get("Destination 설정")).getText();
    }
    
    public String getBroker(){
        return ((JTextField)nameToTextField.get("Broker 설정")).getText();
    }
}
