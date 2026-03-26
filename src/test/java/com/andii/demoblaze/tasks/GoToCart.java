package com.andii.demoblaze.tasks;

import com.andii.demoblaze.ui.CartPage;
import com.andii.demoblaze.ui.HomePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import com.andii.demoblaze.interactions.JsClick;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class GoToCart implements Task {

    public static GoToCart page() {
        return Tasks.instrumented(GoToCart.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(HomePage.CART_NAV, isClickable()).forNoMoreThan(15).seconds(),
                Scroll.to(HomePage.CART_NAV),
                JsClick.on(HomePage.CART_NAV),
                WaitUntil.the(CartPage.CART_TITLE, isVisible()).forNoMoreThan(15).seconds()
        );
    }
}

