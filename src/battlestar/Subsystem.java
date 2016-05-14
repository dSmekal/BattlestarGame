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
 *
 * @author Malanius <malanius@seznam.cz>
 */
abstract class Subsystem {

    /**
     * Name of the sbusystem
     */
    protected final String subsystem;
    /**
     * Current level of subsystem
     */
    protected int level;
    /**
     * Maximum subsystem level
     */
    protected final int maxLevel;
    /**
     * Base training cost per level. Subject to balancing changes.
     */
    protected final int baseUpgradeCost;

    public Subsystem(String subsystem, int maxLevel, int baseUpgradeCost) {
        this.subsystem = subsystem;
        this.level = 1;
        this.maxLevel = maxLevel;
        this.baseUpgradeCost = baseUpgradeCost;
    }

    /**
     * @return the subsystem
     */
    public String getSubsystem() {
        return subsystem;
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @return the maxLevel
     */
    public int getMaxLevel() {
        return maxLevel;
    }

    /**
     * @return the baseUpgradeCost
     */
    public int getBaseUpgradeCost() {
        return baseUpgradeCost;
    }

    /**
     * Upgrades the subsystem if possible.
     * 
     * @param cargo from which resources are taken
     * @param crew that will make the upgrade
     * @throws AlreadyAtMaxException subsystem level is already at maximum
     */
    protected void upgrade(Cargo cargo, Crew crew) throws AlreadyAtMaxException {
        if (level < maxLevel) {
            int upgradeCost = baseUpgradeCost * level;
            try {
                cargo.useResources(upgradeCost);
                level++;
            } catch (MissingResourcesException ex) {
                String message = String.format("%s cant't be upgraded, missing %s resources.", subsystem, ex.getMissing());
                Output.msgInfo(message);
            }
        } else {
            throw new AlreadyAtMaxException();
        }
    }
}
