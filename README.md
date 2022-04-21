# Commons

[![Circle CI](https://circleci.com/gh/dreifadotapp/commons.svg?style=shield)](https://circleci.com/gh/dreifadotapp/commons)
[![Licence Status](https://img.shields.io/github/license/dreifadotapp/commons)](https://github.com/dreifadotapp/commons/blob/master/licence.txt)

## What it does?

Some common helpers and support classes. This library is lightweight and has zero dependencies (other than the Kotlin
runtime JAR).

* [Chaos](./docs/chaos.md)
* [xunitpatterns.com](./docs/xunitpatterns.md)
* TODO - complete this list !

## Dependencies

As with everything in [Dreifa dot App](https://dreifa.app), this library has minimal dependencies:

* Kotlin 1.5
* Java 11


## Adding as a dependency

Maven jars are deployed using [JitPack](https://jitpack.io/).
See [releases](https://github.com/dreifadotapp/commons/releases) for version details.

```groovy
//add jitpack repo
maven { url "https://jitpack.io" }

// add dependency 
implementation "com.github.dreifadotapp:commons:<release>"
```

_JitPack build status is at https://jitpack.io/com/github/dreifadotapp/commons/$releaseTag/build.log_

