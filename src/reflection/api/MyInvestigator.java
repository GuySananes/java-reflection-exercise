package reflection.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyInvestigator implements Investigator {

    private Object instance;

    public MyInvestigator() {
        // Default empty constructor
    }

    @Override
    public void load(Object anInstanceOfSomething) {
        this.instance = anInstanceOfSomething;
    }

    @Override
    public int getTotalNumberOfMethods() {
        if (instance == null){
            return 0;
        }
        try{
            Method[] methods = instance.getClass().getDeclaredMethods();
            return methods.length;
        }
        catch (Exception e){
            return 0;
        }

    }

    @Override
    public int getTotalNumberOfConstructors() {
        if (instance == null){
            return 0;
        }
        try{
            Constructor<?>[] constructors = instance.getClass().getDeclaredConstructors();
            return constructors.length;
        }
        catch (Exception e){
            return 0;
        }
    }

    @Override
    public int getTotalNumberOfFields() {
        if (instance == null){
            return 0;
        }
        try {
            Field[] fields = instance.getClass().getDeclaredFields();
            return fields.length;
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public Set<String> getAllImplementedInterfaces() {
        Set<String> interfaces = new HashSet<>();

        if (instance == null) {
            return Set.of();
        }

        try {
            Class<?>[] implementedInterfaces = instance.getClass().getInterfaces();

            for (Class<?> i : implementedInterfaces) {
                interfaces.add(i.getSimpleName());
            }
            return interfaces;
        } catch (Exception e) {
            return Set.of();
        }
    }

    @Override
    public int getCountOfConstantFields() {
        if (instance == null) {
            return 0;
        }

        try {
            Field[] fields = instance.getClass().getDeclaredFields();
            int count = 0;

            for (Field field : fields) {
                if (Modifier.isFinal(field.getModifiers())) {
                    count++;
                }
            }
            return count;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int getCountOfStaticMethods() {
        if (instance == null) {
            return 0;
        }

        try {
            Method[] methods = instance.getClass().getDeclaredMethods();
            int count = 0;

            for (Method method : methods) {
                if (Modifier.isStatic(method.getModifiers())) {
                    count++;
                }
            }

            return count;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public boolean isExtending() {
        if (instance == null) {
            return false;
        }

        try {
            Class<?> superClass = instance.getClass().getSuperclass();
            return superClass != null && !superClass.equals(Object.class);

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String getParentClassSimpleName() {
        if (instance == null) {
            return "";
        }

        try {
            Class<?> superClass = instance.getClass().getSuperclass();

            if (superClass != null && !superClass.equals(Object.class)) {
                return superClass.getSimpleName();
            }
        } catch (Exception e) {
            return "";
        }

        return "";
    }

    @Override
    public boolean isParentClassAbstract() {
        if (instance == null) {
            return false;
        }

        try {
            Class<?> superClass = instance.getClass().getSuperclass();

            if (superClass != null && !superClass.equals(Object.class)) {
                int modifiers = superClass.getModifiers();
                return Modifier.isAbstract(modifiers);
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    @Override
    public Set<String> getNamesOfAllFieldsIncludingInheritanceChain() {
        Set<String> fieldNames = new HashSet<>();

        if (instance == null) {
            return fieldNames;
        }

        try {
            Class<?> current = instance.getClass();

            while (current != null) {
                Field[] fields = current.getDeclaredFields();
                for (Field field : fields) {
                    fieldNames.add(field.getName());
                }
                current = current.getSuperclass();
            }
        } catch (Exception e) {
            return Set.of();
        }

        return fieldNames;
    }

    @Override
    public int invokeMethodThatReturnsInt(String methodName, Object... args) {
        if (instance == null || methodName == null) {
            return 0;
        }

        try {
            Method[] methods = instance.getClass().getDeclaredMethods();

            for (Method method : methods) {
                if (method.getName().equals(methodName) && method.getReturnType() == int.class) {
                    method.setAccessible(true);
                    Object result = method.invoke(instance, args);
                    return (int) result;
                }
            }
        } catch (Exception e) {
            return 0;
        }

        return 0;
    }

    @Override
    public Object createInstance(int numberOfArgs, Object... args) {
        if (instance == null) {
            return null;
        }

        try {
            Class<?> clazz = instance.getClass();
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();

            for (Constructor<?> constructor : constructors) {
                if (constructor.getParameterCount() == numberOfArgs) {
                    constructor.setAccessible(true);
                    return constructor.newInstance(args);
                }
            }
        } catch (Exception e) {
            return null;
        }

        return null;
    }

    @Override
    public Object elevateMethodAndInvoke(String name, Class<?>[] parametersTypes, Object... args) {
        if (instance == null || name == null || parametersTypes == null) {
            return null;
        }

        try {
            Class<?> clazz = instance.getClass();

            Method method = clazz.getDeclaredMethod(name, parametersTypes);
            method.setAccessible(true);
            return method.invoke(instance, args);

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getInheritanceChain(String delimiter) {
        if (instance == null || delimiter == null) {
            return "";
        }

        try {
            List<String> classNames = new ArrayList<>();
            Class<?> current = instance.getClass();

            while (current != null) {
                classNames.add(0, current.getSimpleName());
                current = current.getSuperclass();
            }

            return String.join(delimiter, classNames);
        } catch (Exception e) {
            return "";
        }
    }
}
