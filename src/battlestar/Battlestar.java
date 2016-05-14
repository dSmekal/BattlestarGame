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
 * Represents player battlestar. All BS operations are carried out by this
 * class.
 *
 * @author Malanius malanius@seznam.cz
 * @version 0.0.1
 */
public class Battlestar {

    /**
     * Subsystem name for debug output
     */
    private final String subsystem = "Battlestar";
    /**
     * Cargohold of battlestar which holds its resources
     */
    private final Cargo cargo;
    /**
     * Crew that serves on battlestar.
     */
    private final Crew crew;
    /**
     * Hull structure of battlestar.
     */
    private final ProtectionLayer hull;
    /**
     * Armor of battlestar
     */
    private final ProtectionLayer armor;
    /**
     * FTL drive of battlestar
     */
    private final FTL ftl;

    /**
     * Creates a battlestar. Factory will be used for setup.
     */
    private Battlestar(Cargo cargo, Crew crew, FTL ftl) {
        this.cargo = cargo;
        this.crew = crew;
        this.ftl = ftl;
    }//End of constructor

    /**
     * Battlestar factory. As battlestar construction reqires lot of other
     * obejcts, that are protected from outside, I've chosen factory for
     * construction.
     *
     * @return completed instance of battlestar
     */
    public static Battlestar constructBattlestar() {
        //TODO rewrite factory to use changed classes
    }//End of factory

    //Resources operations
    /**
     * Handles resources operations invoked from outside, as only BS has acces
     * to cargohold. Input values can be negative for resource loss (from
     * events) and positive for gain.
     *
     * @param ammount ammount of resources to handle.
     */
    public void handleResources(int ammount) {
        if (ammount < 0) {
            cargo.takeResources(Math.abs(ammount));
        } else {
            cargo.addResources(ammount);
        }
    }

    //Crew Operations
    /**
     * Attemps to train the crew to next level. Crew is trained to next level,
     * if there is enough resources and crew isn't on max level.
     */
    

    /**
     * Actions that happens on BS on each end of turn
     */
    private void nextTurn() {
        ftl.cool();
    }
}//End of class
