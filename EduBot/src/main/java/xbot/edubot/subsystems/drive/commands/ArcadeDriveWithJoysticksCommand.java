package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import xbot.edubot.operator_interface.OperatorInterface;
import xbot.edubot.subsystems.drive.DriveSubsystem;

public class ArcadeDriveWithJoysticksCommand extends BaseCommand {
    DriveSubsystem drive;
    OperatorInterface operate;
    @Inject
    public ArcadeDriveWithJoysticksCommand(DriveSubsystem driveSubsystem) {
        drive = driveSubsystem;
    }
    
    @Override
    public void initialize() {
        
    }

    @Override
    public void execute() 
    {
        double leftValue = operate.gamepad.
        
    }

}
