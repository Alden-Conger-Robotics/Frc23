package frc.robot;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public final class Constants {
    public static final class ElectronicConstants{
        public static PneumaticsModuleType kPneumaticsModuleType = PneumaticsModuleType.CTREPCM;
    }
    public static final class DriveConstants{
        public static final double kMaxSpeedMetersPerSecond = 4.5;
        public static final double kMaxRotationalSpeed = 3* Math.PI;
		public static final int RightMasterMotorId = 2;
        public static final int RightSlaveMototId = 1;
        public static final int LeftMasterMotorId = 4;
        public static final int LeftSlaveMotorId = 3;
    }


public static final class OIConstants{
    public static final int kButtonControllerPort = 1;
    public static final int kDriverControllerPort = 0;
    public static final double kDriverControllerDeadband = 0.1;
    public static final int kDriverControllerZeroEncodersButton = 8;
    public static final int kDriverControllerZeroHeadingButton = 9;
}
public static final class ShooterConstants {

    public static final int kTopShooterMotorPort = 19;
    public static final int kBottomShooterMotorPort = 20;

    public static final double kShooterGearRatio = 7.13;
    public static final double kWheelDiameterMeters = 0.1016; // 4 inches
    public static final double kWheelCircumferenceMeters =
        kWheelDiameterMeters * Math.PI; // C = D * pi
    public static final double kShootertoMetersPerSecond =
        (10 * kWheelCircumferenceMeters) / (kShooterGearRatio * 2048);
    // Pre-programmed shoot values
    public static final double fenderShotSpeed = 0.57;
    public static final double tarmacShotSpeed = 0.6;
    public static final double lowShotSpeed = 0.2;
    public static int kMaxShooterSpeedMetersPerSecond = 0 - 9;

public static final class PathFinderConstants{
    // Score and Balance
    //public static final String 
}
}
//pathweaver
    public static final class TrajectoryConstants{

         //public static final double kEncoderPulsesPerREvolution = 42;
         public static final double kGearRatio = 10.71;
         public static final double kWheelDiameterMeters = 0.1524;
         public static final double kWheelCircumfrence = Math.PI * kWheelDiameterMeters;
         public static final double kEncoderDistancePerPulse = kWheelCircumfrence / kGearRatio; 

    }
    public static final class WristConstants{

        public static int wristMotorID = 1;
    }
    public static final class JointConstants{
        public static int JointMotorID = 0;
    }
    public static final class ArmConstants{
        public static int ArmMotorID = 2;
    }
}