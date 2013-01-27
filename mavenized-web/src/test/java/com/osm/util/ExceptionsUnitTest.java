package com.osm.util;

import org.testng.annotations.Test;

import static com.osm.util.Exceptions.newInstantiationException;
import static org.fest.assertions.api.Assertions.assertThat;

@Test
public class ExceptionsUnitTest {

    public void throw_InstantiationException() {
        assertThat(newInstantiationException()).isInstanceOf(InstantiationException.class);
    }
}