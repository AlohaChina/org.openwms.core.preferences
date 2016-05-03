/*
 * openwms.org, the Open Warehouse Management System.
 * Copyright (C) 2014 Heiko Scherrer
 *
 * This file is part of openwms.org.
 *
 * openwms.org is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as 
 * published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * openwms.org is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this software. If not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.openwms.core.configuration;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * An ObjectFactory allows you to programatically construct new instances of the Java representation for XML content. The Java
 * representation of XML content can consist of schema derived interfaces and classes representing the binding of schema type definitions,
 * element declarations and model groups. Factory methods for each of these are provided in this class.
 * 
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version $Revision$
 * @since 0.1
 */
@XmlRegistry
public class ObjectFactory {

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package:
     * org.openwms.core.domain.preferences.
     */
    public ObjectFactory() {
        super();
    }

    /**
     * Create an instance of {@link ModulePreference}.
     * 
     * @return an instance of {@link ModulePreference}
     */
    public ModulePreference createModulePreference() {
        return new ModulePreference();
    }

    /**
     * Create an instance of {@link Preferences}.
     * 
     * @return an instance of {@link Preferences}
     */
    public Preferences createPreferences() {
        return new Preferences();
    }

    /**
     * Create an instance of {@link ApplicationPreference}.
     * 
     * @return an instance of {@link ApplicationPreference}
     */
    public ApplicationPreference createApplicationPreference() {
        return new ApplicationPreference();
    }

    /**
     * Create an instance of {@link UserPreference}.
     *
     * @return an instance of {@link UserPreference}
     */
    public UserPreference createUserPreference() {
        return new UserPreference();
    }

    /**
     * Create an instance of {@link UserPreference}.
     *
     * @param username
     *            The name of the User
     * @param key
     *            The key of the preference
     * @param description
     *            The description text
     * @return an instance of {@link UserPreference}
     */
    public static final UserPreference createUserPreference(String username, String key, String description) {
        UserPreference result = new UserPreference(username, key);
        result.setDescription(description);
        return result;
    }

    /**
     * Create an instance of {@link RolePreference}.
     *
     * @return an instance of {@link RolePreference}
     */
    public RolePreference createRolePreference() {
        return new RolePreference();
    }
}