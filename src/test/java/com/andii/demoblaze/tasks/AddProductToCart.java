package com.andii.demoblaze.tasks;

import com.andii.demoblaze.interactions.AcceptAlert;
import com.andii.demoblaze.interactions.JsClick;
import com.andii.demoblaze.ui.HomePage;
import com.andii.demoblaze.ui.ProductPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AddProductToCart implements Task {

    private final String productName;

    public AddProductToCart(String productName) {
        this.productName = productName;
    }

    public static AddProductToCart named(String productName) {
        return Tasks.instrumented(AddProductToCart.class, productName);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(HomePage.PRODUCT_LINK.of(productName), isClickable()).forNoMoreThan(15).seconds(),
                Click.on(HomePage.PRODUCT_LINK.of(productName)),

                WaitUntil.the(ProductPage.PRODUCT_TITLE, isVisible()).forNoMoreThan(15).seconds(),
                WaitUntil.the(ProductPage.ADD_TO_CART, isClickable()).forNoMoreThan(15).seconds(),
                Click.on(ProductPage.ADD_TO_CART),

                AcceptAlert.withinSeconds(10),

                WaitUntil.the(HomePage.HOME_NAV, isClickable()).forNoMoreThan(15).seconds(),
                Scroll.to(HomePage.HOME_NAV),
                JsClick.on(HomePage.HOME_NAV)
        );
    }
}

