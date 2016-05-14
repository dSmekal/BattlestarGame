/*
 * Copyright (C) 2016 Malanius malanius@seznam.cz
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
 * Represents BS hull structural integrity. When it's destroyed, it's game over.
 * Hull doesn't provide bonuses to damage protection, but can be upgraded to
 * provide better structural integrity. Combat and events damage the hull, but
 * damage can be repaired using resources.
 *
 * @author Malanius malanius@seznam.cz
 * @version 0.0.1
 */
@Deprecated
class Hull {

    /**
     * Current level.
     */
    protected int level;
    /**
     * Maximal level. Subject to balancicng changes.
     */
    protected final int maxLevel = 5;
    /**
     * Base Upgrade cost per level. Subject to balancing changes
     */
    protected final int baseUpgradeCost = 1_000;
    /**
     * Current hit ponts, if reaches zero, game is over.
     */
    protected int hp;
    /**
     * Maximum hit points. Level x hpPerLlv. Subject to balancing changes.
     */
    protected int maxHp;
    /**
     * HP modifier per level. Subject to balancing changes
     */
    protected int hpPerLvl = 1_000;
    /**
     * Subsystem name for debug output.
     */
    protected String subsystem;

    /**
     * Crates hull of battlestar.
     */
    protected Hull() {
        level = 1;
        maxHp = level * hpPerLvl;
        hp = maxHp;
        subsystem = "Hull";
        Output.msgDebug(subsystem, "Initialized...");
    }//End of constructor

    /**
     * Returns structure level.
     *
     * @return level of structure
     */
    protected int getLevel() {
        return level;
    }

    /**
     * Returns structure maximum level.
     *
     * @return maximum structure level
     */
    protected int getMaxLevel() {
        return maxLevel;
    }

    /**
     * Return structure HP.
     *
     * @return current structure HP
     */
    protected int getHp() {
        return hp;
    }

    /**
     * Return structure maximum HP.
     *
     * @return structure maximum HP
     */
    protected int getMaxHp() {
        return maxHp;
    }

    /**
     * Returns current damage on layer.
     *
     * @return hit points damaged
     */
    protected int getDamage() {
        return maxHp - hp;
    }

    protected int getBaseUpgradeCost() {
        return baseUpgradeCost * level;
    }

    /**
     * Upgrades structure level and repairs ut to new maximum hitpoints.
     */
    protected void upgrade() {
        level++;
        maxHp = level * hpPerLvl;
        hp = maxHp;
        Output.msgDebug(subsystem, String.format("Upgraded to level %s.", level));
    }

    /**
     * Restores hit points to maximum.
     */
    protected void repair() {
        hp = maxHp;
        Output.msgDebug(subsystem, "Repaired.");
    }

    /**
     * Deals damage to structure
     */
    protected void takeDamage(int damage) {
        hp -= damage;
        Output.msgDebug(subsystem, String.format("Damaged by %s HP.", damage));
    }

}//End of class
