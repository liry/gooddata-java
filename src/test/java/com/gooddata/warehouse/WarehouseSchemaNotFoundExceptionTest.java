package com.gooddata.warehouse;

import com.gooddata.GoodDataRestException;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class WarehouseSchemaNotFoundExceptionTest {

    @Test
    public void testGetWarehouseSchemaUri() throws Exception {
        final WarehouseSchemaNotFoundException exception = new WarehouseSchemaNotFoundException("TEST",
                mock(GoodDataRestException.class));
        assertThat(exception.getWarehouseSchemaUri(), is("TEST"));
    }

}