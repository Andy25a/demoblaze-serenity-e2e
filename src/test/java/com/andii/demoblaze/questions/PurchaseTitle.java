package com.andii.demoblaze.questions;

import com.andii.demoblaze.ui.PurchaseConfirmation;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class PurchaseTitle implements Question<String> {

    public static PurchaseTitle text() {
        return new PurchaseTitle();
    }

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(PurchaseConfirmation.TITLE).answeredBy(actor).trim();
    }
}

