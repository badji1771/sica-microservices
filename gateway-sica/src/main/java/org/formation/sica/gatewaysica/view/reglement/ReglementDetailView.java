package org.formation.sica.gatewaysica.view.reglement;


import com.vaadin.flow.router.Route;
import io.jmix.core.LoadContext;
import io.jmix.core.SaveContext;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import org.formation.sica.gatewaysica.entity.DetailsReglement;
import org.formation.sica.gatewaysica.entity.Reglement;
import org.formation.sica.gatewaysica.feign.ReglementClient;
import org.formation.sica.gatewaysica.view.main.MainView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Set;

@Route(value = "reglements/:id", layout = MainView.class)
@ViewController("Reglement.detail")
@ViewDescriptor("reglement-detail-view.xml")
@LookupComponent("detailsReglementsDataGrid")
@EditedEntityContainer("reglementDc")
public class ReglementDetailView extends StandardDetailView<Reglement> {
    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {
        System.out.println("event = " + event);
    }


//    @Autowired
//    private IReglementService reglementService;
        @Qualifier("org.formation.sica.gatewaysica.feign.ReglementClient")
        @Autowired
        private  ReglementClient reglementService;

    @ViewComponent
    private CollectionContainer<DetailsReglement> detailsReglementDc;
    private  Long ID_COURANT = 0L;


    /*@Install(to = "detailsReglementsDc", target = Target.DATA_LOADER)
    private List<DetailsReglement> customerDlLoadDelegate(final LoadContext<Reglement> loadContext) {
        Object id = loadContext.getId();

        // Here you can load the entity by id from an external storage.
        // Set the loaded entity to the not-new state using EntityStates.setNew(entity, false).
        return reglementService.loadDetailsByIdReglement(convertToLong(id));
    }*/

    public Long convertToLong(Object o){
        String stringToConvert = String.valueOf(o);
        Long convertedLong = Long.parseLong(stringToConvert);
        return convertedLong;

    }
    @Install(target = Target.DATA_CONTEXT)
    private Set<Object> saveDelegate(final SaveContext saveContext) {
        Reglement entity = getEditedEntity();
        // Here you can save the entity to an external storage and return the saved instance.
        // Set the returned entity to the not-new state using EntityStates.setNew(entity, false).
        // If the new entity ID is assigned by the storage, set the ID to the original instance too 
        // to let the framework match the saved instance with the original one.
        Reglement saved = entity;
        return Set.of(saved);
    }

    @Install(to = "reglementDl", target = Target.DATA_LOADER)
    private Reglement reglementDlLoadDelegate(final LoadContext<Reglement> loadContext) {
        Long id  = (Long)loadContext.getId();
        detailsReglementDc.setItems(reglementService.getDetailsReglementById(id));

        return reglementService.getReglementById((Long)loadContext.getId());
    }
    /*@Install(to = "detailsReglementsDl", target = Target.DATA_LOADER)
    private List<DetailsReglement> DetailsReglementDlLoadDelegate(final LoadContext<Reglement> loadContext) {
        Long id = ID_COURANT;
        return reglementService.getDetailsReglementById(id);
    }*/
}
