package com.osm.util;

import static com.osm.util.Exceptions.newInstantiationException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;
import org.testng.annotations.Test;

public class ExceptionsUnitTest {

    @Test
    public void throw_InstantiationException() {
        assertThat(newInstantiationException()).isInstanceOf(InstantiationException.class);
    }

    @Test
    public void cannot_instantiate_via_reflection() {
        try {
            final Constructor<Exceptions> constructor = Exceptions.class.getDeclaredConstructor();
            assertThat(constructor).isNotNull();
            AccessController.doPrivileged(new PrivilegedAction<Void>() {
                @Override
                public Void run() {
                    constructor.setAccessible(true);
                    return null;
                }
            });
            constructor.newInstance();
            fail(String.format("The class %s shouldn't be instantiated", Exceptions.class.getName()));
        } catch (InvocationTargetException ex) {
            assertThat(ex.getCause()).isNotNull()
                    .isInstanceOf(InstantiationException.class)
                    .hasMessage("This class is not to be instantiated");
        } catch (ReflectiveOperationException | SecurityException ex) {
            fail(ex.getMessage(), ex);
        }
    }
}