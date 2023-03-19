package frc.robot.Commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Subsystems.DriveTrainSubsystem;

/**
 *
 */
public class Drive extends CommandBase {

    private final DriveTrainSubsystem m_drivetrain;
private DoubleSupplier m_speed;
private DoubleSupplier m_rotation;

    public Drive(DriveTrainSubsystem subsystem, DoubleSupplier speed, DoubleSupplier rotation) {
        m_speed = speed;
        m_rotation = rotation;

        m_drivetrain = subsystem;
        addRequirements(m_drivetrain);

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        //double throttleSpeed = 1.0; //RobotContainer.oi.getJoystickDriveThrottleSpeed();

        m_drivetrain.arcadeDrive(m_speed.getAsDouble(), m_rotation.getAsDouble());
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}