package org.kevin.prototype.abstractfactory;

public class Kingdom {

    private King king;

    private Castle castle;

    private Army army;

    public King getKing() {
        return king;
    }

    public void setKing(King king) {
        this.king = king;
    }

    public Castle getCastle() {
        return castle;
    }

    public void setCastle(Castle castle) {
        this.castle = castle;
    }

    public Army getArmy() {
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }

    public static class FactoryMaker{

        public enum KingdomType{
            ELF,ORC
        }

        public static KingdomFactory makeFactory(KingdomType type){
            switch (type){
                case ELF :
                    return new ElfkingdomFactory();
                case ORC:
                    return new OrcKingdomFactory();
                default:
                    throw new IllegalArgumentException("KingdomType not supported");
            }
        }
    }
}
