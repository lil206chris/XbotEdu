package xbot.edubot;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import xbot.common.injection.BaseWPITest;
import xbot.edubot.subsystems.drive.commands.TurnLeft90DegreesCommand;

public class SmallestAngleDif extends BaseWPITest
{
    @Test
    public void test()
    {
        double aa = TurnLeft90DegreesCommand.differenceAngle(-150, 0);
        assertEquals(-150, aa, .001);
    
    }
    @Test
    public void test1()
    {
        double aa = TurnLeft90DegreesCommand.differenceAngle(120, 110);
        assertEquals(10, aa, .001);
    }
}