package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import xbot.edubot.operator_interface.OperatorInterface;
import xbot.edubot.subsystems.drive.DriveSubsystem;

public class ArcadeDriveWithJoysticksCommand extends BaseCommand {
    DriveSubsystem drive;
    OperatorInterface operate;
    @Inject
    public ArcadeDriveWithJoysticksCommand(DriveSubsystem driveSubsystem, OperatorInterface oi) {
        drive = driveSubsystem;
        operate = oi;
        this.requires(drive);
    }
    
    @Override
    public void initialize() {
        
    }

    @Override
    public void execute() 
    {
        double x = operate.gamepad.getLeftVector().x;
        double y = operate.gamepad.getLeftVector().y;
        double leftPower;
        double rightPower;
        /**
        if(y != 0)
        {
            if(x != 0)
            {
                if(x>0)
                {
                    leftPower = y;
                    rightPower = y - x;
                }
                else
                {
                    leftPower = y - x;
                    rightPower = y;
                }
            }
            else
            {
                leftPower = y;
                rightPower = y;
            }
        }
        else
        {
            leftPower = x;
            rightPower = -1*x;
        }
        */
        leftPower = y + x;
        rightPower = y - x;
        drive.tankDrive(leftPower, rightPower);
    }

}
