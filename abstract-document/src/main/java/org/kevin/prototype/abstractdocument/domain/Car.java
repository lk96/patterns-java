package org.kevin.prototype.abstractdocument.domain;

import org.kevin.prototype.abstractdocument.AbstractDocument;

import java.util.Map;

public class Car extends AbstractDocument implements HasModel, HasPrice, HasParts {

    public Car(Map<String, Object> properties) {
        super(properties);
    }
}
