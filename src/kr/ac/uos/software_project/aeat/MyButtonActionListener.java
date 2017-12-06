/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.ac.uos.software_project.aeat;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import kr.ac.uos.software_project.aeat.view.TitlePanel;

/**
 *
 * @author comkeen
 */
public class MyButtonActionListener implements ActionListener {

    private Publisher publisher;
    private TitlePanel titlePanel;
    public MyButtonActionListener(Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Load":
                publisher.onClickedLoadButton();
                break;
            case "Save":
                publisher.onClickedSaveButton();
                break;
            case "Clear":
                publisher.onClickedxmlClearButton();
                break;
            case "메세지 보내기창 비우기":
                publisher.onClickedClearButton();
                break;
            case "메세지 받기창 비우기":
                publisher.onClickedClearReceiverButton();
                break;
            case "메세지 보내기":
                publisher.onClickedSendButton();
                break;
            case "메세지 받기":
                publisher.onClickedReceiveButton();
                break;
            case "AEAT편집기로 보내기1":
                publisher.onClickedGoToAeat1();
                break;
            case "AEAT편집기로 보내기2":
                publisher.onClickedGoToAeat2();
                break;
            case "Destination 설정":
                publisher.onClickedConfigDestination();
                break;
            case "Broker 설정":
                publisher.onClickedConfigBroker();
                break;
            default:
                break;
        }
    }
}
