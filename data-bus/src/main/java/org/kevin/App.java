package org.kevin;

import org.kevin.data.MessageData;
import org.kevin.data.StartingData;
import org.kevin.data.StoppingData;
import org.kevin.members.MessageCollectorMember;
import org.kevin.members.StatusMember;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DataBusPattern是一种组织彼此之间不存在的对象之间的通信的方法。
 * 对象不是直接调用其他对象的方法，而是通过将事件发送到其他总线成员接收到的总线进行通信。
 * 对象必须在总线上注册才能成为其成员，并开始接收发送到该总线的事件。
 * 定义事件的类型和结构取决于应用程序开发人员。
 */
public class App {

    public static void main(String[] args) {
        final DataBus bus = DataBus.getInstance();
        bus.subscribe(new StatusMember(1));
        bus.subscribe(new StatusMember(2));
        final MessageCollectorMember foo = new MessageCollectorMember("Foo");
        final MessageCollectorMember bar = new MessageCollectorMember("Bar");
        bus.subscribe(foo);
        bus.publish(StartingData.of(LocalDateTime.now()));
        bus.publish(MessageData.of("Only Foo should see this"));
        bus.subscribe(bar);
        bus.publish(MessageData.of("Foo and Bar should see this"));
        bus.unSubscribe(foo);
        bus.publish(MessageData.of("Only Bar should see this"));
        bus.publish(StoppingData.of(LocalDateTime.now()));

    }
}
