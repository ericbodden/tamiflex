package de.bodden.tamiflex.playout.transformation;

import static org.objectweb.asm.Opcodes.*;

import java.lang.reflect.Constructor;

import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;

public class ConstructorNewInstanceTransformation extends Transformation {

	public ConstructorNewInstanceTransformation() {
		super(Constructor.class);
	}

	@Override
	protected MethodVisitor getTransformationVisitor(String name, String desc, MethodVisitor parent) {
		if ("newInstance".equals(name))
			return new MethodAdapter(parent) {
			
				@Override
				public void visitInsn(int opcode) {
					if (opcode == ARETURN) {
						mv.visitVarInsn(ALOAD, 0); // Load Constructor instance
						mv.visitMethodInsn(INVOKESTATIC, "de/bodden/tamiflex/playout/rt/ReflLogger", "constructorNewInstance", "(Ljava/lang/reflect/Constructor;)V");
					}
					super.visitInsn(opcode);
				}
			};
		else
			return parent;
	}
}
