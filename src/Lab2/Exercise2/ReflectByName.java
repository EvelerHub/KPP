package Lab2.Exercise2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * Created by Eveler on 14.05.2015.
 */
public class ReflectByName {
    private Object aClassObj;
    private Class<?> aClass;
    private Scanner scanner;

    public ReflectByName(String className) throws ClassNotFoundException {
        aClassObj = null;
        aClass = Class.forName(className);
        scanner = new Scanner(System.in);
        aClassObj = dialog();
    }

    public static void main(String[] args) {
        System.out.println("full class name >> ");
        Scanner scanner1 = new Scanner(System.in);

        try {
            ReflectByName reflectByName = new ReflectByName(scanner1.next());
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
        }
    }

    private Object setConstructor() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        System.out.println("Constructors of MyClass:");

        Constructor[] constructors = aClass.getConstructors();
        for (int i = 0; i < constructors.length; i++) {
            System.out.println((i + 1) + ") " + constructors[i]);
        }

        System.out.print("\nEnter # of constructor >>");

        Constructor constructor = constructors[scanner.nextInt() - 1];

        System.out.println("\nEnter parameters:");

        int parameterCount = constructor.getParameterCount();
        Object[] constructorParameters = new Object[parameterCount];
        for (int i = 0; i < parameterCount; i++) {
            System.out.print(constructor.getParameters()[i] + " >> ");
            constructorParameters[i] = scanner.nextInt();
        }
        return constructor.newInstance(constructorParameters);
    }

    private void setMethods() {
        System.out.println("\nMethods of MyClass:");
        Method[] methods = aClass.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            System.out.println((i + 1) + " " + methods[i]);
        }

        System.out.print("\nEnter # of method >>");
        Method method = methods[scanner.nextInt() - 1];
        int parameterCount = method.getParameterCount();
        Object[] methodParameters = new Object[parameterCount];
        for (int i = 0; i < parameterCount; i++) {
            System.out.print(method.getParameters()[i] + " >> ");
            methodParameters[i] = scanner.nextInt();
        }
        try {
            method.setAccessible(true);
            System.out.println("\nMethod result << " + method.invoke(aClassObj, methodParameters));
            method.setAccessible(false);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private Object dialog() {

        try {
            aClassObj = setConstructor();

            System.out.println("\nCondition of MyClass object:\n" + condition(aClassObj));

            setMethods();

            System.out.println("\nCondition of MyClass object:\n" + condition(aClassObj));

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return aClassObj;
    }

    private String condition(Object object) {

        String str = "";

        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                str += field + " = " + field.get(object).toString() + "\n";
                field.setAccessible(false);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return str;
    }

    public Object getaClassObj() {
        return aClassObj;
    }
}
