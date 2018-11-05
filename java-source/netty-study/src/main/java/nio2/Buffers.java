package nio2;

import lombok.Data;

import java.nio.ByteBuffer;

/**
 * @Author liujt
 * @Date 2018/11/5 14:55
 */
/**
 * 自定义Buffer类中包含读缓冲区和写缓冲区，用于注册通道时的附加对象
 **/
@Data
public class Buffers {

    ByteBuffer readBuffer;
    ByteBuffer writeBuffer;

    public Buffers(int readCapacity,int writeCapacity){

        this.readBuffer = ByteBuffer.allocate(readCapacity);
        this.writeBuffer = ByteBuffer.allocate(writeCapacity);
    }



}
