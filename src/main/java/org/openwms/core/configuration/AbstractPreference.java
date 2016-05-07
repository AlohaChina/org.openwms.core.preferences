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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.openwms.core.AbstractEntity;

/**
 * An AbstractPreference is a superclass for all other preference classes within the application.
 * <p>
 * It encapsulates some common behavior of preference types.
 * </p>
 * 
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version $Revision$
 * @since 0.1
 */
@XmlType(name = "abstractPreference", propOrder = { "description" }, namespace = "http://www.openwms.org/schema/preferences")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "COR_PREFERENCE")
@NamedQueries({ @NamedQuery(name = AbstractPreference.NQ_FIND_ALL, query = "select p from AbstractPreference p") })
public abstract class AbstractPreference extends AbstractEntity<Long> implements Serializable {

    /**
     * Query to find all <code>AbstractPreference</code>s. Name is {@value} .
     */
    public static final String NQ_FIND_ALL = "AbstractPreference" + FIND_ALL;
    /**
     * Suffix for the FIND_BY_OWNER named query. Default {@value}
     */
    public static final String FIND_BY_OWNER = ".findByOwner";

    /**
     * Unique technical key.
     */
    @XmlTransient
    @Id
    @Column(name = "C_ID")
    @GeneratedValue
    private Long id;

    /**
     * The String value of the <code>AbstractPreference</code>.
     */
    @XmlAttribute(name = "val")
    @Column(name = "C_VALUE")
    private String value;

    /**
     * A binary value for this <code>AbstractPreference</code>.
     */
    @XmlTransient
    @Lob
    @Column(name = "C_BINVALUE")
    private Serializable binValue;

    /**
     * A float value of the <code>AbstractPreference</code>.
     */
    @XmlAttribute(name = "floatValue")
    @Column(name = "C_FLOAT_VALUE")
    private Float floatValue;

    /**
     * Description text of the <code>AbstractPreference</code>.
     */
    @XmlValue
    @Column(name = "C_DESCRIPTION")
    private String description;

    /**
     * Minimum value.
     */
    @XmlAttribute(name = "minimum")
    @Column(name = "C_MINIMUM")
    private int minimum = 0;

    /**
     * Maximum value.
     */
    @XmlAttribute(name = "maximum")
    @Column(name = "C_MAXIMUM")
    private int maximum = 0;

    /**
     * Flag to remember if the preference was originally imported from a file.
     */
    @XmlTransient
    @Column(name = "C_FROM_FILE")
    private boolean fromFile = true;

    /**
     * Version field.
     */
    @XmlTransient
    @Version
    @Column(name = "C_VERSION")
    private long version;

    /* ----------------------------- constructors ------------------- */
    /**
     * Accessed by persistence provider.
     */
    protected AbstractPreference() {
        super();
    }

    /* ----------------------------- inherited ------------------- */
    /**
     * {@inheritDoc}
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNew() {
        return id == null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getVersion() {
        return version;
    }

    /* ----------------------------- methods ------------------- */
    /**
     * Return the <code>value</code> of the <code>AbstractPreference</code>.
     * 
     * @return The value of the <code>AbstractPreference</code>
     */
    public String getValue() {
        return value;
    }

    /**
     * Set the <code>value</code> of the <code>AbstractPreference</code>.
     * 
     * @param value
     *            The value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Get the binValue.
     * 
     * @return the binValue.
     */
    public Serializable getBinValue() {
        return binValue;
    }

    /**
     * Set the binValue.
     * 
     * @param binValue
     *            The binValue to set.
     */
    public void setBinValue(Serializable binValue) {
        this.binValue = binValue;
    }

    /**
     * Get the <code>floatValue</code> of the <code>AbstractPreference</code>.
     * 
     * @return The floatValue of the preference
     */
    public Float getFloatValue() {
        return floatValue;
    }

    /**
     * Set the <code>floatValue</code> of the <code>AbstractPreference</code>.
     * 
     * @param floatValue
     *            The floatValue to set
     */
    public void setFloatValue(Float floatValue) {
        this.floatValue = floatValue;
    }

    /**
     * Return the <code>description</code> of the <code>AbstractPreference</code>.
     * 
     * @return The description as String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set a <code>description</code> for the <code>AbstractPreference</code>.
     * 
     * @param description
     *            The description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Return the possible minimum value of the <code>AbstractPreference</code>.
     * 
     * @return The possible minimum value
     */
    public int getMinimum() {
        return minimum;
    }

    /**
     * Set a possible minimum value for the <code>AbstractPreference</code>.
     * 
     * @param minimum
     *            The possible minimum value to set
     */
    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    /**
     * Return the possible maximum value of the <code>AbstractPreference</code>.
     * 
     * @return The possible maximum value
     */
    public int getMaximum() {
        return maximum;
    }

    /**
     * Set a possible maximum value for the <code>AbstractPreference</code>.
     * 
     * @param maximum
     *            The possible maximum value to set
     */
    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    /**
     * Check whether the preference was originally imported from a file.
     * 
     * @return <code>true</code> if imported from a file, otherwise <code>false</code>
     */
    public boolean isFromFile() {
        return fromFile;
    }

    /**
     * Set the fromFile.
     * 
     * @param fromFile
     *            The fromFile to set.
     */
    public void setFromFile(boolean fromFile) {
        this.fromFile = fromFile;
    }

    /**
     * Return all fields as concatenated String.
     * 
     * @return fields as String
     */
    public String getPropertiesAsString() {
        ToStringBuilder.setDefaultStyle(ToStringStyle.SIMPLE_STYLE);
        return new ToStringBuilder(this).append(getFields()).append(getValue()).append(getDescription())
                .append(getFloatValue()).append(getMinimum()).append(getMaximum()).toString();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Use all fields to calculate the hashCode and don't use the hashCode of the super class.
     * </p>
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((binValue == null) ? 0 : binValue.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((floatValue == null) ? 0 : floatValue.hashCode());
        result = prime * result + (fromFile ? 1231 : 1237);
        result = prime * result + maximum;
        result = prime * result + minimum;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        result = prime * result + (int) (version ^ (version >>> 32));
        return result;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Use all fields for comparison but don't call the super class.
     * </p>
     */
    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        AbstractPreference other = (AbstractPreference) obj;
        if (binValue == null) {
            if (other.binValue != null) {
                return false;
            }
        } else if (!binValue.equals(other.binValue)) {
            return false;
        }
        if (description == null) {
            if (other.description != null) {
                return false;
            }
        } else if (!description.equals(other.description)) {
            return false;
        }
        if (floatValue == null) {
            if (other.floatValue != null) {
                return false;
            }
        } else if (!floatValue.equals(other.floatValue)) {
            return false;
        }
        if (fromFile != other.fromFile) {
            return false;
        }
        if (maximum != other.maximum) {
            return false;
        }
        if (minimum != other.minimum) {
            return false;
        }
        if (value == null) {
            if (other.value != null) {
                return false;
            }
        } else if (!value.equals(other.value)) {
            return false;
        }
        if (version != other.version) {
            return false;
        }
        return true;
    }

    /**
     * Return all fields as an array of objects.
     * 
     * @return fields as array
     */
    protected abstract Object[] getFields();

    /**
     * Return the particular type of the preference.
     * 
     * @return The type of the preference
     */
    public abstract PropertyScope getType();

    /**
     * Return a {@link PreferenceKey} of this preference.
     * 
     * @return A {@link PreferenceKey}
     */
    public abstract PreferenceKey getPrefKey();
}