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
 * Represents BS quantum FTL drive. It provides FTL jump ability. FTL drive has
 * cooldown of #n turns, during which ship can't jump. Upgrading FTL drive using
 * resources shortens the cooldown time.
 *
 * @author Malanius malanius@seznam.cz
 * @version 0.0.1
 */
class FTL {

    /**
     * Subsystem name for debug output
     */
    private final String subysystem = "FTL drive";
    /**
     * Level of subsystem
     */
    private int level;
    /**
     * Maximum level of subsystem. Subject to balancing changes
     */
    private final int maxLevel = 5;
    /**
     * Basic upgrade cost per level. Subject to balancing changes.
     */
    private final int basicUpgradeCost = 2_000;
    /**
     * Number of turns FTL drive needs to cooldown
     */
    private int cooldown;
    /**
     * Basic number of turn to cooldwon. Subject to balancing changes.
     */
    private final int basicCoolDown = 10;

    /**
     * Creates FTL drive for battlestar
     */
    protected FTL() {
        level = 1;
        resetCooldown();
        Output.msgDebug(subysystem, "Initialized...");
    }//End of constructor

    /**
     * @return the level of FTL drive
     */
    protected int getLevel() {
        return level;
    }//End of getLevel

    /**
     * @return the maxLevel of FTL drive
     */
    protected int getMaxLevel() {
        return maxLevel;
    }//End of getMaxLevel

    /**
     * @return the basicUpgradeCost of FTL drive
     */
    protected int getBasicUpgradeCost() {
        return basicUpgradeCost;
    }//End of getBasicUpgradeCost

    /**
     * Check if FTL drive is cooled down and ready to next jump
     */
    protected boolean isReady() {
        return cooldown == 0;
    }//End of isReady

    /**
     * Resets cooldown. Usable when upgradidng and jumping
     */
    private void resetCooldown() {
        cooldown = (int) Math.ceil(basicCoolDown / level);
    }//End of resetCooldown

    /**
     * Cools down the drive on each turn
     */
    protected void cool() {
        cooldown -= 1;
        if (cooldown < 0) {
            cooldown = 0; //Cooldown can't be zero
        }
        Output.msgDebug(subysystem, String.format("FTL cooldown: %s turns remaining.", cooldown));
    }//End of cool

    /**
     * Upgrades the FTL drive and resets the cooldown
     */
    protected void upgrade() {
        level += 1;
        resetCooldown();
        Output.msgDebug(subysystem, String.format("FTL upgraded to level %s,", level));
    }//End of upgrade
}//End of class
