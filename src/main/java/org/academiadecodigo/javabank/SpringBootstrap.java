package org.academiadecodigo.javabank;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * An {@link ApplicationListener} implementation that is notified on each {@link ContextRefreshedEvent}
 */
public class SpringBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    /**
     * Prints the application active profiles
     *
     * @see ApplicationListener#onApplicationEvent(ApplicationEvent)
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        String[] profiles = event.getApplicationContext().getEnvironment().getActiveProfiles();

        System.out.println("#### Active Profiles: ####");
        for (String profile : profiles) {
            System.out.println("=> " + profile);
        }
    }

}
