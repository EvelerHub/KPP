package Lab2.Exercise3;

import Lab2.Exercise2.ReflectByName;

import java.lang.reflect.Array;

//  tasks@programist.ru
//  them javaFKN

/**
 * Created by Eveler on 27.03.2015.
 */
public class MetaManipulate {

    public static void main(String[] args) {
        try {

            Object[] array = (Object[]) Array.newInstance(Object.class, 3);

            for (int i = 0; i < 2; i++) {
                Array.set(array, i, new ReflectByName("Lab2.Exercise3.MyClass").getaClassObj());
            }

            for (int i = 0; i < 2; i++) {
                System.out.println(Array.get(array, i));
            }
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
        }
    }

}
