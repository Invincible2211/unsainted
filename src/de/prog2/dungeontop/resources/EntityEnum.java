package de.prog2.dungeontop.resources;

import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.entities.Minion;

public enum EntityEnum
{
    // Asmodeus
    ASMODEUS_R1 (new Minion("Asmodeus", 3, 1, 0, 1, 1, AssetIds.ASMODEUS)),
    ASMODEUS_R2 (new Minion("Asmodeus", 4, 2, 0, 1, 1, AssetIds.ASMODEUS)),
    ASMODEUS_R3 (new Minion("Asmodeus", 6, 3, 0, 1, 1, AssetIds.ASMODEUS)),

    // Akephalos
    AKEPHALOS_R1 (new Minion("Akephalos", 3, 1, 0, 1, 1, AssetIds.AKEPHALOS)),
    AKEPHALOS_R2 (new Minion("Akephalos", 4, 2, 0, 1, 1, AssetIds.AKEPHALOS)),
    AKEPHALOS_R3 (new Minion("Akephalos", 6, 3, 0, 1, 1, AssetIds.AKEPHALOS)),

    // Aynaet
    AYNAET_R1 (new Minion("Aynaet", 3, 1, 0, 1, 1, AssetIds.AYNAET)),
    AYNAET_R2 (new Minion("Aynaet", 4, 2, 0, 1, 1, AssetIds.AYNAET)),
    AYNAET_R3 (new Minion("Aynaet", 6, 3, 0, 1, 1, AssetIds.AYNAET)),

    // Asasel
    ASASEL_R1 (new Minion("Asasel", 3, 1, 0, 1, 1, AssetIds.ASASEL)),
    ASASEL_R2 (new Minion("Asasel", 4, 2, 0, 1, 1, AssetIds.ASASEL)),
    ASASEL_R3 (new Minion("Asasel", 6, 3, 0, 1, 1, AssetIds.ASASEL)),

    // Baal
    BAAL_R1 (new Minion("Baal", 3, 1, 0, 1, 1, AssetIds.BAAL)),
    BAAL_R2 (new Minion("Baal", 4, 2, 0, 1, 1, AssetIds.BAAL)),
    BAAL_R3 (new Minion("Baal", 6, 3, 0, 1, 1, AssetIds.BAAL)),

    // Belial
    BELIAL_R1 (new Minion("Belial", 3, 1, 0, 1, 1, AssetIds.BELIAL)),
    BELIAL_R2 (new Minion("Belial", 4, 2, 0, 1, 1, AssetIds.BELIAL)),
    BELIAL_R3 (new Minion("Belial", 6, 3, 0, 1, 1, AssetIds.BELIAL)),

    // Beelzebub
    BEELZEBUB_R1 (new Minion("Beelzebub", 3, 1, 1, 0, 1, AssetIds.BEELZEBUB)),
    BEELZEBUB_R2 (new Minion("Beelzebub", 4, 2, 1, 0, 1, AssetIds.BEELZEBUB)),
    BEELZEBUB_R3 (new Minion("Beelzebub", 6, 3, 1, 0, 1, AssetIds.BEELZEBUB)),

    // Incubus
    INCUBUS_R1 (new Minion("Incubus", 3, 1, 0, 1, 1, AssetIds.INCUBUS)),
    INCUBUS_R2 (new Minion("Incubus", 4, 2, 0, 1, 1, AssetIds.INCUBUS)),
    INCUBUS_R3 (new Minion("Incubus", 6, 3, 0, 1, 1, AssetIds.INCUBUS)),

    // Lilith
    LILITH_R1 (new Minion("Lilith", 3, 1, 0, 1, 1, AssetIds.LILITH)),
    LILITH_R2 (new Minion("Lilith", 4, 2, 0, 1, 1, AssetIds.LILITH)),
    LILITH_R3 (new Minion("Lilith", 6, 3, 0, 1, 1, AssetIds.LILITH)),

    // Medusa
    MEDUSA_R1 (new Minion("Medusa", 3, 1, 0, 1, 1, AssetIds.MEDUSA)),
    MEDUSA_R2 (new Minion("Medusa", 4, 2, 0, 1, 1, AssetIds.MEDUSA)),
    MEDUSA_R3 (new Minion("Medusa", 6, 3, 0, 1, 1, AssetIds.MEDUSA)),

    // Sphinx
    SPHINX_R1 (new Minion("Sphinx", 3, 1, 0, 1, 1, AssetIds.SPHINX)),
    SPHINX_R2 (new Minion("Sphinx", 4, 2, 0, 1, 1, AssetIds.SPHINX)),
    SPHINX_R3 (new Minion("Sphinx", 6, 3, 0, 1, 1, AssetIds.SPHINX)),

    // Vanth
    VANTH_R1 (new Minion("Vanth", 3, 1, 0, 1, 1, AssetIds.VANTH)),
    VANTH_R2 (new Minion("Vanth", 4, 2, 0, 1, 1, AssetIds.VANTH)),
    VANTH_R3 (new Minion("Vanth", 6, 3, 0, 1, 1, AssetIds.VANTH)),

    // Legion
    LEGION_R1 (new Minion("Legion", 3, 1, 0, 1, 1, AssetIds.LEGION)),
    LEGION_R2 (new Minion("Legion", 4, 2, 0, 1, 1, AssetIds.LEGION)),
    LEGION_R3 (new Minion("Legion", 6, 3, 0, 1, 1, AssetIds.LEGION));

    private final Entity value;
    private EntityEnum(Entity entity)
    {
        this.value = entity;
    }

    public Entity getValue()
    {
        return value;
    }
}
