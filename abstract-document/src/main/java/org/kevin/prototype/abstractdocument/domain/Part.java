package org.kevin.prototype.abstractdocument.domain;

import org.kevin.prototype.abstractdocument.AbstractDocument;

import java.util.Map;

public class Part extends AbstractDocument implements HasType, HasModel,HasPrice {

    public Part(Map<String, Object> properties) {
        super(properties);
    }
}
