# Pruebas Automatizadas para SwagLabs

Este proyecto contiene pruebas automatizadas utilizando Selenium WebDriver y Java para probar diferentes funcionalidades de la página web SwagLabs. Se utiliza el entorno de desarrollo IntelliJ IDEA para escribir, organizar y ejecutar las pruebas.

## Requisitos previos

Antes de comenzar, asegúrate de tener lo siguiente instalado en tu sistema:

- IntelliJ IDEA
- Java Development Kit (JDK)
- Dependencias de Maven en el archivo `pom.xml`


## Ejecución de pruebas

Ejecución de pruebas

1. Abre el proyecto en IntelliJ IDEA.      
2. Asegúrate de que las dependencias de Maven se hayan descargado correctamente.     
3. Navega hasta la ubicación de las pruebas: src → test → java.      
4. Dentro de la carpeta java, busca la clase de prueba que deseas ejecutar, como `FooterTest.java` o `CheckoutTests.java`.      
5. Dentro de la clase de prueba, haz clic derecho en un método de prueba específico y selecciona "Run 'NombreDelMetodo'" para ejecutar ese test en particular.

## Notas adicionales

- Las clases en el paquete `pages` representan diferentes páginas y componentes de la aplicación web.
- La clase `DriverManager` proporciona el controlador de Selenium WebDriver para las pruebas.
- Las pruebas son ejecutadas utilizando el framework JUnit.