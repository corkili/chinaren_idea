package chinaren.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李浩然 on 2017/7/26.
 */
public class MessageCounter {
    private String date;

    private long classId;

    private String className;

    private List<Message> messages;

    public MessageCounter() {
        messages = new ArrayList<>();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getClassId() {
        return classId;
    }

    public void setClassId(long classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
