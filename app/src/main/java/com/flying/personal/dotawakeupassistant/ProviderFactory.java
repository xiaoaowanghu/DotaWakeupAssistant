package com.flying.personal.dotawakeupassistant;

import com.flying.personal.dotawakeupassistant.impl.DataProviderImplByMock;

/**
 * Created by wangxian on 8/18/2015.
 */
public class ProviderFactory {
    private static ProviderFactory instance = new ProviderFactory();
    private IDataProvider dataProvider;

    private ProviderFactory() {
        dataProvider = new DataProviderImplByMock();
    }

    public static ProviderFactory getInstance() {
        return instance;
    }

    public IDataProvider getDataProvider() {
        return dataProvider;
    }
}
