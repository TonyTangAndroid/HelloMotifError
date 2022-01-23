package motif.error.sample.common.entity;

import com.google.common.truth.Truth;
import org.junit.Test;

public class CommonTest {

  @Test
  public void testIt() {
    Truth.assertThat(1 + 1).isNotEqualTo(3);
  }
}
