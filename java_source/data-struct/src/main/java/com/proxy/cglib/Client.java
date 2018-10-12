package com.proxy.cglib;
public class Client {

    public static void main(String[] args) {
        Client c = new Client();
        c.anyonecanManager();
       c.haveAuthManager();
    }

    
    public void haveAuthManager() {
        System.out.println("the loginer's name is maurice,so have permits do manager");
        InfoManager authManager = InfoManagerFactory.getAuthInstance(new AuthProxy("maurice"));
        doCRUD(authManager);
        separatorLine();
    }
    
    /**
     * ģ�⣺û���κ�Ȩ��Ҫ���κ��˶����Բ���
     */
    public void anyonecanManager() {
        System.out.println("any one can do manager");
        InfoManager manager = InfoManagerFactory.getInstance();
        doCRUD(manager);
        separatorLine();
    }

    /**
     * ��Info�����ӣ����£�ɾ������ѯ����
     * 
     * @param manager
     */
    private void doCRUD(InfoManager manager) {
        manager.create();
        manager.update();
        manager.delete();
        manager.query();
    }

    /**
     * ��һ���ָ��У���������
     */
    private void separatorLine() {
        System.out.println("################################");
    }

}