package com.andii.demoblaze.ui;

import net.serenitybdd.screenplay.targets.Target;

public class ProductPage {
    public static final Target PRODUCT_TITLE = Target.the("Product title")
            .locatedBy("//h2[@class='name']");

    public static final Target ADD_TO_CART = Target.the("Add to cart button")
            .locatedBy("//a[@class='btn btn-success btn-lg' and contains(normalize-space(),'Add to cart')]");
}

