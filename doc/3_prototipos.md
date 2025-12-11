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


## 2 - Prototipo  v2.0

Funcionalidades engadidas na segunda versión:

* Creación de movementos recurrentes: Podense crear movementos recurrentes engadindo unha data e unha recurrencia entre outros datos. Unha vez chegue a data crearase unha nova data de renovación e crearase un movemento na táboa Mov.
* Creación dunha pantalla para visualizar os movementos recurrentes: Pantalla donde se mostrarán todos os movementos recurrentes ordenados polo máis recente.
  * Dentro desta pantalla tamén se poderá **eliminar** un movemento recurrente.
  * Poderanse **editar** os datos dun movemento recurrente pulsando no mesmo dende a pantalla de movementos recurrentes
* Implementado o **editado e eliminación** dun movemento dende a pantalla de historial de movementos: Poderase modificar os datos dun movemento xa engadido
* Implementado sistema de notificacións para os movementos recurrentes: Engadido un worker que enviará notificacións sempre que se cree un movemento recurrente mostrando o nome e o valor da renovación. O permiso de notificación será solicitado unha vez se acceda a pantalla de novo movemento recurrente
* Implementado o exportado de datos: Exportanse os 3 ficheiros da base de datos, a base de datos e os ficheros donde se gardan os datos temporais a un zip.
* Implementado o importado de datos: Faise un importado dun zip a os ficheros da base de datos. Previamente cerrase a conexión coa base de datos para evitar corrupción 

### Pendente de implementar
* Filtrar os movementos por categoría
* Abstraer algún código repetitivo máis nun método auxiliar
* Formatear titulos e textos aliñar cousas do código de forma igual

### Problemas atopados
* Cando creaba un movemento recurrente para poder ter tanto movementos recurrentes gastos como ingresos non tiña unha maneira de diferencialos. Como solución engadin un atributo **"val tipo: TypeMov?"**
* A veces tiña problemas por indicar mal o nome do tipo de movemento (ingreso, gasto) xa que é un String. Polo que creei un enum class que almacene os únicos 2 posibles valores que terá o tipo de movemento.
* No movemento recurrente tiña almacenada as datas como Long, pero dificultábame moito manexalas e operar con elas, polo que as cambio a String para utilizar os métodos comúns de date.
* Chamaba ao método de creación de movementos recurrentes no Main Activity, pero esto daba erros xa que cando a app estaba cerrada non se chamaban aos métodos, polo que se implementa unha clase Aplication. Forma de  facelo que recomenda Google: https://developer.android.com/topic/libraries/architecture/workmanager#schedule_from_application
* A solicitude de permisos provocaba un bug e un exception na execución da aplicación. Investigando descubrin que este sistema de notificacións non é compatible con Fragment, clase da que heredaba o meu MainActivity, polo que migro a clase para que herede de: AppCompatActivity() e soluciona o problema.