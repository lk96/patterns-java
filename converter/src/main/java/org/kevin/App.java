package org.kevin;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class App {

    public static void main(String[] args) {

        Convert<UserDto, User> userConvert = new UserConvert();
        UserDto userDto = new UserDto("John", "Doe", true, "1");
        User user = userConvert.convertFromDto(userDto);
        log.info("Entity convert from Dto: {}", user);

        List<User> users = List.of(
                new User("Camile", "Tough", true, "123asd"),
                new User("Marti", "Luther", false, "565dsf"),
                new User("Kate", "Smith", true, "ij89bb")
        );
        log.info("Domain entities:");
        users.stream().map(User::toString).forEach(log::info);

        log.info("Dto entities converted from domain:");
        List<UserDto> dtoList = userConvert.createFromEntityList(users);
        dtoList.stream().map(UserDto::toString).forEach(log::info);
    }
}
