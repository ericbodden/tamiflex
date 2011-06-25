/*******************************************************************************
 * Copyright (c) 2010 Eric Bodden.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Eric Bodden - initial API and implementation
 ******************************************************************************/
package de.bodden.tamiflex.playout.rt;

public class TargetFieldLogEntry extends RuntimeLogEntry {

	protected final String declaringClass;
	protected final String fieldType;
	protected final String name;
	protected final boolean isAccessible;

	public TargetFieldLogEntry(String containerMethod, int lineNumber, Kind kind, String declaringClass, String fieldType, String name, boolean isAccessible) {
		super(containerMethod, lineNumber, kind);
		this.declaringClass = declaringClass;
	    this.fieldType = fieldType;
	    this.name = name;
		this.isAccessible = isAccessible;
	}

    public PersistedLogEntry toPersistedEntry() {
        String hashedContainerMethod = replaceByHashedClassNameAndMethodName(containerMethod);
        String hashedDeclaringClass = replaceByHashedClassName(declaringClass);
        String hashedReturnType = replaceByHashedClassName(fieldType);
            
        String sootSignature = sootSignature(hashedDeclaringClass, hashedReturnType, name); // FIXME What should this be?
        return new PersistedLogEntry(hashedContainerMethod, lineNumber, kind, sootSignature, "isAccessible="+Boolean.toString(isAccessible), count);
    }
    
    private static String sootSignature(String declaringClass, String fieldType, String name) {
        StringBuilder b = new StringBuilder();
        b.append("<");
        b.append(declaringClass);
        b.append(": ");
        b.append(fieldType);
        b.append(" ");
        b.append(name);
        b.append(">");
        return b.toString();
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((declaringClass == null) ? 0 : declaringClass.hashCode());
		result = prime * result
				+ ((fieldType == null) ? 0 : fieldType.hashCode());
		result = prime * result + (isAccessible ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TargetFieldLogEntry other = (TargetFieldLogEntry) obj;
		if (declaringClass == null) {
			if (other.declaringClass != null)
				return false;
		} else if (!declaringClass.equals(other.declaringClass))
			return false;
		if (fieldType == null) {
			if (other.fieldType != null)
				return false;
		} else if (!fieldType.equals(other.fieldType))
			return false;
		if (isAccessible != other.isAccessible)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

    @Override
    public String toString() {
        String targetField = sootSignature(declaringClass, fieldType, name);
        return kind.label() + ";" + targetField + ";" + containerMethod + ";" + (lineNumber>-1?lineNumber:"") + ";" + (count>0?count:"");
    }
}
