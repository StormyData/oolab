package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parse() {
        OptionsParser op=new OptionsParser();
        MoveDirection[] expected =new MoveDirection[]{MoveDirection.RIGHT,MoveDirection.FORWARD,MoveDirection.FORWARD,MoveDirection.LEFT,MoveDirection.BACKWARD};
        String[] args= new String[]{"right","f","forward","left","backward"};
        assertArrayEquals(expected,op.parse(args));
        String[] args2= new String[]{"right","f","forward","fnfewiuf","awnda","left","backward"};
        assertThrows(IllegalArgumentException.class,() ->op.parse(args2),"fnfewiuf is not legal move specification");
    }
}