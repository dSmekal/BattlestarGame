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
    private final Hull hull;
    /**
     * Armor of battlestar
     */
    private final Armor armor;

    /**
     * Creates a battlestar. Factory will be used for setup.
     */
    private Battlestar(Cargo cargo, Crew crew, Hull hull, Armor armor) {
        this.cargo = cargo;
        this.crew = crew;
        this.hull = hull;
        this.armor = armor;
    }//End of constructor

    /**
     * Battlestar factory. As battlestar construction reqires lot of other
     * obejcts, that are protected from outside, I've chosen factory for
     * construction.
     *
     * @return completed instance of battlestar
     */
    public static Battlestar constructBattlestar() {
        Output.msgDebug("Factory", "Constructing Battlestar");
        Output.msgDebug("Factory", "Initializing cargo...");
        Cargo crg = new Cargo();
        Output.msgDebug("Factory", "Initializing crew...");
        Crew crw = new Crew();
        Output.msgDebug("Factory", "Initializing hull...");
        Hull hull = new Hull();
        Output.msgDebug("Factory", "Initializing armor");
        Armor arm = new Armor();
        Battlestar bs = new Battlestar(crg, crw, hull, arm);
        Output.msgDebug("Factory", "Battlestar constructed!");
        return bs;
    }//End of factory

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

    /**
     * Attemps to train the crew to enxt level. Crew is trained to next level,
     * if there is enough resources and crew isn't on max level.
     */
    public void trainCrew() {
        if (crew.getLevel() < crew.getMaxLevel()) {
            if (cargo.isAvalible(crew.getTrainingCost())) {
                cargo.takeResources(crew.getTrainingCost());
                crew.trainCrew();
                Output.msgInfo("XO: Our crew is bit more efficient again, sir.");
            } else {
                Output.msgInfo("XO: Sir, we don't have enough resources for training.");
            }
        } else {
            Output.msgInfo("XO: We have nothing more to teach our crew, sir!");
        }
    }
    //TODO Hull operations
    //TODO Armor operations
    //TODO FLT operations
    //TODO Guns operations
    //TODO Hangar operations
}//End of class
