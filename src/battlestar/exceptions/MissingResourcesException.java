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
package battlestar.exceptions;

/**
 * Missing resources exception.
 *
 * @author Malanius <malanius@seznam.cz>
 */
public class MissingResourcesException extends Exception {

    /**
     * Current resources avalibe
     */
    private final int current;
    private final int requested;

    /**
     * Missing resources exception
     *
     * @param current ammount of resources avalible
     * @param requested
     */
    public MissingResourcesException(int current, int requested) {
        this.current = current;
        this.requested = requested;
    }

    public int getMissing() {
        return current - requested;
    }
}
