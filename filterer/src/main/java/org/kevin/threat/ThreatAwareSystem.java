package org.kevin.threat;

import org.kevin.Filterer;

import java.util.List;

public interface ThreatAwareSystem  {

    String systemId();

    List<? extends Threat> threats();

    Filterer<? extends ThreatAwareSystem, ? extends Threat> filtered();
}
