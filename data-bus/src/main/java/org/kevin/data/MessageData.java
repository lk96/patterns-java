package org.kevin.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.kevin.AbstractDatatype;
import org.kevin.DataType;

/**
 * 发送消息时触发的事件
 */
@Getter
@AllArgsConstructor
public class MessageData extends AbstractDatatype {

    private final String message;

    public static DataType of(final String message) {
        return new MessageData(message);
    }
}
