package org.kevin.pattern;

import org.kevin.user.User;

public interface Service {

    String getWelcomeMessage(User user);

    boolean isEnhanced();
}
