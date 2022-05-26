package de.prog2.dungeontop.model.skills;
//Will only be implemented when we implement furter battlelogik. Optional fuer die Abgabe
public class ManaPool
{
    private int currentMana = 0, maxMana = 0;
    public ManaPool(int maxMana)
    {
        this.maxMana = maxMana;
    }
    public void increaseMana()
    {
        if(this.currentMana < this.maxMana) this.currentMana++;
    }
    public void resetMana()
    {
        this.currentMana = 0;
    }

    public int getCurrentMana() {
        return currentMana;
    }
}
