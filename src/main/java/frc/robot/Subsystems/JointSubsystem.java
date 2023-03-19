package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class JointSubsystem extends SubsystemBase{
    private Spark jointMotor;

    public JointSubsystem(){
        jointMotor = new Spark(Constants.JointConstants.JointMotorID);
    }
    public void Up(){
        jointMotor.set(.75);
    }
    public void Down(){
        jointMotor.set(-.75);
    }
    public void Stop(){
        jointMotor.set(0);
    }
}
