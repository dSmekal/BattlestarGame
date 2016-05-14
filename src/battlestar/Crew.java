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
 * Represents Battlestar's crew. Crew operates all BS systems and provides
 * efficiency bonuses based on their level. Crew can be trained to higher level
 * for better bonuses.
 *
 * @author Malanius malanius@seznam.cz
 * @version 0.0.1
 */
class Crew extends Subsystem {

    public Crew(String subsystem, int maxLevel, int baseUpgradeCost) {
        super(subsystem, maxLevel, baseUpgradeCost);
        level = 0;
    }

    /**
     * Calculates new value based on crew efficiency.
     *
     * @return value with crew bonus applied
     */
    protected int calculateBonus(int base) {
        int bonus = Math.round(base * (level / 10.0f));
        int newValue = base - bonus;
        Output.msgDebug(subsystem, String.format("Crew bonus from %s is %s units (resources/damage).", -bonus, newValue));
        return newValue;
    }//End of getCrewBonusCost
}//End of class
