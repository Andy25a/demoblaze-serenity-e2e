package com.andii.demoblaze.ui;

import net.serenitybdd.screenplay.targets.Target;

public class PlaceOrderModal {
    /** Base XPath del modal "Place order" para localizar inputs dentro del mismo diálogo. */
    private static final String MODAL_CONTENT =
            "//h5[@id='orderModalLabel' and normalize-space()='Place order']/ancestor::div[contains(@class,'modal-content')]";

    public static final Target MODAL_TITLE = Target.the("Place order modal title")
            .locatedBy(MODAL_CONTENT + "//h5[@id='orderModalLabel']");

    public static final Target NAME = Target.the("Name input")
            .locatedBy(MODAL_CONTENT + "//input[@id='name']");
    public static final Target COUNTRY = Target.the("Country input")
            .locatedBy(MODAL_CONTENT + "//input[@id='country']");
    public static final Target CITY = Target.the("City input")
            .locatedBy(MODAL_CONTENT + "//input[@id='city']");
    public static final Target CREDIT_CARD = Target.the("Credit card input")
            .locatedBy(MODAL_CONTENT + "//input[@id='card']");
    public static final Target MONTH = Target.the("Month input")
            .locatedBy(MODAL_CONTENT + "//input[@id='month']");
    public static final Target YEAR = Target.the("Year input")
            .locatedBy(MODAL_CONTENT + "//input[@id='year']");

    public static final Target PURCHASE = Target.the("Purchase button")
            .locatedBy(MODAL_CONTENT + "//button[@onclick='purchaseOrder()' and normalize-space()='Purchase']");
}

