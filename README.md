## Reproducer instructions

- Start a Postgres container:

  ```shell
  docker run --name postgres -e=POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres:latest
  ```

- Install JDK 21 GraalVM:

    ```shell
    sdk install java 21.0.1-graalce
    ```

- Build the native executable:

    ```shell
    ./gradlew nativeCompile
    ```

- Run the executable, requires postgres to be running

    ```shell
    ./build/native/nativeCompile/micronaut-hibernate-native-reproducer
    ```
### Logs

```text
07:22:32.648 [main] INFO  com.zaxxer.hikari.HikariDataSource - HikariPool-1 - Starting...
07:22:32.696 [main] INFO  com.zaxxer.hikari.pool.HikariPool - HikariPool-1 - Added connection org.postgresql.jdbc.PgConnection@6da5301c
07:22:32.696 [main] INFO  com.zaxxer.hikari.HikariDataSource - HikariPool-1 - Start completed.
07:22:32.700 [main] INFO  i.m.flyway.AbstractFlywayMigration - Running migrations for database with qualifier [default]
07:22:32.700 [main] INFO  o.f.c.i.license.VersionPrinter - Flyway Community Edition 9.22.3 by Redgate
07:22:32.700 [main] INFO  o.f.c.i.license.VersionPrinter - See release notes here: https://rd.gt/416ObMi
07:22:32.700 [main] INFO  o.f.c.i.license.VersionPrinter -
07:22:32.701 [main] INFO  org.flywaydb.core.FlywayExecutor - Database: jdbc:postgresql://localhost:5432/postgres (PostgreSQL 16.0)
07:22:32.705 [main] WARN  o.f.c.i.database.base.Database - Flyway upgrade recommended: PostgreSQL 16.0 is newer than this version of Flyway and support has not been tested. The latest supported version of PostgreSQL is 15.
07:22:32.709 [main] INFO  o.f.c.internal.database.base.Schema - Creating schema "demo" ...
07:22:32.714 [main] INFO  o.f.c.i.s.JdbcTableSchemaHistory - Creating Schema History table "demo"."flyway_schema_history" ...
07:22:32.772 [main] INFO  o.f.core.internal.command.DbMigrate - Current version of schema "demo": null
07:22:32.773 [main] INFO  o.f.core.internal.command.DbMigrate - Schema "demo" is up to date. No migration necessary.
07:22:32.790 [main] INFO  org.hibernate.Version - HHH000412: Hibernate ORM core version 6.2.13.Final
07:22:32.791 [main] INFO  org.hibernate.cfg.Environment - HHH000406: Using bytecode reflection optimizer
07:22:32.826 [main] ERROR io.micronaut.runtime.Micronaut - Error starting Micronaut server: Bean definition [org.hibernate.SessionFactory] could not be loaded: Error instantiating bean of type  [org.hibernate.SessionFactory]

Message: No classes have been predefined during the image build to load from bytecodes at runtime.
Path Taken: SessionFactoryPerDataSourceFactory.buildHibernateSessionFactoryBuilder(SessionFactoryBuilder sessionFactoryBuilder) --> SessionFactoryPerDataSourceFactory.buildHibernateSessionFactoryBuilder([SessionFactoryBuilder sessionFactoryBuilder])
io.micronaut.context.exceptions.BeanInstantiationException: Bean definition [org.hibernate.SessionFactory] could not be loaded: Error instantiating bean of type  [org.hibernate.SessionFactory]

Message: No classes have been predefined during the image build to load from bytecodes at runtime.
Path Taken: SessionFactoryPerDataSourceFactory.buildHibernateSessionFactoryBuilder(SessionFactoryBuilder sessionFactoryBuilder) --> SessionFactoryPerDataSourceFactory.buildHibernateSessionFactoryBuilder([SessionFactoryBuilder sessionFactoryBuilder])
	at io.micronaut.context.DefaultBeanContext.initializeContext(DefaultBeanContext.java:1980)
	at io.micronaut.context.DefaultApplicationContext.initializeContext(DefaultApplicationContext.java:291)
	at io.micronaut.context.DefaultBeanContext.readAllBeanDefinitionClasses(DefaultBeanContext.java:3322)
	at io.micronaut.context.DefaultBeanContext.finalizeConfiguration(DefaultBeanContext.java:3677)
	at io.micronaut.context.DefaultBeanContext.start(DefaultBeanContext.java:361)
	at io.micronaut.context.DefaultApplicationContext.start(DefaultApplicationContext.java:199)
	at io.micronaut.runtime.Micronaut.start(Micronaut.java:73)
	at io.micronaut.runtime.Micronaut.run(Micronaut.java:322)
	at io.micronaut.runtime.Micronaut.run(Micronaut.java:297)
	at com.example.AppKt.main(App.kt:17)
	at java.base@21.0.1/java.lang.invoke.LambdaForm$DMH/sa346b79c.invokeStaticInit(LambdaForm$DMH)
Caused by: io.micronaut.context.exceptions.BeanInstantiationException: Error instantiating bean of type  [org.hibernate.SessionFactory]

Message: No classes have been predefined during the image build to load from bytecodes at runtime.
Path Taken: SessionFactoryPerDataSourceFactory.buildHibernateSessionFactoryBuilder(SessionFactoryBuilder sessionFactoryBuilder) --> SessionFactoryPerDataSourceFactory.buildHibernateSessionFactoryBuilder([SessionFactoryBuilder sessionFactoryBuilder])
	at io.micronaut.context.DefaultBeanContext.resolveByBeanFactory(DefaultBeanContext.java:2324)
	at io.micronaut.context.DefaultBeanContext.doCreateBean(DefaultBeanContext.java:2279)
	at io.micronaut.context.DefaultBeanContext.doCreateBean(DefaultBeanContext.java:2291)
	at io.micronaut.context.DefaultBeanContext.createRegistration(DefaultBeanContext.java:3054)
	at io.micronaut.context.SingletonScope.getOrCreate(SingletonScope.java:80)
	at io.micronaut.context.DefaultBeanContext.findOrCreateSingletonBeanRegistration(DefaultBeanContext.java:2956)
	at io.micronaut.context.DefaultBeanContext.resolveBeanRegistration(DefaultBeanContext.java:2917)
	at io.micronaut.context.DefaultBeanContext.resolveBeanRegistration(DefaultBeanContext.java:2730)
	at io.micronaut.context.DefaultBeanContext.getBean(DefaultBeanContext.java:1729)
	at io.micronaut.context.AbstractBeanResolutionContext.getBean(AbstractBeanResolutionContext.java:89)
	at io.micronaut.context.AbstractInitializableBeanDefinition.resolveBean(AbstractInitializableBeanDefinition.java:2165)
	at io.micronaut.context.AbstractInitializableBeanDefinition.getBeanForConstructorArgument(AbstractInitializableBeanDefinition.java:1328)
	at io.micronaut.configuration.hibernate.jpa.conf.$SessionFactoryPerDataSourceFactory$BuildHibernateSessionFactoryBuilder4$Definition.instantiate(Unknown Source)
	at io.micronaut.context.BeanDefinitionDelegate.instantiate(BeanDefinitionDelegate.java:159)
	at io.micronaut.context.DefaultBeanContext.resolveByBeanFactory(DefaultBeanContext.java:2309)
	at io.micronaut.context.DefaultBeanContext.doCreateBean(DefaultBeanContext.java:2279)
	at io.micronaut.context.DefaultBeanContext.doCreateBean(DefaultBeanContext.java:2291)
	at io.micronaut.context.DefaultBeanContext.createRegistration(DefaultBeanContext.java:3054)
	at io.micronaut.context.SingletonScope.getOrCreate(SingletonScope.java:80)
	at io.micronaut.context.DefaultBeanContext.findOrCreateSingletonBeanRegistration(DefaultBeanContext.java:2956)
	at io.micronaut.context.DefaultBeanContext.initializeEagerBean(DefaultBeanContext.java:2667)
	at io.micronaut.context.DefaultBeanContext.initializeContext(DefaultBeanContext.java:1974)
	... 10 common frames omitted
Caused by: com.oracle.svm.core.jdk.UnsupportedFeatureError: No classes have been predefined during the image build to load from bytecodes at runtime.
	at org.graalvm.nativeimage.builder/com.oracle.svm.core.util.VMError.unsupportedFeature(VMError.java:121)
	at org.graalvm.nativeimage.builder/com.oracle.svm.core.hub.PredefinedClassesSupport.throwNoBytecodeClasses(PredefinedClassesSupport.java:76)
	at org.graalvm.nativeimage.builder/com.oracle.svm.core.hub.PredefinedClassesSupport.loadClass(PredefinedClassesSupport.java:130)
	at java.base@21.0.1/java.lang.ClassLoader.defineClass(ClassLoader.java:280)
	at net.bytebuddy.utility.dispatcher.JavaDispatcher$DynamicClassLoader.invoker(JavaDispatcher.java:1383)
	at net.bytebuddy.utility.dispatcher.JavaDispatcher$InvokerCreationAction.run(JavaDispatcher.java:459)
	at net.bytebuddy.utility.dispatcher.JavaDispatcher$InvokerCreationAction.run(JavaDispatcher.java:452)
	at java.base@21.0.1/java.security.AccessController.executePrivileged(AccessController.java:129)
	at java.base@21.0.1/java.security.AccessController.doPrivileged(AccessController.java:319)
	at net.bytebuddy.utility.dispatcher.JavaDispatcher.doPrivileged(JavaDispatcher.java)
	at net.bytebuddy.utility.dispatcher.JavaDispatcher.<clinit>(JavaDispatcher.java:87)
	at net.bytebuddy.description.type.TypeDescription$ForLoadedType.<clinit>(TypeDescription.java:8659)
	at net.bytebuddy.matcher.ElementMatchers.isFinalizer(ElementMatchers.java:1624)
	at org.hibernate.bytecode.internal.bytebuddy.ByteBuddyState$ProxyDefinitionHelpers.<init>(ByteBuddyState.java:296)
	at org.hibernate.bytecode.internal.bytebuddy.ByteBuddyState.<clinit>(ByteBuddyState.java:71)
	at org.hibernate.bytecode.internal.bytebuddy.BytecodeProviderImpl.<init>(BytecodeProviderImpl.java:124)
	at org.hibernate.bytecode.internal.bytebuddy.BytecodeProviderImpl.<init>(BytecodeProviderImpl.java:116)
	at org.hibernate.bytecode.internal.BytecodeProviderInitiator.buildBytecodeProvider(BytecodeProviderInitiator.java:59)
	at org.hibernate.bytecode.internal.BytecodeProviderInitiator.initiateService(BytecodeProviderInitiator.java:36)
	at org.hibernate.bytecode.internal.BytecodeProviderInitiator.initiateService(BytecodeProviderInitiator.java:22)
	at org.hibernate.boot.registry.internal.StandardServiceRegistryImpl.initiateService(StandardServiceRegistryImpl.java:119)
	at org.hibernate.service.internal.AbstractServiceRegistryImpl.createService(AbstractServiceRegistryImpl.java:264)
	at org.hibernate.service.internal.AbstractServiceRegistryImpl.initializeService(AbstractServiceRegistryImpl.java:239)
	at org.hibernate.service.internal.AbstractServiceRegistryImpl.getService(AbstractServiceRegistryImpl.java:216)
	at org.hibernate.boot.internal.SessionFactoryBuilderImpl.<init>(SessionFactoryBuilderImpl.java:69)
	at org.hibernate.boot.internal.SessionFactoryBuilderImpl.<init>(SessionFactoryBuilderImpl.java:46)
	at org.hibernate.boot.internal.DefaultSessionFactoryBuilderService.createSessionFactoryBuilder(DefaultSessionFactoryBuilderService.java:26)
	at org.hibernate.boot.internal.MetadataImpl.getSessionFactoryBuilder(MetadataImpl.java:170)
	at io.micronaut.configuration.hibernate.jpa.conf.AbstractHibernateFactory.buildHibernateSessionFactoryBuilder(AbstractHibernateFactory.java:96)
	at io.micronaut.configuration.hibernate.jpa.conf.SessionFactoryPerDataSourceFactory.buildHibernateSessionFactoryBuilder(SessionFactoryPerDataSourceFactory.java:100)
	at io.micronaut.configuration.hibernate.jpa.conf.$SessionFactoryPerDataSourceFactory$BuildHibernateSessionFactoryBuilder3$Definition.doInstantiate(Unknown Source)
	at io.micronaut.context.AbstractInitializableBeanDefinition.instantiate(AbstractInitializableBeanDefinition.java:774)
	at io.micronaut.context.BeanDefinitionDelegate.instantiate(BeanDefinitionDelegate.java:156)
	at io.micronaut.context.DefaultBeanContext.resolveByBeanFactory(DefaultBeanContext.java:2309)
	... 31 common frames omitted
```