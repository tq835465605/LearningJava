package com.foxhis.dll;
import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.exceptions.NativeException;
import org.xvolks.jnative.pointers.Pointer;
import org.xvolks.jnative.pointers.memory.MemoryBlockFactory;

public class JNativeDemo {

	public static void main(String[] args) throws NativeException, IllegalAccessException {
		JNative n = new JNative("MAINDLL.DLL", "NewKey");
		n.setRetVal(Type.INT);
		int i = 0;
		n.setParameter(i++, "1001");n.setParameter(i++, "99");
		n.setParameter(i++, "19050615381905091200");n.setParameter(i++, "");
		n.setParameter(i++, "");n.setParameter(i++, "99");
		n.setParameter(i++, 1);n.setParameter(i++, 1);
		n.setParameter(i++, "");n.setParameter(i++, "");
		n.invoke();
		System.out.println(n.getRetValAsInt());
		n = new JNative("MAINDLL.DLL", "ReadCard");
		Pointer p1 = createPointer();Pointer p2 = createPointer();
		Pointer p3 = createPointer();Pointer p4 = createPointer();
		Pointer p5 = createPointer();Pointer p6 = createPointer();
		Pointer p7 = createPointer();Pointer p8 = createPointer();
		n.setRetVal(Type.INT);
		int j = 0;
		n.setParameter(j++, p1);n.setParameter(j++, p2);
		n.setParameter(j++, p3);n.setParameter(j++, p4);
		n.setParameter(j++, p5);n.setParameter(j++, "00");
		n.setParameter(j++, p6);
		n.setParameter(j++, p7);n.setParameter(j++, p8);
		n.invoke();
	}
	public static Pointer createPointer() throws NativeException {
	    Pointer pointer = new Pointer(MemoryBlockFactory.createMemoryBlock(20));
	    return pointer;
	  }

}
