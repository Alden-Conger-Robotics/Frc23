package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class GripperSubsystem extends SubsystemBase {

    // TODO - Make sure the 0 and 1 match up with where you plugged the solenoid wires into the pneumatics control module.
    DoubleSolenoid m_gripperSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);

    public GripperSubsystem() {
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public void close() {
        m_gripperSolenoid.set(Value.kForward);
    }

    public void open() {
        m_gripperSolenoid.set(Value.kReverse);
    }
}