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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

import org.openwms.core.AbstractEntity;
import org.openwms.core.exception.WrongClassTypeException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * A PreferencesDaoImpl is a JPA implementation of {@link PreferenceWriter} and implicitly of <code>PreferenceDao</code> to find, remove and
 * save preference objects to the persistent storage. It can be injected by name {@value #COMPONENT_NAME}.
 * <p>
 * All methods have to be invoked within an active transaction context.
 * </p>
 * 
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version $Revision: $
 * @since 0.1
 */
@Transactional(propagation = Propagation.MANDATORY)
@Repository(PreferencesDaoImpl.COMPONENT_NAME)
public class PreferencesDaoImpl implements PreferenceWriter<Long> {

    @PersistenceContext
    private EntityManager em;
    /** Springs component name. */
    public static final String COMPONENT_NAME = "preferencesJpaDao";

    /**
     * {@inheritDoc}
     */
    @Override
    public AbstractPreference findByKey(Long id) {
        return em.find(AbstractPreference.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T extends AbstractPreference> List<T> findByType(Class<T> clazz) {
        return (List<T>) em.createNamedQuery(getQueryName(clazz) + AbstractEntity.FIND_ALL).getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T extends AbstractPreference> List<T> findByType(Class<T> clazz, String owner) {
        return (List<T>) em.createNamedQuery(getQueryName(clazz) + AbstractPreference.FIND_BY_OWNER)
                .setParameter("owner", owner).getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<AbstractPreference> findAll() {
        List<AbstractPreference> prefs = em.createNamedQuery(AbstractPreference.NQ_FIND_ALL).getResultList();
        if (null == prefs) {
            return Collections.<AbstractPreference> emptyList();
        }
        return prefs;
    }

    /**
     * {@inheritDoc}
     * 
     * Call {@link EntityManager#persist(Object)} for transient instances and {@link EntityManager#merge(Object)} for detached and managed
     * ones.
     */
    @Override
    public <T extends AbstractPreference> T save(T entity) {
        if (entity.isNew()) {
            em.persist(entity);
        }
        return em.merge(entity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends AbstractPreference> void persist(T entity) {
        em.persist(entity);
    };

    /**
     * {@inheritDoc}
     * 
     * If <code>entity</code> is not already managed, call {@link EntityManager#merge(Object)} before to attach it to the persistence
     * context.
     */
    @Override
    public void remove(AbstractPreference entity) {
        if (em.contains(entity)) {
            em.remove(entity);
        } else {
            em.remove(em.merge(entity));
        }
    }

    private <T extends AbstractPreference> String getQueryName(Class<T> clazz) {
        for (int i = 0; i < Preferences.TYPES.length; i++) {
            if (Preferences.TYPES[i].equals(clazz)) {
                return clazz.getSimpleName();
            }
        }
        throw new WrongClassTypeException("Type " + clazz + " not a valid Preferences type");
    }
}