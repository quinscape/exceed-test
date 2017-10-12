package de.quinscape.exceedapp;

import de.quinscape.exceed.runtime.action.ParameterProvider;
import de.quinscape.exceed.runtime.action.ParameterProviderFactory;

import java.lang.annotation.Annotation;

public class AppSpecificProviderFactory
    implements ParameterProviderFactory
{
    @Override
    public ParameterProvider createIfApplicable(Class<?> parameterClass, Annotation[] annotations)
    {
        return null;
    }
}
