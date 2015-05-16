package Lab2.Exercise1;

import java.io.IOException;
import java.lang.reflect.*;

import java.util.Scanner;

/**
 * Created by Eveler on 27.03.2015.
 */
public class MetaByName {

    public void classInf(String className){
        try {
            Class<?> aClass = Class.forName(className);
            System.out.println("Class name>>" + aClass.getName() + "\n");

            System.out.println("<------------- Class methods ------------->\n");
            for (Method method : aClass.getMethods()) {
                System.out.println(method);
            }

            System.out.println("\n<------------- Class fields ------------->\n");
            for (Field field : aClass.getFields()) {
                System.out.println(field);
            }

            System.out.println("\n<------------- Class declared fields ------------->\n");
            for (Field field : aClass.getDeclaredFields()) {
                System.out.println(field);
            }

            System.out.println("\n<------------- Class declared methods ------------->\n");
            for (Method method : aClass.getDeclaredMethods()) {
                System.out.println(method);
            }

            System.out.println("\n<------------- Class type parameters ------------->\n");
            for (TypeVariable<? extends Class<?>> typeVariable : aClass.getTypeParameters()) {
                System.out.println(typeVariable);
            }

            System.out.println("\n<------------- Class declared constructors ------------->\n");
            for (Constructor<?> constructor : aClass.getDeclaredConstructors()) {
                System.out.println(constructor);
            }

            System.out.println("\n<------------- Class interfaces ------------->\n");
            for (Class<?> aClass1 : aClass.getInterfaces()) {
                System.out.println(aClass1);
            }

            System.out.println("\n<------------- Class GenericSuperclass------------->\n");
            System.out.println(aClass.getGenericSuperclass());

            if (aClass.getConstructors().length>0) {
                for (Parameter parameter : aClass.getConstructors()[0].getParameters()) {
                    System.out.println(parameter);
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Ops! ClassNotFoundException");
        }
    }

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        MetaByName metaByName = new MetaByName();
        String className;

        System.out.print("Enter class name " + "\n" +
                        "\t" + "for example:" + "\n" +
                        "\t\t" + "java.util.ArrayList" + "\n" +
                        "\t\t" + "java.util.Scanner" + "\n" +
                        "\t\t" + "java.lang.reflect.Method" + "\n" +
                        "\n" +
                        "Class name >>"
        );

        className = scanner.next();

        metaByName.classInf(className);
    }
}
