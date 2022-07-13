package de.prog2.dungeontop.resources;

import de.prog2.dungeontop.model.game.EntityCard;

public enum EntityCardEnum
{
    // EntityCardEnum which are used for the cards

    // Asmodeus
    ASMODEUS_R1_CARD ( new EntityCard(EntityEnum.ASMODEUS_R1.getValue(), 3, 50, 1, 1, 1)),
    ASMODEUS_R2_CARD ( new EntityCard(EntityEnum.ASMODEUS_R2.getValue(), 3, 100, 2, 2, 2)),
    ASMODEUS_R3_CARD ( new EntityCard(EntityEnum.ASMODEUS_R3.getValue(), 3, 200, 3, 3, 3)),

    // Akephalos
    AKEPHALOS_R1_CARD (new EntityCard(EntityEnum.AKEPHALOS_R1.getValue(), 3, 50, 1, 1, 4)),
    AKEPHALOS_R2_CARD (new EntityCard(EntityEnum.AKEPHALOS_R2.getValue(), 3, 50, 2, 1, 5)),
    AKEPHALOS_R3_CARD (new EntityCard(EntityEnum.AKEPHALOS_R3.getValue(), 3, 50, 3, 1, 6)),

    // Aynaet
    AYNAET_R1_CARD (new EntityCard(EntityEnum.AYNAET_R1.getValue(), 3, 50, 1, 1, 7)),
    AYNAET_R2_CARD (new EntityCard(EntityEnum.AYNAET_R2.getValue(), 3, 50, 2, 1, 8)),
    AYNAET_R3_CARD (new EntityCard(EntityEnum.AYNAET_R3.getValue(), 3, 50, 3, 1, 9)),

    // Asasel
    Asasel_R1_CARD (new EntityCard(EntityEnum.ASASEL_R1.getValue(), 3, 50, 1, 1, 10)),
    Asasel_R2_CARD (new EntityCard(EntityEnum.ASASEL_R2.getValue(), 3, 50, 2, 1, 11)),
    Asasel_R3_CARD (new EntityCard(EntityEnum.ASASEL_R3.getValue(), 3, 50, 3, 1, 12)),

    // Baal
    BAAL_R1_CARD (new EntityCard(EntityEnum.BAAL_R1.getValue(), 3, 50, 1, 1, 13)),
    BAAL_R2_CARD (new EntityCard(EntityEnum.BAAL_R2.getValue(), 3, 50, 2, 1, 14)),
    BAAL_R3_CARD (new EntityCard(EntityEnum.BAAL_R3.getValue(), 3, 50, 3, 1, 15)),

    // Belial
    BELIAL_R1_CARD (new EntityCard(EntityEnum.BELIAL_R1.getValue(), 3, 50, 1, 1, 16)),
    BELIAL_R2_CARD (new EntityCard(EntityEnum.BELIAL_R2.getValue(), 3, 50, 2, 1, 17)),
    BELIAL_R3_CARD (new EntityCard(EntityEnum.BELIAL_R3.getValue(), 3, 50, 3, 1, 18)),

    // Beelzebub
    BEELZEBUB_R1_CARD (new EntityCard(EntityEnum.BEELZEBUB_R1.getValue(), 3, 50, 1, 1, 19)),
    BEELZEBUB_R2_CARD (new EntityCard(EntityEnum.BEELZEBUB_R2.getValue(), 3, 50, 2, 1, 20)),
    BEELZEBUB_R3_CARD (new EntityCard(EntityEnum.BEELZEBUB_R3.getValue(), 3, 50, 3, 1, 21)),

    // Incubus
    INCUBUS_R1_CARD (new EntityCard(EntityEnum.INCUBUS_R1.getValue(), 3, 50, 1, 1, 22)),
    INCUBUS_R2_CARD (new EntityCard(EntityEnum.INCUBUS_R2.getValue(), 3, 50, 2, 1, 23)),
    INCUBUS_R3_CARD (new EntityCard(EntityEnum.INCUBUS_R3.getValue(), 3, 50, 3, 1, 24)),

    // Lilith
    LILITH_R1_CARD (new EntityCard(EntityEnum.LILITH_R1.getValue(), 3, 50, 1, 1, 25)),
    LILITH_R2_CARD (new EntityCard(EntityEnum.LILITH_R2.getValue(), 3, 50, 2, 1, 26)),
    LILITH_R3_CARD (new EntityCard(EntityEnum.LILITH_R3.getValue(), 3, 50, 3, 1, 27)),

    // Medusa
    MEDUSA_R1_CARD (new EntityCard(EntityEnum.MEDUSA_R1.getValue(), 3, 50, 1, 1, 28)),
    MEDUSA_R2_CARD (new EntityCard(EntityEnum.MEDUSA_R2.getValue(), 3, 50, 2, 1, 29)),
    MEDUSA_R3_CARD (new EntityCard(EntityEnum.MEDUSA_R3.getValue(), 3, 50, 3, 1, 30)),

    // Sphinx
    SPHINX_R1_CARD (new EntityCard(EntityEnum.SPHINX_R1.getValue(), 3, 50, 1, 1, 31)),
    SPHINX_R2_CARD (new EntityCard(EntityEnum.SPHINX_R2.getValue(), 3, 50, 2, 1, 32)),
    SPHINX_R3_CARD (new EntityCard(EntityEnum.SPHINX_R3.getValue(), 3, 50, 3, 1, 33)),

    // Vanth
    VANTH_R1_CARD (new EntityCard(EntityEnum.VANTH_R1.getValue(), 3, 50, 1, 1, 34)),
    VANTH_R2_CARD (new EntityCard(EntityEnum.VANTH_R2.getValue(), 3, 50, 2, 1, 35)),
    VANTH_R3_CARD (new EntityCard(EntityEnum.VANTH_R3.getValue(), 3, 50, 3, 1, 36)),

    // Legion
    LEGION_R1_CARD (new EntityCard(EntityEnum.LEGION_R1.getValue(), 3, 50, 1, 1, 37)),
    LEGION_R2_CARD (new EntityCard(EntityEnum.LEGION_R2.getValue(), 3, 50, 2, 1, 38)),
    LEGION_R3_CARD (new EntityCard(EntityEnum.LEGION_R3.getValue(), 3, 50, 3, 1, 39));


    private EntityCard value;
    private EntityCardEnum(EntityCard card)
    {
        this.value = card;
    }

    public EntityCard getValue()
    {
        return value;
    }
}
