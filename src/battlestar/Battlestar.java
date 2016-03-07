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

    private final String subsystem = "Battlestar";
    /**
     * Cargohold of battlestar which holds its resources
     */
    private Cargo cargo;
    /**
     * Crew that serves on battlestar.
     */
    private Crew crew;

    /**
     * Creates a battlestar. Factory will be used for setup.
     */
    private Battlestar(Cargo cargo, Crew crew) {
        this.cargo = cargo;
        this.crew = crew;

    }//End of constructor

    public static Battlestar constructBattlestar() {
        Output.msgDebug("Factory", "Constructing Battlestar");
        Output.msgDebug("Factory", "Initializing cargo...");
        Cargo crg = new Cargo();
        Output.msgDebug("Factory", "Initializing crew...");
        Crew crw = new Crew();
        Battlestar bs = new Battlestar(crg,crw);
        Output.msgDebug("Factory", "Battlestar constructed!");
        return bs;
    }
}//End of class
