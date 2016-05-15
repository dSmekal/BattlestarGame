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

import battlestar.exceptions.MissingResourcesException;
import battlestar.exceptions.AlreadyAtMaxException;
import io.Output;

/**
 * Subsystem of battlestar. Contains basic methods for subsystems.
 *
 * @author Malanius <malanius@seznam.cz>
 * @version 1.0
 */
abstract class Subsystem {

    /**
     * Name of the subsystem.
     */
    protected final String subsystem;
    /**
     * Current level of subsystem.
     */
    protected int level;
    /**
     * Maximum subsystem level.
     */
    protected final int maxLevel;
    /**
     * Base training cost per level.
     */
    protected final int baseUpgradeCost;

    /**
     * Creates subsystem of battlestar.
     *
     * @param subsystem name of the subsystem.
     * @param maxLevel maximum level subsystem can be upgraded to.
     * @param baseUpgradeCost basic upgrade cost modifier.
     */
    protected Subsystem(String subsystem, int maxLevel, int baseUpgradeCost) {
        this.subsystem = subsystem;
        this.level = 1;
        this.maxLevel = maxLevel;
        this.baseUpgradeCost = baseUpgradeCost;
    }//End of construcor

    /**
     * @return the subsystem
     */
    protected String getSubsystem() {
        return subsystem;
    }

    /**
     * @return the level
     */
    protected int getLevel() {
        return level;
    }

    /**
     * @return the maxLevel
     */
    protected int getMaxLevel() {
        return maxLevel;
    }

    /**
     * @return the baseUpgradeCost
     */
    protected int getBaseUpgradeCost() {
        return baseUpgradeCost;
    }

    /**
     * Upgrades the subsystem if possible.
     *
     * @param cargo from which resources are taken
     * @param crew that will make the upgrade
     * @throws AlreadyAtMaxException subsystem level is already at maximum
     */
    protected void upgrade(Cargo cargo, Crew crew) throws AlreadyAtMaxException, MissingResourcesException {
        if (level < maxLevel) {
            int upgradeCost = baseUpgradeCost * level;
            if (!(this instanceof Crew)) {
                upgradeCost = crew.calculateBonus(upgradeCost);
            }
            try {
                cargo.useResources(upgradeCost);
                level++;
            } catch (MissingResourcesException ex) {
                throw ex;
            }
        } else {
            throw new AlreadyAtMaxException();
        }
    }//End of upgrade
}//End of Subsystem class
