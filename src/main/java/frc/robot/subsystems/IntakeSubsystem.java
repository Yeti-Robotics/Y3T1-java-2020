package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {

    public enum IntakeStatus{
        DOWN, UP
    }
    public static IntakeStatus intakeStatus;

    private VictorSPX intakeVictor;
    private DoubleSolenoid pistons;

    public IntakeSubsystem() {
        pistons = new DoubleSolenoid(Constants.INTAKE_PISTONS_SOLENOID[0], Constants.INTAKE_PISTONS_SOLENOID[1]);
        intakeVictor = new VictorSPX(Constants.INTAKE_VICTOR);
        intakeVictor.setInverted(true);
    }

    public void extend(){
        pistons.set(DoubleSolenoid.Value.kForward);
        intakeStatus = intakeStatus.DOWN;
    }

    public void retract(){
        pistons.set(DoubleSolenoid.Value.kReverse);
        intakeStatus = intakeStatus.UP;
    }

    public void rollIn(){
        intakeVictor.set(ControlMode.PercentOutput, Constants.ROLL_IN_SPEED);
    }

    public void rollOut(){
        intakeVictor.set(ControlMode.PercentOutput, Constants.ROLL_OUT_SPEED);
    }

    public void stopRoll(){
        intakeVictor.set(ControlMode.PercentOutput, 0);
    }

    public static IntakeStatus getIntakePosition(){
        return intakeStatus;
    }

}

