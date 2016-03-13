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
class Crew {

    /**
     * Subsystem name for debug output.
     */
    private final String subsystem = "Crew";
    /**
     * Level of crew ability
     */
    private int level;
    /**
     * Max level of crew. Subject to balancing changes.
     */
    private final int maxLevel = 5;
    /**
     * Base training cost per level. Subject to balancing changes.
     */
    private final int baseTrainingCost = 1_000;

    /**
     * Creates crew for battlestar.
     */
    protected Crew() {
        level = 1;
        Output.msgDebug(subsystem, "Initialized...");
    }

    /**
     * Returns crew level.
     *
     * @return crew level
     */
    protected int getLevel() {
        return level;
    }

    /**
     * Return max level whuch crew can be trained for.
     *
     * @return max level of crew
     */
    protected int getMaxLevel() {
        return maxLevel;
    }

    /**
     * Increases level of crew.
     */
    protected void trainCrew() {
        level++;
        Output.msgDebug(subsystem, "Crew trained");
    }

    /**
     * Returns the resource cost of training crew to another level.
     *
     * @return training cost
     */
    protected int getTrainingCost() {
        return level * baseTrainingCost;
    }
}//End of class
