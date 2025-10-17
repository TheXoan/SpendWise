# ANTEPROXECTO

ANTEPROXECTO SPENDWISE

Juan Vázquez Bueno

[1. Descrición do proxecto
[3](#descrición-do-proxecto)](#descrición-do-proxecto)

[2. Recursos [5](#recursos)](#recursos)

[3. Análise de requirimentos do sistema
[5](#análise-de-requirimentos-do-sistema)](#análise-de-requirimentos-do-sistema)

[4. Deseño [7](#deseño)](#deseño)

# Descrición do proxecto

O proxecto consistirá nunha aplicación móbil que permita ás persoas
poder visualizar os seus gastos e ingresos dunha forma real e
estruturada. Que poidan ver a suma dos "gastos formiga" e que poidan
aforrar dunha forma fácil.

1.  Xustificación do proxecto

A idea do proxecto xurdiume despois de ver como persoas que coñezo
anotaban os seus gastos, por exemplo, do supermercado, cando iban a
tomar algo e gastaban cartos en ocio, facían un Bizum, ou mesmo
apartaban cartos para outra conta de aforro, e anotaban todo esto nun
papel, ou mesmo nas notas do móbil, para despois a fin de mes poder
sumar todo e ver canto destinaran a cada cousa.

Vendo todo esto xurdiume a idea de crear unha aplicación que solucionara
todas estas necesidades que lle xurdían ás persoas. Poder anotar gastos,
asignalos a categorías, anotar subscricións ou pagos recurrentes etc.

2.  Estudo de necesidades

Con respecto a aplicación máis utilizada para este fin sería a propia
aplicación do banco, a cal na maioría dos casos xa nos fai unha pequena
separación de gastos por categorías e uns resumos mensuais. Tamén
existen outras como "Money manager & expenses" ou "Money Tracker-Expense
& Budget". Moitas aplicacións que limitan as funcionalidades básicas a
versións "pro" ou implementan publicidade que empeora a experiencia de
usuario ou como no caso das que nos suele incluír o banco, non nos
permite cambiar categorías ou incluír outras contas etc

3.  Persoas destinatarias

O público obxectivo desta aplicación serían todas as persoas físicas,
que podrían dispor de un móbil e tiveran coñecementos básicos de como
utilizalo. Xa que sería unha aplicación moi intuitiva e sinxela. A idade
de uso iría dende os máis novos ata persoas maiores ou xubilados, xa que
administrar o noso diñeiro é algo que dunha forma ou doutra faremos ao
longo de toda a nosa vida.

4.  Modelo de negocio

O modelo de negocio sería "freemium" donde a aplicación gratuíta
incluiría todas as funcionalidades básicas e máis usadas. E a versión
Premium sería utilizada para poder desfrutar das últimas novidades antes
de tempo, acceso a un histórico de movementos ilimitado e máis opcións
para mostrar estatísticas e gráficos dos gastos e ingresos.

5.  Funcionalidades do proxecto (obxectivos e alcance)

A aplicación terá as seguintes funcionalidades:

- Permitirá visualizar o balance de ingresos e gastos.

Fará a diferenza dos ingresos e os gastos no mes actual.

- Crear categorías.

Estas categorías usarémolas á hora de engadir un novo gasto na
aplicación. Cando engadamos un novo gasto deberemos asignalo a unha
categoría.

- Engadir gastos e ingresos

Podermos engadir un gasto ou un ingreso a través dos seus respectivos
formularios.

- Visualizar movementos

Podermos visualizar o histórico de movementos. Visualizaremos o día do
movemento, se foi ingreso ou gasto e a súa categoría, e podermos filtrar
por mes e ano.

- Desprazamento a través de un menú inferior que nos permitirá movernos
  a través das pantallas

Un BottomBar que nos permitirá movernos entre as distintas pantallas

- Configuración de límite de gasto mensual por categoría.

Poderemos configurar un límite de gasto para cada categoría que
queiramos, para así que nos notifique cando nos esteamos acercando a ese
límite.

- Engadir gastos ou ingresos recurrentes

Teremos a posibilidade de configurar gastos recurrentes que se nos farán
automáticamente, e da mesma forma poderemos configurar ingresos tamén
recurrentes, como por exemplo a nosa nómina ou ingresos por un alquiler
dunha vivenda etc.

- Visualizar os movementos recurrentes

Pantalla donde poderemos consultar os movementos recurrentes tanto
ingresos como gastos que teñamos configurados

- Exportado / Importado de Datos

Permitirá exportar os datos da BBDD para o seu posterior importado
noutro dispositivo móbil

# Recursos

O proxecto desenvolverase utilizando Android Studio, xa que é o IDE
desenvolto por Google máis recomendado para crear aplicacións Android.

Como linguaxe de programación utilizarase Kotlin, que é a linguaxe que
Google recomenda para a creación de aplicacións Android dende 2017.

A aplicación será programada usando Jetpack Compose, un kit de
ferramentas que Google recomenda usar dende 2021 para crear interfaces
modernas e de unha forma máis fácil, rápida e sen usar XML.

La BBDD utilizará SQLite, e estará implementada na aplicación usando
ROOM, unha librería de persistencia de datos recomendada dende 2017.
Máis segura, con menos código repetitivo. Crearase de forma local no
propio dispositivo, pero creada desta forma sería moi fácil migrala a
plataformas como Supabase ou a un servidor de BBDD propio.

# Análise de requirimentos do sistema

1.  Funcionalidades

+-----------------------------------+-----------------------------------+
| ACCIÓN                            | DESCRICIÓN                        |
+===================================+:==================================+
| Alta gasto                        | Engadir un novo gasto na base de  |
|                                   | datos                             |
+-----------------------------------+-----------------------------------+
| Alta ingreso                      | Engadir un novo ingreso na base   |
|                                   | de datos                          |
+-----------------------------------+-----------------------------------+
| Alta gasto/ingreso recurrente     | Crear un novo gasto/ingreso       |
|                                   | periódico                         |
|                                   |                                   |
|                                   | que se rexistrará automaticamente |
|                                   | como un gasto ou ingreso según    |
|                                   | corresponda                       |
+-----------------------------------+-----------------------------------+
| Consulta balance mensual          | Amosar un resumo do balance entre |
|                                   | ingresos e gastos                 |
+-----------------------------------+-----------------------------------+
| Consulta historial                | Visualizar o rexistro de gastos e |
|                                   | ingresos doutros meses ou anos    |
|                                   | anteriores                        |
+-----------------------------------+-----------------------------------+
| Consultar movementos recurrentes  | Amosar a lista completa de        |
|                                   | movementos recurrentes activos e  |
|                                   | a cantidade correspondente a cada |
|                                   | un                                |
+-----------------------------------+-----------------------------------+
| Consultar movementos recurrentes  | Amosa a lista completa de         |
| activos                           | movementos recurrentes sexan      |
|                                   | ingresos ou gastos                |
+-----------------------------------+-----------------------------------+
| Exportado de Datos                | Permitirá exportar os datos da    |
|                                   | BBDD                              |
+-----------------------------------+-----------------------------------+
| Importado de Datos                | Permitirá importar os datos       |
|                                   | incluíndo un fichero coa BBDD     |
+-----------------------------------+-----------------------------------+

2.  Normativa

🔹 Aviso legal

A aplicación SpendWise é unha ferramenta desenvolvida con fins
educativos e persoais para a xestión de gastos e ingresos.

Non recolle datos persoais identificativos (como nome, correo
electrónico ou número de teléfono), xa que toda a información
introducida polo usuario almacénase localmente no dispositivo mediante a
base de datos SQLite/Room e non se comparte con terceiros nin se
transmite a servidores externos.

O responsable do tratamento dos datos é o desenvolvedor da aplicación,
quen garante que a información persoal dos usuarios se empregará
unicamente co propósito de permitir o correcto funcionamento da
aplicación e ofrecer as funcionalidades previstas.

🔹 Política de privacidade

Finalidade do tratamento:\
Os datos introducidos polo usuario (rexistros de gastos, ingresos e
subscricións) utilízanse exclusivamente para o cálculo de balances,
estatísticas e historial de transaccións dentro da propia aplicación.

Base lexítima:\
O tratamento realízase co consentimento expreso do usuario, que é quen
decide introducir e conservar a información no seu dispositivo.

Dereitos do usuario:\
O usuario pode, en calquera momento:

- Modificar ou eliminar os seus rexistros financeiros.

- Desinstalar a aplicación, o que implica a eliminación total dos datos
  almacenados localmente.

Medidas de seguridade:

- A información almacénase unicamente na base de datos local do
  dispositivo (sen conexión a internet).

- Non se realiza transferencia de datos a servidores externos nin a
  terceiros.

- Empreganse mecanismos de seguridade do sistema operativo Android para
  protexer o acceso á información (seguridade biométrica).

🔹 Política de cookies

A aplicación non utiliza cookies nin tecnoloxías de seguimento web, xa
que funciona de forma local e independente da rede.

# Deseño

1.  Deseño da arquitectura do sistema

A aplicación SpendWise está baseada nunha arquitectura en capas,
seguindo o patrón MVVM (Model-View-ViewModel) recomendada por Android
para proxectos con Jetpack Compose.

Esta estrutura permite unha separación clara e facilita o mantemento, as
probas e a escalabilidade do proxecto.

Deseño:

Interface do Usuario UI: Implementada con Jetpack Compose. Contén as
pantallas, compoñentes visuais e interaccións do usuario.

ViewModel: Actúa como intermediario entre a UI e os datos. Contén a
lóxica e xestiona o estado da interface accedendo aos datos.

Repositorio: Encargase de proporcionar os datos á ViewModel. Xestiona o
acceso á base de datos local mediante DAO de Room.

Base de datos (Room / SQLite): Almacena de forma persistente os
rexistros de gastos, ingresos e subscricións no dispositivo. Non se
emprega ningunha conexión á nube.

Modelo datos: Define a información que se gardan na base de datos e se
utilizan en toda a aplicación.

![](media/image1.png){width="6.268055555555556in"
height="5.204861111111111in"}

2.  Deseño da persistencia de datos

![](media/image2.png){width="6.268055555555556in"
height="2.6284722222222223in"}

3.  Deseño da interface de usuario