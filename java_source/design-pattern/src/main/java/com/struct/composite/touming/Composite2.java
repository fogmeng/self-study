package com.struct.composite.touming;

import java.util.ArrayList;
import java.util.List;

public class Composite2 extends Component2 {
    /**
     * �����洢��϶����а��������������
     */
    private List<Component2> childComponents = new ArrayList<Component2>();
    /**
     * ��϶��������
     */
    private String name;
    /**
     * ���췽����������϶��������
     * @param name    ��϶��������
     */
    public Composite2(String name){
        this.name = name;
    }
    /**
     * �ۼ�������������һ���ӹ�������
     * @param child �ӹ�������
     */
    public void addChild(Component2 child){
        childComponents.add(child);
    }
    /**
     * �ۼ���������ɾ��һ���ӹ�������
     * @param index �ӹ���������±�
     */
    public void removeChild(int index){
        childComponents.remove(index);
    }
    /**
     * �ۼ������������������ӹ�������
     */
    public List<Component2> getChild(){
        return childComponents;
    }
    /**
     * ������������ṹ
     * @param preStr ǰ׺����Ҫ�ǰ��ղ㼶ƴ�ӿո�ʵ���������
     */
    @Override
    public void printStruct(String preStr) {
        // �Ȱ��Լ����
        System.out.println(preStr + "+" + this.name);
        //��������������������ô�������Щ���������
        if(this.childComponents != null){
            //��������ո񣬱�ʾ������������ո�
            preStr += "  ";
            //�����ǰ������Ӷ���
            for(Component2 c : childComponents){
                //�ݹ����ÿ���Ӷ���
                c.printStruct(preStr);
            }
        }
        
    }

}