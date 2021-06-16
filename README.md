![Karumi logo][karumilogo] KataSuperHeroes with Jetpack Compose [![Build, lint, and test](https://github.com/Karumi/KataSuperHeroesCompose/actions/workflows/build.yml/badge.svg)](https://github.com/Karumi/KataSuperHeroesCompose/actions/workflows/build.yml)
============================

- We are here to learn about [Jetpack Compose](https://developer.android.com/jetpack/compose).
- We are going to use [Jetpack Compose Testing Tools](https://developer.android.com/jetpack/compose/testing) to interact with the Application UI from our tests.
- We are going to use [Shot](https://github.com/karumi/Shot) test our UI using Screenshot Testing.
- We are going to use [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) to replace production code with [Test Doubles][testDoubles].
- We are going to practice pair programming.

---

## Getting started

This repository contains an Android application to show super heroes information:

![ApplicationScreencast][applicationScreencast]

This Application is based on Jetpack Compose components and Jetpack View Models:

* ``SuperHeroListScreen`` showing a list of super heroes with name, photo and a special badge if is part of the Avengers Team.

![MainActivityScreenshot][mainActivityScreenshot]

* ``SuperHeroDetailScreen`` showing detailed information about a super hero like his or her name, photo and description.

![SuperHeroDetailActivityScreenshot][superHeroDetailActivityScreenshot]

The application architecture, dependencies and configuration is ready to just start working with view models, Hilt as dependency injector and Jetpack Compose for the UI.

## Tasks

1. UI Development:

Your task as an Android Developer is to **write all the missing components**  needed to implement this Application UI. Navigation and the main skeleton is in place, but you'll have to implement some Jetpack Compose components using the assets provided. ``main`` branch will be used as the solution and ``write-ui`` branch will be used as your starting point.

2. Writing tests:

Your task as Android Developer is to **write all the UI tests** needed to check if the Application UI is working as expected. ``main`` branch will be used as the solution and ``write-tests`` branch will be used as your starting point.

## Extra Tasks

If you are done and you want to keep practicing, try to continue with the following tasks:

* Modify the detail screen to handle an error case where the name of the super hero used to start this activity does not exist and show a message if this happens.
* Modify the project to handle connection errors and show a message explaining that something went wrong in both screens.
* Add coverage to all the new scenarios.

---

Data provided by Marvel. © 2021 MARVEL

# License

Copyright 2021 Karumi

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

[karumilogo]: https://cloud.githubusercontent.com/assets/858090/11626547/e5a1dc66-9ce3-11e5-908d-537e07e82090.png
[testDoubles]: http://www.martinfowler.com/bliki/TestDouble.html
[applicationScreencast]: ./art/ApplicationScreencast.gif
[mainActivityScreenshot]: ./art/MainActivityScreenshot.png
[superHeroDetailActivityScreenshot]: ./art/SuperHeroDetailActivityScreenshot.png
