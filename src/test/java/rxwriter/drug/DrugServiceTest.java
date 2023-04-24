package rxwriter.drug;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import rxwriter.drug.database.DrugRecord;
import rxwriter.drug.database.DrugSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DrugService should ")
class DrugServiceTest implements DrugSource {

    private DrugService drugService;

    @BeforeEach
    void setup() {
        drugService = new DrugService(this);
    }

    @Test
    @Tag("database")
    @DisplayName("return drugs from the database sorted by drug name")
     void drugsAreReturnedSorted() {
        List<DispensableDrug> foundDrugs = drugService.findDrugsStartingWith("as");
        assertNotNull(foundDrugs);
        assertEquals(2, foundDrugs.size());
        assertEquals("asmanex", foundDrugs.get(0).drugName());
        assertEquals("aspirin", foundDrugs.get(1).drugName());
    }

    @Nested
    @DisplayName("throw an illegal argument exception")
    class ThrowsExceptionTests {
        @Test
        @DisplayName("when passed a blank string for startingWith")
        void throwsExceptionOnBlankStartsWith() {
            assertThrows(IllegalArgumentException.class, ()->drugService.findDrugsStartingWith(" "));
        }

        @Test
        @DisplayName("when passed an empty string for startingWith")
        void throwsExceptionOnEmptyStartsWith() {
            assertThrows(IllegalArgumentException.class, ()->drugService.findDrugsStartingWith(""));
        }
    }


    @Test
    @Tag("database")
    @DisplayName("return dispensable drugs with all properties set correctly from database")
    void setsDrugPropertiesCorrectly() {
        List<DispensableDrug> foundDrugs = drugService.findDrugsStartingWith("aspirin");
        DispensableDrug drug = foundDrugs.get(0);

        DrugClassification[] expectedClassifications = new DrugClassification[] {
                DrugClassification.ANALGESIC, DrugClassification.PLATELET_AGGREGATION_INHIBITORS
        };
        assertAll("DispensableDrug properites",
                () -> assertEquals("aspirin", drug.drugName()),
                () -> assertFalse(drug.isControlled()),
                () -> assertEquals(2, drug.drugClassifications().length),
                () -> assertArrayEquals(expectedClassifications, drug.drugClassifications())
                );
    }

    @Override
    public List<DrugRecord> findDrugsStartingWith(String startingString) {
        List<DrugRecord> records = new ArrayList<>();
        if(startingString.equals("as")) {
            records.add(new DrugRecord("aspirin", new int[] {3645, 3530}, 0));
            records.add(new DrugRecord("asmanex", new int[] {301}, 0));
        } else if(startingString.equals("aspirin")) {
            records.add(new DrugRecord("aspirin", new int[] {3645, 3530}, 0));
        }

        return records;
    }
}