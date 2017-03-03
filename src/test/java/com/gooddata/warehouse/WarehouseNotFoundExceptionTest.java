package com.gooddata.warehouse;

import com.gooddata.GoodDataRestException;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class WarehouseNotFoundExceptionTest {

    @Test
    public void testGetWarehouseUri() throws Exception {
        final WarehouseNotFoundException exception = new WarehouseNotFoundException("TEST",
                mock(GoodDataRestException.class));
        assertThat(exception.getWarehouseUri(), is("TEST"));
    }

}