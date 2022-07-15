package de.prog2.dungeontop.model.items;

public interface Equippable
{
    /**
     * Equips the item.
     * @return true if the item was equipped, false otherwise.
     */
    boolean equip();

    /**
     * Unequips the item.
     * @return true if the item was unequipped, false otherwise.
     */
    boolean unequip();

    /**
     * Checks if the item is equipped.
     */
    boolean isEquipped();
}
