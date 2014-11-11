## Обзор высокоуровневых сетевых Java библиотек
--------------------
####Наиболее популярные высокоуровневые сетевые библиотеки:

* [Netty](http://netty.io/)
* [Apache MINA](https://mina.apache.org/)
* [Grizzly](https://grizzly.java.net/)
* [xSockets](http://xsockets.net/) (не поддерживает Java)

Также есть интересная библиотека [CoralReactor](http://www.coralblocks.com/index.php/category/coralreactor/) в рамках проекта [Coral Blocks](http://www.coralblocks.com/). Интересна она тем, что [согласно их собственным тестам](http://www.coralblocks.com/index.php/2014/04/coralreactor-vs-netty-performance-comparison/) производительности, CoralReactor работает в среднем быстрее Netty в несколько раз. Судя по всему она находится в стадии разработки, так как библиотеки нет в свободном доступе в сети, более того о ней нет какой-либо информации, помимо их собственного сайта и аккаунта на [stackoverflow](http://stackoverflow.com/users/3610482/coral-blocks). 

------------------------------
### [Netty](http://netty.io/) vs [Apache MINA](https://mina.apache.org/)

Просмотрев несколько [ответов](http://stackoverflow.com/questions/1637752/netty-vs-apache-mina), статей и тестов [памяти](http://www.znetdevelopment.com/blogs/2009/04/09/scalable-nio-servers-part-2-memory/)/[производительности](http://www.znetdevelopment.com/blogs/2009/04/07/scalable-nio-servers-part-1-performance/) выяснилось, что основными конкурирующими между собой библиотеками на сегодня являются Netty и MINA. 

#### Я попробовал использовать каждую из них, написав простые серверы:  

 *  Используя Netty был реализован сервер, который принимает сообщение и рассылает его всем подключенным клиентам.  
 * С Apache MINA был реализован ещё более простой сервер, отправляющий в ответ на любое сообщение клиенту текущее время сервера. 

---------------------
#### Некоторые сравнительные характеристики библиотек: 

* В MINA - сообщения обрабатываются только используя Handler, в то время как в Netty есть встроенные pipeline с готовыми классами, такими как encode, decode, replayingDecoders, что дает больше гибкости во время выполнения. 
*  Помимо TCP/IT, HTTP и UPD Netty дополнительно поддерживает SSL и HTTPS.
*  Netty начиная с версии 4.0.0 Alpha поддерживает [NIO.2](https://docs.oracle.com/javase/tutorial/essential/io/fileio.html) (file I/O) в качестве движка, вместе с NIO и блокирующими сокетами. MINA не поддерживает NIO.2.
* Netty немного быстрей работает при высокой нагрузке, судя по различным непроверенным данным и отзывам.
* Netty имеет лучшую документацию.
