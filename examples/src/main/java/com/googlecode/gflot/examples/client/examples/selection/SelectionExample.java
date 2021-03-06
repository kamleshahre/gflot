package com.googlecode.gflot.examples.client.examples.selection;


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gflot.client.Axis;
import com.googlecode.gflot.client.DataPoint;
import com.googlecode.gflot.client.PlotModel;
import com.googlecode.gflot.client.PlotSelectionArea;
import com.googlecode.gflot.client.Series;
import com.googlecode.gflot.client.SeriesHandler;
import com.googlecode.gflot.client.SimplePlot;
import com.googlecode.gflot.client.event.PlotHoverListener;
import com.googlecode.gflot.client.event.PlotItem;
import com.googlecode.gflot.client.event.PlotPosition;
import com.googlecode.gflot.client.event.PlotSelectedListener;
import com.googlecode.gflot.client.event.PlotSelectingListener;
import com.googlecode.gflot.client.event.PlotUnselectedListener;
import com.googlecode.gflot.client.jsni.Plot;
import com.googlecode.gflot.client.options.AxisOptions;
import com.googlecode.gflot.client.options.GridOptions;
import com.googlecode.gflot.client.options.PlotOptions;
import com.googlecode.gflot.client.options.Range;
import com.googlecode.gflot.client.options.SelectionOptions;
import com.googlecode.gflot.client.options.TickFormatter;
import com.googlecode.gflot.client.options.SelectionOptions.SelectionMode;
import com.googlecode.gflot.examples.client.examples.DefaultActivity;
import com.googlecode.gflot.examples.client.resources.Resources;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesData;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesRaw;
import com.googlecode.gflot.examples.client.source.SourceAnnotations.GFlotExamplesSource;

/**
 * @author Nicolas Morel
 */
@GFlotExamplesRaw( SelectionPlace.UI_RAW_SOURCE_FILENAME )
public class SelectionExample
    extends DefaultActivity
{

    private static Binder binder = GWT.create( Binder.class );

    interface Binder
        extends UiBinder<Widget, SelectionExample>
    {
    }

    private static final String[] MONTH_NAMES = { "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct",
        "nov", "dec" };

    private static final String SELECTING = "Selecting :";

    private static final String SELECTED = "Selected :";

    private static final String UNSELECTED = "No selection";

    /**
     * Plot
     */
    @GFlotExamplesData
    @UiField( provided = true )
    SimplePlot plot;

    @UiField
    Label selectionLabel;

    @UiField
    Button clear;

    public SelectionExample( Resources resources )
    {
        super( resources );
    }

    /**
     * Create plot
     */
    @GFlotExamplesSource
    public Widget createPlot()
    {
        PlotModel model = new PlotModel();
        PlotOptions plotOptions = PlotOptions.create();

        // add tick formatter to the options
        plotOptions.addXAxisOptions( AxisOptions.create().setTicks( 12 ).setTickFormatter( new TickFormatter() {
            public String formatTickValue( double tickValue, Axis axis )
            {
                return MONTH_NAMES[(int) ( tickValue - 1 )];
            }
        } ) );

        plotOptions.setSelectionOptions( SelectionOptions.create().setMode( SelectionMode.XY ).setColor( "red" ) );
        plotOptions.setGridOptions( GridOptions.create().setHoverable( true ) );

        // create a series
        SeriesHandler handler =
            model.addSeries( Series.of( "Ottawa's Month Temperatures (Daily Average in &deg;C)", "blue" ) );

        // add data
        handler.add( DataPoint.of( 1, -10.5 ) );
        handler.add( DataPoint.of( 2, -8.6 ) );
        handler.add( DataPoint.of( 3, -2.4 ) );
        handler.add( DataPoint.of( 4, 6 ) );
        handler.add( DataPoint.of( 5, 13.6 ) );
        handler.add( DataPoint.of( 6, 18.4 ) );
        handler.add( DataPoint.of( 7, 21 ) );
        handler.add( DataPoint.of( 8, 19.7 ) );
        handler.add( DataPoint.of( 9, 14.7 ) );
        handler.add( DataPoint.of( 10, 8.2 ) );
        handler.add( DataPoint.of( 11, 1.5 ) );
        handler.add( DataPoint.of( 12, -6.6 ) );

        // create the plot
        plot = new SimplePlot( model, plotOptions );

        final PopupPanel popup = new PopupPanel();
        final Label hoverLabel = new Label( "You are hovering the selected zone" );
        popup.setWidget( hoverLabel );

        plot.addHoverListener( new PlotHoverListener() {
            @Override
            public void onPlotHover( Plot plot, PlotPosition position, PlotItem item )
            {
                PlotSelectionArea area = plot.getSelection();
                if ( null == area )
                {
                    popup.hide();
                    return;
                }
                double xFrom = area.getX().getFrom();
                double xTo = area.getX().getTo();
                double yFrom = area.getY().getFrom();
                double yTo = area.getY().getTo();
                if ( xFrom <= position.getX() && xTo >= position.getX() && yFrom <= position.getY()
                    && yTo >= position.getY() )
                {
                    popup.setPopupPosition( position.getPageX() + 10, position.getPageY() - 25 );
                    popup.show();
                }
                else
                {
                    popup.hide();
                }
            }
        }, false );

        plot.addSelectedListener( new PlotSelectedListener() {
            @Override
            public void onPlotSelected( PlotSelectionArea area )
            {
                selectionLabel.setText( buildSelectString( SELECTED, area ) );
                clear.setEnabled( true );
            }
        } );
        plot.addSelectingListener( new PlotSelectingListener() {
            @Override
            public void onPlotSelecting( PlotSelectionArea area )
            {
                selectionLabel.setText( buildSelectString( SELECTING, area ) );
            }
        } );
        plot.addUnselectedListener( new PlotUnselectedListener() {
            @Override
            public void onPlotUnselected()
            {
                selectionLabel.setText( UNSELECTED );
                clear.setEnabled( false );
            }
        } );

        plot.setSelection( PlotSelectionArea.create().setX( Range.of( 2, 4 ) ).setY( Range.of( 0, 20 ) ), false );

        return binder.createAndBindUi( this );
    }

    private String buildSelectString( String start, PlotSelectionArea area )
    {
        StringBuilder builder = new StringBuilder( start );
        builder.append( "x=[from:\"" ).append( area.getX().getFrom() ).append( "\", to=\"" )
            .append( area.getX().getTo() ).append( "\"], y=[from:\"" ).append( area.getY().getFrom() )
            .append( "\", to=\"" ).append( area.getY().getTo() ).append( "\"]" );
        return builder.toString();
    }

    @UiHandler( "clear" )
    void onClickClear( ClickEvent e )
    {
        plot.clearSelection( false );
        selectionLabel.setText( UNSELECTED );
        clear.setEnabled( false );
    }

}
