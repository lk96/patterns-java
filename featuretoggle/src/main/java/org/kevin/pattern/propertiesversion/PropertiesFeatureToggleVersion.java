package org.kevin.pattern.propertiesversion;

import lombok.Getter;
import org.kevin.pattern.Service;
import org.kevin.user.User;

import java.util.Properties;

@Getter
public class PropertiesFeatureToggleVersion implements Service {

    private final boolean enhanced;

    public PropertiesFeatureToggleVersion(final Properties properties) {
        if (properties == null) {
            throw new IllegalArgumentException("No Properties provided.");
        }else{
            try {
                enhanced = (boolean) properties.get("enhancedWelcome");
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid Enhancement Settings provided");
            }
        }
    }

    @Override
    public String getWelcomeMessage(User user) {
        if (isEnhanced()) {
            return "Welcome " + user + ". You're using the enhanced welcome message.";
        }
        return "Welcome to the application";
    }
}
