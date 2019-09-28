package xbot.edubot.subsystems.drive.commands;

import com.google.inject.Inject;

import org.opencv.core.Mat;

import xbot.common.command.BaseCommand;
import xbot.edubot.subsystems.drive.DriveSubsystem;
import xbot.edubot.subsystems.pose.PoseSubsystem;

public class TurnLeft90DegreesCommand extends BaseCommand {
    
    DriveSubsystem drive;
    PoseSubsystem pose;
    double orgin;
    double oldAngle;
    double v;
    @Inject
    public TurnLeft90DegreesCommand(DriveSubsystem driveSubsystem, PoseSubsystem pose) {
        this.drive = driveSubsystem;
        this.pose = pose;
    }
    
    @Override
    public void initialize() {
        orgin = pose.getCurrentHeading().getValue();
        oldAngle = orgin;
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
        double angle  = pose.getCurrentHeading().getValue();
        double aa = normalizeAngle((orgin - 90) - angle);
        double scale = (90 - (Math.abs(orgin) - Math.abs(angle)));
        v = (Math.abs(oldAngle) - Math.abs(angle))
        
    }
    public static double differenceInAngles(Double a1, double a2)
    {

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


}
