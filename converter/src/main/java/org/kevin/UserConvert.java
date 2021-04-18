package org.kevin;

import java.util.function.Function;

public class UserConvert extends Convert<UserDto,User>{

    public UserConvert() {
        super(UserConvert::convertToEntity, UserConvert::convertToDto);
    }

    private static UserDto convertToDto(User user) {
        return new UserDto(user.getFirstName(), user.getLastName(), user.isActive(), user.getUserId());
    }

    private static User convertToEntity(UserDto userDto) {
        return new User(userDto.getFirstName(), userDto.getLastName(), userDto.isActive(), userDto.getUserId());
    }
}
