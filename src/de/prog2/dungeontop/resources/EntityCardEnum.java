package de.prog2.dungeontop.resources;

import de.prog2.dungeontop.model.game.EntityCard;

public enum EntityCardEnum
{
    // EntityCardEnum which are used for the cards

    // Asmodeus
    ASMODEUS_R1_CARD ( new EntityCard(EntityEnum.ASMODEUS_R1.getValue(), 3, 50, 1, 1)),
    ASMODEUS_R2_CARD ( new EntityCard(EntityEnum.ASMODEUS_R2.getValue(), 3, 100, 2, 2)),
    ASMODEUS_R3_CARD ( new EntityCard(EntityEnum.ASMODEUS_R3.getValue(), 3, 200, 3, 3)),

    // Akephalos
    AKEPHALOS_R1_CARD (new EntityCard(EntityEnum.AKEPHALOS_R1.getValue(), 3, 50, 1, 1)),
    AKEPHALOS_R2_CARD (new EntityCard(EntityEnum.AKEPHALOS_R2.getValue(), 3, 50, 2, 1)),
    AKEPHALOS_R3_CARD (new EntityCard(EntityEnum.AKEPHALOS_R3.getValue(), 3, 50, 3, 1)),

    // Aynaet
    AYNAET_R1_CARD (new EntityCard(EntityEnum.AYNAET_R1.getValue(), 3, 50, 1, 1)),
    AYNAET_R2_CARD (new EntityCard(EntityEnum.AYNAET_R2.getValue(), 3, 50, 2, 1)),
    AYNAET_R3_CARD (new EntityCard(EntityEnum.AYNAET_R3.getValue(), 3, 50, 3, 1)),

    // Asasel
    Asasel_R1_CARD (new EntityCard(EntityEnum.ASASEL_R1.getValue(), 3, 50, 1, 1)),
    Asasel_R2_CARD (new EntityCard(EntityEnum.ASASEL_R2.getValue(), 3, 50, 2, 1)),
    Asasel_R3_CARD (new EntityCard(EntityEnum.ASASEL_R3.getValue(), 3, 50, 3, 1)),

    // Baal
    BAAL_R1_CARD (new EntityCard(EntityEnum.BAAL_R1.getValue(), 3, 50, 1, 1)),
    BAAL_R2_CARD (new EntityCard(EntityEnum.BAAL_R2.getValue(), 3, 50, 2, 1)),
    BAAL_R3_CARD (new EntityCard(EntityEnum.BAAL_R3.getValue(), 3, 50, 3, 1)),

    // Belial
    BELIAL_R1_CARD (new EntityCard(EntityEnum.BELIAL_R1.getValue(), 3, 50, 1, 1)),
    BELIAL_R2_CARD (new EntityCard(EntityEnum.BELIAL_R2.getValue(), 3, 50, 2, 1)),
    BELIAL_R3_CARD (new EntityCard(EntityEnum.BELIAL_R3.getValue(), 3, 50, 3, 1)),

    // Beelzebub
    BEELZEBUB_R1_CARD (new EntityCard(EntityEnum.BEELZEBUB_R1.getValue(), 3, 50, 1, 1)),
    BEELZEBUB_R2_CARD (new EntityCard(EntityEnum.BEELZEBUB_R2.getValue(), 3, 50, 2, 1)),
    BEELZEBUB_R3_CARD (new EntityCard(EntityEnum.BEELZEBUB_R3.getValue(), 3, 50, 3, 1)),

    // Incubus
    INCUBUS_R1_CARD (new EntityCard(EntityEnum.INCUBUS_R1.getValue(), 3, 50, 1, 1)),
    INCUBUS_R2_CARD (new EntityCard(EntityEnum.INCUBUS_R2.getValue(), 3, 50, 2, 1)),
    INCUBUS_R3_CARD (new EntityCard(EntityEnum.INCUBUS_R3.getValue(), 3, 50, 3, 1)),

    // Lilith
    LILITH_R1_CARD (new EntityCard(EntityEnum.LILITH_R1.getValue(), 3, 50, 1, 1)),
    LILITH_R2_CARD (new EntityCard(EntityEnum.LILITH_R2.getValue(), 3, 50, 2, 1)),
    LILITH_R3_CARD (new EntityCard(EntityEnum.LILITH_R3.getValue(), 3, 50, 3, 1)),

    // Medusa
    MEDUSA_R1_CARD (new EntityCard(EntityEnum.MEDUSA_R1.getValue(), 3, 50, 1, 1)),
    MEDUSA_R2_CARD (new EntityCard(EntityEnum.MEDUSA_R2.getValue(), 3, 50, 2, 1)),
    MEDUSA_R3_CARD (new EntityCard(EntityEnum.MEDUSA_R3.getValue(), 3, 50, 3, 1)),

    // Sphinx
    SPHINX_R1_CARD (new EntityCard(EntityEnum.SPHINX_R1.getValue(), 3, 50, 1, 1)),
    SPHINX_R2_CARD (new EntityCard(EntityEnum.SPHINX_R2.getValue(), 3, 50, 2, 1)),
    SPHINX_R3_CARD (new EntityCard(EntityEnum.SPHINX_R3.getValue(), 3, 50, 3, 1)),

    // Vanth
    VANTH_R1_CARD (new EntityCard(EntityEnum.VANTH_R1.getValue(), 3, 50, 1, 1)),
    VANTH_R2_CARD (new EntityCard(EntityEnum.VANTH_R2.getValue(), 3, 50, 2, 1)),
    VANTH_R3_CARD (new EntityCard(EntityEnum.VANTH_R3.getValue(), 3, 50, 3, 1)),

    // Legion
    LEGION_R1_CARD (new EntityCard(EntityEnum.LEGION_R1.getValue(), 3, 50, 1, 1)),
    LEGION_R2_CARD (new EntityCard(EntityEnum.LEGION_R2.getValue(), 3, 50, 2, 1)),
    LEGION_R3_CARD (new EntityCard(EntityEnum.LEGION_R3.getValue(), 3, 50, 3, 1));


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
