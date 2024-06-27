package org.formation.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DateUtilTest {
    @Test
    public void testConvertToDate() {
        LocalDate localDate = LocalDate.of(2023, 6, 24);
        Date date = DateUtil.convertToDate(localDate);

        assertNotNull(date);
        assertEquals(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli(), date.getTime());
    }
}
