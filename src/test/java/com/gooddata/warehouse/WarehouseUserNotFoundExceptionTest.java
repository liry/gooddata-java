package com.gooddata.warehouse;

import com.gooddata.GoodDataRestException;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.testng.Assert.*;

public class WarehouseUserNotFoundExceptionTest {

    @Test
    public void testGetUserUri() throws Exception {
        final WarehouseUserNotFoundException exception = new WarehouseUserNotFoundException("TEST",
                mock(GoodDataRestException.class));
        assertThat(exception.getUserUri(), is("TEST"));
    }

}