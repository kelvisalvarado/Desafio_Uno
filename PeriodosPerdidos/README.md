# Título del Proyecto

Solución Desafio 1 Nivel 3 Periodos perdidos

Este proyecto expone un API REST que permite invocar al servicio GDD y entregar la respuesta en formato JSON con las fechas recibidas y las fechas faltantes. Entrega la siguiente información:

id: identificador fechaCreacion: Fecha de inicio de la secuencia fechaFin: Fecha de fin de la secuencia fechas: Lista de fechas que están en el rango de la fecha que se encuentra en “fechaCreacion” hasta la fecha “fechaFin”  fechasFaltantes: Lista de fechas del periodo indicado que no fueron enviadas en el campo fechas.

Ejemplo.
```json
{
    "id": 1,
    "fechaCreacion": "2019-01-01",
    "fechaFin": "2019-04-01",
    "fechas": [
      "2019-01-01",
      "2019-02-01",
      "2019-04-01"]
	"fechasFaltantes": [
	  "2019-03-01"],
}
```
*Nota*:
El formato de las fechas es yyyy-MM-dd

# Detalle de los sistemas

Swagger 2
Java 8
Spring-Boot 2.1.6.RELEASE
Maven 3.5.1


# Compilar y ejecutar el proyecto

Para copilar el proyecto se requiere Java y Maven instalado.
Ingresar al directorio *PeriodosPerdidos* ejecutar el siguiente comando *maven*

```bash
mvn package
```

Luego de compilar el proyecto ingresar al directorio *target* ejecutar el siguiente comando *java*

```bash
java -jar .\periodosPerdidos-0.0.1-SNAPSHOT.jar
```
*Nota*:
Debe estar disponible el puerto *8090* en el PC donde se ejecute esta API

# Visualizar Documentación y consumir la API

La documentación swagger del API (una vez que se levanta el API) queda disponible en

http://127.0.0.1:8090/swagger-ui.html

Para consumir el servicio se debe invocar la siguiente URL

```bash
curl -X GET --header 'Accept: application/json' 'http://127.0.0.1:8090/periodos/listar'
```
