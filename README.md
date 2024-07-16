# Conversordemonedas

Este es un conversor de monedas en Java que utiliza una interfaz gráfica de usuario (GUI) con `JOptionPane` y obtiene las tasas de cambio en tiempo real desde una API. La aplicación permite a los usuarios convertir entre diferentes monedas y manejar errores de entrada de manera amigable.

## Características

- Conversión de monedas en tiempo real utilizando `ExchangeRate-API`.
- Interfaz gráfica de usuario sencilla y amigable.
- Validación de entradas para evitar errores con caracteres no numéricos.
- Opciones para continuar o finalizar la aplicación después de cada conversión.

## Requisitos

- Java Development Kit (JDK) 8 o superior.
- NetBeans IDE (opcional, pero recomendado).
- Biblioteca `json-simple` para el análisis de JSON.
- Clave de API de ExchangeRate-API.

## Configuración

### 1. Obtener la Clave de API

Visita [ExchangeRate-API](https://www.exchangerate-api.com/) y regístrate para obtener una clave de API gratuita.

### 2. Descargar la Biblioteca `json-simple`

Descarga la biblioteca `json-simple` desde [json-simple](https://code.google.com/archive/p/json-simple/).

### 3. Agregar la Biblioteca `json-simple` a tu Proyecto en NetBeans

1. Abre NetBeans y abre tu proyecto.
2. En el panel de proyectos, haz clic derecho sobre el nombre de tu proyecto y selecciona `Properties`.
3. En la ventana de propiedades, selecciona `Libraries` en el menú de la izquierda.
4. Haz clic en `Add JAR/Folder`.
5. Navega hasta donde descargaste `json-simple` y selecciona el archivo JAR.
6. Haz clic en `Open` para añadir la biblioteca a tu proyecto.
7. Haz clic en `OK` para cerrar la ventana de propiedades.

## Uso

1. Clona el repositorio en tu máquina local:
   ```bash
   git clone https://github.com/tu-usuario/currency-converter.git
2. Abre el proyecto en NetBeans.

3. Reemplaza "TU_API_KEY" en el código con tu clave de API obtenida de ExchangeRate-API.


4. Ejecuta la aplicación desde NetBeans.
