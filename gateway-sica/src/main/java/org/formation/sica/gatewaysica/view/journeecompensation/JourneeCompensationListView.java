package org.formation.sica.gatewaysica.view.journeecompensation;

import org.formation.sica.gatewaysica.entity.JourneeCompensation;
import org.formation.sica.gatewaysica.service.JourneeCompensationService;
import org.formation.sica.gatewaysica.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.core.LoadContext;
import io.jmix.flowui.view.*;

import java.util.Collection;
import java.util.List;

@Route(value = "journeeCompensations", layout = MainView.class)
@ViewController("JourneeCompensation.list")
@ViewDescriptor("journee-compensation-list-view.xml")
@LookupComponent("journeeCompensationsDataGrid")
@DialogMode(width = "50em")
public class JourneeCompensationListView extends StandardListView<JourneeCompensation> {

    private final JourneeCompensationService journeeCompensationService;

    public JourneeCompensationListView(JourneeCompensationService journeeCompensationService) {
        this.journeeCompensationService = journeeCompensationService;
    }

    @Install(to = "journeeCompensationsDl", target = Target.DATA_LOADER)
    protected List<JourneeCompensation> journeeCompensationsDlLoadDelegate(LoadContext<JourneeCompensation> loadContext) {
        List<JourneeCompensation>  result = journeeCompensationService.loadAll();
        // Here you can load entities from an external storage.
        // Set the loaded entities to the not-new state using EntityStates.setNew(entity, false).
        return result;
    }

    @Install(to = "journeeCompensationsDataGrid.remove", subject = "delegate")
    private void journeeCompensationsDataGridRemoveDelegate(final Collection<JourneeCompensation> collection) {
        for (JourneeCompensation entity : collection) {
            // Here you can remove entities from an external storage
        }
    }
}
