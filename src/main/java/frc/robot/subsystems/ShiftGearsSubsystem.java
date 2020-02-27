package frc.robot.subsystems;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShiftGearsSubsystem extends SubsystemBase {
    private DoubleSolenoid shifter;

    public ShiftGearsSubsystem() {

        shifter = new DoubleSolenoid(Constants.SHIFTER_SOLENOID[0], Constants.SHIFTER_SOLENOID[1]);

    }
    public void shiftUp() {
        shifter.set(DoubleSolenoid.Value.kForward);
    }

    //Shifts the drive train into low gear
    public void shiftDown() {
        shifter.set(DoubleSolenoid.Value.kReverse);
    }

    //Finds out what position the shifter is currently in
    public DoubleSolenoid.Value getShifterPosition() {
        return shifter.get();
    }

}

