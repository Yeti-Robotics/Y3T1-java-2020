package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {

// Any variables/fields used in the constructor must appear before the "INSTANCE" variable
// so that they are initialized before the constructor is called.



    private VictorSPX intakeVictor;

    public IntakeSubsystem() {
        intakeVictor = new VictorSPX(Constants.INTAKE_VICTOR);
    }

    public void rollIn(){
        intakeVictor.set(ControlMode.PercentOutput, Constants.ROLL_IN_SPEED);
    }

    public void rollOut(){
        intakeVictor.set(ControlMode.PercentOutput, -Constants.ROLL_IN_SPEED);
    }

    public void stopRoll(){
        intakeVictor.set(ControlMode.PercentOutput, 0);
    }

}

