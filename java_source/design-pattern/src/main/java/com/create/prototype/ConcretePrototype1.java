package com.create.prototype;
public class ConcretePrototype1 implements Prototype {
    public Prototype clone(){
        //��򵥵Ŀ�¡���½�һ�������������û�����ԾͲ��ٸ���ֵ��
        Prototype prototype = new ConcretePrototype1();
        return prototype;
    }
}