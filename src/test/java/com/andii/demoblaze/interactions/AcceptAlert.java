package com.andii.demoblaze.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AcceptAlert implements Interaction {

    private final Duration timeout;

    public AcceptAlert(Duration timeout) {
        this.timeout = timeout;
    }

    public static AcceptAlert withinSeconds(long seconds) {
        return Tasks.instrumented(AcceptAlert.class, Duration.ofSeconds(seconds));
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        Alert alert = new WebDriverWait(driver, timeout).until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }
}

