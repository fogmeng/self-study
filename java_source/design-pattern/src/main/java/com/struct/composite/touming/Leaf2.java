package com.struct.composite.touming;
public class Leaf2 extends Component2 {
    /**
     * Ҷ�Ӷ��������
     */
    private String name;
    /**
     * ���췽��������Ҷ�Ӷ��������
     * @param name Ҷ�Ӷ��������
     */
    public Leaf2(String name){
        this.name = name;
    }
    /**
     * ���Ҷ�Ӷ���Ľṹ��Ҷ�Ӷ���û���Ӷ���Ҳ�������Ҷ�Ӷ��������
     * @param preStr ǰ׺����Ҫ�ǰ��ղ㼶ƴ�ӵĿո�ʵ���������
     */
    @Override
    public void printStruct(String preStr) {
        // TODO Auto-generated method stub
        System.out.println(preStr + "-" + name);
    }

}