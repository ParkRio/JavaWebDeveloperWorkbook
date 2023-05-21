package org.zerock.jdbcex.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

public enum MapperUtil {

    INSTANCE;

    private ModelMapper modelMapper;

    MapperUtil() {
        this.modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);
        // ModelMapper 설정을 변경하려면 getConfiguration()을 이용해서 private 으로 선언된
        // 필드도 접근 가능하도록 설정을 변경하고 get() 을 이용해서 ModelMapper 를 사용할 수 있도로 구성한다.
    }

    public ModelMapper get() {
        return modelMapper;
    }
}
