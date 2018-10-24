package growing.jdk7;

import java.io.*;
import java.util.zip.GZIPOutputStream;

/*
一个小问题
在使用try-with-resource的过程中，一定需要了解资源的close方法内部的实现逻辑。否则还是可能会导致资源泄露。

举个例子，在Java BIO中采用了大量的装饰器模式。当调用装饰器的close方法时，本质上是调用了装饰器内部包裹的流的close方法。比如：

public class TryWithResource {
    public static void main(String[] args) {
        try (FileInputStream fin = new FileInputStream(new File("input.txt"));
                GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(new File("out.txt")))) {
            byte[] buffer = new byte[4096];
            int read;
            while ((read = fin.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
在上述代码中，我们从FileInputStream中读取字节，并且写入到GZIPOutputStream中。GZIPOutputStream实际上是FileOutputStream的装饰器。由于try-with-resource的特性，实际编译之后的代码会在后面带上finally代码块，并且在里面调用fin.close()方法和out.close()方法。我们再来看GZIPOutputStream类的close方法：

public void close() throws IOException {
    if (!closed) {
        finish();
        if (usesDefaultDeflater)
            def.end();
        out.close();
        closed = true;
    }
}
我们可以看到，out变量实际上代表的是被装饰的FileOutputStream类。在调用out变量的close方法之前，GZIPOutputStream还做了finish操作，该操作还会继续往FileOutputStream中写压缩信息，此时如果出现异常，则会out.close()方法被略过，然而这个才是最底层的资源关闭方法。正确的做法是应该在try-with-resource中单独声明最底层的资源，保证对应的close方法一定能够被调用。在刚才的例子中，我们需要单独声明每个FileInputStream以及FileOutputStream：

public class TryWithResource {
    public static void main(String[] args) {
        try (FileInputStream fin = new FileInputStream(new File("input.txt"));
                FileOutputStream fout = new FileOutputStream(new File("out.txt"));
                GZIPOutputStream out = new GZIPOutputStream(fout)) {
            byte[] buffer = new byte[4096];
            int read;
            while ((read = fin.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
由于编译器会自动生成fout.close()的代码，这样肯定能够保证真正的流被关闭。
 */
public class TryWithResource {

    public static void main(String[] args) {

    }

    /**
     * 错误的实例
     */
    public static void test2(){
        try (FileInputStream fin = new FileInputStream(new File("input.txt"));
             GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(new File("out.txt")))) {
            byte[] buffer = new byte[4096];
            int read;
            while ((read = fin.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 正确的实例
     */
    public static void testRight(){
        try (FileInputStream fin = new FileInputStream(new File("input.txt"));
             FileOutputStream fout = new FileOutputStream(new File("out.txt"));
             GZIPOutputStream out = new GZIPOutputStream(fout)) {
            byte[] buffer = new byte[4096];
            int read;
            while ((read = fin.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
