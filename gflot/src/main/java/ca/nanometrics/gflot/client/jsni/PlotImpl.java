/*
 * Copyright (c) 2008 Nanometrics Inc.
 *
 *	Permission is hereby granted, free of charge, to any person obtaining a copy
 *	of this software and associated documentation files (the "Software"), to deal
 *	in the Software without restriction, including without limitation the rights
 *	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *	copies of the Software, and to permit persons to whom the Software is
 *	furnished to do so, subject to the following conditions:
 *
 *	The above copyright notice and this permission notice shall be included in
 *	all copies or substantial portions of the Software.
 *
 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *	THE SOFTWARE.
 */
package ca.nanometrics.gflot.client.jsni;

import ca.nanometrics.gflot.client.Axes;
import ca.nanometrics.gflot.client.PlotSelectionArea;
import ca.nanometrics.gflot.client.event.LoadImagesCallback;
import ca.nanometrics.gflot.client.event.PlotClickListener;
import ca.nanometrics.gflot.client.event.PlotHoverListener;
import ca.nanometrics.gflot.client.event.PlotPanListener;
import ca.nanometrics.gflot.client.event.PlotSelectedListener;
import ca.nanometrics.gflot.client.event.PlotSelectingListener;
import ca.nanometrics.gflot.client.event.PlotUnselectedListener;
import ca.nanometrics.gflot.client.event.PlotZoomListener;
import ca.nanometrics.gflot.client.options.PlotOptions;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;

/**
 * @author AlexanderDeleon
 */
public class PlotImpl
{

    static native Plot create( Element container, JavaScriptObject series )
    /*-{
        return new $wnd.jQuery.plot($wnd.jQuery("#" + container.id), series);
    }-*/;

    static native Plot create( Element container, JavaScriptObject series, JavaScriptObject options )
    /*-{
        return new $wnd.jQuery.plot($wnd.jQuery("#" + container.id), series,
                options);
    }-*/;

    static native void loadDataImages( JavaScriptObject data, JavaScriptObject options, LoadImagesCallback callback )
    /*-{
        $wnd.jQuery.plot.image
                .loadDataImages(
                        data,
                        options,
                        function() {
                            callback.@ca.nanometrics.gflot.client.event.LoadImagesCallback::onImagesLoaded(Lcom/google/gwt/core/client/JavaScriptObject;Lcom/google/gwt/core/client/JavaScriptObject;)(data, options);
                        });
    }-*/;

    static native void setData( Plot plot, JavaScriptObject series )
    /*-{
        plot.setData(series);
    }-*/;

    static native void addPlotSelectedListener( Element container, PlotSelectedListener listener )
    /*-{
    	$wnd
    			.jQuery("#" + container.id)
    			.bind(
    					"plotselected",
    					function(event, area) {
    						listener.@ca.nanometrics.gflot.client.event.PlotSelectedListener::onPlotSelected(Lca/nanometrics/gflot/client/PlotSelectionArea;)(area);
    					});
    }-*/;

    static native void addPlotSelectingListener( Element container, PlotSelectingListener listener )
    /*-{
        $wnd
                .jQuery("#" + container.id)
                .bind(
                        "plotselecting",
                        function(event, area) {
                            listener.@ca.nanometrics.gflot.client.event.PlotSelectingListener::onPlotSelecting(Lca/nanometrics/gflot/client/PlotSelectionArea;)(area);
                        });
    }-*/;

    static native void addPlotUnselectedListener( Element container, PlotUnselectedListener listener )
    /*-{
        $wnd
                .jQuery("#" + container.id)
                .bind(
                        "plotunselected",
                        function(event) {
                            listener.@ca.nanometrics.gflot.client.event.PlotUnselectedListener::onPlotUnselected()();
                        });
    }-*/;

    static native PlotSelectionArea getSelection( Plot plot )
    /*-{
        return plot.getSelection();
    }-*/;

    static native void setSelection( Plot plot, JavaScriptObject area, boolean preventEvent )
    /*-{
        plot.setSelection(area, preventEvent);
    }-*/;

    static native void addPlotHoverListener( Element container, PlotHoverListener listener, boolean onlyOnDatapoint,
                                             Plot plot )
    /*-{
        $wnd
                .jQuery("#" + container.id)
                .bind(
                        "plothover",
                        function(event, pos, item) {
                            if (item != null || !onlyOnDatapoint) {
                                listener.@ca.nanometrics.gflot.client.event.PlotHoverListener::onPlotHover(Lca/nanometrics/gflot/client/jsni/Plot;Lca/nanometrics/gflot/client/event/PlotPosition;Lca/nanometrics/gflot/client/event/PlotItem;)(plot, pos, item);
                            }
                        });
    }-*/;

    static native void addPlotClickListener( Element container, PlotClickListener listener, boolean onlyOnDatapoint,
                                             Plot plot )
    /*-{
        $wnd
                .jQuery("#" + container.id)
                .bind(
                        "plotclick",
                        function(event, pos, item) {
                            if (item != null || !onlyOnDatapoint) {
                                listener.@ca.nanometrics.gflot.client.event.PlotClickListener::onPlotClick(Lca/nanometrics/gflot/client/jsni/Plot;Lca/nanometrics/gflot/client/event/PlotPosition;Lca/nanometrics/gflot/client/event/PlotItem;)(plot, pos, item);
                            }
                        });
    }-*/;

    static native int getPlotOffsetLeft( Plot plot )
    /*-{
        var offset = plot.getPlotOffset().left;
        return (offset === undefined) ? -1 : offset;
    }-*/;

    static native int getPlotOffsetRight( Plot plot )
    /*-{
        var offset = plot.getPlotOffset().right;
        return (offset === undefined) ? -1 : offset;
    }-*/;

    static native int getPlotOffsetTop( Plot plot )
    /*-{
        var offset = plot.getPlotOffset().top;
        return (offset === undefined) ? -1 : offset;
    }-*/;

    static native int getPlotOffsetBottom( Plot plot )
    /*-{
        var offset = plot.getPlotOffset().bottom;
        return (offset === undefined) ? -1 : offset;
    }-*/;

    static native PlotOptions getPlotOptions( Plot plot )
    /*-{
    	return plot.getOptions();
    }-*/;

    native static void saveAsImage( Plot plot )
    /*-{
        $wnd.Canvas2Image.saveAsPNG(plot.getCanvas());
    }-*/;

    native static void saveAsImage( Plot plot, int width, int height )
    /*-{
        $wnd.Canvas2Image.saveAsPNG(plot.getCanvas(), false, width, height);
    }-*/;

    native static Element getImage( Plot plot )
    /*-{
        return $wnd.Canvas2Image.saveAsPNG(plot.getCanvas(), true);
    }-*/;

    native static Element getImage( Plot plot, int width, int height )
    /*-{
        return $wnd.Canvas2Image.saveAsPNG(plot.getCanvas(), true, width,
                height);
    }-*/;

    static native Axes getAxes( Plot plot )
    /*-{
        return plot.getAxes();
    }-*/;

    static native void addPlotPanListener( Element container, PlotPanListener listener )
    /*-{
        $wnd
                .jQuery("#" + container.id)
                .bind(
                        "plotpan",
                        function(event, plot) {
                            listener.@ca.nanometrics.gflot.client.event.PlotPanListener::onPlotPan(Lca/nanometrics/gflot/client/Axes;)(@ca.nanometrics.gflot.client.jsni.PlotImpl::getAxes(Lca/nanometrics/gflot/client/jsni/Plot;)(plot));
                        });
    }-*/;

    static native void addPlotZoomListener( Element container, PlotZoomListener listener )
    /*-{
        $wnd
                .jQuery("#" + container.id)
                .bind(
                        "plotzoom",
                        function(event, plot) {
                            listener.@ca.nanometrics.gflot.client.event.PlotZoomListener::onPlotZoom(Lca/nanometrics/gflot/client/Axes;)(@ca.nanometrics.gflot.client.jsni.PlotImpl::getAxes(Lca/nanometrics/gflot/client/jsni/Plot;)(plot));
                        });
    }-*/;
}
