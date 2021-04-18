package org.kevin;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public final class Student implements Serializable {

    @Serial
    private static final long serialVersionUID = -6411619728234830668L;

    @EqualsAndHashCode.Include
    private int studentId;

    private String name;

    private char grade;

}
