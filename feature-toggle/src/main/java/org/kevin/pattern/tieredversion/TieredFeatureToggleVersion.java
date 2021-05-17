package org.kevin.pattern.tieredversion;

import org.kevin.pattern.Service;
import org.kevin.user.User;
import org.kevin.user.UserGroup;

public class TieredFeatureToggleVersion implements Service {
    @Override
    public String getWelcomeMessage(User user) {
        if (UserGroup.isPaid(user)) {
            return "You're amazing " + user + ". Thanks for paying for this awesome software";
        }

        return "I suppose you can use this software";
    }

    @Override
    public boolean isEnhanced() {
        return true;
    }
}
