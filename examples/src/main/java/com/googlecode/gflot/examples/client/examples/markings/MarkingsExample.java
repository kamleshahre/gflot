package com.googlecode.gflot.examples.client.examples.markings;


import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gflot.client.DataPoint;
import com.googlecode.gflot.client.PlotModel;
import com.googlecode.gflot.client.Series;
import com.googlecode.gflot.client.SeriesHandler;
import com.googlecode.gflot.client.SimplePlot;
import com.googlecode.gflot.client.options.GlobalSeriesOptions;
import com.googlecode.gflot.client.options.GridOptions;
import com.googlecode.gflot.client.options.LegendOptions;
import com.googlecode.gflot.client.options.LineSeriesOptions;
import com.googlecode.gflot.client.options.Marking;
import com.googlecode.gflot.client.options.Markings;
import com.googlecode.gflot.client.options.PlotOptions;
import com.googlecode.gflot.client.options.PointsSeriesOptions;
import com.googlecode.gflot.client.options.Range;
import com.googlecode.gflot.examples.client.examples.DefaultActivity;
import com.googlecode.gflot.examples.client.resources.Resources;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesData;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesRaw;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesSource;

/**
 * @author Nicolas Morel
 */
@GFlotExamplesRaw( MarkingsPlace.UI_RAW_SOURCE_FILENAME )
public class MarkingsExample
    extends DefaultActivity
{

    private static Binder binder = GWT.create( Binder.class );

    interface Binder
        extends UiBinder<Widget, MarkingsExample>
    {
    }

    /**
     * Plot
     */
    @GFlotExamplesData
    @UiField( provided = true )
    SimplePlot plot;

    public MarkingsExample( Resources resources )
    {
        super( resources );
    }

    /**
     * Create plot
     */
    @Override
    @GFlotExamplesSource
    public Widget createPlot()
    {
        PlotModel model = new PlotModel();
        PlotOptions plotOptions = PlotOptions.create();
        plotOptions.setGlobalSeriesOptions( GlobalSeriesOptions.create()
            .setLineSeriesOptions( LineSeriesOptions.create().setLineWidth( 1 ).setShow( true ) )
            .setPointsOptions( PointsSeriesOptions.create().setRadius( 2 ).setShow( true ) ).setShadowSize( 1 ) );
        plotOptions.setLegendOptions( LegendOptions.create().setShow( false ) );

        SeriesHandler s = model.addSeries( Series.of( "Series 1" ) );
        s.add( DataPoint.of( 1, 2 ) );
        s.add( DataPoint.of( 2, 5 ) );
        s.add( DataPoint.of( 3, 7 ) );
        s.add( DataPoint.of( 4, 5 ) );
        s.add( DataPoint.of( 5, 3 ) );
        s.add( DataPoint.of( 6, 2 ) );
        s.add( DataPoint.of( 7, 5 ) );
        s.add( DataPoint.of( 8, 7 ) );
        s.add( DataPoint.of( 9, 5 ) );
        s.add( DataPoint.of( 10, 3 ) );

        // Start of Marking Code
        Marking m = Marking.create().setX( Range.of( 2, 2 ) ).setLineWidth( 2 ).setColor( "#3BEFc3" );
        Marking m2 = Marking.create().setX( Range.of( 5, 7 ) ).setY( Range.of( 2, 6 ) ).setColor( "#cccccc" );
        Marking m3 = Marking.create().setX( Range.create().setFrom( 8 ) ).setColor( "#000000" );
        // End of Marking Code

        // Add Markings Objects to Grid Options
        plotOptions.setGridOptions( GridOptions.create().setMarkings(
            Markings.create().addMarking( m ).addMarking( m2 ).addMarking( m3 ) ) );

        // create plot
        plot = new SimplePlot( model, plotOptions );

        return binder.createAndBindUi( this );
    }

}
