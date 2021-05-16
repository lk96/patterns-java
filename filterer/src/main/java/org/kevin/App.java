package org.kevin;

import lombok.extern.slf4j.Slf4j;
import org.kevin.threat.*;

import java.util.List;

@Slf4j
public class App {

    public static void main(String[] args) {
        filteringSimpleThreats();
        filteringSimpleProbableThreats();
    }

    private static void filteringSimpleProbableThreats() {
        log.info("### Filtering ProbabilisticThreatAwareSystem by probability ###");

        var trojanArcBomb = new SimpleProbableThreat("Trojan-ArcBomb", 1, ThreatType.TROJAN, 0.99);
        var rootkit = new SimpleProbableThreat("Rootkit-Kernel", 2, ThreatType.ROOTKIT, 0.8);

        List<ProbableThreat> probableThreats = List.of(trojanArcBomb, rootkit);

        var probabilisticThreatAwareSystem =
                new SimpleProbabilisticThreatAwareSystem("Sys-1", probableThreats);

        log.info("Filtering ProbabilisticThreatAwareSystem. Initial : "
                + probabilisticThreatAwareSystem);

        //Filtering using filterer
        var filteredThreatAwareSystem = probabilisticThreatAwareSystem.filtered()
                .by(probableThreat -> Double.compare(probableThreat.probability(), 0.99) == 0);

        log.info("Filtered by probability = 0.99 : " + filteredThreatAwareSystem);
    }

    private static void filteringSimpleThreats() {
        log.info("### Filtering ThreatAwareSystem by ThreatType ###");

        var rootkit = new SimpleThreat(ThreatType.ROOTKIT, 1, "Simple-Rootkit");
        var trojan = new SimpleThreat(ThreatType.TROJAN, 2, "Simple-Trojan");
        List<Threat> threats = List.of(rootkit, trojan);

        var threatAwareSystem = new SimpleThreatAwareSystem("Sys-1", threats);

        log.info("Filtering ThreatAwareSystem. Initial : " + threatAwareSystem);

        //Filtering using Filterer
        var rootkitThreatAwareSystem = threatAwareSystem.filtered()
                .by(threat -> threat.type() == ThreatType.ROOTKIT);

        log.info("Filtered by threatType = ROOTKIT : " + rootkitThreatAwareSystem);
    }

}
