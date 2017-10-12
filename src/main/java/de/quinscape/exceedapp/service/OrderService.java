package de.quinscape.exceedapp.service;

import de.quinscape.exceed.runtime.RuntimeContext;
import de.quinscape.exceed.runtime.domain.DomainObject;

public interface OrderService
{
    void cancelOrder(RuntimeContext runtimeContext, DomainObject domainObject);
}
