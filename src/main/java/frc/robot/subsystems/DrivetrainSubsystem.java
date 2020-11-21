package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Encoder;

public class DrivetrainSubsystem extends SubsystemBase {
    private TalonSRX leftTalon, rightTalon;

    private VictorSPX leftVictor1, leftVictor2, rightVictor1, rightVictor2;
    
    private Encoder leftEncoder, rightEncoder;

    public DrivetrainSubsystem() {
        
        leftTalon = new TalonSRX(Constants.LEFT_TALON);
        leftVictor1 = new VictorSPX(Constants.LEFT_VICTOR_1);
        leftVictor2 = new VictorSPX(Constants.LEFT_VICTOR_2);
        rightTalon = new TalonSRX(Constants.RIGHT_TALON);
        rightVictor1 = new VictorSPX(Constants.RIGHT_VICTOR_1);
        rightVictor2 = new VictorSPX(Constants.RIGHT_VICTOR_2);

//         leftTalon.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor,0,0);
//         rightTalon.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor,0,0);
        
        leftEncoder = new Encoder(Constants.LEFT_ENCODER_A, Constants.LEFT_ENCODER_B);
        rightEncoder = new Encoder(Constants.RIGHT_ENCODER_A, Constants.RIGHT_ENCODER_B);

        // metalShavings = 42069;

        leftTalon.setNeutralMode(NeutralMode.Brake);
        leftVictor1.setNeutralMode(NeutralMode.Brake);
        leftVictor2.setNeutralMode(NeutralMode.Brake);
        rightTalon.setNeutralMode(NeutralMode.Brake);
        rightVictor1.setNeutralMode(NeutralMode.Brake);
        rightVictor2.setNeutralMode(NeutralMode.Brake);

        rightTalon.setInverted(true);
        rightVictor1.setInverted(true);
        rightVictor2.setInverted(true);
//        leftVictor1.set(VictorSPXControlMode.Follower, Constants.LEFT_VICTOR_1);
//        leftVictor2.set(VictorSPXControlMode.Follower, Constants.LEFT_VICTOR_2);
//        rightVictor1.set(VictorSPXControlMode.Follower, Constants.RIGHT_VICTOR_1);
//        rightVictor2.set(VictorSPXControlMode.Follower, Constants.RIGHT_VICTOR_2);
                
    }

    public void drive(double leftPower, double rightPower) {
        leftTalon.set(ControlMode.PercentOutput, leftPower);
        leftVictor1.set(ControlMode.PercentOutput, leftPower);
        leftVictor2.set(ControlMode.PercentOutput, leftPower);
        rightTalon.set(ControlMode.PercentOutput, rightPower);
        rightVictor1.set(ControlMode.PercentOutput, rightPower);
        rightVictor2.set(ControlMode.PercentOutput, rightPower);
    }

    public void stopDrive() {
        leftTalon.set(ControlMode.PercentOutput, 0);
        leftVictor1.set(ControlMode.PercentOutput, 0);
        leftVictor2.set(ControlMode.PercentOutput, 0);
        rightTalon.set(ControlMode.PercentOutput, 0);
        rightVictor1.set(ControlMode.PercentOutput, 0);
        rightVictor2.set(ControlMode.PercentOutput, 0);
    }

    public double getLeftEncoder() {
        return (leftEncoder.getRaw() * Constants.DISTANCE_PER_PULSE);
    }

    public double getRightEncoder() {
        return (rightEncoder.getRaw() * Constants.DISTANCE_PER_PULSE);
    }

    public double getAverageEncoder() {
        return (getLeftEncoder() + getRightEncoder()) / 2;
    }

    public void resetEncoder() {
        leftEncoder.reset();
        rightEncoder.reset();
    }

    public void driveWithMinPower(double leftPower, double rightPower, double minAbsolutePower) {
        double realLeftPower = (leftPower / Math.abs(leftPower)) * Math.max(Math.abs(leftPower), minAbsolutePower);
        double realRightPower = (rightPower / Math.abs(rightPower)) * Math.max(Math.abs(rightPower), minAbsolutePower);
    }

    @Override
    public void periodic() {

        // System.out.println("drivetrain periodic");

        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");
        NetworkTableEntry ta = table.getEntry("ta");

//read values periodically
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);

        // post to smart dashboard periodically
//        SmartDashboard.putNumber("LimelightX", x);
//        SmartDashboard.putNumber("LimelightY", y);
//        SmartDashboard.putNumber("LimelightArea", area);
        System.out.println(getRightEncoder());
        System.out.println(getLeftEncoder());
    }

}

