package com.struct.flyweight;

import java.util.ArrayList;
import java.util.List;


//����coffee����
public class Client {
	// �ͻ��µĶ���
	private static List<Coffee> coffees = new ArrayList<Coffee>();

	// �����������ɹ���
	private static FlavorFactory flavorFactory;

	// ���Ӷ���
	private static void takeOrders(String flavor) {
		coffees.add(flavorFactory.getCoffee(flavor));
	}

	public static void main(String[] args) {
		// �������ɹ���
		flavorFactory = FlavorFactory.getInstance();

		// ���Ӷ���
		takeOrders("Ħ��");
		takeOrders("������ŵ");
		takeOrders("����Ǳ���");
		takeOrders("����Ǳ���");
		takeOrders("����");
		takeOrders("������ŵ");
		takeOrders("����");
		takeOrders("������ŵ");
		takeOrders("Ħ��");
		takeOrders("����Ǳ���");
		takeOrders("������ŵ");
		takeOrders("Ħ��");
		takeOrders("����Ǳ���");
		takeOrders("����");
		takeOrders("����");

		// ������
		for (Coffee coffee : coffees) {
			coffee.sell();
		}

		// ��ӡ���ɵĶ���java��������
		System.out.println("\n�ͻ�һ������ " + coffees.size() + " ������! ");

		// ��ӡ���ɵĶ���java��������
		System.out.println("�������� " + flavorFactory.getTotalFlavorsMade()
				+ " �� FlavorCoffee java����! ");
	}
}