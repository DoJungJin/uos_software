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
    
    //함수이름: setTextArea
    //입력: 문자열
    //출력: 없음
    //부수효과: 메세지패널의 내용을 입력받은 문자열로 수정한다.
    public void setTextArea(String text){
        this.textArea.setText(text);
    }
    
    //함수이름: getTextArea
    //입력: 없음
    //출력: 메세지패널의 내용을 반환한다.
    //부수효과: 없음
    public String getTextArea(){
        return textArea.getText();
    }
    
    //함수이름: initComponents
    //입력: 없음
    //출력: 없음
    //부수효과: TextArea를 만들어서 메세지패널에 추가한다.
    private void initComponents() {
        this.textArea = new JTextArea();
        this.add(textArea);
    }

    //함수이름: loadAeat
    //입력: 불러온 xml형식 문자열
    //출력: 없음
    //부수효과: 불러온 xml형식의 문자열을 텍스트area 내용으로 수정
    public void loadAeat(String aeatXML) {
        textArea.setText(aeatXML);
    }

    //함수이름: clearTextArea
    //입력: 없음
    //출력: 없음
    //부수효과: 텍스트영역을 비운다.
    public void clearTextArea() {
        this.textArea.setText("");
    }
    
    //함수이름: setEditable
    //입력: 참 or 거짓
    //출력: 없음
    //부수효과: 텍스트 영역의 수정가능 여부를 설정한다.
    public void setEditable(boolean b){
        this.textArea.setEditable(b);
    }
}
