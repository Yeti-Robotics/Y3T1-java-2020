package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class NeckSubsystem extends SubsystemBase {
    private TalonSRX neckTalon;
    private DigitalInput lowerBeamBreak;
    private DigitalInput upperBeamBreak;

    public NeckSubsystem() {
      neckTalon = new TalonSRX(Constants.NECK_TALON);
      lowerBeamBreak = new DigitalInput(Constants.LOWER_BEAM_BREAK);
      upperBeamBreak = new DigitalInput(Constants.UPPER_BEAM_BREAK);
    }

    public void moveUp(){
        neckTalon.set(ControlMode.PercentOutput, Constants.NECK_UP_SPEED);
    }

    public void moveDown(){
        neckTalon.set(ControlMode.PercentOutput, Constants.NECK_DOWN_SPEED);
    }

    public void stopNeck(){
        neckTalon.set(ControlMode.PercentOutput, 0);
    }

    public boolean getLowerBeamBreak() {
        return lowerBeamBreak.get();
    }

    public boolean getUpperBeamBreak() {
        return upperBeamBreak.get();
    }

}

