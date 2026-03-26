package com.andii.demoblaze.e2e;

import com.andii.demoblaze.questions.IsProductInCart;
import com.andii.demoblaze.questions.PurchaseTitle;
import com.andii.demoblaze.tasks.AddProductToCart;
import com.andii.demoblaze.tasks.CompletePurchase;
import com.andii.demoblaze.tasks.GoToCart;
import com.andii.demoblaze.tasks.OpenDemoblaze;
import com.andii.demoblaze.ui.CartPage;
import com.andii.demoblaze.ui.PurchaseConfirmation;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class DemoblazePurchaseStepDefinitions {

    private static final String PRODUCT_1 = PurchaseTestData.get("product.1");
    private static final String PRODUCT_2 = PurchaseTestData.get("product.2");
    private static final String BUYER_NAME = PurchaseTestData.get("buyer.name");
    private static final String BUYER_COUNTRY = PurchaseTestData.get("buyer.country");
    private static final String BUYER_CITY = PurchaseTestData.get("buyer.city");
    private static final String BUYER_CARD = PurchaseTestData.get("buyer.card");
    private static final String BUYER_MONTH = PurchaseTestData.get("buyer.month");
    private static final String BUYER_YEAR = PurchaseTestData.get("buyer.year");

    @Managed
    WebDriver driver;

    private Actor cliente;

    @Before
    public void setUp() {
        cliente = Actor.named("Cliente");
        cliente.can(BrowseTheWeb.with(driver));
    }

    @Given("que el usuario navega a la pagina de Demoblaze")
    public void queElUsuarioNavegaALaPaginaDeDemoblaze() {
        cliente.attemptsTo(OpenDemoblaze.home());
    }

    @When("agrega dos productos al carrito")
    public void agregaDosProductosAlCarrito() {
        cliente.attemptsTo(
                AddProductToCart.named(PRODUCT_1),
                AddProductToCart.named(PRODUCT_2)
        );
    }

    @And("visualiza el carrito de compras")
    public void visualizaElCarritoDeCompras() {
        cliente.attemptsTo(
                GoToCart.page(),
                WaitUntil.the(CartPage.CART_ITEM_BY_NAME.of(PRODUCT_1), isVisible()).forNoMoreThan(20).seconds(),
                WaitUntil.the(CartPage.CART_ITEM_BY_NAME.of(PRODUCT_2), isVisible()).forNoMoreThan(20).seconds(),
                Ensure.that(IsProductInCart.named(PRODUCT_1)).isTrue(),
                Ensure.that(IsProductInCart.named(PRODUCT_2)).isTrue()
        );
    }

    @And("completa el formulario de compra con sus datos")
    public void completaElFormularioDeCompraConSusDatos() {
        cliente.attemptsTo(
                CompletePurchase.withData(
                        BUYER_NAME,
                        BUYER_COUNTRY,
                        BUYER_CITY,
                        BUYER_CARD,
                        BUYER_MONTH,
                        BUYER_YEAR
                )
        );
    }

    @Then("la compra se finaliza exitosamente")
    public void laCompraSeFinalizaExitosamente() {
        cliente.attemptsTo(
                Ensure.that(PurchaseTitle.text()).containsIgnoringCase("thank you for your purchase"),
                Click.on(PurchaseConfirmation.OK)
        );
    }
}
