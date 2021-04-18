package org.kevin.prototype.abstractdocument.domain;

import org.kevin.prototype.abstractdocument.IDocument;
import org.kevin.prototype.abstractdocument.domain.enums.Property;

import java.util.Optional;

public interface HasModel extends IDocument {

    default Optional<String> getModel(){
        return Optional.ofNullable((String) get(Property.MODEL.toString()));
    }
}
