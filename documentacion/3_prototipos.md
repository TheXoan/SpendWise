# PROTOTIPOS E SEGUIMENTO

## 1 - Prototipo  v1.0

Neste primeiro prototipo están desenvoltas as seguintes funcionalidades:

* Creación de Categorías: Permite crear categorías que logo serán asociadas aos movementos (ingresos/gastos) será necesario asignarlle un nome.
* Creación dun gasto: Permitenos crear un gasto que será un movemento negativo que restará no balance de ingresos e gastos. Será necesario asignalo a unha categoría e asignarlle unha descripción.
* Creación dun ingreso: Permitenos crear un ingreso que será un movemento positivo que sumará no balance de ingresos e gastos. Será necesario asignalo a unha categoría e asignarlle unha descripción.
* Visualizado do historial: Permitenos visualizar en tempo real todos os movementos cos seus ingresos e gastos, cando foron creados, a súa categoría e o importe, mostrando se foi ingreso ou gasto según a cor. Tamén filtra según os anos que teñen movementos e dentro deste os meses que dispoñen de movementos.
* Permitenos eliminar categorías: Podemos seleccionar unha categoría e borrala eliminando así todos os movementos asociados a mesma
* Creación dun text na pantalla principal: Mostranos o balance de ingresos e gastos, facendo o cálculo en tempo real según vamos engadindo ingresos ou gastos.
* BottomBar: Permite desprazarse as principais pantallas da aplicación de forma áxil dende calquera das pantallas. Ademáis mostra iconos cos que facilita a accesibilidade da aplicación.
* Jetpack Navigation Compose: Navegación creada utilizando unha clase navigation donde nos permite indicar que pantallas son navegables y cales non, cal se mostra primeiro, permite a navegación dunha a outra e permitenos restrinxir as pantallas que queiramos que utilicen a navigation bar.

### Pendente de implementar
* Permitir crear movementos recurrentes
* Visualizar os movementos recurrentes
* Permitir o exportado dos datos da aplicación
* Permitir importar os datos doutra aplicación

### Problemas atopados
* Se non se dispoñía dun método de autenticación no dispositivo non iniciaba sen indicar erro algún.
* O balance de ingresos e gastos e o historial de movementos non se actualizaba cando se creaban gastos ou ingresos. Solucionouse implementando FLOW que permite datos dinámicos.
* Os gastos e ingresos podíanse crear sen categoría, esto rompía a app e cerrabase. Polo que se bloquea esa posibilidade mostrando unha mensaxe informativa.
* O historial estaba creado poñendo a categoría no medio do nome e o importe, cando o nome ou importe era máis largo do normal este rompía a visualización. Solucionouse poñendo a categoría abaixo de todo. Tamén se implementou que o nome cando é moi largo poña un intro e siga na seguinte línea. Para o importe fixose doutra maneira, cando o importe supera certa cifra modificase a propiedade fontSize para que se faga máis pequeno.