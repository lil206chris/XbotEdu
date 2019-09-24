package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import xbot.edubot.subsystems.drive.DriveSubsystem;
import xbot.edubot.subsystems.pose.PoseSubsystem;

public class DriveToPositionCommand extends BaseCommand {

    DriveSubsystem drive;
    PoseSubsystem pose;
    double goal;
    @Inject
    public DriveToPositionCommand(DriveSubsystem driveSubsystem, PoseSubsystem pose) {
        this.drive = driveSubsystem;
        this.pose = pose;
    }
    
    public void setTargetPosition(double position) {
        goal= position;
        // This method will be called by the test, and will give you a goal distance.
        // You'll need to remember this target position and use it in your calculations.
    }
    
    @Override
    public void initialize() {
        // If you have some one-time setup, do it here.
    }

    @Override
    public void execute() {
        // Here you'll need to figure out a technique that:
        // - Gets the robot to move to the target position 
        // - Hint: use pose.getPosition() to find out where you are
        // - Gets the robot stop (or at least be moving really really slowly) at the target position
        // How you do this is up to you. If you get stuck, ask a mentor or student for some hints!
        if(isFinished())
        {
            drive.tankDrive(-1, -1);
        }
        else
        {
            drive.tankDrive(1, 1);
            if((goal - 2) < pose.getPosition() && goal > pose.getPosition())
            {
                drive.tankDrive(.01, .01);
            }
        }
    }
    
    @Override
    public boolean isFinished() {
        // Modify this to return true once you have met your goal, 
        // and you're moving fairly slowly (ideally stopped)
        if((goal + .1)>pose.getPosition() && (goal - .3) < pose.getPosition())
        {
            drive.tankDrive(-1, -1);
            return true;
        }
        return false;
    }

}
