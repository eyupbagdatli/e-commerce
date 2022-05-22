package com.tr.ecommerce;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.tr.ecommerce");

        noClasses()
            .that()
            .resideInAnyPackage("com.tr.ecommerce.service..")
            .or()
            .resideInAnyPackage("com.tr.ecommerce.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.tr.ecommerce.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
