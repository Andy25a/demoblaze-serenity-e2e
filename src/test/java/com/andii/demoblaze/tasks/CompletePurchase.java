package com.andii.demoblaze.tasks;

import com.andii.demoblaze.ui.CartPage;
import com.andii.demoblaze.ui.PlaceOrderModal;
import com.andii.demoblaze.ui.PurchaseConfirmation;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class CompletePurchase implements Task {

    private final String name;
    private final String country;
    private final String city;
    private final String creditCard;
    private final String month;
    private final String year;

    public CompletePurchase(String name, String country, String city, String creditCard, String month, String year) {
        this.name = name;
        this.country = country;
        this.city = city;
        this.creditCard = creditCard;
        this.month = month;
        this.year = year;
    }

    public static CompletePurchase withData(String name, String country, String city, String creditCard, String month, String year) {
        return Tasks.instrumented(CompletePurchase.class, name, country, city, creditCard, month, year);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(CartPage.PLACE_ORDER, isClickable()).forNoMoreThan(15).seconds(),
                Click.on(CartPage.PLACE_ORDER),

                WaitUntil.the(PlaceOrderModal.MODAL_TITLE, isVisible()).forNoMoreThan(15).seconds(),
                WaitUntil.the(PlaceOrderModal.NAME, isClickable()).forNoMoreThan(20).seconds(),
                Enter.theValue(name).into(PlaceOrderModal.NAME),
                Enter.theValue(country).into(PlaceOrderModal.COUNTRY),
                Enter.theValue(city).into(PlaceOrderModal.CITY),
                Enter.theValue(creditCard).into(PlaceOrderModal.CREDIT_CARD),
                Enter.theValue(month).into(PlaceOrderModal.MONTH),
                Enter.theValue(year).into(PlaceOrderModal.YEAR),

                WaitUntil.the(PlaceOrderModal.PURCHASE, isClickable()).forNoMoreThan(15).seconds(),
                Click.on(PlaceOrderModal.PURCHASE),

                WaitUntil.the(PurchaseConfirmation.TITLE, isVisible()).forNoMoreThan(15).seconds()
        );
    }
}

