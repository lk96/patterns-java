package org.kevin;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class App {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        RainbowFish zed = new RainbowFish("Zed", 10, 20, 30);
        log.info("fish zed name:{} age:{} length:{} weight:{}",
                zed.getName(), zed.getAge(), zed.getLengthMeters(), zed.getWeightTons());
        RainbowFishSerializer.writeV1(zed,"zed.out");
        RainbowFish deserializerZed = RainbowFishSerializer.readV1("zed.out");
        log.info("deserializer fish zed name:{} age:{} length:{} weight:{}",
                deserializerZed.getName(), deserializerZed.getAge(), deserializerZed.getLengthMeters(), deserializerZed.getWeightTons());

        RainbowFishV2 scar = new RainbowFishV2("Scar", 5, 15, 20, true, true, true);
        log.info("fish scar name:{} age:{} length:{} weight:{} sleeping:{} hungry:{} angry:{}",
                scar.getName(), scar.getAge(), scar.getLengthMeters(), scar.getWeightTons(),scar.isSleeping(),
                scar.isHungry(),scar.isAngry());
        RainbowFishSerializer.writeV2(scar, "scar.out");
        RainbowFish deserializerScar = RainbowFishSerializer.readV1("scar.out");
        log.info("fish scar name:{} age:{} length:{} weight:{}",
                deserializerScar.getName(), deserializerScar.getAge(), deserializerScar.getLengthMeters(), deserializerScar.getWeightTons());
    }
}
