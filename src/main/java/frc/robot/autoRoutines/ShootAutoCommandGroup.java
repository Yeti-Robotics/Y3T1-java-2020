package frc.robot.autoRoutines;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.shooting.SetHoodAngleCommand;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootAutoCommandGroup extends SequentialCommandGroup {
    public ShootAutoCommandGroup(ShooterSubsystem shooterSubsystem) {
        super();
        addCommands(new SetHoodAngleCommand(shooterSubsystem, ));
    }
}