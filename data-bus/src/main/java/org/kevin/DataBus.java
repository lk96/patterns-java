package org.kevin;

import java.util.HashSet;
import java.util.Set;

/**
 * 数据总线实现
 */
public class DataBus {

    private static final DataBus INSTANCE = new DataBus();

    private final Set<Member> listeners = new HashSet<>();

    public static DataBus getInstance() {
        return INSTANCE;
    }

    /**
     * 注册成员 开始接受事件
     *
     * @param member 成员
     */
    public void subscribe(final Member member) {
        this.listeners.add(member);
    }

    /**
     * 注销成员 停止接收消息
     *
     * @param member 成员
     */
    public void unSubscribe(final Member member) {
        this.listeners.remove(member);
    }

    /**
     * 向成员发送消息
     * @param dataType 事件
     */
    public void publish(final DataType dataType) {
        dataType.setDataBus(this);
        listeners.forEach(listeners -> listeners.accept(dataType));
    }

}
