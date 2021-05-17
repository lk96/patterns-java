package org.kevin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class DependentObject<T> {

    T data;

}
