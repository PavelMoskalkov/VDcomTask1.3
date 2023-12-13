package org.example.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputStringParserTest {
    @Test
    public void testParseQuestion() {
        String input = "2 kilobyte = ? bit";
        ConversionInfo result = InputStringParser.parseQuestion(input);

        assertEquals("kilobyte", result.getFromUnit());
        assertEquals("bit", result.getToUnit());
        assertEquals(2, result.getCoefficient());
    }

    @Test
    public void testParseString() {
        String input = "1024 byte = 1 kilobyte";
        ConversionInfo result = InputStringParser.parseString(input);

        assertEquals("byte", result.getFromUnit());
        assertEquals("kilobyte", result.getToUnit());
        assertEquals(1024, result.getCoefficient());
    }

}