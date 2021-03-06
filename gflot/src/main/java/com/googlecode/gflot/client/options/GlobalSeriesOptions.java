/*
 * Copyright (c) 2012 Nicolas Morel
 * 
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package com.googlecode.gflot.client.options;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * Options for all series. If you need to define specific option for your series, use the options on Series
 * 
 * @author Nicolas Morel
 */
public class GlobalSeriesOptions
    extends CommonSeriesOptions<GlobalSeriesOptions>
{
    /**
     * Creates a {@link GlobalSeriesOptions}
     */
    public static final GlobalSeriesOptions create()
    {
        return JavaScriptObject.createObject().cast();
    }

    private static final String MULTIPLE_BARS_KEY = "multipleBars";

    protected GlobalSeriesOptions()
    {
    }

    /**
     * Enable the multiple bars patch to flot.
     */
    public final GlobalSeriesOptions setMultipleBars( boolean multiplebars )
    {
        put( MULTIPLE_BARS_KEY, multiplebars );
        return this;
    }

    /**
     * @return true if the multiple bars patch to flot is enabled
     */
    public final Boolean getMultipleBars()
    {
        return getBoolean( MULTIPLE_BARS_KEY );
    }

    /**
     * Clear if the multiple bars patch to flot is enabled
     */
    public final GlobalSeriesOptions clearMultipleBars()
    {
        clear( MULTIPLE_BARS_KEY );
        return this;
    }

}
