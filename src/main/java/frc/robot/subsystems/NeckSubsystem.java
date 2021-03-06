package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class NeckSubsystem extends SubsystemBase {
    private TalonSRX neckBeltTalon;
    private TalonSRX neckRollerTalon;
    private DigitalInput lowerBeamBreak;
    private DigitalInput upperBeamBreak;

    public NeckSubsystem() {
      neckBeltTalon = new TalonSRX(Constants.NECK_BELT_TALON);
      neckRollerTalon = new TalonSRX(Constants.NECK_ROLLER_TALON);
      lowerBeamBreak = new DigitalInput(Constants.LOWER_BEAM_BREAK);
      upperBeamBreak = new DigitalInput(Constants.UPPER_BEAM_BREAK);
    }

    public void moveUp(){
        neckBeltTalon.set(ControlMode.PercentOutput, Constants.NECK_UP_SPEED);
        neckRollerTalon.set(ControlMode.PercentOutput, Constants.NECK_UP_SPEED);
    }

    public void moveDown(){
        neckBeltTalon.set(ControlMode.PercentOutput, Constants.NECK_DOWN_SPEED);
        neckRollerTalon.set(ControlMode.PercentOutput, Constants.NECK_DOWN_SPEED);
    }

    public void stopNeck(){
        neckBeltTalon.set(ControlMode.PercentOutput, 0);
        neckRollerTalon.set(ControlMode.PercentOutput, 0);
    }


    public boolean getLowerBeamBreak() {
        return lowerBeamBreak.get();
    }

    public boolean getUpperBeamBreak() {
        return upperBeamBreak.get();
    }

}

