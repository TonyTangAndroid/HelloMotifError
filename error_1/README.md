# Motif common error message 1 reproduction

## Environment  to reproduce the error:

Different Java Environment will reproduce different error message.
However, no matter what's the message, the error is thrown at the Java compiler level.
It will NOT fallback to motif compiler to process the error.


## Command to reproduce the error:

```
./gradlew :error_1:feature_logged_in:testDebugUnitTest --tests "motif.sample.app.application.LoggedInTest"
```


## The reproduced message on Java 8  :

```
> Task :error_1:feature_logged_in:compileDebugJavaWithJavac FAILED
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
Task :error_1:feature_logged_in:compileDebugJavaWithJavac FAILED
The following annotation processors are not incremental: jetified-motif-compiler-0.3.7.jar (com.uber.motif:motif-compiler:0.3.7).
Make sure all annotation processors are incremental to improve your build speed.
/Users/tonytang/0_motif/HelloMotifError/error_1/feature_logged_in/build/generated/ap_generated_sources/debug/out/motif/sample/app/root/LoggedInScopeImpl.java:35: error: cannot find symbol
  public GreetingScope greetingScope(final PersonEntity personEntity) {
                                           ^
  symbol:   class PersonEntity
  location: class LoggedInScopeImpl
1 error

FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':error_1:feature_logged_in:compileDebugJavaWithJavac'.
> Compilation failed; see the compiler error output for details.

* Try:
Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output. Run with --scan to get full insights.

* Get more help at https://help.gradle.org

```

## Why such message is reproduced?

While in `LoggedInScope`, it does not explicitly reference `PersonEntity`. However, it does extends the
`GreetingScope.Builder`, which means that it declares that `LoggedInScope` is capable of building `GreetingScope`,
which actually is referencing `PersonEntity`.
Hence, we need to add `implementation project(':error_1:common')` into `error_1/feature_logged_in/build.gradle` dependencies
to avoid the error message

`com.sun.tools.javac.code.Symbol$CompletionFailure: class file for motif.sample.app.root.PersonEntity not found`.