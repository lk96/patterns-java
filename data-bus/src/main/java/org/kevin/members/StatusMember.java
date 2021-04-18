package org.kevin.members;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kevin.DataType;
import org.kevin.Member;
import org.kevin.data.MessageData;
import org.kevin.data.StartingData;
import org.kevin.data.StoppingData;

import java.time.LocalDateTime;

/**
 * 数据总线事件的接收者
 */
@Slf4j
@Getter
@RequiredArgsConstructor
public class StatusMember implements Member {

    private final int id;

    private LocalDateTime started;

    private LocalDateTime stopped;

    @Override
    public void accept(final DataType dataType) {
        if (dataType instanceof StartingData) {
            handleEvent((StartingData) dataType);
        } else if (dataType instanceof StoppingData) {
            handleEvent((StoppingData) dataType);
        }
    }

    private void handleEvent(StartingData data) {
        started = data.getTime();
        log.info("Receiver {} sees application started at {}", id, started);
    }

    private void handleEvent(StoppingData data) {
        stopped = data.getTime();
        log.info("Receiver {} sees application stopping at {}", id, stopped);
        log.info("Receiver {} sending goodbye message", id);
        data.getDataBus().publish(MessageData.of(String.format("Goodbye cruel world from #%d!", id)));
    }
}
