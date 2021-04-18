package org.kevin;

import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventManager implements ThreadCompleteListener {

    public static final int MAX_RUNNING_EVENTS = 1000;

    public static final int MIN_ID = 1;

    public static final int MAX_ID = MAX_RUNNING_EVENTS;

    public static final int MAX_EVENT_TIME = 1800;

    private int currentlyRunningSyncEvent = -1;

    private final SecureRandom random;

    private final Map<Integer, Event> eventPool;

    private static final String DOES_NOT_EXIST = " does not exist";

    public EventManager() {
        this.random = new SecureRandom();
        this.eventPool = new ConcurrentHashMap<>(MAX_RUNNING_EVENTS);
    }

    /**
     * 创建同步事件
     *
     * @param eventTime 事件运行时间
     * @return 事件id
     * @throws MaxNumOfEventsAllowedException 运行太多事件
     * @throws InvalidOperationException      当一个已经运行，不能创建新的同步事件
     * @throws LongRunningEventException      不允许长时间运行事件
     */
    public int create(int eventTime) throws MaxNumOfEventsAllowedException, InvalidOperationException, LongRunningEventException {
        if (currentlyRunningSyncEvent != -1) {
            throw new InvalidOperationException("Event [" + currentlyRunningSyncEvent + "] is still running. please wait until it finished and try again");
        }
        int eventId = createEvent(eventTime, true);
        currentlyRunningSyncEvent = eventId;
        return eventId;
    }

    /**
     * 创建异步事件
     *
     * @param eventTime 事件运行时间
     * @return 事件id
     * @throws MaxNumOfEventsAllowedException 运行太多事件
     * @throws LongRunningEventException      不允许长时间运行事件
     */
    public int createAsync(int eventTime) throws MaxNumOfEventsAllowedException, LongRunningEventException {
        return createEvent(eventTime, false);
    }


    private int createEvent(int eventTime, boolean isSynchronous) throws MaxNumOfEventsAllowedException, LongRunningEventException {
        if (eventPool.size() == MAX_RUNNING_EVENTS) {
            throw new MaxNumOfEventsAllowedException("Too many events are running at the moment. please try again letter");
        }
        if (eventTime >= MAX_EVENT_TIME) {
            throw new LongRunningEventException("Maximum event time allowed is " + MAX_EVENT_TIME + "seconds.please try again");
        }
        int newEventId = generateId();
        Event newEvent = new Event(newEventId, eventTime, isSynchronous);
        newEvent.addListener(this);
        eventPool.put(newEventId, newEvent);
        return newEventId;
    }

    /**
     * 启动事件
     *
     * @param eventId 事件id
     * @throws EventDoesNotExistException 事件不存在
     */
    public void start(int eventId) throws EventDoesNotExistException {
        if (!eventPool.containsKey(eventId)) {
            throw new EventDoesNotExistException(eventId + DOES_NOT_EXIST);
        }
        eventPool.get(eventId).start();
    }

    /**
     * 停止事件
     *
     * @param eventId 事件id
     * @throws EventDoesNotExistException 事件不存在
     */
    public void cancel(int eventId) throws EventDoesNotExistException {
        if (!eventPool.containsKey(eventId)) {
            throw new EventDoesNotExistException(eventId + DOES_NOT_EXIST);
        }
        if (eventId == currentlyRunningSyncEvent) {
            currentlyRunningSyncEvent = -1;
        }
        eventPool.get(eventId).stop();
        eventPool.remove(eventId);
    }

    /**
     * 事件状态
     *
     * @param eventId 事件id
     * @throws EventDoesNotExistException 事件不存在
     */
    public void status(int eventId) throws EventDoesNotExistException {
        if (!eventPool.containsKey(eventId)) {
            throw new EventDoesNotExistException(eventId + DOES_NOT_EXIST);
        }
        eventPool.get(eventId).status();
    }

    /**
     * 所有事件状态
     */
    public void statusOfAllEvents() {
        eventPool.entrySet().forEach(integerEventEntry -> ((Event) ((Map.Entry) integerEventEntry).getValue()).status());
    }

    /**
     * 停止所有事件
     */
    public void shutdown() {
        eventPool.entrySet().forEach(integerEventEntry -> ((Event) ((Map.Entry) integerEventEntry).getValue()).stop());
    }

    /**
     * 生成id 介于最小值和最大值之间的随机数
     *
     * @return
     */
    private int generateId() {
        int randomNum = random.nextInt((MAX_ID - MIN_ID) + 1) + MAX_ID;
        while (eventPool.containsKey(randomNum)) {
            randomNum = random.nextInt((MAX_ID - MIN_ID) + 1) + MAX_ID;
        }
        return randomNum;
    }

    /**
     * Event 的回调（一旦完成） 从池中移除
     *
     * @param eventId
     */
    @Override
    public void completedEventHandler(int eventId) {
        eventPool.get(eventId).status();
        if (eventPool.get(eventId).isSynchronous()) {
            currentlyRunningSyncEvent = -1;
        }
        eventPool.remove(eventId);
    }

    /**
     * 获取事件池
     *
     * @return 事件池
     */
    public Map<Integer, Event> getEventPool() {
        return eventPool;
    }

    /**
     * 当前运行事件数量
     *
     * @return 事件数量
     */
    public int numOfCurrentlyRunningSyncEvent() {
        return currentlyRunningSyncEvent;
    }
}
