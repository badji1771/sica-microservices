package org.formation.sica.gatewaysica.app;

import io.jmix.core.DataStore;
import io.jmix.core.LoadContext;
import io.jmix.core.SaveContext;
import io.jmix.core.ValueLoadContext;
import io.jmix.core.entity.KeyValueEntity;
import org.formation.sica.gatewaysica.service.JourneeCompensationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component("sample_JourneeCompensationDataStore")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class JourneeCompensationDataStore implements DataStore {
    @Autowired
    private JourneeCompensationService journeeCompensationService;
    private String name;
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object load(LoadContext<?> context) {
        return journeeCompensationService.loadJourneeCompensation((Long) context.getId());
    }

    @Override
    public List<Object> loadList(LoadContext<?> context) {
        return new ArrayList<> (journeeCompensationService.loadAll());
    }

    @Override
    public long getCount(LoadContext<?> context) {
        return 0;
    }

    @Override
    public Set<?> save(SaveContext context) {
        return null;
    }

    @Override
    public List<KeyValueEntity> loadValues(ValueLoadContext context) {
        return null;
    }

    @Override
    public long getCount(ValueLoadContext context) {
        return 0;
    }
}