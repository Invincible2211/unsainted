package de.prog2.dungeontop.model.skills;

import de.prog2.dungeontop.resources.ActiveSkillValues;

public class TestActiveSkill extends ActiveSkill
{
    public TestActiveSkill() {
        super(ActiveSkillValues.TEST_ACTIVE_SKILL_NAME, ActiveSkillValues.TEST_ACTIVE_SKILL_DESCRIPTION, ActiveSkillValues.TEST_ACTIVE_SKILL_RANGE);
    }

    @Override
    public void doSomething() {

    }
}
