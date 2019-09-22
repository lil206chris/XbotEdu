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
        double leftPower;
        double rightPower;
        if(operate.gamepad.getLeftVector().x <= 0)
        {
            leftPower =(-1 * operate.gamepad.getLeftVector().y) + operate.gamepad.getLeftVector().x;
            rightPower = (-1 * operate.gamepad.getLeftVector().y) - operate.gamepad.getLeftVector().x;
        }
        else
        {
            leftPower = (-1 * operate.gamepad.getLeftVector().y) - operate.gamepad.getLeftVector().x;
            rightPower = (-1 * operate.gamepad.getLeftVector().y) + operate.gamepad.getLeftVector().x;
        }
        double max = Math.abs(leftPower);
        if(max < Math.abs(rightPower))
        {
            max = Math.abs(rightPower);
        }
        if(max > 1)
        {
            leftPower /= max;
            rightPower /= max;
        }
        drive.tankDrive(1.0, 1.0);
    }

}
