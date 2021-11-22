package com.huazie.fleaframework.common.log4j;

import com.huazie.fleaframework.common.log4j.helpers.FleaPatternParser;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.helpers.PatternParser;

/**
 * <p> Flea Pattern Layout </p>
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class FleaPatternLayout extends PatternLayout {

    @Override
    protected PatternParser createPatternParser(String pattern) {
        return new FleaPatternParser(pattern);
    }
}
