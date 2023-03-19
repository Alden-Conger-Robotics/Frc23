package frc.robot.Subsystems;

import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.TrajectoryConstants;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;


/**
 * @param <Encoder>
 *
 */
public class DriveTrainSubsystem extends SubsystemBase {

    private MotorControllerGroup leftMotors;
    private MotorControllerGroup rightMotors;
    private CANSparkMax leftMaster;
    private CANSparkMax leftSlave;
    private CANSparkMax rightSlave;
    private CANSparkMax rightMaster;
    private final DifferentialDrive differentialDrive;

    // Odometry class for tracking robot pose
    //private final DifferentialDriveOdometry m_odometry;
    private RelativeEncoder m_leftEncoder, m_rightEncoder;

    /**
    *
    */
    public DriveTrainSubsystem() {

        rightMaster = new CANSparkMax(DriveConstants.RightMasterMotorId, MotorType.kBrushless);
        rightMaster.restoreFactoryDefaults();
        rightMaster.setInverted(false);
        rightMaster.setIdleMode(IdleMode.kBrake);

        rightSlave = new CANSparkMax(DriveConstants.RightSlaveMototId, MotorType.kBrushless);
        rightSlave.restoreFactoryDefaults();
        rightSlave.follow(rightMaster);
        rightSlave.setInverted(false);
        rightSlave.setIdleMode(IdleMode.kBrake);

        leftMaster = new CANSparkMax(DriveConstants.LeftMasterMotorId, MotorType.kBrushless);
        leftMaster.restoreFactoryDefaults();
        leftMaster.setInverted(true);
        leftMaster.setIdleMode(IdleMode.kBrake);

        leftSlave = new CANSparkMax(DriveConstants.LeftSlaveMotorId, MotorType.kBrushless);
        leftSlave.restoreFactoryDefaults();
        leftSlave.follow(leftMaster);
        leftSlave.setInverted(true);
        leftSlave.setIdleMode(IdleMode.kBrake);

        leftMotors = new MotorControllerGroup(leftMaster, leftSlave);
        rightMotors = new MotorControllerGroup(rightMaster, rightSlave);

        // differentialDrive = new DifferentialDrive(leftMaster, rightMaster);
        differentialDrive = new DifferentialDrive(leftMotors, rightMotors);

        m_leftEncoder = leftMaster.getEncoder();
        m_rightEncoder = rightMaster.getEncoder();
        m_leftEncoder.setPositionConversionFactor(TrajectoryConstants.kEncoderDistancePerPulse);
        m_rightEncoder.setPositionConversionFactor(TrajectoryConstants.kEncoderDistancePerPulse);
        m_leftEncoder.setVelocityConversionFactor(TrajectoryConstants.kEncoderDistancePerPulse / 60);
        m_rightEncoder.setVelocityConversionFactor(TrajectoryConstants.kEncoderDistancePerPulse / 60);

       // resetEncoders();
       //m_odometry = new DifferentialDriveOdometry(m_gyro.getRotation2d(), m_leftEncoder.getPosition(), m_rightEncoder.getPosition());

        // ensure the CAN messages are recieved by controllers prior to burning them 
        Timer.delay(.2);

        rightMaster.burnFlash();
        rightSlave.burnFlash();
        leftMaster.burnFlash();
        leftSlave.burnFlash();

    }

    /**
     * Returns the current wheel speeds of the robot.
     * 
     * @param <DifferentialDriveWheelSpeeds>
     *
     * @return The current wheel speeds.
     */
    public DifferentialDriveWheelSpeeds getWheelSpeeds() {
        SmartDashboard.putNumber("Left Drive Velocity", m_leftEncoder.getVelocity());
        SmartDashboard.putNumber("Right Drive Velocity", m_rightEncoder.getVelocity());
        return new DifferentialDriveWheelSpeeds(m_leftEncoder.getVelocity(), m_rightEncoder.getVelocity());
    }

    /**
     * Returns the heading of the robot.
     *
     * @return the robot's heading in degrees, from -180 to 180
     */
    public double getHeading() {
       // return m_gyro.getRotation2d().getDegrees() * -1;
       return 0.0;
    }

    /**
     * Returns the currently-estimated pose of the robot.
     *
     * @return The pose.
     */
   // public Pose2d getPose() {
        //return m_odometry.getPoseMeters();
   // }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Left Drive Position", m_leftEncoder.getPosition());
        SmartDashboard.putNumber("Right Drive Position", m_rightEncoder.getPosition());
        // This method will be called once per scheduler run
        // Update the odometry in the periodic block
    }

      /**
   * Controls the left and right sides of the drive directly with voltages.
   *
   * @param leftVolts the commanded left output
   * @param rightVolts the commanded right output
   */
  public void tankDriveVolts(double leftVolts, double rightVolts) {
    leftMotors.setVoltage(leftVolts);
    rightMotors.setVoltage(rightVolts);
    differentialDrive.feed();
  }

    /**
   * Resets the odometry to the specified pose.
   *
   * @param pose The pose to which to set the odometry.
   */
  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    //m_odometry.resetPosition(m_gyro.getRotation2d(), m_leftEncoder.getPosition(), m_rightEncoder.getPosition(), pose);
  }

  public void resetHeading() {
      //m_gyro.reset();
  }
    /** Resets the drive encoders to currently read a position of 0. */
    public void resetEncoders() {
        m_leftEncoder.setPosition(0);
        m_rightEncoder.setPosition(0);
      }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    public void arcadeDrive(final double speed, final double rotation) {
        differentialDrive.arcadeDrive(speed, rotation);
        SmartDashboard.putNumber("arcade speed", speed);
        SmartDashboard.putNumber("arcade rotation", rotation);

    }

    public void tankDrive(final double leftSpeed, final double rightSpeed) {
        differentialDrive.tankDrive(leftSpeed, rightSpeed);
        SmartDashboard.putNumber("leftspeed", leftSpeed);
        SmartDashboard.putNumber("Rightspeed", rightSpeed);
    }

    public void stop() {
        differentialDrive.stopMotor();
    }

}