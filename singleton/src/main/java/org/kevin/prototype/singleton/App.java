package org.kevin.prototype.singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {

        // 饿汉式
        IvoryTower ivoryTower1 = IvoryTower.getInstance();
        IvoryTower ivoryTower2 = IvoryTower.getInstance();
        log.info("ivoryTower1={}", ivoryTower1);
        log.info("ivoryTower2={}", ivoryTower2);

        // 懒汉式
        ThreadSafeLazyLoadIvoryTower threadSafeLazyLoadIvoryTower1 = ThreadSafeLazyLoadIvoryTower.getInstance();
        ThreadSafeLazyLoadIvoryTower threadSafeLazyLoadIvoryTower2 = ThreadSafeLazyLoadIvoryTower.getInstance();
        log.info("threadSafeLazyLoadIvoryTower1={}", threadSafeLazyLoadIvoryTower1);
        log.info("threadSafeLazyLoadIvoryTower2={}", threadSafeLazyLoadIvoryTower2);

        // 枚举
        IvoryTowerEnum ivoryTowerEnum1 = IvoryTowerEnum.INSTANCE;
        IvoryTowerEnum ivoryTowerEnum2 = IvoryTowerEnum.INSTANCE;
        log.info("ivoryTowerEnum1={}", ivoryTowerEnum1);
        log.info("ivoryTowerEnum2={}", ivoryTowerEnum2);

        // 双重检查
        ThreadSafeDoubleCheckLocking threadSafeDoubleCheckLocking1 = ThreadSafeDoubleCheckLocking.getInstance();
        ThreadSafeDoubleCheckLocking threadSafeDoubleCheckLocking2 = ThreadSafeDoubleCheckLocking.getInstance();
        log.info("threadSafeDoubleCheckLocking1={}", threadSafeDoubleCheckLocking1);
        log.info("threadSafeDoubleCheckLocking2={}", threadSafeDoubleCheckLocking2);

        InitializingOnDemandHolderIdiom demandHolderIdiom1 = InitializingOnDemandHolderIdiom.getInstance();
        InitializingOnDemandHolderIdiom demandHolderIdiom2 = InitializingOnDemandHolderIdiom.getInstance();
        log.info("demandHolderIdiom1={}", demandHolderIdiom1);
        log.info("demandHolderIdiom2={}", demandHolderIdiom2);

    }
}
