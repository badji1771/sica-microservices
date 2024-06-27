package org.formation.sica.gatewaysica.app;

import io.jmix.core.metamodel.model.StoreDescriptor;
import org.springframework.stereotype.Component;

@Component("sample_JourneeCompensationDataStoreDescriptor")
public class JourneeCompensationStoreDescriptor implements StoreDescriptor {
    @Override
    public String getBeanName() {
        return "sample_JourneeCompensationDataStore";
    }

    @Override
    public boolean isJpa() {
        return false;
    }
}