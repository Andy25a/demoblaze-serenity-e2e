package com.andii.demoblaze.ui;

import net.serenitybdd.screenplay.targets.Target;

public class PurchaseConfirmation {
    public static final Target TITLE = Target.the("Purchase confirmation title")
            .locatedBy("//div[contains(@class,'sweet-alert') and contains(@class,'showSweetAlert')]//h2");

    public static final Target OK = Target.the("Confirmation OK button")
            .locatedBy("//div[contains(@class,'sweet-alert') and contains(@class,'showSweetAlert')]//button[contains(@class,'confirm')]");
}

