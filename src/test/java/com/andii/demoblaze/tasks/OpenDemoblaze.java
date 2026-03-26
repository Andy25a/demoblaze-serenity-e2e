package com.andii.demoblaze.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

public class OpenDemoblaze implements Task {

    public static OpenDemoblaze home() {
        return Tasks.instrumented(OpenDemoblaze.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        EnvironmentVariables environmentVariables = SystemEnvironmentVariables.createEnvironmentVariables();
        String baseUrl = environmentVariables.getProperty("webdriver.base.url", "https://www.demoblaze.com/");
        actor.attemptsTo(Open.url(baseUrl));
    }
}

