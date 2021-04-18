package org.kevin;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Room {

    private int id;

    private String roomType;

    private int price;

    private boolean booked;
}
