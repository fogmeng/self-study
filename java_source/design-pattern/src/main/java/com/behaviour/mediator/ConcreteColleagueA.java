package com.behaviour.mediator;
public class ConcreteColleagueA extends Colleague {

    public ConcreteColleagueA(Mediator mediator) {
        super(mediator);
    }
    /**
     * ʾ�ⷽ����ִ��ĳЩ����
     */
    public void operation(){
        //����Ҫ������ͬ��ͨ�ŵ�ʱ��֪ͨ��ͣ�߶���
        getMediator().changed(this);
    }
}