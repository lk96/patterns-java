package org.kevin.prototype.acyclicvisitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Zoom extends Modem {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigureForDosVisitor.class);

    @Override
    public void accept(ModemVisitor modemVisitor) {
        if (modemVisitor instanceof ZoomVisitor) {
            ((ZoomVisitor) modemVisitor).visit(this);
        } else {
            LOGGER.info("Only ZoomVisitor is allowed to visit Zoom modem");
        }
    }
}
