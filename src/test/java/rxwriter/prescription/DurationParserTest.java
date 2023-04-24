package rxwriter.prescription;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class DurationParserTest {

    @ParameterizedTest
    @CsvSource({"2 weeks, 14", "1 month, 30"})
    public void parseDurationWithValidUnitAndQuantity(String durationString, int expectedDays) {
        assertEquals(expectedDays, DurationParser.parseDays(durationString));
    }
}
