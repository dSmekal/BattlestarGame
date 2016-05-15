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

import battlestar.exceptions.MissingResourcesException;
import io.Output;
import java.util.MissingResourceException;

/**
 * Represents BS long range and flank guns. The number of guns and crew skill
 * determines attack power. Guns can be build using ship resources or destroyed
 * in events. Small craft can't be hit by long range weapons, but can be
 * attacked with flak when they reach the battlestar.
 *
 * @author Malanius malanius@seznam.cz
 * @version 0.0.1
 */
class Guns {

    private int numGuns;
    private int maxGuns;
    private int baseGunCost;

    protected Guns() {

    }

    /**
     * @return the numGuns
     */
    protected int getNumGuns() {
        return numGuns;
    }

    /**
     * @return the maxGuns
     */
    protected int getMaxGuns() {
        return maxGuns;
    }

    /**
     * @return the baseGunCost
     */
    protected int getBaseGunCost() {
        return baseGunCost;
    }

    protected void buildGuns(int number, Cargo cargo, Crew crew) {
        int cost = baseGunCost * number;
        cost = crew.calculateBonus(cost);
        try {
            cargo.useResources(cost);
            numGuns += number;
        } catch (MissingResourcesException ex) {
            String message = String.format("%s guns can't be build, missing %s resources.", number, ex.getMissing());
            Output.msgInfo(message);
        }

    }//End of buidGuns

    protected void looseGuns(int number) {
        numGuns -= number;                
    }//End uf looseGuns

    protected int getAttackPower(Crew crew) {
        return 0;
    }
}//End of class
