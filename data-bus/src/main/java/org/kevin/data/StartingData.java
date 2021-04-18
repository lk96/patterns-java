package org.kevin.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.kevin.AbstractDatatype;
import org.kevin.DataType;

import java.time.LocalDateTime;

/**
 * 应用程序启动时触发的事件 启动时间
 */
@AllArgsConstructor
@Getter
public class StartingData extends AbstractDatatype {

    private final LocalDateTime time;

    public static DataType of(final LocalDateTime localDateTime) {
        return new StartingData(localDateTime);
    }
}
