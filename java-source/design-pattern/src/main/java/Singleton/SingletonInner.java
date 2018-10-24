package Singleton;

public class SingletonInner {

    private static class Holder{
        private static SingletonInner instance = new SingletonInner();
    }

    private  SingletonInner(){ }

    public static SingletonInner getInstance(){
        return Holder.instance;
    }
}
