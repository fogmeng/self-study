package com.behaviour.visitor.demo;

//http://www.cnblogs.com/java-my-life/archive/2012/06/14/2545381.html

//��̬˫�ط���
public class Client {

	public static void main(String[] args) {
		// ���1
		East east = new SubEast1();
		West west = new SubWest1();
		east.goEast(west);
		// ���2
		// east = new SubEast1();
		// west = new SubWest2();
		// east.goEast(west);
	}

}