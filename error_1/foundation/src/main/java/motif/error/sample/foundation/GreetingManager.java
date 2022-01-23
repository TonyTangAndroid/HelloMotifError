package motif.error.sample.foundation;

import motif.error.sample.common.entity.UserEntity;

public class GreetingManager {

  private final String template;
  private final UserEntity userEntity;

  public GreetingManager(String template, UserEntity userEntity) {
    this.template = template;
    this.userEntity = userEntity;
  }

  public String greeting() {
    return String.format(template, this.userEntity.getName());
  }
}
