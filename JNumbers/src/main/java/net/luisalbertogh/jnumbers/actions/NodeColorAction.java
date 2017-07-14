/**
 * Jwire is an advanced GUI for the crawler WIRE. It launches, manages, and configures WIRE for its use.
 * It also includes an advanced toolset of data visualization tools based on the most common and well-known
 * visualization techniques.
 * Copyright (C) 2010 Luis Alberto Garcia Hernandez
 */
/**
 * This file is part of Jwire.

 Jwire is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 Jwire is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with Jwire. If not, see <http://www.gnu.org/licenses/>.
 */
package net.luisalbertogh.jnumbers.actions;

import prefuse.action.assignment.ColorAction;
import prefuse.util.ColorLib;

/**
 * Set node fill colors
 */
public class NodeColorAction extends ColorAction {
    /**
     * TODO Description
     * 
     * @param group
     * @param visualItem
     */
    public NodeColorAction(String group, String visualItem) {
        super(group, visualItem, ColorLib.rgb(0, 0, 0));
        add("_hover", ColorLib.rgb(255, 215, 0));
        add("ingroup('_search_')", ColorLib.rgb(255, 140, 0));
        add("ingroup('_focus_')", ColorLib.rgb(205, 51, 51));
    }

}
