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
class FTL extends Subsystem {

    /**
     * Number of turns FTL drive needs to cooldown
     */
    private int cooldown;
    /**
     * Basic number of turn to cooldwon. Subject to balancing changes.
     */
    private final int basicCoolDown;

    public FTL(String subsystem, int maxLevel, int baseUpgradeCost, int basicCooldown) {
        super(subsystem, maxLevel, baseUpgradeCost);
        this.basicCoolDown = basicCooldown;
        cooldown = basicCoolDown;
    }

    /**
     * Check if FTL drive is cooled down and ready to next jump
     */
    protected boolean isReady() {
        return cooldown == 0;
    }//End of isReady

    /**
     * Resets cooldown. Usable when upgradidng and jumping.
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
        Output.msgDebug(subsystem, String.format("FTL cooldown: %s turns remaining.", cooldown));
    }//End of cool

    /** Upgrades the FTL drive. Cooldown is reseted upon upgrade completetion.
     * 
     * @param cargo from which resources for upgrade are taken
     * @param crew that makes the upgrade, used for calculating resource bonus
     */
    @Override
    protected void upgrade(Cargo cargo, Crew crew) {
        try {
            super.upgrade(cargo, crew);
            resetCooldown();
        } catch (AlreadyAtMaxException ex) {
            String message = String.format("%s can't be upgraded, already at maximul level.", subsystem);
            Output.msgInfo(message);
        }
    }
}//End of class
