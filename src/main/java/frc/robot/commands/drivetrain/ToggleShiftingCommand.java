package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.ShiftGearsSubsystem;


public class ToggleShiftingCommand extends CommandBase {
    private final ShiftGearsSubsystem shiftGearsSubsystem;

    public ToggleShiftingCommand(ShiftGearsSubsystem shiftGearsSubsystem) {
        this.shiftGearsSubsystem = shiftGearsSubsystem;
        addRequirements(shiftGearsSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if (shiftGearsSubsystem.getShifterPosition() == DoubleSolenoid.Value.kForward) {
            shiftGearsSubsystem.shiftDown();
        } else {
            shiftGearsSubsystem.shiftUp();
        }
    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return true;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
