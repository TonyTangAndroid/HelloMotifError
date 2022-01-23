package motif.error.sample.foundation;

import com.google.common.truth.Truth;
import motif.error.sample.common.entity.UserEntity;
import org.junit.Test;

public class FoundationTest {

  @Test
  public void testIt() {
    Truth.assertThat(
            new GreetingScopeImpl(() -> new UserEntity("tony")).greetingManager().greeting())
        .isEqualTo("Hello tony");
  }
}
