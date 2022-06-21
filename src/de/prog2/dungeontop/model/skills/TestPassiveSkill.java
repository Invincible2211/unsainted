package de.prog2.dungeontop.model.skills;

import de.prog2.dungeontop.resources.PassiveSkillValues;

public class TestPassiveSkill extends PassiveSkill
{

    public TestPassiveSkill() {
        super(PassiveSkillValues.TEST_PASSIVE_SKILL_NAME, PassiveSkillValues.TEST_PASSIVE_SKILL_DESCRIPTION, PassiveSkillValues.TEST_PASSIVE_SKILL_RANGE);
    }

    @Override
    public void doSomething() {

    }
}
