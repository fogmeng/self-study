/**
 * 
 */
package com.struct.decorator;

/**
 * @author snoopy
 *
 */
public abstract class Beverage {

	
	String description="unknow beverage";
	
	//����
	public String getDescription(){
		return this.description;
	}
	
	//�ɱ�
	public abstract double cost();
	
}
