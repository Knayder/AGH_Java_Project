package agh.cs.lab2.utility;


import agh.cs.lab2.utility.MoveDirection;
import agh.cs.lab2.utility.OptionsParser;
import org.junit.jupiter.api.Test;

import static agh.cs.lab2.utility.MoveDirection.*;
import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parse() {
        MoveDirection[] parsed;
        MoveDirection[] correct;

        parsed = OptionsParser.parse( new String[]{"f", "r", "l", "b"} );
        correct = new MoveDirection[]{FORWARD, RIGHT, LEFT, BACKWARD};
        assertEquals(parsed.length, correct.length);
        for(int i = 0; i<parsed.length; ++i)
            assertEquals(parsed[i], correct[i]);

        parsed = OptionsParser.parse( new String[]{"forward", "right", "left", "backward"} );
        correct = new MoveDirection[]{FORWARD, RIGHT, LEFT, BACKWARD};
        assertEquals(parsed.length, correct.length);
        for(int i = 0; i<parsed.length; ++i)
            assertEquals(parsed[i], correct[i]);

        parsed = OptionsParser.parse( new String[]{"f", "l", "b"} );
        correct = new MoveDirection[]{FORWARD, LEFT, BACKWARD};
        assertEquals(parsed.length, correct.length);
        for(int i = 0; i<parsed.length; ++i)
            assertEquals(parsed[i], correct[i]);

        parsed = OptionsParser.parse( new String[]{"f"} );
        correct = new MoveDirection[]{FORWARD};
        assertEquals(parsed.length, correct.length);
        for(int i = 0; i<parsed.length; ++i)
            assertEquals(parsed[i], correct[i]);

        parsed = OptionsParser.parse( new String[]{} );
        correct = new MoveDirection[]{};
        assertEquals(parsed.length, correct.length);
        for(int i = 0; i<parsed.length; ++i)
            assertEquals(parsed[i], correct[i]);

        parsed = OptionsParser.parse( new String[]{"forwardsss", "right", "left", "backward"} );
        correct = new MoveDirection[]{FORWARD, RIGHT, LEFT, BACKWARD};
        assertEquals(parsed.length, correct.length);
        for(int i = 0; i<parsed.length; ++i)
            assertEquals(parsed[i], correct[i]);
    }
}