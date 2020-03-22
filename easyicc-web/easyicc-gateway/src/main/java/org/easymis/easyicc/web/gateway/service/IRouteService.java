package org.easymis.easyicc.web.gateway.service;

import java.util.Collection;

import org.springframework.cloud.gateway.route.RouteDefinition;

public interface IRouteService {
    Collection<RouteDefinition> getRouteDefinitions();

    boolean save(RouteDefinition routeDefinition);

    boolean delete(String routeId);
}
