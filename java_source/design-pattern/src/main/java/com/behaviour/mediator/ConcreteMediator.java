package com.behaviour.mediator;
public class ConcreteMediator implements Mediator {
    //���в�ά��ͬ��A
    private ConcreteColleagueA colleagueA;
    //���в�ά��ͬ��B
    private ConcreteColleagueB colleagueB;    
    
    public void setColleagueA(ConcreteColleagueA colleagueA) {
        this.colleagueA = colleagueA;
    }

    public void setColleagueB(ConcreteColleagueB colleagueB) {
        this.colleagueB = colleagueB;
    }

    @Override
    public void changed(Colleague c) {
        /**
         * ĳһ��ͬ���෢���˱仯��ͨ����Ҫ������ͬ�½���
         * ����Э����Ӧ��ͬ�¶�����ʵ��Э����Ϊ
         */
    }

}