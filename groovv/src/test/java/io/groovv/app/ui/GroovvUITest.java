package io.groovv.app.ui;

import com.aire.ux.test.Adapter;
import com.aire.ux.test.AireTest;
import com.aire.ux.test.ComponentHierarchyNodeAdapter;
import com.aire.ux.test.spring.EnableSpring;
import io.groovv.app.ui.config.PersistenceConfiguration;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestExecutionListeners.MergeMode;

@Documented
@AireTest
@EnableSpring
@SpringBootTest(
    classes = {
        PersistenceConfiguration.class
    })
@TestExecutionListeners(
    mergeMode = MergeMode.MERGE_WITH_DEFAULTS,
    listeners = WithSecurityContextTestExecutionListener.class
)
@Adapter(ComponentHierarchyNodeAdapter.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface GroovvUITest {


}
