package club.wedcloud.www.service.impl;

import club.wedcloud.www.aspect.annotation.CacheDuration;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class NoteCodeService {
    @Cacheable(cacheNames = "noteCode", key = "#mobile")
    @CacheDuration(duration = 60)
    public String getCode(String mobile) {
        return String.valueOf(new Random().nextInt(8999) + 1000);
    }
}
