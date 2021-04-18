package org.kevin;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class UserDto {

    private final String firstName;

    private final String lastName;

    private final boolean active;

    private final String userId;
}
