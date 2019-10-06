package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import xbot.edubot.subsystems.drive.DriveSubsystem;
import xbot.edubot.subsystems.pose.PoseSubsystem;

public class DriveToOrientationCommand extends BaseCommand{
    
    DriveSubsystem drive;
    PoseSubsystem pose;
    double origin;
    double oldAngle;
    double angle;
    double goal;
    
    @Inject
    public DriveToOrientationCommand(DriveSubsystem driveSubsystem, PoseSubsystem pose) {
        this.drive = driveSubsystem;
        this.pose = pose;
    }
    
    public void setTargetHeading(double heading) {
        goal = TurnLeft90DegreesCommand.normalizeAngle(heading);
    }
    
    @Override
    public void initialize() {
        origin = pose.getCurrentHeading().getValue();
        angle = origin;
        oldAngle = origin;
        
    }

    @Override
    public void execute() {
        // Here you'll need to figure out a technique that:
        // - Gets the robot to turn to the target orientation
        // - Gets the robot stop (or at least be moving really really slowly) at the target position
        
        angle  = pose.getCurrentHeading().getValue();
        double error = TurnLeft90DegreesCommand.differenceAngle(goal , angle);
        double speed = TurnLeft90DegreesCommand.differenceAngle(angle, oldAngle);
        System.out.println(String.format("Error:%f, Speed:%f", error, speed));
        double power = error * 0.05 - speed *0.432;
        System.out.println(angle);
        drive.tankDrive( -1 *power, power);
        oldAngle = angle;
        // How you do this is up to you. If you get stuck, ask a mentor or student for some hints!
    }

    @Override
    public boolean isFinished()
    {
        if(goal -.2 < angle && goal + .02 > angle)
        {
            return true;
        }
        return false;
    }
}
