# План работ для junior java developer

Список материала для изучения с ссылками на места, где можно почитать по каждой теме подробней

Для создания веб-приложений с использованием языка Java необходимо познакомиться c рядом технологий и инструментов:

1. Сборка проекта с помощью Maven
    - [Maven](https://maven.apache.org/what-is-maven.html)
    - [Archetypes](https://maven.apache.org/guides/introduction/introduction-to-archetypes.html)
    - [POM](https://maven.apache.org/guides/introduction/introduction-to-the-pom.html)
    - [Dependencies, Scopes](https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html)
    - [Build LifeCycle, Phases](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html)
    - [Build Profiles](https://maven.apache.org/guides/introduction/introduction-to-profiles.html)
    - [Plugins](https://maven.apache.org/plugins/)
1. Java Web, Servlet API
    - Servlet, Servlet Container (Tomcat), ServletContext `Книга "Java Методы программирования" Глава 15. СЕРВЛЕТЫ стр. 456`
    - Session, Cookie, Filter `Книга "Java Методы программирования" Глава 17. CЕССИИ, СОБЫТИЯ И ФИЛЬТРЫ стр. 522`
    - [Дескрипторы развертывания](https://cloud.google.com/appengine/docs/standard/java/config/webxml#descriptors)
    - JSP `Книга "Java Методы программирования" Глава 16. JAVA SERVER PAGE стр. 485`
    - JSTL `Книга "Java Методы программирования" Глава 18. JSP STANDARD TAG LIBRARY стр. 544`
    - [MultiPart(опционально)](https://docs.oracle.com/javaee/7/tutorial/servlets011.htm#BABFGCHB)
1. [JDBC](https://docs.oracle.com/javase/tutorial/jdbc/basics/index.html)
    - [Connection, DriverManager, Driver](https://docs.oracle.com/javase/tutorial/jdbc/basics/connecting.html)
    - [ResultSet](https://docs.oracle.com/javase/tutorial/jdbc/basics/retrieving.html)
    - [Statement, PreparedStatement, CallableStatement](https://docs.oracle.com/javase/tutorial/jdbc/basics/prepared.html)
    - [Transactions](https://docs.oracle.com/javase/tutorial/jdbc/basics/transactions.html)
1. [Spring Core](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#spring-core)
    - [DI  общая информация](https://en.wikipedia.org/wiki/Dependency_injection), [IoC общая информация](https://en.wikipedia.org/wiki/Inversion_of_control)
    - [Spring Container](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-introduction)
    - [Beans, Bean Definitions](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-definition)
    - [Bean Scopes](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-scopes)
    - [Dependencies](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-dependencies)
    - [@Autowired](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-autowire)
    - [Bean Lifecycle](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-nature)
    - [Container Extension Points](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-factory-extension)
    - [Bean Factory](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-beanfactory)
1. [Spring MVC](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc)
    - [DispatcherServlet](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-servlet)
    - [Controllers, @Contoller](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-controller)
    - [@RequestMapping, @PathParams, @QueryParams](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-methods)
    - [ViewResolvers](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-viewresolver)
    - [Spring MVC + JSP](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-view-jsp)
    - [Model, View, ModalAndView и другие возвращаемые значения](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-return-types)
1. Spring Web Rest
    - [Serialization & Deserialization](https://www.baeldung.com/java-serialization)
    - [Rest Clients](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#webmvc-client)
    - [@RestController - @Controller + @ResponseBody](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-controller)
1. [Spring JDBC Template](https://docs.spring.io/spring/docs/current/spring-framework-reference/data-access.html#jdbc-choose-style)
    - [JdbcTemplate как замена JDBC](https://docs.spring.io/spring/docs/current/spring-framework-reference/data-access.html#jdbc-core)
    - [NamedParameterJdbcTemplate](https://docs.spring.io/spring/docs/current/spring-framework-reference/data-access.html#jdbc-NamedParameterJdbcTemplate)
    - [DataSource](https://docs.spring.io/spring/docs/current/spring-framework-reference/data-access.html#jdbc-datasource)
    - [Выполнение SQL выражений](https://docs.spring.io/spring/docs/current/spring-framework-reference/data-access.html#jdbc-statements-executing)
    - [Выполнение запросов](https://docs.spring.io/spring/docs/current/spring-framework-reference/data-access.html#jdbc-statements-querying)
    - [Инициализация схемы](https://docs.spring.io/spring/docs/current/spring-framework-reference/data-access.html#jdbc-initializing-datasource)
1. [Докуметнация Bootstrap](https://getbootstrap.com/)
1. jQuery
   - [jQuery ввеление](https://antonshevchuk.gitbooks.io/jquery-for-beginners/content/10_go_on/)
   - [jQuery Атрибуты и свойства](https://antonshevchuk.gitbooks.io/jquery-for-beginners/content/20_attributes_and_properties/)
   - [jQuery События](https://antonshevchuk.gitbooks.io/jquery-for-beginners/content/30_events/)
   - [jQuery Show/Hide и анимация](https://antonshevchuk.gitbooks.io/jquery-for-beginners/content/40_animation/)
   - [jQuery Манипуляция с DOM](https://antonshevchuk.gitbooks.io/jquery-for-beginners/content/50_document_manipulation/)
1. AJAX + jQuery
   - [JSON](https://learn.javascript.ru/json)
   - [Введение в AJAX](https://learn.javascript.ru/ajax-intro)
   - [Атака CSRF](https://learn.javascript.ru/csrf)
   - [jQuery.ajax learn.jquery.com](https://learn.jquery.com/ajax/), [AJAX на основе jQuery.ajax](https://antonshevchuk.gitbooks.io/jquery-for-beginners/content/60_ajax/)
