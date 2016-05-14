/*
 * Copyright (C) 2016 Malanius <malanius@seznam.cz>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package battlestar;

import io.Output;

/**
 * Battlestars' Hull or armor. Armor provides bonus damage resistance based on
 * level. If hull HPs goest to 0, it's game over.
 *
 * @author Malanius <malanius@seznam.cz>
 */
public class ProtectionLayer extends Subsystem {

    /**
     * Current HP of layer
     */
    private int hp;
    /**
     * Maximum HP of layer
     */
    private int maxHp;
    /**
     * Indicates ammount of HP layer has per level
     */
    private final int hpPerLvl;
    /**
     * Flag for armor. Armor provides protection bonus.
     */
    boolean armor;

    /**
     * Creates protection layer for battlestar. Specify name of layer to determine Hull/Armor
     * @param subsystem name - Armor or Hull
     * @param maxLevel maximum level of subsystem
     * @param baseUpgradeCost basic cost of upgrade, total upgrade cost is level * baseUprade cost
     * @param hpPerLvl base hitpint for level, total max hitpoints is level * hpPerLvl
     */
    protected ProtectionLayer(String subsystem, int maxLevel, int baseUpgradeCost, int hpPerLvl) {
        super(subsystem, maxLevel, baseUpgradeCost);
        this.hpPerLvl = hpPerLvl;
        this.level = 1;
        this.maxHp = hpPerLvl * level;
        if (subsystem.equals("Armor")) {
            armor = true;
        } else {
            armor = false;
        }

    }

    /**
     * @return the hp
     */
    protected int getHp() {
        return hp;
    }
    /** Upgrades the layer, if possible.
     * 
     * @param cargo from resources for upgrade are taken
     * @param crew that makes the upgrade (for resource bonus calculation)
     * @throws AlreadyAtMaxException 
     */
    @Override
    protected void upgrade(Cargo cargo, Crew crew) throws AlreadyAtMaxException {
        if (level < maxLevel) {
            int upgradeCost = baseUpgradeCost * level;
            try {
                cargo.useResources(upgradeCost);
                level++;
                maxHp = hpPerLvl * level;
                hp = maxHp;
            } catch (MissingResourcesException ex) {
                String message = String.format("%s cant't be upgraded, missing %s resources.", subsystem, ex.getMissing());
                Output.msgInfo(message);
            }
        } else {
            throw new AlreadyAtMaxException();
        }
    }

    /**
     * Calculates bonus protection for armor. It's not used for hull.
     * @param damage incoming damage, already reduced by crew bonus
     * @return reduced damage
     */
    protected int getBonusProtection(int damage) {
        int bonus = Math.round(damage * (level / 10.0f));
        int newValue = damage - bonus;
        Output.msgDebug(subsystem, String.format("Layer bonus from %s damage is %s damage).", -bonus, newValue));
        return newValue;
    }

    /**
     * Takes the hit and reduces hit point.
     * Crew and armor bonuses are aplied before reducing hitpoints.
     * @param damage incoming damage
     * @param crew crew for calculating bonus
     */
    protected void takeDamage(int damage, Crew crew) {
        damage = crew.calculateBonus(damage);
        if (armor) {
            damage = getBonusProtection(damage);
        }
        hp -= damage;
        if (hp <= 0) {
            hp = 0;
            String message = String.format("%s was destroyed!", subsystem);
            Output.msgInfo(message);
        }
    }

    /**
     * Repairs the layer, if enough resources is avalible.
     * @param cargo from where resources for repairs are taken
     * @param crew crew that makes the repairs, used for counting resources bonus
     * @throws NoDamageException if there is nothing to repair
     */
    protected void repair(Cargo cargo, Crew crew) throws NoDamageException {
        if (getHp() == maxHp) {
            throw new NoDamageException();
        } else {
            int repairCost = maxHp - getHp();
            repairCost = crew.calculateBonus(repairCost);
            try {
                cargo.useResources(repairCost);
                hp = maxHp;
            } catch (MissingResourcesException ex) {
                String message = String.format("%s can't be repaired, we're missing %s respurces", subsystem, ex.getMissing());
                Output.msgInfo(message);
            }
        }
    }
}
