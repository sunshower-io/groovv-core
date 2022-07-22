package io.groovv.app.ui;

import com.aire.ux.test.Adapter;
import com.aire.ux.test.AireTest;
import com.aire.ux.test.ComponentHierarchyNodeAdapter;
import com.aire.ux.test.spring.EnableSpring;
import io.groovv.app.ui.config.PersistenceConfiguration;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.context.SpringBootTest;

@Documented
@AireTest
@EnableSpring
@SpringBootTest(
    classes = {
        PersistenceConfiguration.class
    })
@Adapter(ComponentHierarchyNodeAdapter.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface GroovvUITest {


}
