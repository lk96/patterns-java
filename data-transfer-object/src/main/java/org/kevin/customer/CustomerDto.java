package org.kevin.customer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomerDto {

    private final String id;

    private final String firstName;

    private final String lastName;
}
