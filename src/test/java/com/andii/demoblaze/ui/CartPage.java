package com.andii.demoblaze.ui;

import net.serenitybdd.screenplay.targets.Target;

public class CartPage {
    public static final Target CART_TITLE = Target.the("Cart page title")
            .locatedBy("//h2[normalize-space()='Products']");

    public static final Target CART_ITEM_BY_NAME = Target.the("Cart item row by product name")
            .locatedBy("//*[@id='tbodyid']//tr/td[2][normalize-space()='{0}']");

    public static final Target PLACE_ORDER = Target.the("Place Order button")
            .locatedBy("//button[contains(normalize-space(),'Place Order')]");
}

