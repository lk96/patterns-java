package org.kevin.threat;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.kevin.Filterer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@EqualsAndHashCode
@ToString
public class SimpleThreatAwareSystem implements ThreatAwareSystem{

    private final String systemId;

    private final List<Threat> issues;

    @Override
    public String systemId() {
        return systemId;
    }

    @Override
    public List<? extends Threat> threats() {
        return new ArrayList<>(issues);
    }

    @Override
    public Filterer<? extends ThreatAwareSystem, ? extends Threat> filtered() {
        return this::filteredGroup;
    }

    private ThreatAwareSystem filteredGroup(Predicate<? super Threat> predicate) {
        return new SimpleThreatAwareSystem(this.systemId, filteredItems(predicate));
    }

    private List<Threat> filteredItems(Predicate<? super Threat> predicate) {
        return this.issues.stream().filter(predicate).collect(Collectors.toUnmodifiableList());
    }
}
