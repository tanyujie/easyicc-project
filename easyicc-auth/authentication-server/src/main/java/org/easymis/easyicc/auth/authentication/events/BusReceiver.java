package org.easymis.easyicc.auth.authentication.events;

import lombok.extern.slf4j.Slf4j;

import org.easymis.easyicc.auth.authentication.service.impl.ResourceService;
import org.easymis.easyicc.sysadmin.organization.entity.po.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BusReceiver {

    @Autowired
    private ResourceService resourceService;

    public void handleMessage(Resource resource) {
        log.info("Received Message:<{}>", resource);
        resourceService.saveResource(resource);
    }
}
