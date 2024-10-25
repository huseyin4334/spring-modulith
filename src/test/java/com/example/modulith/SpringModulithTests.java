package com.example.modulith;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

public class SpringModulithTests {
    ApplicationModules modules = ApplicationModules.of(
            SpringModulithApplication.class,
            JavaClass.Predicates.resideInAPackage("com.example.modulith.events")
    );
    @Test
    void shouldBeCompliant() {
        modules.verify();
    }

    @Test
    void writeDocumentationSnippets() {
        modules.forEach(System.out::println);
        Documenter d = new Documenter(modules)
                .writeModulesAsPlantUml()
                .writeIndividualModulesAsPlantUml();
    }

    // https://docs.spring.io/spring-modulith/reference/production-ready.html

}
