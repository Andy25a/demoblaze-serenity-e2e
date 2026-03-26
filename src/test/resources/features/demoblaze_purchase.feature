Feature: Compra de productos en Demoblaze

  Scenario: Completar flujo de compra exitosa
    Given que el usuario navega a la pagina de Demoblaze
    When agrega dos productos al carrito
    And visualiza el carrito de compras
    And completa el formulario de compra con sus datos
    Then la compra se finaliza exitosamente
