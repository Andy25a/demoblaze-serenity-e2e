package com.andii.demoblaze.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JsClick implements Interaction {

    private final Target target;

    public JsClick(Target target) {
        this.target = target;
    }

    public static JsClick on(Target target) {
        return Tasks.instrumented(JsClick.class, target);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebElement element = target.resolveFor(actor);
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}

