package de.prog2.dungeontop.model.entities.monsterindividuals;

import de.prog2.dungeontop.model.entities.Minion;
import de.prog2.dungeontop.model.game.Card;
import de.prog2.dungeontop.model.game.Player;
import de.prog2.dungeontop.model.perks.Perk;
import de.prog2.dungeontop.model.skills.ActiveSkill;
import de.prog2.dungeontop.model.skills.ManaPool;
import de.prog2.dungeontop.model.world.Coordinate;
import de.prog2.dungeontop.model.world.arena.Arena;
import de.prog2.dungeontop.resources.DefaultMinionValues;
import de.prog2.dungeontop.resources.LoggerStringValues;
import de.prog2.dungeontop.utils.GlobalLogger;

public class Samira extends Minion
{
    @Override
    public Arena attackAction (Coordinate positionGettingAttacked, Arena arena)
    {
        for (int i = 0; i < arena.getArenaHashmap().size(); i++) {

        }
        // check active perks,
        // check if mana is full or not -> if so instead of attack it will do spell
        // call takeDamage on each reciever and set arenda to version with damge taken on reciever
        //fill up mana with fixed value

        this.setAttackRange(this.getAttackRange() + 1);
        return super.attackAction(positionGettingAttacked, arena);
    }

    @Override
    public Arena takeDamage (Coordinate position, Arena arena)
    {
        return super.takeDamage(position, arena);
    }

    @Deprecated
    public Samira (Card card, int hp, int attackDamage, int movement, int maxMoves,
                   int attackRange, Player owner, String name, int possibleAttacksPerRound,
                   ActiveSkill activeSkill,
                   ManaPool manaPool, Perk perk1,
                   Perk perk2)
    {
        super(card, hp, attackDamage, movement, maxMoves, attackRange, owner, possibleAttacksPerRound,
                activeSkill,
                manaPool, perk1, perk2);
    }

    public Samira (Card card, Player owner, int rank, Coordinate coordinate)
    {
        super(card, owner, rank, coordinate);


        this.setCard(card);
        this.setOwner(owner);
        this.setPosition(coordinate);
        this.addPerk(DefaultMinionValues.SAMIRA_FIRST_PERK);
        this.addPerk(DefaultMinionValues.SAMIRA_SECOND_PERK);

        switch(rank){
            case 1:
                setOneStar();
                GlobalLogger.log(LoggerStringValues.RANK_ONE_SAMIRA_BEING_CREATED);
                break;
            case 2:
                setTwoStars();
                GlobalLogger.log(LoggerStringValues.RANK_TWO_SAMIRA_BEING_CREATED);
                break;
            case 3:
                setThreeStars();
                GlobalLogger.log(LoggerStringValues.RANK_THREE_SAMIRA_BEING_CREATED);
                break;
            default:
                GlobalLogger.warning(LoggerStringValues.SPECIFY_RANK);
                break;
        }
    }

    //Samira one Star
    private void setOneStar ()
    {
        this.setHp(DefaultMinionValues.SAMIRA_HP_RANK_ONE);
        this.setAttackDamage(DefaultMinionValues.SAMIRA_DMG_RANK_ONE);
        this.setMaxMoves(DefaultMinionValues.SAMIRA_MAXMOV_RANK_ONE);
        this.setAttackRange(DefaultMinionValues.SAMIRA_RANGE_RANK_ONE);
    }
    //Samira two Stars
    private void setTwoStars ()
    {
        this.setHp(DefaultMinionValues.SAMIRA_HP_RANK_TWO);
        this.setAttackDamage(DefaultMinionValues.SAMIRA_DMG_RANK_TWO);
        this.setMaxMoves(DefaultMinionValues.SAMIRA_MAXMOV_RANK_TWO);
        this.setAttackRange(DefaultMinionValues.SAMIRA_RANGE_RANK_TWO);
    }
    //Samira three Stars
    private void setThreeStars ()
    {
        this.setHp(DefaultMinionValues.SAMIRA_HP_RANK_THREE);
        this.setAttackDamage(DefaultMinionValues.SAMIRA_DMG_RANK_THREE);
        this.setMaxMoves(DefaultMinionValues.SAMIRA_MAXMOV_RANK_THREE);
        this.setAttackRange(DefaultMinionValues.SAMIRA_RANGE_RANK_THREE);
    }

}
