package com.struct.composite;
public class Client {
    public static void main(String[]args){
    	//�����������µķ�ʽ�������νṹ
        Composite root = new Composite("��װ");
        
        
        Composite c2 = new Composite("Ůװ");
        root.addChild(c2);
        Leaf leaf3 = new Leaf("ȹ��");
        Leaf leaf4 = new Leaf("��װ");
        c2.addChild(leaf3);
        c2.addChild(leaf4);
        
        
        Composite c1 = new Composite("��װ");   
        root.addChild(c1);
        Leaf leaf1 = new Leaf("����");
        Leaf leaf2 = new Leaf("�п�");
        c1.addChild(leaf1);
        c1.addChild(leaf2);
        
       
     
        
        root.printStruct("");
    }
}