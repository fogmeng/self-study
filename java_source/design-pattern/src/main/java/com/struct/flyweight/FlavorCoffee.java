package com.struct.flyweight;

public class FlavorCoffee extends Coffee {
	public String flavor;

	// ��ȡ���ȿ�ζ
	public FlavorCoffee(String flavor) {
		this.flavor = flavor;
	}

	@Override
	public void sell() {
		System.out.println("����һ��" + flavor + "�Ŀ��ȡ�");
	}
}