package org.formation.sica.gatewaysica.view.journeecompensation;

import org.formation.sica.gatewaysica.entity.JourneeCompensation;
import org.formation.sica.gatewaysica.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.LoadContext;
import io.jmix.core.SaveContext;
import io.jmix.flowui.view.*;

import java.util.Set;

@Route(value = "journeeCompensations/:id", layout = MainView.class)
@ViewController("JourneeCompensation.detail")
@ViewDescriptor("journee-compensation-detail-view.xml")
@EditedEntityContainer("journeeCompensationDc")
public class JourneeCompensationDetailView extends StandardDetailView<JourneeCompensation> {

    @Install(to = "journeeCompensationDl", target = Target.DATA_LOADER)
    private JourneeCompensation customerDlLoadDelegate(final LoadContext<JourneeCompensation> loadContext) {
        Object id = loadContext.getId();
        // Here you can load the entity by id from an external storage.
        // Set the loaded entity to the not-new state using EntityStates.setNew(entity, false).
        return null;
    }

    @Install(target = Target.DATA_CONTEXT)
    private Set<Object> saveDelegate(final SaveContext saveContext) {
        JourneeCompensation entity = getEditedEntity();
        // Here you can save the entity to an external storage and return the saved instance.
        // Set the returned entity to the not-new state using EntityStates.setNew(entity, false).
        // If the new entity ID is assigned by the storage, set the ID to the original instance too 
        // to let the framework match the saved instance with the original one.
        JourneeCompensation saved = entity;
        return Set.of(saved);
    }
}
