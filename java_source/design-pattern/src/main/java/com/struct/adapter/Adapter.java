package com.struct.adapter;
public class Adapter  implements Target {
	
	private Adaptee adaptee;
	public Adapter( Adaptee adaptee){
		this.adaptee=adaptee;
	}
    /**
     * ����Դ��Adapteeû�з���sampleOperation2()
     * ����������������������
     */
    @Override
    public void sampleOperation2() {
        //д��صĴ���
    	
    	System.out.println("this is sampleOperation2 method from Adapter ");
    }

	@Override
	public void sampleOperation1() {
		adaptee.sampleOperation1();
		
	}

}