package com.osm.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ClassWithPrivateConstructorUnitTest {

    @Test(dataProvider = "private classes")
    public void cannot_instantiate_via_reflection(Class<?> classWithPrivateConstructor) {
        try {
            final Constructor<?> constructor = classWithPrivateConstructor.getDeclaredConstructor();
            assertThat(constructor).isNotNull();
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                @Override
                public Void run() {
                    constructor.setAccessible(true);
                    return null;
                }
            });
            constructor.newInstance();
            fail(String.format("The class %s shouldn't be instantiated", classWithPrivateConstructor.getName()));
        } catch (InvocationTargetException ex) {
            assertThat(ex.getCause()).isNotNull()
                    .isInstanceOf(InstantiationException.class)
                    .hasMessage(String.format("The class %s shouldn't be instantiated", classWithPrivateConstructor.getName()));
        } catch (ReflectiveOperationException | SecurityException ex) {
            fail(ex.getMessage(), ex);
        }
    }

    @DataProvider(name = "private classes", parallel = true)
    public Object[][] private_classes() {
        return new Object[][]{
            {Exceptions.class},
            {Constants.class},
            {Constants.HibernateGenerator.class}
        };
    }
}