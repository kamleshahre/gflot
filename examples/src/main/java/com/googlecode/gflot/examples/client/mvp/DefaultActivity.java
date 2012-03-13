package com.googlecode.gflot.examples.client.mvp;


import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gflot.examples.client.resources.Resources;

public abstract class DefaultActivity
    extends AbstractActivity
    implements IsWidget
{
    private Resources resources;

    private Widget widget;

    public DefaultActivity( Resources resources )
    {
        this.resources = resources;
    }

    @Override
    public void start( AcceptsOneWidget panel, EventBus eventBus )
    {
        if ( null == widget )
        {
            widget = createWidget();
        }
        panel.setWidget( widget );
    }

    @UiFactory
    public Resources getResources()
    {
        return resources;
    }

    protected abstract Widget createWidget();

    @Override
    public Widget asWidget()
    {
        return widget;
    }
}