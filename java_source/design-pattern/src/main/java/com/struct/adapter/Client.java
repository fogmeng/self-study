/**
 * 
 */
package com.struct.adapter;

/**������ģʽ
 * @author snoopy
 *
 */
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Target target=new Adapter(new Adaptee());
		
		target.sampleOperation1();
		target.sampleOperation2();
		

	}

}
