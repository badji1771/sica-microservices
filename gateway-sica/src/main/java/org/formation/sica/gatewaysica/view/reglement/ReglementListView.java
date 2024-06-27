package org.formation.sica.gatewaysica.view.reglement;


import com.vaadin.flow.router.Route;
import io.jmix.core.LoadContext;
import io.jmix.flowui.view.*;
import org.formation.sica.gatewaysica.entity.Reglement;
import org.formation.sica.gatewaysica.feign.ReglementClient;
import org.formation.sica.gatewaysica.view.main.MainView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Route(value = "reglements", layout = MainView.class)
@ViewController("Reglement.list")
@ViewDescriptor("reglement-list-view.xml")
@LookupComponent("reglementsDataGrid")
@DialogMode(width = "50em")
public class ReglementListView extends StandardListView<Reglement> {

//    @Autowired
//    private IReglementService reglementService;

    @Qualifier("org.formation.sica.gatewaysica.feign.ReglementClient")
    @Autowired
    private ReglementClient reglementService;
   @Install(to = "reglementsDl", target = Target.DATA_LOADER)
    protected List<Reglement> reglementsDlLoadDelegate(LoadContext<Reglement> loadContext) {
        // Here you can load entities from an external storage.
        // Set the loaded entities to the not-new state using EntityStates.setNew(entity, false).
        return reglementService.loadAll();
    }

    /* @Install(to = "reglementsDataGrid.remove", subject = "delegate")
    private void reglementsDataGridRemoveDelegate(final Collection<Reglement> collection) {
        for (Reglement entity : collection) {
            // Here you can remove entities from an external storage
        }
    }*/
}
