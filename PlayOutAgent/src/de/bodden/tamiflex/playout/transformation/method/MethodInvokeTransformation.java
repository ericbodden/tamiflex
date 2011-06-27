/*******************************************************************************
 * Copyright (c) 2010 Eric Bodden.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Eric Bodden - initial implementation
 ******************************************************************************/
package de.bodden.tamiflex.playout.transformation.method;

import static de.bodden.tamiflex.playout.rt.Kind.MethodInvoke;

import org.objectweb.asm.commons.Method;

import de.bodden.tamiflex.playout.rt.Kind;

public class MethodInvokeTransformation extends MethodTransformation {
	
	public MethodInvokeTransformation() {
		super(new Method("invoke", "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;"));
	}

	@Override
	protected Kind methodKind() {
		return MethodInvoke;
	}
	
}
