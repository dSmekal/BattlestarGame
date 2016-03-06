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
package io;

/**
 * This class handles all console output. All objects comunicate on two levels:
 * debug, which will be hiden in play and info, which is visible to the player.
 *
 * @author Malanius malanius@seznam.cz
 * @version 0.0.1
 */
public class Output {

    private Output() {
        /**
         * Class is static, as lot of objects uses it.
         */
    }//End of constructor

    /**
     * Sends message to System.err channel for debug info.
     *
     * @param sender Object that sends the message
     * @param message Debug message to be displayed
     */
    public static void msgDebug(String sender, String message) {
        System.err.printf("%s: %s\n", sender, message);
    }//End of msgDebug

    /**
     * Sends message to nfo channel visible to player.
     *
     * @param messsage Message to be displayed
     */
    public static void msgInfo(String messsage) {
        System.out.println(messsage);
    }//End of msgInfo
}//End of Class
