package work.biochat.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {

    /**消息类型 0-签到 1-签退 2-群发消息 3-点对点消息*/
    private String flag;

    private String fromUid;

    private List<String> toUid;

    private String msg;

}
