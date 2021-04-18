package org.kevin.members;

import lombok.extern.slf4j.Slf4j;
import org.kevin.DataType;
import org.kevin.Member;
import org.kevin.data.MessageData;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据总线事件的接收器 从每个事件接收消息
 */
@Slf4j
public class MessageCollectorMember implements Member {

    private final String name;

    private final List<String> messages = new ArrayList<>();

    public MessageCollectorMember(String name) {
        this.name = name;
    }

    @Override
    public void accept(DataType dataType) {
        if (dataType instanceof MessageData) {
            handleEvent((MessageData) dataType);
        }
    }

    private void handleEvent(MessageData data) {
        log.info("{} sees message{}", name, data.getMessage());
        messages.add(data.getMessage());
    }

    public List<String> getMessages() {
        return List.copyOf(messages);
    }
}
