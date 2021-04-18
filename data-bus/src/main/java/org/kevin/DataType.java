package org.kevin;

/**
 * 事件通过数据总线发送
 */
public interface DataType {

    /**
     *  获取数据总线
     * @return DataBus
     */
    DataBus getDataBus();

    /**
     * 设置数据总线
     * @param dataBus dataBus
     */
    void setDataBus(DataBus dataBus);

}
