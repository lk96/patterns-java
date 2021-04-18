package org.kevin;

import java.util.function.Consumer;

/**
 * 成员从数据总线接收数据
 */
public interface Member extends Consumer<DataType> {

    void accept(DataType dataType);
}
