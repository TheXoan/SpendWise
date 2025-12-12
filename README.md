# PROXECTO FIN DE CICLO

## Sobre o autor

Son Juan Vázquez Bueno, administrador de sistemas e programador. Ademáis deso teño certificacións como __LPIC-1__, __Docker__, __Git-GitHub__ e 4 anos de experiencia como SysAdmin donde obtiven diversos coñecementos en administración de Firewalls como Fortinet, en switch managment, Active Directory entre outras.  

Como developer o meu recorrido é máis corto, actualmente formo parte do equipo de Desenvolvemento dunha aplicación que forma parte da AMTEGA. Os meus coñecementos máis amplios están en linguaxes como Java, Kotlin e Python. Tamén teño coñecementos en ThymeLeaf, Spring Boot, Android Studio e experiencia en BBDD MySql, Oracle e PostgreSQL.  

Os meus puntos fortes son a constancia, as ganas por aprender, a dedicación e a profesionalidad, sempre trato de aportar proactividade aos proxectos donde me atopo.  
Escollin este proxecto porque vin unha necesidade nunha gran parte da sociedade, que busca unha solución ao descontrol das súas finanzas, con esta aplicación tratarei de solucionar este problema e poder facer unha aportación a comunidade.  

Se tes algunha dúbida ou proposta, podes contactarme no seguinte correo:
juanbuenovazquez@gmail.com

🔗 [Linkedin](https://www.linkedin.com/in/juan-v%C3%A1zquez-bueno-65b9581b1/?locale=es_ES)

## Uso
  
#### Principais características
  - Visualización do balance de ingresos/gastos mensual
  - Creación de gastos
  - Creación de ingresos
  - Creación de movementos recurrentes ingresos/gastos (semanal-mensual-anual)
  - Visualización do histórico de movementos e visualización dos mesmos (ingresos-gastos-movementos recurrentes)
  - Visualización dos movementos recurrentes e edición dos mesmos
  - Filtrado dos movementos por ano e mes con movementos dispoñibles
  - Xestión de permisos de notificacións
  - Posibilidade de activar/desactivar as notificacións dende a pantalla de Configuración
  - Posibilidade de importar ou exportar datos dende a pantalla de Configuración
  - Acceso mediante método biométrico/patrón/PIN
  - Acceso mediante login correo-contrasinal
  - Notificación de creación de movementos recurrentes creados automáticamente según data de inicio e data de renovación
  - Gardado remoto de datos con posibilidade de multidispositivo
  - Xestión de pantallas mediante un menú de navegación inferior


## Índice: Estrutura do proxecto (plantillas de apoio)

1. [Documentación](doc/)

   - [Proposta inicial](doc/1_proposta.md)
   - [Anteproxecto](doc/2_anteproxecto.md)
   - [Seguimento/prototipos](doc/3_prototipos.md)
   - [Manual Técnico](doc/6_manual_tecnico.md)
   - [Manual Usuario](doc/7_manual_usuario.md)
   - [Video demostración](doc/videos/Video_demostración_final_SpendWise.mkv)

#### [Documentación KDoc](https://a23juanvb-ed7d92.pages.iessanclemente.net/)

2. [Proxecto](/)  
📁 [project-root](/)  
├─ 📁 [bin](bin/) → Contén os ficheiros APK xerados ou publicados da aplicación.  
├─ 📁 [app](app/) → Código fonte principal da aplicación Android (Java/Kotlin + recursos).  
├─ 📁 [documentacion](documentacion/) → Documentación do proxecto (manuais, diagramas, especificacións…).  
├─ 📁 [gradle](gradle/) → Ficheiros internos utilizados polo sistema de construción Gradle.  
├─ 📄 [.gitignore](.gitignore) → Indica que ficheiros deben ser ignorados polo control de versións Git.  
├─ 📄 [LICENSE.md](LICENSE.md) → Licenza oficial do proxecto.  
├─ 📄 [README.md](README.md) → Descrición xeral do proxecto e do autor.  
├─ 📄 [build.gradle.kts](build.gradle.kts) → Configuración principal do proxecto Gradle en formato Kotlin Script.  
├─ 📄 [gradle.properties](gradle.properties) → Axustes globais de Gradle (rendemento, opcións de compilación…).  
├─ 📄 [gradlew](gradlew) → Script para executar Gradle en Linux/macOS sen instalalo no sistema.  
├─ 📄 [gradlew.bat](gradlew.bat) → Script para executar Gradle en Windows.  
├─ 🦊 [.gitlab-ci.yml](gradlew.bat) → Script para crear repositorio public en gitlab pages.  
└─ 📄 [settings.gradle.kts](settings.gradle.kts) → Define os módulos incluídos no proxecto e configuración inicial.  


## Instalación/Posta en marcha

- Para poder despregar a aplicación en local e poder utilizala simplemente se deberá [descargar](bin/SpendWise.apk) e instalar a aplicación nun dispositivo Android que dispoña das seguintes características mínimas:
    - Débese dispor de un método de autenticación biométrica ou PIN/Patrón  __configurado__ no dispositivo
    - A versión mínima de Android para utilizar a app é: __Android 14__ Upside Down Cake (2023)
    - Para poder dispor da mellor experiencia de usuario recoméndase utilizar un dispositivo con mínimo 6.3" de pantalla
- Para máis información consultar o apartado de despregue da aplicación no [manual técnico](doc/6_manual_tecnico.md)
- **Durante a avaliación do proxecto estará habilitado o seguinte usuario e contrasinal para acceder a aplicación:** [credenciais](doc/credenciais.md)

## Licenza
   - [GNU GPL v3](LICENSE.md)