package com.github.joelws.release.tracker.service;

import com.github.joelws.release.tracker.factory.Factory;
import com.github.joelws.release.tracker.factory.ReleaseTrackerFactory;
import com.github.joelws.release.tracker.interfaces.JsonAdapter;
import com.github.joelws.release.tracker.interfaces.RestResponseBuilder;

public class ServiceHelperImpl implements ServiceHelper
{
    private final RestResponseBuilder responseBuilder;

    private final JsonAdapter jsonAdapter;

    private final Factory factory;

    public ServiceHelperImpl(RestResponseBuilder responseBuilder, JsonAdapter jsonAdapter,
            ReleaseTrackerFactory factory)
    {
        this.responseBuilder = responseBuilder;
        this.jsonAdapter = jsonAdapter;
        this.factory = factory;
    }

    @Override public RestResponseBuilder getRestResponseBuilder()
    {
        return responseBuilder;
    }

    @Override public JsonAdapter getJsonAdapter()
    {
        return jsonAdapter;
    }

    @Override public Factory getFactory()
    {
        return factory;
    }

}
