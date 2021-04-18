package org.kevin.prototype.abstractdocument.domain;

import org.kevin.prototype.abstractdocument.IDocument;
import org.kevin.prototype.abstractdocument.domain.enums.Property;

import java.util.Optional;

public interface HasPrice extends IDocument {

    default Optional<Number> getPrice() {
        return Optional.ofNullable((Number) get(Property.PRICE.toString()));
    }
}
