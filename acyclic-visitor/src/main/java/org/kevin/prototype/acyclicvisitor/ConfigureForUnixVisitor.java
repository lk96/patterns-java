package org.kevin.prototype.acyclicvisitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigureForUnixVisitor implements ZoomVisitor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigureForUnixVisitor.class);

    @Override
    public void visit(Zoom zoom) {
        LOGGER.info(zoom + "used with Unix configurator");
    }
}
