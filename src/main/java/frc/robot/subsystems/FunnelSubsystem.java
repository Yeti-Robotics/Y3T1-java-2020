package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class FunnelSubsystem extends SubsystemBase {

    private TalonSRX funnelTalonLeft, funnelTalonRight;

    public FunnelSubsystem() {
        funnelTalonLeft = new TalonSRX(Constants.HOPPER_LEFT_TALON);
        funnelTalonRight = new TalonSRX(Constants.HOPPER_RIGHT_TALON);
    }

    public void funnelIn() {
        funnelTalonLeft.set(ControlMode.PercentOutput, Constants.FUNNEL_IN_SPEED);
        funnelTalonRight.set(ControlMode.PercentOutput, -Constants.FUNNEL_IN_SPEED);
    }

    public void funnelOut() {
        funnelTalonLeft.set(ControlMode.PercentOutput, Constants.FUNNEL_OUT_SPEED);
        funnelTalonRight.set(ControlMode.PercentOutput, -Constants.FUNNEL_OUT_SPEED);
    }

    public void funnelStop() {
        funnelTalonLeft.set(ControlMode.PercentOutput, 0);
        funnelTalonRight.set(ControlMode.PercentOutput, 0);
    }
}

