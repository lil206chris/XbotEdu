package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import xbot.common.command.BaseCommand;
import xbot.common.math.PIDFactory;
import xbot.common.math.PIDManager;
import xbot.edubot.subsystems.drive.DriveSubsystem;
import xbot.edubot.subsystems.pose.PoseSubsystem;

public class DriveToPositionCommand extends BaseCommand {

    DriveSubsystem drive;
    PoseSubsystem pose;
    double goal;
    boolean brake;
    double oldPos;
    double v;
	PIDManager pid;
    @Inject
    public DriveToPositionCommand(DriveSubsystem driveSubsystem, PoseSubsystem pose, PIDFactory pf) {
        this.drive = driveSubsystem;
        this.pose = pose;
        this.pid = pf.createPIDManager("DriveToPoint");
        pid.setEnableErrorThreshold(true);
        pid.setErrorThreshold(0.1);

        pid.setEnableDerivativeThreshold(true);
        pid.setDerivativeThreshold(0.1);
        pid.setP(.9);
        pid.setD(4);
    }
    
    public void setTargetPosition(double position) {
        goal= position;
        brake = true;
        oldPos = 0;
        // This method will be called by the test, and will give you a goal distance.
        // You'll need to remember this target position and use it in your calculations.
    }
    
    @Override
    public void initialize() {
        // If you have some one-time setup, do it here.
        pid.reset();
        brake = true;
    }

    @Override
    public void execute() {
        // Here you'll need to figure out a technique that:
        // - Gets the robot to move to the target position 
        // - Hint: use pose.getPosition() to find out where you are
        // - Gets the robot stop (or at least be moving really really slowly) at the target position
        // How you do this is up to you. If you get stuck, ask a mentor or student for some hints!
        /** 
        double pos = pose.getPosition();
        double scale =  (goal - pos) * .275 ;
        v =  (pos - oldPos);
        double power = scale * 3.75 - v * -4;
        drive.tankDrive(power, power);
        oldPos = pos;
        */
        double currentPosition = pose.getPosition();
        double power = pid.calculate(goal, currentPosition);
        drive.tankDrive(power, power);
    }
    
    @Override
    public boolean isFinished() {
        // Modify this to return true once you have met your goal, 
        // and you're moving fairly slowly (ideally stopped)
        /**
        if(((goal + .2)>pose.getPosition() && (goal - .2) < pose.getPosition()) && (Math.abs(v) < .1) )
        {
            return true;
        }
        return false;
        */
        return pid.isOnTarget();
    }

}
