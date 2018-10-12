package com.struct.composite.touming;

import java.util.List;

public abstract class Component2 {
    /**
     * ����齨���������
     */
    public abstract void printStruct(String preStr);
    /**
     * �ۼ�������������һ���ӹ�������
     * @param child �ӹ�������
     */
    public void addChild(Component2 child){
        /**
         * ȱʡʵ�֣��׳��쳣����ΪҶ�Ӷ���û�д˹���
         * ���������û��ʵ���������
         */
        throw new UnsupportedOperationException("����֧�ִ˹���");
    }
    /**
     * �ۼ���������ɾ��һ���ӹ�������
     * @param index �ӹ���������±�
     */
    public void removeChild(int index){
        /**
         * ȱʡʵ�֣��׳��쳣����ΪҶ�Ӷ���û�д˹���
         * ���������û��ʵ���������
         */
        throw new UnsupportedOperationException("����֧�ִ˹���");
    }
    
    /**
     * �ۼ������������������ӹ�������
     */
    public List<Component2> getChild(){
        /**
         * ȱʡʵ�֣��׳��쳣����ΪҶ�Ӷ���û�д˹���
         * ���������û��ʵ���������
         */
        throw new UnsupportedOperationException("����֧�ִ˹���");
    }
}