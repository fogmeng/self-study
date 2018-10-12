package com.struct.composite.touming;

public class Client2 {
	public static void main(String[] args) {
		Component2 root = new Composite2("��װ");
		Component2 c1 = new Composite2("��װ");
		Component2 c2 = new Composite2("Ůװ");

		Component2 leaf1 = new Leaf2("����");
		Component2 leaf2 = new Leaf2("�п�");
		Component2 leaf3 = new Leaf2("ȹ��");
		Component2 leaf4 = new Leaf2("��װ");

		root.addChild(c1);
		root.addChild(c2);
		c1.addChild(leaf1);
		c1.addChild(leaf2);
		c2.addChild(leaf3);
		c2.addChild(leaf4);

		root.printStruct("");
	}
}