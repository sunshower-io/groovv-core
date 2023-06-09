package io.groovv.app.ui;

import com.aire.ux.test.Adapter;
import com.aire.ux.test.AireTest;
import com.aire.ux.test.ComponentHierarchyNodeAdapter;
import com.aire.ux.test.spring.EnableSpring;
import io.groovv.app.ui.config.AdministrationConfiguration;
import io.groovv.service.test.ServiceTest;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestExecutionListeners.MergeMode;

@Documented
@AireTest
@EnableSpring
@ServiceTest
@Import({AdministrationConfiguration.class})
@TestExecutionListeners(
    mergeMode = MergeMode.MERGE_WITH_DEFAULTS,
    listeners = WithSecurityContextTestExecutionListener.class)
@Adapter(ComponentHierarchyNodeAdapter.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface GroovvUITest {}
