package org.kevin.threat;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.kevin.Filterer;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class SimpleProbabilisticThreatAwareSystem implements ProbabilisticThreatAwareSystem{

    private final String systemId;

    private final List<ProbableThreat> threats;

    @Override
    public List<? extends Threat> threats() {
        return threats;
    }

    @Override
    public Filterer<? extends ProbabilisticThreatAwareSystem, ? extends ProbableThreat> filtered() {
        return this::filteredGroup;
    }

    @Override
    public String systemId() {
        return systemId;
    }

    private ProbabilisticThreatAwareSystem filteredGroup(
            final Predicate<? super ProbableThreat> predicate) {
        return new SimpleProbabilisticThreatAwareSystem(this.systemId, filteredItems(predicate));
    }

    private List<ProbableThreat> filteredItems(
            final Predicate<? super ProbableThreat> predicate) {
        return this.threats.stream()
                .filter(predicate)
                .collect(Collectors.toUnmodifiableList());
    }
}
