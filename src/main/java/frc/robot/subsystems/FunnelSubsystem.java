package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class FunnelSubsystem extends SubsystemBase {

    private VictorSPX funnelVictor;

    public FunnelSubsystem() {
        funnelVictor = new VictorSPX(Constants.FUNNEL_VICTOR);
    }

    public void funnelIn() {
        funnelVictor.set(ControlMode.PercentOutput,Constants.FUNNEL_IN_SPEED);
    }

    public void funnelOut() {
        funnelVictor.set(ControlMode.PercentOutput,-Constants.FUNNEL_IN_SPEED);
    }

    public void funnelStop() {
        funnelVictor.set(ControlMode.PercentOutput, 0);
    }

}

