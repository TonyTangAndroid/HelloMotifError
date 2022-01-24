# Motif common error message 1 reproduction

## Environment  to reproduce the error:

Different Java Environment will reproduce different error message.
However, no matter what's the message, the error is thrown at the Java compiler level.
It will NOT fallback to motif compiler to process the error.


## Command to reproduce the error:

```
./gradlew :samples:access:feature_logged_in:testDebugUnitTest --tests "motif.sample.app.application.LoggedInTest"
```


## The reproduced message on Java 8  :

```
> Task :samples:access:feature_logged_in:compileDebugJavaWithJavac FAILED
The following annotation processors are not incremental: jetified-compiler-0.3.8-SNAPSHOT.jar (project :compiler).
Make sure all annotation processors are incremental to improve your build speed.
error: cannot access motif.sample.app.root.PersonEntity
  class file for motif.sample.app.root.PersonEntity not found
  Consult the following stack trace for details.
  com.sun.tools.javac.code.Symbol$CompletionFailure: class file for motif.sample.app.root.PersonEntity not found
1 error

```

## The reproduced message on Java 11 :

```
TODO
```

## Why such message is reproduced?

While in `LoggedInScope`, it does not explicitly reference `PersonEntity`. However, it does extends the
`GreetingScope.Builder`, which means that it declares that `LoggedInScope` is capable of building `GreetingScope`,
which actually is referencing `PersonEntity`.
Hence, we need to add `implementation project(':samples:access:common')` into `samples/access/feature_logged_in/build.gradle` dependencies
to avoid the error message

`com.sun.tools.javac.code.Symbol$CompletionFailure: class file for motif.sample.app.root.PersonEntity not found`.