package org.kevin;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class User {

    private final String firstName;

    private final String lastName;

    private final boolean active;

    private final String userId;

}
