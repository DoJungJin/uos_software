/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.ac.uos.software_project.aeat.view;

import aeat.AEATType;
import aeat.LangType;
import aeat.LiveMediaType;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
public class LiveMediaPanel extends JPanel{
    private MyButtonActionListener buttonActionListener;
    private Map<String, JComponent> nameToTextField;

    public LiveMediaPanel(MyButtonActionListener buttonActionListener) {
        super();
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        super.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        this.buttonActionListener = buttonActionListener;
        nameToTextField = new HashMap<>();
        initComponents();
    }
    private void initComponents(){
        this.add(createRecord("bsid"));
        this.add(createRecord("ServiceId"));
        this.add(createRecord("ServiceName"));
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
    
    //함수이름: loadAeat()
    //입력: AEATType 객체
    //출력: 없음
    //부수효과: 각 이름을 가진 텍스트필드에 입력으로 받은 AEATType의 알맞은 항목을 꺼내어 출력한다.
    public void loadAeat(AEATType aeat) {
        ((JTextField)nameToTextField.get("bsid")).setText(aeat.getAEA().get(0).getLiveMedia().getBsid().get(0).toString());
        ((JTextField)nameToTextField.get("ServiceId")).setText(Integer.toString(aeat.getAEA().get(0).getLiveMedia().getServiceId()));
        ((JTextField)nameToTextField.get("ServiceName")).setText(aeat.getAEA().get(0).getLiveMedia().getServiceName().get(0).getValue());
    }
    
    public void clear() {
        ((JTextField)nameToTextField.get("bsid")).setText("");
        ((JTextField)nameToTextField.get("ServiceId")).setText("");
        ((JTextField)nameToTextField.get("ServiceName")).setText("");
    }
    
    //함수이름: getBsid()
    //입력: LiveMediaType 객체
    //출력: 없음
    //부수효과: 입력받은 LiveMediaType 변수에서 Bsid를 얻어 텍스트필드에 있는 문자열을 Integer로 변환하여 추가한다.
    public void getBsid(LiveMediaType livemedia){
        livemedia.getBsid().add(Integer.parseInt(((JTextField)nameToTextField.get("bsid")).getText()));
    }
    
    //함수이름: getServiceId()
    //입력: 없음
    //출력: 텍스트 필드에 입력된 값이 문자열이므로 이를 Integer로 변환하여 반환한다.
    //부수효과: 각 텍스트 필드에 입력으로 받은 문자열을 Integer로 변환하여 반환한다.
    public int getServiceId(){
        return Integer.parseInt(((JTextField)nameToTextField.get("ServiceId")).getText());
    }
    
    //함수이름: getServiceName()
    //입력: LiveMediaType 객체
    //출력: 없음
    //부수효과: LangType arraylist를 생성한다.
    //          LnagType 객체를 생성한다.
    //          LangType 객체의 값을 텍스트필드에 입력받은 값으로 바꾼다.
    //          LangType arraylist에 LangType 객체를 추가한다.
    //          입력받은 LiveMediaType 객체에 LangType arraylist를 추가한다.
    public void getServiceName(LiveMediaType livemedia){
        ArrayList<LangType> servicename = new ArrayList<LangType>();
        LangType text = new LangType();
        text.setValue(((JTextField)nameToTextField.get("ServiceName")).getText());
        servicename.add(0,text);
        livemedia.getServiceName().add(servicename.get(0));
    }
    
    
}
