
# Android Architecture

## Table of contents
<!-- TOC -->

- [Clean Architecture](#Clean-Architecture)
    - [Clean architecture layer](#Clean-architecture-layer)
    - [How does it work](#How-does-it-work)
    - [Clean architecture flow](#Clean-architecture-flow)
    - [Benefit](#Benefit)
- [External Dependencies](#external-dependencies)
    - [Dependency manager](#dependency-manager)
    - [Third party](#third-party)
    - [Open Source Code](#open-source-code)
    - [Open Source tooling](#open-source-tooling)

_Schemas have been built with [Draw.io](https://draw.io) based on this [file](./assets/20191204-ios-architecture.drawio)_

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

## Architecture layers

To support this code architecture, we need to go further and break down the app into smaller modules. A module represent an isolated piece of logic that has one goal only.

![architecture-layer](./assets/20191204-architecture-layer.png)

_The current graph represent only partially the internal modules planned. It doesn't represent dependencies as a whole (Thrift or Statd missing for instance)_

### What are the benefits

Here is a list of some benefits of this new dependency architecture:

* Improve testability, reusability and maintainability by isolating logic from main app.
* Remove strong dependencies between layers (App -> Model, Pluto -> Model, Storage -> Model, etc.)
* Remove complexity in Main app: read more [here](./core-refactoring.md)
* Allow scalable project
* Avoid spaghetti code 🍝 

### How does it works

The top layer is the closest to the user interaction. Navigating through the layers, we go deeper in the business logic, caring less about where the interaction comes from. 

⚠️ Dependencies are __unidirectional__, only from bottom to top. For instance, _Networking_ module can be used in _Feed_ or _Catalog_ but can't depend of _Wishlist_. 

At the same time, __modules can't depend of each other from same level__: _Feed_ can't depend of _Wishlist_, _Storage_ can't depend from _Networking_. However, using protocol oriented programming allows us to inject different dependencies through layers.

⚠️ Dependencies are __optionals__: not every module should depend of previous layers: only _App_ implement _UI_ component, _Checkout_ might need only _Web Content_, etc.

Ideally, each layer should limit dependencies to next layer: the _App_ one shouldn't access any _Core_ or _Foundation_ one directly, but use _Flow_ one to abstract any computation logic, except for specific usage like UI (style guide).

## External Dependencies

Here is a list of known external dependencies in iOS application.

### Dependency manager
* Carthage
* Rome

### Third party

* Google Analytics / Google Tag Manager / Firebase Analytics (Analytics)
* Firebase Crashlytics (Crash Report)
* Swrve SDK (PushNotification + Analytics)
* Adjust / Criteo (Analytics)
* Facebook (Social Network + Analytics)
* Akamai (Security + Monitoring)
* Crittercism (Crash Report)

### Open source code

* RxSwift / RxCocoa / RxTest / RxBlocking
* RxDataSources / RxSwiftExt
* IGListKit (UI)
* DrawerKit (UI)
* YoutubePlayer-in-WKWebView (UI)
* AMScrollingNavBar (UI)
* DTFoundation / DTCoreText
* SDWebImage (Image)
* OCThumbor (Image)
* SwiftyJSON
* Alamofire (through Pluto)
* Moya / RxMoya (through Pluto)
* Cache (through Pluto)

### Open source tooling

* SwiftGen (code generator translation)
* SwiftLint (code formatting)
* Sourcery (code generator)
