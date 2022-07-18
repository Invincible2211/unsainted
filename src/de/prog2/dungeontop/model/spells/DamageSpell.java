package de.prog2.dungeontop.model.spells;

import de.prog2.dungeontop.control.controller.EntityController;
import de.prog2.dungeontop.control.manager.BattleManager2;
import de.prog2.dungeontop.control.network.NetManager;
import de.prog2.dungeontop.model.entities.Entity;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.arena.Arena;
import de.prog2.dungeontop.resources.TestConstants;
import de.prog2.dungeontop.utils.ArenaUtils;

public class DamageSpell extends Spell
{
    private final int damage;
    private final int radius;

    public DamageSpell (String name, String description, int assetId, int damage, int radius)
    {
        super(name, description, assetId);
        this.damage = damage;
        this.radius = radius;
    }

    @Override
    public void cast(Arena arena, Coordinate coordinate)
    {
        for (int x = coordinate.getX() - radius; x <= coordinate.getX() + radius; x++)
        {
            for (int y = coordinate.getY() - radius; y <= coordinate.getY() + radius; y++)
            {
                Coordinate c = new Coordinate(x, y);
                Entity entity = arena.getOpponent().get(c);
                if (entity != null)
                {
                    if (EntityController.applyDamage(arena.getOpponent().get(c), damage))
                    {
                        NetManager.getInstance().getNetworkAPI().sendChangeEntityHpPackage(ArenaUtils.invertCoordinate(arena, c), damage);
                        return;
                    }
                    NetManager.getInstance().getNetworkAPI().sendRemoveEntity(ArenaUtils.invertCoordinate(arena, c));
                    BattleManager2.getInstance().remove(c, false);
                }
            }
        }
    }

    public int getDamage()
    {
        return damage;
    }

    public int getRadius()
    {
        return radius;
    }
}
