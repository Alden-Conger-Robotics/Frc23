package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class WristSubsystem extends SubsystemBase{
    private Spark wristMotor;

    public WristSubsystem(){
        wristMotor = new Spark(Constants.WristConstants.wristMotorID);
    }
    public void Up(){
        wristMotor.set(.75);
    }
    public void Down(){
        wristMotor.set(-.75);
    }
    public void Stop(){
        wristMotor.set(0);
    }
}
