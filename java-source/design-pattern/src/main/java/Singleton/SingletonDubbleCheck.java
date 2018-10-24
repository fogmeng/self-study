package Singleton;

public class SingletonDubbleCheck {

    private static  volatile SingletonDubbleCheck instance;

    private SingletonDubbleCheck(){}

    public static SingletonDubbleCheck getInstance(){
        if (instance == null){
            synchronized (SingletonDubbleCheck.class){
                if (instance == null) {
                    instance = new SingletonDubbleCheck();
                }
            }
        }
        return  instance;
    }
}
