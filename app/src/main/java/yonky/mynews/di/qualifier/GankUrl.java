package yonky.mynews.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Administrator on 2017/11/6.
 */
@Qualifier
@Documented
@Retention(RUNTIME)
public @interface GankUrl {
}
