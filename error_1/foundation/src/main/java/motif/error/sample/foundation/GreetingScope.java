package motif.error.sample.foundation;

import motif.Scope;
import motif.error.sample.common.entity.UserEntity;

@Scope
public interface GreetingScope {

  GreetingManager greetingManager();

  interface Builder {
    GreetingScope greetingScope(UserEntity userEntity);
  }

  @motif.Objects
  abstract class Objects {

    static String format() {
      return "Hello %s";
    }

    abstract GreetingManager greetingManager();
  }
}
