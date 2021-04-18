package org.kevin.prototype.abstractdocument.domain;

import org.kevin.prototype.abstractdocument.IDocument;
import org.kevin.prototype.abstractdocument.domain.enums.Property;

import java.util.stream.Stream;

public interface HasParts extends IDocument {

    default Stream<Part> getParts(){
        return children(Property.PARTS.toString(), Part::new);
    }
}

