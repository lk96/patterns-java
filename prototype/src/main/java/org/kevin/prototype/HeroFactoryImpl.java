package org.kevin.prototype;

public class HeroFactoryImpl implements HeroFactory{

    private final Mage mage;
    private final Warlord warlord;
    private final Beast beast;

    public HeroFactoryImpl(Mage mage, Warlord warlord, Beast beast) {
        this.mage = mage;
        this.warlord = warlord;
        this.beast = beast;
    }

    @Override
    public Mage createMage() {
        return mage.copy();
    }

    @Override
    public Beast createBeast() {
        return beast.copy();
    }

    @Override
    public Warlord createWarlord() {
        return warlord.copy();
    }
}
