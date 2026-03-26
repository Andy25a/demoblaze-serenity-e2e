package com.andii.demoblaze.questions;

import com.andii.demoblaze.ui.CartPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.WebElementQuestion;

public class IsProductInCart implements Question<Boolean> {

    private final String productName;

    public IsProductInCart(String productName) {
        this.productName = productName;
    }

    public static IsProductInCart named(String productName) {
        return new IsProductInCart(productName);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return WebElementQuestion.the(CartPage.CART_ITEM_BY_NAME.of(productName))
                .answeredBy(actor)
                .isCurrentlyVisible();
    }
}

