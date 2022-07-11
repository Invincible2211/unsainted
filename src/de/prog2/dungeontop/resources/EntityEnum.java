package de.prog2.dungeontop.resources;

import de.prog2.dungeontop.control.manager.PlayerManager;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.entities.Minion;

public enum EntityEnum
{
    // Asmodeus
    ASMODEUS_R1 (new Minion("Asmodeus", 3, 1, 1, AssetIds.EXAMPLE_MINION)),
    ASMODEUS_R2 (new Minion("Asmodeus", 4, 2, 1, AssetIds.EXAMPLE_MINION)),
    ASMODEUS_R3 (new Minion("Asmodeus", 6, 3, 1, AssetIds.EXAMPLE_MINION)),

    // Akephalos
    AKEPHALOS_R1 (new Minion("Akephalos", 3, 1, 1, AssetIds.EXAMPLE_MINION)),
    AKEPHALOS_R2 (new Minion("Akephalos", 4, 2, 1, AssetIds.EXAMPLE_MINION)),
    AKEPHALOS_R3 (new Minion("Akephalos", 6, 3, 1, AssetIds.EXAMPLE_MINION)),

    // Aynaet
    AYNAET_R1 (new Minion("Aynaet", 3, 1, 1, AssetIds.EXAMPLE_MINION)),
    AYNAET_R2 (new Minion("Aynaet", 4, 2, 1, AssetIds.EXAMPLE_MINION)),
    AYNAET_R3 (new Minion("Aynaet", 6, 3, 1, AssetIds.EXAMPLE_MINION)),

    // Asasel
    ASASEL_R1 (new Minion("Asasel", 3, 1, 1, AssetIds.EXAMPLE_MINION)),
    ASASEL_R2 (new Minion("Asasel", 4, 2, 1, AssetIds.EXAMPLE_MINION)),
    ASASEL_R3 (new Minion("Asasel", 6, 3, 1, AssetIds.EXAMPLE_MINION)),

    // Baal
    BAAL_R1 (new Minion("Baal", 3, 1, 1, AssetIds.EXAMPLE_MINION)),
    BAAL_R2 (new Minion("Baal", 4, 2, 1, AssetIds.EXAMPLE_MINION)),
    BAAL_R3 (new Minion("Baal", 6, 3, 1, AssetIds.EXAMPLE_MINION)),

    // Belial
    BELIAL_R1 (new Minion("Belial", 3, 1, 1, AssetIds.EXAMPLE_MINION)),
    BELIAL_R2 (new Minion("Belial", 4, 2, 1, AssetIds.EXAMPLE_MINION)),
    BELIAL_R3 (new Minion("Belial", 6, 3, 1, AssetIds.EXAMPLE_MINION)),

    // Beelzebub
    BEELZEBUB_R1 (new Minion("Beelzebub", 3, 1, 1, AssetIds.EXAMPLE_MINION)),
    BEELZEBUB_R2 (new Minion("Beelzebub", 4, 2, 1, AssetIds.EXAMPLE_MINION)),
    BEELZEBUB_R3 (new Minion("Beelzebub", 6, 3, 1, AssetIds.EXAMPLE_MINION)),

    // Incubus
    INCUBUS_R1 (new Minion("Incubus", 3, 1, 1, AssetIds.EXAMPLE_MINION)),
    INCUBUS_R2 (new Minion("Incubus", 4, 2, 1, AssetIds.EXAMPLE_MINION)),
    INCUBUS_R3 (new Minion("Incubus", 6, 3, 1, AssetIds.EXAMPLE_MINION)),

    // Lilith
    LILITH_R1 (new Minion("Lilith", 3, 1, 1, AssetIds.EXAMPLE_MINION)),
    LILITH_R2 (new Minion("Lilith", 4, 2, 1, AssetIds.EXAMPLE_MINION)),
    LILITH_R3 (new Minion("Lilith", 6, 3, 1, AssetIds.EXAMPLE_MINION)),

    // Medusa
    MEDUSA_R1 (new Minion("Medusa", 3, 1, 1, AssetIds.EXAMPLE_MINION)),
    MEDUSA_R2 (new Minion("Medusa", 4, 2, 1, AssetIds.EXAMPLE_MINION)),
    MEDUSA_R3 (new Minion("Medusa", 6, 3, 1, AssetIds.EXAMPLE_MINION)),

    // Sphinx
    SPHINX_R1 (new Minion("Sphinx", 3, 1, 1, AssetIds.EXAMPLE_MINION)),
    SPHINX_R2 (new Minion("Sphinx", 4, 2, 1, AssetIds.EXAMPLE_MINION)),
    SPHINX_R3 (new Minion("Sphinx", 6, 3, 1, AssetIds.EXAMPLE_MINION)),

    // Vanth
    VANTH_R1 (new Minion("Vanth", 3, 1, 1, AssetIds.EXAMPLE_MINION)),
    VANTH_R2 (new Minion("Vanth", 4, 2, 1, AssetIds.EXAMPLE_MINION)),
    VANTH_R3 (new Minion("Vanth", 6, 3, 1, AssetIds.EXAMPLE_MINION)),

    // Legion
    LEGION_R1 (new Minion("Legion", 3, 1, 1, AssetIds.EXAMPLE_MINION)),
    LEGION_R2 (new Minion("Legion", 4, 2, 1, AssetIds.EXAMPLE_MINION)),
    LEGION_R3 (new Minion("Legion", 6, 3, 1, AssetIds.EXAMPLE_MINION));

    private Entity value;
    private EntityEnum(Entity entity)
    {
        this.value = entity;
    }

    public Entity getValue()
    {
        return value;
    }
}
