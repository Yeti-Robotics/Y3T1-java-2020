package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimberSubsystem extends SubsystemBase {

    private TalonSRX climberTalon1;
    private TalonSRX climberTalon2;

    public ClimberSubsystem() {
        climberTalon1 = new TalonSRX(Constants.CLIMBER_TALON_1);
        climberTalon2 = new TalonSRX(Constants.CLIMBER_TALON_2);
    }

    public void climbUp(){
        climberTalon1.set(ControlMode.PercentOutput, Constants.CLIMBER_SPEED);
        climberTalon2.set(ControlMode.PercentOutput, Constants.CLIMBER_SPEED);
    }

    public void climbDown(){
        climberTalon1.set(ControlMode.PercentOutput, -Constants.CLIMBER_SPEED);
        climberTalon2.set(ControlMode.PercentOutput, -Constants.CLIMBER_SPEED);
    }

    public void stopClimb(){
        climberTalon1.set(ControlMode.PercentOutput, 0);
        climberTalon2.set(ControlMode.PercentOutput, 0);
    }

}

