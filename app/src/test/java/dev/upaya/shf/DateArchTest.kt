package dev.upaya.shf

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses
import org.junit.Test
import java.time.OffsetDateTime
import java.time.ZonedDateTime


class DateArchTest {

    @Test
    fun `OffsetDateTime only be used for database`() {
        val importedClasses = ClassFileImporter().importPackages("dev.upaya.shf");

        val myRule: ArchRule = noClasses()
            .that()
                .resideOutsideOfPackage("dev.upaya.shf.data.session_data.datastore..")
            .should()
                .accessClassesThat()
                .haveFullyQualifiedName(OffsetDateTime::class.java.name)
            .orShould()
                .accessClassesThat()
                .haveFullyQualifiedName(ZonedDateTime::class.java.name)
            .because("we only need offsets when storing in database")

        myRule.check(importedClasses);
    }
}
