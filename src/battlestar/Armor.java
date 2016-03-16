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
 * Represents BS armor protection. It has bigger HP pool than hull and provides
 * bonus to damage protection. It can be also upgraded. Level of armor anc crew
 * skill determines defense power. Combat and events damage the hull, but damage
 * can be repaired using resources.
 *
 * @author Malanius malanius@seznam.cz
 * @version 0.0.1
 */
class Armor extends Hull {

    /**
     * Bonus to HP pool. Subject to balancing changes.
     */
    private final int hpBonus = 5;

    //*Creates armor of battlestar.*/
    protected Armor() {
        super();
        subsystem = "Armor";
        maxHp = hpPerLvl * hpBonus;
        hp = maxHp;
        Output.msgDebug(subsystem, "Initialized...");
    }//End of constructor

    /**
     * Upgrades strutucre level and repairs it to max level
     */
    @Override
    protected void upgrade() {
        level++;
        maxHp = level * hpPerLvl * hpBonus;
        hp = maxHp;
        Output.msgDebug(subsystem, String.format("Upgraded to level %s.", level));
    }
}//End of class
