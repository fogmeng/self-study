package com.behaviour.mediator;
public class ConcreteColleagueB extends Colleague {

    public ConcreteColleagueB(Mediator mediator) {
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