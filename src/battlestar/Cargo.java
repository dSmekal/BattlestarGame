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
 * Represents BS cargo space. It stores resources required for BS operations. It
 * has limited space, due to construction limitations of battlestars.
 *
 * @author Malanius malanius@seznam.cz
 * @version 0.0.1
 */
class Cargo {

    /**
     * Subystem name for debug output
     */
    private final String subsystem = "Cargo";
    /**
     * Current resources in cargo hold
     */
    private int resources;
    /**
     * Maximum possible load of resources. Subject to ballancing changes.
     */
    private final int maxResources = 5_000;

    /**
     * Creates cargohold of BattleStar
     */
    protected Cargo() {
        resources = 500;
        Output.msgDebug(subsystem, "Initialized...");
    }//End of constructor

    /**
     * Returns resources avalible in cargo.
     */
    protected int getResources() {
        return resources;
    }//End of getResources

    /**
     * Check if requested resources are avalible
     *
     * @param request ammount of requested resources
     */
    protected boolean isAvalible(int request) {
        return (resources >= request);
    }//End of isAvalible

    /**
     * Adds resources to cargohold. Resources that doesn't fit into cargohold
     * are jetsisioned.
     *
     * @param addition ammount of resources to add
     */
    protected void addResources(int addition) {
        resources += addition;
        if (resources > maxResources) {
            Output.msgInfo(String.format("XO: We have full cargo, sir! %s resources was jetsisioned.", resources - maxResources));
            resources = maxResources;
        }
        Output.msgDebug(subsystem, String.format("Added %s resources. %s resources avalible.", addition, resources));
    }//End of addResources

    /**
     * Removes resources from cargohold.
     *
     * @param request ammount of requested
     */
    protected void takeResources(int request) {
        resources -= request;
        Output.msgDebug(subsystem, String.format("Removed %s resources. %s resources avalible.", request, resources));
    }//End of takeResources
    
    /** Tries to use esources. */
    public void useResources(int ammount) throws MissingResourcesException{
        if (isAvalible(ammount)){
            takeResources(ammount);
        }
        else{
            throw new MissingResourcesException(resources,ammount);
        }
    }
}//End of class
