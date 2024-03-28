package dev.upaya.shf.data.session_history

import androidx.room.Entity
import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes
import org.junit.Test


class SessionHistoryArchTests {

    @Test
    fun `Room entities should not be accessed from outside package`() {
        val importedClasses = ClassFileImporter().importPackages("dev.upaya.shf");

        val myRule: ArchRule = classes()
            .that()
                .areAnnotatedWith(Entity::class.java)
            .should()
                .onlyBeAccessed()
                .byClassesThat()
                .resideInAPackage("dev.upaya.shf.data.session_history..")

        myRule.check(importedClasses);
    }
}
