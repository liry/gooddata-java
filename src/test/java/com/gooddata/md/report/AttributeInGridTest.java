/**
 * Copyright (C) 2004-2016, GoodData(R) Corporation. All rights reserved.
 * This source code is licensed under the BSD-style license found in the
 * LICENSE.txt file in the root directory of this source tree.
 */
package com.gooddata.md.report;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gooddata.md.Attribute;
import com.gooddata.md.DisplayForm;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import static com.gooddata.JsonMatchers.serializesToJson;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AttributeInGridTest {

    private static final String URI = "/URI";
    private static final String ALIAS = "ALIAS";
    private static final String ATTR_TITLE = "attrTitle";

    @Test
    public void testDeserialization() throws Exception {
        final InputStream is = getClass().getResourceAsStream("/md/report/attributeInGrid.json");
        final AttributeInGrid attr = new ObjectMapper().readValue(is, AttributeInGrid.class);

        assertThat(attr, is(notNullValue()));
        assertThat(attr.getUri(), is(URI));
        assertThat(attr.getAlias(), is(ALIAS));

        assertThat(attr.getStringTotals(), is(notNullValue()));
        assertThat(attr.getStringTotals(), hasSize(2));
        final Iterator<List<Total>> i = attr.getTotals().iterator();
        final List<Total> subTotal1 = i.next();
        assertThat(subTotal1, hasSize(1));
        assertThat(subTotal1.iterator().next(), is(Total.SUM));
        final List<Total> subTotal2 = i.next();
        assertThat(subTotal2, hasSize(2));
        assertThat(subTotal2.get(0), is(Total.AVG));
        assertThat(subTotal2.get(1), is(Total.MED));
    }

    @Test
    public void testSerialization() throws Exception {
        final List<List<Total>> totals = asList(asList(Total.SUM), asList(Total.AVG, Total.MED));
        final AttributeInGrid attr = new AttributeInGrid(URI, ALIAS, totals);
        assertThat(attr, serializesToJson("/md/report/attributeInGrid.json"));
    }

    @Test
    public void testCreateFromAttribute() throws Exception {
        final Attribute attr = mock(Attribute.class);
        final DisplayForm displayForm = mock(DisplayForm.class);
        when(attr.getDefaultDisplayForm()).thenReturn(displayForm);
        when(attr.getTitle()).thenReturn(ATTR_TITLE);
        when(displayForm.getUri()).thenReturn(URI);
        when(displayForm.getTitle()).thenReturn(ALIAS);

        final AttributeInGrid attrInGrid = new AttributeInGrid(attr);
        assertThat(attrInGrid.getUri(), is(URI));
        assertThat(attrInGrid.getAlias(), is(ATTR_TITLE));
    }

    @Test
    public void testCreateFromDisplayForm() throws Exception {
        final DisplayForm displayForm = mock(DisplayForm.class);
        when(displayForm.getUri()).thenReturn(URI);
        when(displayForm.getTitle()).thenReturn(ALIAS);

        final AttributeInGrid attrInGrid = new AttributeInGrid(displayForm);
        assertThat(attrInGrid.getUri(), is(URI));
        assertThat(attrInGrid.getAlias(), is(ALIAS));
    }

    @Test
    public void testToStringFormat() throws Exception {
        final InputStream is = getClass().getResourceAsStream("/md/report/attributeInGrid.json");
        final AttributeInGrid attr = new ObjectMapper().readValue(is, AttributeInGrid.class);

        assertThat(attr.toString(), matchesPattern(AttributeInGrid.class.getSimpleName() + "\\[.*\\]"));
    }
}