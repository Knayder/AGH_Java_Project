package test.agh.cs.lab2;

import main.agh.cs.lab2.OptionsParser;
import main.agh.cs.lab2.utility.MoveDirection;
import org.junit.jupiter.api.Test;

import static main.agh.cs.lab2.utility.MoveDirection.*;
import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parse() {
        MoveDirection[] strs;   // co znaczy "strs"?
        MoveDirection[] dirs;

        strs = OptionsParser.parse( new String[]{"f", "r", "l", "b"} );
        dirs = new MoveDirection[]{FORWARD, RIGHT, LEFT, BACKWARD};
        assertEquals(strs.length, dirs.length);
        for(int i = 0; i<strs.length; ++i)
            assertEquals(strs[i], dirs[i]);

        strs = OptionsParser.parse( new String[]{"forward", "right", "left", "backward"} );
        dirs = new MoveDirection[]{FORWARD, RIGHT, LEFT, BACKWARD};
        assertEquals(strs.length, dirs.length);
        for(int i = 0; i<strs.length; ++i)
            assertEquals(strs[i], dirs[i]);

        strs = OptionsParser.parse( new String[]{"f", "rs", "l", "b"} );
        dirs = new MoveDirection[]{FORWARD, LEFT, BACKWARD};
        assertEquals(strs.length, dirs.length);
        for(int i = 0; i<strs.length; ++i)
            assertEquals(strs[i], dirs[i]);

        strs = OptionsParser.parse( new String[]{"f"} );
        dirs = new MoveDirection[]{FORWARD};
        assertEquals(strs.length, dirs.length);
        for(int i = 0; i<strs.length; ++i)
            assertEquals(strs[i], dirs[i]);

        strs = OptionsParser.parse( new String[]{} );
        dirs = new MoveDirection[]{};
        assertEquals(strs.length, dirs.length);
        for(int i = 0; i<strs.length; ++i)
            assertEquals(strs[i], dirs[i]);
    }
}