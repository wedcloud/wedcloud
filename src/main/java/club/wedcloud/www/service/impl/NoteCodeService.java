package club.wedcloud.www.service.impl;

import java.util.Random;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import club.wedcloud.www.aspect.annotation.CacheDuration;

@Service
public class NoteCodeService {
  @Cacheable(cacheNames = "noteCode", key = "#mobile")
  @CacheDuration(duration = 60)
  public String getCode(String mobile) {
    return String.valueOf(new Random().nextInt(8999) + 1000);
  }
}
