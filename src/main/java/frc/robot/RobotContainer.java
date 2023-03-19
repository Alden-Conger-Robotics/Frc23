package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Commands.Drive;
import frc.robot.Commands.Autonomous.AutoBalance;
import frc.robot.Constants.OIConstants;
import frc.robot.Subsystems.ArmSubsystem;
import frc.robot.Subsystems.DriveTrainSubsystem;
import frc.robot.Subsystems.GripperSubsystem;
import frc.robot.Subsystems.JointSubsystem;
import frc.robot.Subsystems.WristSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class RobotContainer {
    private static RobotContainer m_robotContainer = new RobotContainer();
    // The robot's subsystems
    private final DriveTrainSubsystem m_robotDriveSubsystem = new DriveTrainSubsystem();
    private final WristSubsystem m_wristSubsystem = new WristSubsystem();
    private final JointSubsystem m_jointSubsystem = new JointSubsystem();
    private final ArmSubsystem m_armSubsystem = new ArmSubsystem();
    private final GripperSubsystem m_gripperSubsytem = new GripperSubsystem();

    private final Command autobalance = new AutoBalance();

    private final SendableChooser<Command> autoChooser = new SendableChooser<>();

    private final Joystick m_driverController = new Joystick(OIConstants.kDriverControllerPort);
    private final Joystick operatorJoystick = new Joystick(OIConstants.kButtonControllerPort);

    private RobotContainer() {

        configureButtonBindings();
        m_robotDriveSubsystem.setDefaultCommand(
                new Drive(m_robotDriveSubsystem, () -> m_driverController.getY(), () -> m_driverController.getX()));
    }

    public static RobotContainer getInstance() {
        return m_robotContainer;
    }

    private void configureButtonBindings() {
        JoystickButton wristTurnleft = new JoystickButton(operatorJoystick, 6);
        JoystickButton wristTurnright = new JoystickButton(operatorJoystick, 4);

        wristTurnleft.whileTrue(new StartEndCommand(() -> m_wristSubsystem.Down(), () -> m_wristSubsystem.Stop()));
        wristTurnright.whileTrue(new StartEndCommand(() -> m_wristSubsystem.Up(), () -> m_wristSubsystem.Stop()));

        JoystickButton jointTurnleft = new JoystickButton(operatorJoystick, 3);
        JoystickButton jointTurnright = new JoystickButton(operatorJoystick, 5);

        jointTurnleft.whileTrue(new StartEndCommand(() -> m_jointSubsystem.Down(), () -> m_jointSubsystem.Stop()));
        jointTurnright.whileTrue(new StartEndCommand(() -> m_jointSubsystem.Up(), () -> m_jointSubsystem.Stop()));

        Trigger armUp = new Trigger(() -> operatorJoystick.getY() > 0.15);
        Trigger armDown = new Trigger(() -> operatorJoystick.getY() < -0.15);

        armUp.whileTrue(new StartEndCommand(() -> m_armSubsystem.Up(operatorJoystick), () -> m_armSubsystem.Stop()));
        armDown.whileTrue(new StartEndCommand(() -> m_armSubsystem.Down(operatorJoystick), () -> m_armSubsystem.Stop()));


        JoystickButton gripperOpenButton = new JoystickButton(operatorJoystick, 1);
        JoystickButton gripperCloseButton = new JoystickButton(operatorJoystick, 2);
        gripperOpenButton.onTrue(new InstantCommand(() -> m_gripperSubsytem.open()));     
        gripperCloseButton.onTrue(new InstantCommand(() -> m_gripperSubsytem.close()));
    }

    public Command getAutonomousCommand() {
        return autoChooser.getSelected();
    }
}