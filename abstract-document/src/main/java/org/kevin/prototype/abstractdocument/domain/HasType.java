package org.kevin.prototype.abstractdocument.domain;

import org.kevin.prototype.abstractdocument.IDocument;
import org.kevin.prototype.abstractdocument.domain.enums.Property;

import java.util.Optional;

public interface HasType extends IDocument {

    default Optional<String> getType() {
        return Optional.ofNullable((String) get(Property.TYPE.toString()));
    }
}
