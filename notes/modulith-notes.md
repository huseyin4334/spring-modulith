# Spring Modulith Fundamentals
Spring modulith is a way to build a modular monolith using Spring Boot.
Generally, we will set rules for the modules and the dependencies between them.
Modulith package will control and verify the dependencies between modules.
If the dependencies are not configured or used correctly the build will fail. And modulith package will tell us where is the problem.
We have some classes and annotations to see and control the dependencies between modules.

## Application Modules
An application module have some parts;
- An API exposed to other modules implemented by Spring bean instances and application events published by the module. (Maybe Service)
- Internal implementation components that are not supposed to be accessed by other modules.
  - Repositories, models, services, etc.
- References to API exposed by other modules in the form of Spring bean dependencies, application events listened to and configuration properties exposed
  - Listeners by `@EventListener` annotation


## ApplicationModules Class
Spring modulith allows to inspect the dependencies between modules.
We can reach these information by `ApplicationModules` class. It's in the `spring-modulith-core` package.

```java
import com.example.modulith.SpringModulithApplication;

void run() {
    ApplicationModules modules = ApplicationModules.of(SpringModulithApplication.class);
    modules.forEach(System.out::println);
}
```

## Excluding Packages
We can exclude some packages from the module scanning.

```java
import com.tngtech.archunit.core.domain.JavaClass;

void run() {
  ApplicationModules modules = ApplicationModules.of(SpringModulithApplication.class, JavaClass.Predicates.resideInAPackage("com.example.modulith"));
  modules.verify();
  
  // com.example.modulith package will be excluded all files in the given package.
  // com.example.modulith.. will be excluded all files in the given package and subpackages. (recursively)
  // ..example.. All matches will be excluded. (a.example.b, a.example, a.example.c, etc.)
}
```


## Spring Application Modules
`events` and `modules` are the main parts of the Spring application module.
`modules.customer` is the module package.

When we have some internal files in the module and this file injected some other modules, java compiler can not prevent this. But Spring modulith can prevent this


## Open Application Modules
Firstly, we should know `package-info.java` and `module-info.java` files.
`package-info.java` is a file that contains package-level annotations and descriptions.
`module-info.java` is a file that contains module-level requirements and exports.
Requirements are the modules that the current module depends on. For example, `requires spring.context;` means that the current module depends on the `spring.context` module.
Exports are the packages that the current module exposes to other modules. For example, `exports com.example.modulith.customer;` means that the current module exposes the `com.example.modulith.customer` package to other modules.

We will define a package-info.java for the modulith annotations

```java
@org.springframework.modulith.ApplicationModule(
  type = Type.OPEN
)
package com.example.modulith.shipment;
```


## Explicit Dependencies
We can define a module allowed dependencies.

```java
@org.springframework.modulith.ApplicationModule(
  type = Type.OPEN,
  allowedDependencies = {
    "customer", "shipment::service", "calculator::*"
  }
)
package com.example.modulith.shipment;
```


I want to explain how can we reach classes;
- If we want to reach a module fully, we have to open a package-info.java file, and we have to set the type as `OPEN`.
- If we don't want to reach a module fully, we have to open package-info.java in which module we want to reachable, and we should set `@NamedInterface` annotation.
  - Don't forget when we open this subpackage, other subpackages of this subpackage won't be reachable.
  - Then, just API classes will be reachable. (We defined this api classes above).

## Named Interface
It's like a gateway for the other modules. We can reach the classes in the module by this interface.
Or we can define `@org.springframework.modulith.PackageInfo` annotation in the package-info.java file. It will be the same.

```java
@org.springframework.modulith.NamedInterface(name = "service")
package com.example.modulith.shipment.service;
```


## Customize Module Detection
If we want to detection manually, we can use `@org.springframework.modulith.Module` annotation or `@org.springframework.modulith.ApplicationModule` annotation.
For detection, we should set a value in application.properties file. `spring.modulith.detection-strategy=explicitly-annotated`
Also, we can write a custom module detection strategy. ![Custom Module Detection](https://docs.spring.io/spring-modulith/reference/fundamentals.html#customizing-modules)

Also, we can use `@Modulithic` annotation for the same purpose.
We can set additionalPackages property for detect modules extra.
> ![Modulithic Annotation](https://docs.spring.io/spring-modulith/reference/fundamentals.html#customizing-modules-arrangement)


# Verifying Module Structure

```java
void verify() {
  ApplicationModules.of(Application.class).verify();
}
```

- Cycle detection
- Efferent module access via API packages only. (`@NamedInterface` annotation)
- Explicitly allowed application module dependencies only (allowedDependencies)