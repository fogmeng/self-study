package com.struct.flyweight;

import java.util.HashMap;
import java.util.Map;

public class FlavorFactory {
	
	private Map<String, Coffee> flavorCoffeePool = new HashMap<String, Coffee>();

	// ��̬����,�������ɶ�������
	private static FlavorFactory flavorFactory = new FlavorFactory();

	private FlavorFactory() {
	}

	public static FlavorFactory getInstance() {
		return flavorFactory;
	}

	public Coffee getCoffee(String flavor) {
		Coffee order = null;

		if (flavorCoffeePool.containsKey(flavor)) {// �����ӳ�����ָ������ӳ���ϵ���򷵻� true
			order = flavorCoffeePool.get(flavor);

		} else {
			order = new FlavorCoffee(flavor);
			flavorCoffeePool.put(flavor, order);
		}
		return order;
	}

	public int getTotalFlavorsMade() {
		return flavorCoffeePool.size();
	}
}