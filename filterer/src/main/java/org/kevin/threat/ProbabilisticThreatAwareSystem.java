package org.kevin.threat;

import org.kevin.Filterer;

import java.util.List;

public interface ProbabilisticThreatAwareSystem extends ThreatAwareSystem{

    @Override
    List<? extends Threat> threats();

    @Override
    Filterer<? extends ProbabilisticThreatAwareSystem, ? extends ProbableThreat> filtered();
}
