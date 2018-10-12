package com.behaviour.mediator;
public abstract class Colleague {
    //����һ����ͣ�߶���
    private Mediator mediator;
    /**
     * ���캯��
     */
    public Colleague(Mediator mediator){
        this.mediator = mediator;
    }
    /**
     * ��ȡ��ǰͬ�����Ӧ�ĵ�ͣ�߶���
     */
    public Mediator getMediator() {
        return mediator;
    }
    
}