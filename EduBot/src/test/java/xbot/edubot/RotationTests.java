package xbot.edubot;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import xbot.common.injection.BaseWPITest;
import xbot.edubot.subsystems.drive.commands.TurnLeft90DegreesCommand;

public class RotationTests extends BaseWPITest
{
    @Test
    public void testNormalizingLargePositveAngles()
    {
        double results = TurnLeft90DegreesCommand.normalizeAngle(900);
        assertEquals(180, results, 0.001);
        
    }
    @Test
    public void testNormalizingLargeNegativeAngles()
    {
        assertEquals(-170, TurnLeft90DegreesCommand.normalizeAngle(-890), 0.0001);
    }

    @Test
    public void testNormalizingNegativeSmallAngles()
    {
        assertEquals(160, TurnLeft90DegreesCommand.normalizeAngle(-200), 0.001);
    }
    @Test
    public void testNormalizingSmallPositiveAngles()
    {
        assertEquals(-160, TurnLeft90DegreesCommand.normalizeAngle(200), 0.001);
    }

}