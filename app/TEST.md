
# Android Architecture

## Table of contents
<!-- TOC -->

- [Clean Architecture](#Clean-Architecture)
    - [Clean architecture layer](#Clean-architecture-layer)
    - [How does it work](#How-does-it-works)
    - [Clean architecture flow](#Clean-architecture-flow)
    - [Benefit of Clean Architecture](#Benefit-of-Clean-Architecture)
- [Modularization Architecture](#Modularization-Architecture)
    - [Modularization Diagram](#Modularization-Diagram)
    - [Benefit of Modularization Architecture](#Benefit-of-Modularization-Architecture)
- [Architecture In Zalora App](#Architecture-In-Zalora-App)
    - [Feature Module](#Feature-Module)
    - [CA layers inside the feature module](#CA-layers-inside-the-feature-module)   


## Clean Architecture

### Clean architecture layer

![unnamed](https://user-images.githubusercontent.com/71365481/122347718-876aa000-cf74-11eb-9fd7-6089b8b82d3d.png)
* __Presentation__: UI (Activity / Fragment), MVVM (ViewModel), DI, Service/ WorkManager, etc
* __Domain__: Use case, Model, Repository (Interface), make sure this layer dont have any Android Framework, it is pure Java or Kotlin
* __Data__: the user interface that user can interact with
### How does it works
Clean architecture is a software design concept that separates the elements of a design into ring levels. It works based on The Dependency Inversion principle: high level modules should not depend on low level modules. both should depend on abstractions. Abstractions should not depend on details. Details should depend upon abstractions. Code on the inner layers don’t know anything about class, variable, functions in the outer layer.
### Clean architecture flow

![structure_clean_wishlist](https://user-images.githubusercontent.com/71365481/122355003-b9cbcb80-cf7b-11eb-9fc6-16e0942290ad.png)
Activity or Fragment will define view mode, view model will call to use case or repository then repository will get data from network, database or cache and handle it then return back. Finally View will display it to user.
### Benefit
*  Your code is further decoupled (the biggest advantage.)
* The UI can change easily, without changing the rest of the system. Because of separating business logic and UI
* Communication and clarity: As you can see, business use cases are most easily visible. This provides excellent clarity.
* It makes our code more testable. The business rules can be tested without the UI, Database, Web Server, or any other external element, and easy to write Unit Test for Each layer
	* Domain layer: Use case (test use case call to right repository or not)
	* Data layer: Repository (test repository call to right data source or not)
	* Presentation layer: View Model (test observer execute like what we expect or not), logic for View and View holder (test data display like like what we expect or not)

## Modularization Architecture
### Modularization Diagram
![modularized_architecture](https://user-images.githubusercontent.com/71365481/122501617-3d8ac400-d01f-11eb-8620-b3da93920139.png)

While splitting your app into several features, all of those features will likely depend on some common business logic or UI components. Hence we need to introduce a third level of “library modules”.
### Benefit of Modularization Architecture
*  Speed up project build time, on top of that Gradle adds some caching where only some of thee modules are may be recompiled instead of the whole project
* Take advantage of Android dynamic delivery
* Reduce complexity for your app because each feature will have it own resource, example module A have it own layout resource, string resource, dimension resource
* We can also have proper feature ownership per team (eg. the team X can work on code stored within a single feature module)
* It is much easier to define cross-feature dependencies and certain features may depend on 3rd party libraries that are not needed for other features
## Architecture In Zalora App
### Feature Module
![Modularization diagram](https://user-images.githubusercontent.com/71365481/122505205-11267600-d026-11eb-826e-0311ace7863b.png)

Each feature will be module and have it own resources.
Base, theme module, etc are called share module or core module because these modules can be used in one or some feature modules.
### CA layers inside the feature module


