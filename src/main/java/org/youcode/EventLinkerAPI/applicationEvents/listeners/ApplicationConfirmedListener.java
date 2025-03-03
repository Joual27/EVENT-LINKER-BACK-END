package org.youcode.EventLinkerAPI.applicationEvents.listeners;


import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.youcode.EventLinkerAPI.application.Application;
import org.youcode.EventLinkerAPI.applicationEvents.events.ApplicationConfirmedEvent;
import org.youcode.EventLinkerAPI.worker.interfaces.WorkerService;

@Component
@AllArgsConstructor
public class ApplicationConfirmedListener {
    private final WorkerService workerService;

    @EventListener
    public void handleApplicationConfirmedEvent(ApplicationConfirmedEvent event){
        Application application = event.getApplication();
        double amount = event.getAmount();
        workerService.updateWorkerBalance(application.getApplicant() , amount);
    }
}
