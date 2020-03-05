package frc.robot.autoRoutines;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.DriveForDistanceCommand;
import frc.robot.subsystems.*;

public class ShootThenForwardCommandGroup extends SequentialCommandGroup {
    public ShootThenForwardCommandGroup(ShooterSubsystem shooterSubsystem, HopperSubsystem hopperSubsystem, NeckSubsystem neckSubsystem, DrivetrainSubsystem drivetrainSubsystem, IntakeSubsystem intakeSubsystem) {
       super();
        addCommands(
                new ShootNoTurnCommandGroup(shooterSubsystem, hopperSubsystem, neckSubsystem, intakeSubsystem),
                new DriveForDistanceCommand(drivetrainSubsystem, 30, .5,  .5 )
        );
    }
}