# Pair Programming

[![Circle CI](https://circleci.com/gh/verath/EDA397_2016_Group8.svg?style=svg&circle-token=5bddd588db7063b09b5957a46b72dc96e06f9486)](https://circleci.com/gh/verath/EDA397_2016_Group8)

This project was done as part of the Agile Development Processes course at Chalmers. The goal of the project was to develop an Android application following (or trying) a number of agile and/or XP (Extreme programming) practices, such as pair programming and continious integration.

The application developed was meant to be an app that could help the "co-pilot" when pair programming.

## Architecture
The code follows closely the MVP pattern, as implemented in the [Google Android Architecture Blueprints](https://github.com/googlesamples/android-architecture/tree/todo-mvp). One of the primary benefit of the MVP pattern is that logic is separated from the view layer, making it much easier to write tests for that functionallity. This also goes hand in hand with TDD, which is heavily recommended by XP.

The package structure tries to keep one package per feature.

## Continous Integration
A continous integration (CI) service (in this case [CircleCI](https://circleci.com/)) was used to run a build on each commit to the Github repository. That in combination with developing each feature on a separate branch and merging only via pull requests, the develop and master branch were kept in a runnable state at all times.

The CI service essentially runs `./gradlew clean build`, and reports if the command finished succesfully or not. This command does a full build of the project, including compiling the code, running the tests, among other things. The result is then reported back to Github, as well as made available for further inspection.

## Coding Style
The coding style used follows closely the [Code Style for Android Contributors](https://source.android.com/source/code-style.html). 

In addition, a tool called [Checkstyle](http://checkstyle.sourceforge.net/) was used to enforce that all code did follow the standards. This tool is integrated with the gradle build, and will fail the build on failures. The coding style enforced by Checkstyle is defined in the [checkstyle.xml](checkstyle.xml) file in the root of the project directory.

## Functionallity
The application has a couple of features, as visualized below:

| Image          | Description
| -------------- | --------------
| ![Main Screen][main-screen] | Main screen!
| ![Notes Screen][notes-screen] | Notes screen!
| ![Backlog Screen][backlog-screen] | Backlog screen!
| ![Backlog Add Screen][backlog-add-screen] | Backlog add screen!
| ![Requirements Screen][requirements-screen] | Requirements screen!
| ![Timer Screen][timer-screen] | Timer screen!



[main-screen]: docs/screenshot-main-2016-05-16.png
[notes-screen]: docs/screenshot-notes-2016-05-16.png
[backlog-screen]: docs/screenshot-backlog-2016-05-16.png
[backlog-add-screen]: docs/screenshot-backlog-add-2016-05-16.png
[requirements-screen]: docs/screenshot-requirements-2016-05-16.png
[timer-screen]: docs/screenshot-timer-2016-05-16.png

