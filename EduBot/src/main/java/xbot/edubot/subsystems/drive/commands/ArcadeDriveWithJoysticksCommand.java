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
        if(operate.gamepad.getLeftVector().x = 0)
        {
            double leftPower = operate.gamepad.getLeftVector().y * (1 + operate.gamepad.getLeftVector().x);
            double rightPower = operate.gamepad.getLeftVector().y * (1 - operate.gamepad.getLeftVector().x);
        }
        else if(operate)
        double leftPower = operate.gamepad.getLeftVector().y * (1 + operate.gamepad.getLeftVector().x);
        double rightPower = operate.gamepad.getLeftVector().y * (1 - operate.gamepad.getLeftVector().x);
        drive.tankDrive(leftPower, rightPower);
        
    }

}
