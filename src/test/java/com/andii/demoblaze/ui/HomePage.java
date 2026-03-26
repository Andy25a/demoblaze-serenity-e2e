package com.andii.demoblaze.ui;

import net.serenitybdd.screenplay.targets.Target;

public class HomePage {
    public static final Target HOME_NAV = Target.the("Home (navbar)")
            .locatedBy("//a[contains(@class,'nav-link') and contains(normalize-space(),'Home')]");

    public static final Target CART_NAV = Target.the("Cart (navbar)")
            .locatedBy("//a[@class='nav-link' and normalize-space()='Cart']");

    public static final Target PRODUCT_LINK = Target.the("Product link")
            .locatedBy("//a[@class='hrefch' and normalize-space()='{0}']");
}

