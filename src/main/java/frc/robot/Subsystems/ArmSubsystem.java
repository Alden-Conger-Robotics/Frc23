package frc.robot.Subsystems;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmSubsystem extends SubsystemBase{
    private Spark armMotor;

    public ArmSubsystem(){
        armMotor = new Spark(Constants.ArmConstants.ArmMotorID);
    }
    public void Up(Joystick joystick){
        // SmartDashboard.putNumber("ArmSpeed", joystick.getY());
        // armMotor.set(joystick.getY());
        armMotor.set(.5);
    }
    public void Down(Joystick joystick){
        // SmartDashboard.putNumber("ArmSpeed", joystick.getY());
        // armMotor.set(joystick.getY());
        armMotor.set(-.5);
    }
    public void Stop(){
        armMotor.set(0);
    }
}
