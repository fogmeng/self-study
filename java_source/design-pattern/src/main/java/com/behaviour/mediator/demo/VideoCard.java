package com.behaviour.mediator.demo;
public class VideoCard extends Colleague {
    /**
     * ���캯��
     */
    public VideoCard(Mediator mediator) {
        super(mediator);
    }
    /**
     * ��ʾ��Ƶ����
     */
    public void showData(String data){
        System.out.println("�����ڹۿ����ǣ�" + data);
    }
}