EJERCICIO 1 - PRUEBA FUNCIONAL E2E EN DEMOBLAZE (SERENITY BDD)
================================================================

1. Descripción general
----------------------
Este proyecto implementa una prueba funcional automatizada End-to-End (E2E) sobre el flujo de compra de la aplicación web: https://www.demoblaze.com/. 
La solución se desarrolló con Serenity BDD y Screenplay, incorporando un enfoque BDD con Gherkin/Cucumber y parametrización de datos de prueba para evitar hardcodeo. 
Con esta base, la automatización mantiene legibilidad funcional, facilita el mantenimiento técnico y genera evidencia reproducible mediante reportes HTML con trazabilidad de ejecución.

2. Objetivo y alcance funcional automatizado
-------------------------
Automatizar de punta a punta el proceso de compra requerido en el enunciado:
- Agregar dos productos al carrito; Visualizar el carrito; Completar el formulario de compra; Finalizar la compra y validar confirmación exitosa.

El escenario automatizado valida el siguiente flujo:
1) Ingreso a la página principal de Demoblaze.
2) Selección y agregado al carrito de dos productos: Samsung galaxy s6 y Nokia lumia 1520
3) Navegación al carrito y verificación de presencia de ambos productos.
4) Apertura del modal "Place Order".
5) Llenado del formulario de compra.
6) Confirmación de compra con validación del mensaje: "Thank you for your purchase".

3. Stack tecnológico
--------------------
- Java 11, Maven (con Maven Wrapper incluido), Serenity BDD, JUnit Platform (JUnit 5), Screenplay, Selenium WebDriver (Chrome)

4. Estructura del proyecto
--------------------------
Ruta base: Carpeta raíz del proyecto tras clonar o descomprimir el repositorio (donde están pom.xml y mvnw.cmd).
Ejemplo en Windows: C:\Users\andii\demoblaze-serenity-e2e

Estructura principal:
- pom.xml: Definición de dependencias, plugins y ciclo de build.
- src/test/resources/serenity.properties: Configuración global de Serenity y WebDriver.
- src/test/java/com/andii/demoblaze/ui: Objetos Target (localizadores UI).
- src/test/java/com/andii/demoblaze/tasks: Acciones del usuario en términos de negocio (abrir sitio, agregar productos, ir al carrito, completar compra).
- src/test/java/com/andii/demoblaze/questions: Validaciones/consultas para verificar el estado esperado.
- src/test/java/com/andii/demoblaze/interactions: Interacciones técnicas reutilizables (manejo de alertas y click por JavaScript cuando aplica).
- src/test/java/com/andii/demoblaze/e2e/DemoblazeCucumberTestSuite.java: Runner principal de Cucumber + Serenity.
- src/test/java/com/andii/demoblaze/e2e/DemoblazePurchaseStepDefinitions.java: Definiciones Given/When/Then del escenario.
- src/test/resources/features/demoblaze_purchase.feature: Escenario Gherkin ejecutable.
- src/test/resources/test-data.properties: Datos de prueba externos (productos y datos del comprador).
- src/test/java/com/andii/demoblaze/e2e/PurchaseTestData.java: Carga centralizada de datos parametrizados.
- run.ps1 / run.cmd: Único flujo de ejecución (pruebas, reporte, log y evidencia). Cómo ejecutarlo: ver sección 8.
- readme.txt: Este documento.
- conclusiones.txt: Hallazgos y conclusiones del ejercicio.
- target/site/serenity: Reporte base generado por Serenity en cada ejecución.
- reports/serenity: Copia del reporte para versionar en GitHub público.

5. Datos de prueba utilizados (parametrizados)
----------------------------------------------
Los datos no están hardcodeados en los steps. Se cargan desde: - src/test/resources/test-data.properties

Datos del formulario de compra:
- Name: Andy Briones
- Country: Ecuador
- City: Guayaquil
- Credit card: 5399831666475023
- Month: 07
- Year: 2031

Productos usados en el escenario:
- Samsung galaxy s6 y Nokia lumia 1520

Nota:
Los datos de tarjeta, mes y año se utilizan únicamente con fines de prueba funcional y no corresponden a una transacción real.

6. Pre-requisitos
-----------------
- Sistema operativo Windows con PowerShell.
- Java JDK 11 o superior instalado y disponible en PATH.
- Google Chrome instalado.
- Conexión a internet.
Importante:
No es obligatorio tener Maven instalado globalmente, ya que el proyecto incluye Maven Wrapper (mvnw.cmd).

7. Cómo ejecutar el proyecto
----------------------------
Tenemos **dos opciones** para ejecutar la misma automatización. Use **solo una** (dan el mismo resultado). Abajo van los pasos en orden:

--- Opción 1: usar el archivo run.cmd (Opcion mas facil): run.cmd es un archivo que ya viene en esa carpeta. Hace falta **solo** abrirlo (doble clic)

  **Pasos si quiere usar doble clic (no hace falta abrir CMD):**
  Paso 1: Abra el Explorador de archivos de Windows.
  Paso 2: Entre en la carpeta del proyecto (la que tiene pom.xml y run.cmd).
  Paso 3: Haga doble clic en el archivo run.cmd.
  Paso 4: Espere a que la ventana negra termine y cierre sola o deje de mostrar texto nuevo.

  **Pasos si prefiere usar CMD o PowerShell:**
  Paso 1: Abra CMD o PowerShell (o la terminal del IDE).
  Paso 2: Vaya a la carpeta del proyecto. Escriba cd, un espacio, y la ruta de la carpeta, luego
          Enter. Ejemplo: cd C:\Users\andii\demoblaze-serenity-e2e
  Paso 3: Escriba run.cmd y pulse Enter.
  Paso 4: Espere a que termine el proceso.

--- Opción 2: escribir el comando de PowerShell usted mismo

  **Pasos:**
  Paso 1: Abra CMD o PowerShell (o la terminal del IDE).
  Paso 2: Vaya a la carpeta del proyecto con cd (igual que en la Opción 1, paso 2).
  Paso 3: Copie o escriba este comando y pulse Enter: powershell -ExecutionPolicy Bypass -File .\run.ps1
  Paso 4: Espere a que termine la ejecucion del comando.


Parámetros opcionales (válidos al final de la línea, tanto con run.cmd como con el comando de la Opción 2):
- -Headless          (Chrome sin ventana visible)
- -Browser chrome    (u otro driver soportado por el proyecto)
- -SkipEvidence      (no genera carpeta ni ZIP de evidencia; solo pruebas + reportes + log)
- -SkipOpenReport    (no abre el navegador al final)

Ejemplos:
  - run.cmd -Headless
  - powershell -ExecutionPolicy Bypass -File .\run.ps1 -SkipOpenReport

8. Ejecucion alternativa (solo Maven)
-------------------------------------
Esta opcion es solo para quien quiera ejecutar Maven manualmente, sin usar run.ps1/run.cmd.
Comando: .\mvnw.cmd clean verify

Resultado:
- Genera reporte en target\site\serenity\index.html.
- No copia automaticamente a reports\serenity.
- No genera ZIP de evidencia.
Si necesita reporte en reports\serenity y evidencia ZIP, use la seccion 8.

9. Entregables del repositorio
-------------------------------
Esta seccion solo enumera lo que se entrega junto con el proyecto:
- Código fuente de automatización.
- Scripts de ejecución.
- Reportes de resultados.
- Instrucciones de ejecución (readme.txt).
- Hallazgos y conclusiones (conclusiones.txt).

10. Verificación posterior a la ejecución
-----------------------------------------
Al finalizar la ejecución, valide lo siguiente:
1) La ejecución termina sin errores (BUILD SUCCESS y 0 errores en pruebas).
2) Se abre o existe el reporte HTML en reports\serenity\index.html.
3) Se genera un log en reports\execution-logs.
4) Si no usó -SkipEvidence, existe la evidencia en reports\evidence\ y el ZIP en reports\.

11. Consideraciones técnicas
----------------------------
- Demoblaze presenta intermitencias ocasionales; por ello se utilizaron esperas explícitas.
- El sitio utiliza alertas JavaScript al agregar productos al carrito; la automatización las maneja de forma explícita.
- Se implementó click por JavaScript en puntos específicos para evitar fallos por elementos superpuestos del carrusel.

12. Resultado esperado
----------------------
La ejecución final debe completar satisfactoriamente:
- 1 prueba ejecutada.
- 0 fallos.
- 0 errores.
- Reporte Serenity disponible para revisión técnica y evidencia de entrega.
