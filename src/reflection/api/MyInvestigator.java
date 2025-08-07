package reflection.api;

import java.util.Set;

public class MyInvestigator implements Investigator {
    @Override
    public void load(Object anInstanceOfSomething) {

    }

    @Override
    public int getTotalNumberOfMethods() {
        return 0;
    }

    @Override
    public int getTotalNumberOfConstructors() {
        return 0;
    }

    @Override
    public int getTotalNumberOfFields() {
        return 0;
    }

    @Override
    public Set<String> getAllImplementedInterfaces() {
        return Set.of();
    }

    @Override
    public int getCountOfConstantFields() {
        return 0;
    }

    @Override
    public int getCountOfStaticMethods() {
        return 0;
    }

    @Override
    public boolean isExtending() {
        return false;
    }

    @Override
    public String getParentClassSimpleName() {
        return "";
    }

    @Override
    public boolean isParentClassAbstract() {
        return false;
    }

    @Override
    public Set<String> getNamesOfAllFieldsIncludingInheritanceChain() {
        return Set.of();
    }

    @Override
    public int invokeMethodThatReturnsInt(String methodName, Object... args) {
        return 0;
    }

    @Override
    public Object createInstance(int numberOfArgs, Object... args) {
        return null;
    }

    @Override
    public Object elevateMethodAndInvoke(String name, Class<?>[] parametersTypes, Object... args) {
        return null;
    }

    @Override
    public String getInheritanceChain(String delimiter) {
        return "";
    }
}
