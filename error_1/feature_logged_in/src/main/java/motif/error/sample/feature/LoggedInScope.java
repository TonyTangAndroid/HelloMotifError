package motif.error.sample.feature;

import motif.Scope;
import motif.error.sample.foundation.GreetingScope;

@Scope
public interface LoggedInScope extends GreetingScope.Builder {

  LoggedManager greetingManager();

  @motif.Objects
  abstract class Objects {

    abstract LoggedManager greetingManager();
  }
}
