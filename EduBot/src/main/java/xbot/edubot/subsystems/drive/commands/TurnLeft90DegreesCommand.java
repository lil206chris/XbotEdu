package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import org.opencv.core.Mat;

import xbot.common.command.BaseCommand;
import xbot.edubot.subsystems.drive.DriveSubsystem;
import xbot.edubot.subsystems.pose.PoseSubsystem;

public class TurnLeft90DegreesCommand extends BaseCommand {
    
    DriveSubsystem drive;
    PoseSubsystem pose;
    double origin;
    double oldAngle;
    double v;
    double angle;
    double goal;
    @Inject
    public TurnLeft90DegreesCommand(DriveSubsystem driveSubsystem, PoseSubsystem pose) {
        this.drive = driveSubsystem;
        this.pose = pose;
    }
    
    @Override
    public void initialize() {
        origin = pose.getCurrentHeading().getValue();
        angle = origin;
        oldAngle = origin;
        goal = normalizeAngle(origin + 90);
    }

    /**
        double pos = pose.getPosition();
        double scale =  (goal - pos) * .275 ;
        v =  (pos - oldPos);
        double power = scale * 3.75 - v * 4;
        drive.tankDrive(power, power);
        oldPos = pos;
     **/
    @Override
    public void execute() {
        
        angle  = pose.getCurrentHeading().getValue();
        
        double error = differenceAngle(goal , angle);
        double speed = differenceAngle(angle, oldAngle);
        System.out.println(String.format("Error:%f, Speed:%f", error, speed));
        double power = error * 0.05 - speed *0.432;
        System.out.println(angle);
        drive.tankDrive( -1 *power, power);
        oldAngle = angle;
        
        /** 
        System.out.println(angle);
        drive.tankDrive(.2, -.2);
        */
        

        
    }
    public static double differenceAngle(double Anchor, double Robot)
    {
        double a = Anchor - Robot;
        if(a > 180)
        {
            a += -360;
        }
        else if(a < -180)
        {
            a += 360;
        }
        return a;
    }
    public static double normalizeAngle(double Angle)
    {
        double actualAngle = Angle % 360;
        if(actualAngle < -180)
        {
            return 180  - (Math.abs(actualAngle) - 180);
        }
        else if(actualAngle > 180)
        {
            return -180 + (actualAngle - 180);
        }
        return actualAngle;
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
