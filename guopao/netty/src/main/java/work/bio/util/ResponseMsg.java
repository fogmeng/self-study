package work.bio.util;

import java.util.Date;
import java.util.List;

/**
 * @author SongQingWei
 * @date 2018年11月01 17:36
 */
public class ResponseMsg {

    /** 发送人 */
    private String from;

    /** 收件人 */
    private List<String> to;

    /** 消息 */
    private String msg;

    /** 消息模式：1->一对一 2->一对多 3->系统推送 */
    private Integer msgType;

    /** 发送时间 */
    private Date sendTime;

    public ResponseMsg(String from, List<String> to, String msg, Integer msgType, Date sendTime) {
        this.from = from;
        this.to = to;
        this.msg = msg;
        this.msgType = msgType;
        this.sendTime = sendTime;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
