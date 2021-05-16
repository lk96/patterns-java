package org.kevin;

import lombok.extern.slf4j.Slf4j;
import org.kevin.pattern.propertiesversion.PropertiesFeatureToggleVersion;
import org.kevin.pattern.tieredversion.TieredFeatureToggleVersion;
import org.kevin.user.User;
import org.kevin.user.UserGroup;

import java.util.Properties;

@Slf4j
public class App {

    public static void main(String[] args) {
        final Properties properties = new Properties();
        properties.put("enhancedWelcome", true);
        PropertiesFeatureToggleVersion service = new PropertiesFeatureToggleVersion(properties);
        String message = service.getWelcomeMessage(new User("kevin No coder"));
        log.info(message);

        Properties turnedOff = new Properties();
        turnedOff.put("enhancedWelcome", false);
        PropertiesFeatureToggleVersion turnedService = new PropertiesFeatureToggleVersion(turnedOff);
        String turnOffMessage = turnedService.getWelcomeMessage(new User("kevin No coder"));
        log.info(turnOffMessage);

        TieredFeatureToggleVersion service2 = new TieredFeatureToggleVersion();
        final User paidUser = new User("kevin coder");
        final User freeUser = new User("Alan Defect");

        UserGroup.addUserToPaidGroup(paidUser);
        UserGroup.addUserToFreeGroup(freeUser);

        String welcomeMessagePaidUser = service2.getWelcomeMessage(paidUser);
        String welcomeMessageFreeUser = service2.getWelcomeMessage(freeUser);

        log.info(welcomeMessagePaidUser);
        log.info(welcomeMessageFreeUser);
    }
}
