package xbot.edubot.subsystems.drive;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import edu.wpi.first.wpilibj.MockDistanceSensor;
import xbot.common.command.BaseSubsystem;
import xbot.common.controls.actuators.XCANTalon;
import xbot.common.injection.wpi_factories.CommonLibFactory;
import xbot.edubot.MockHeadingSensor;

@Singleton
public class DriveSubsystem extends BaseSubsystem {
    
    public XCANTalon frontLeft;
    public XCANTalon frontRight;
    public XCANTalon rearLeft;
    public XCANTalon rearRight;
    public boolean percisionMode = false;
    public double Power = 1.0;
    @Inject
    public DriveSubsystem(CommonLibFactory factory) {
        // instantiate speed controllers and sensors here, save them as class members        
        frontLeft = factory.createCANTalon(1);
        rearLeft = factory.createCANTalon(3);
        frontRight = factory.createCANTalon(2);
        rearRight = factory.createCANTalon(4);
    }
    
    public void tankDrive(double leftPower, double rightPower) {
        // You'll need to take these power values and assign them to all of the motors. As
        // an example, here is some code that has the frontLeft motor to spin according to
        // the value of leftPower:
        frontLeft.simpleSet(leftPower * Power);
        rearLeft.simpleSet(leftPower * Power);
        frontRight.simpleSet(rightPower * Power);
        rearRight.simpleSet(rightPower * Power);
    }
    public void SetPrecisionMode(double x)
    {
        Power = x;
    }
    public void TogglePrecisionMode()
    {
        if(!percisionMode)
        {
            SetPrecisionMode(0.5);
            percisionMode = !percisionMode;
        }
        else
        {
            SetPrecisionMode(1);
            percisionMode = !percisionMode;
        }
    }
}
