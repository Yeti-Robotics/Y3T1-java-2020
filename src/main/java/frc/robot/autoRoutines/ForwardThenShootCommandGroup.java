package frc.robot.autoRoutines;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.DriveForDistanceCommand;
import frc.robot.commands.intake.RetractIntakeCommand;
import frc.robot.commands.shooting.StopSpinCommand;
import frc.robot.subsystems.*;

public class ForwardThenShootCommandGroup extends SequentialCommandGroup {
    public ForwardThenShootCommandGroup(ShooterSubsystem shooterSubsystem, HopperSubsystem hopperSubsystem, NeckSubsystem neckSubsystem, DrivetrainSubsystem drivetrainSubsystem, IntakeSubsystem intakeSubsystem) {
        super();
        addCommands(
                new RetractIntakeCommand(intakeSubsystem),
                new DriveForDistanceCommand(drivetrainSubsystem, 72, .5,  .5 ),
                new ShootNoTurnCommandGroup(shooterSubsystem, hopperSubsystem, neckSubsystem, intakeSubsystem).withTimeout(5),
                new StopSpinCommand(shooterSubsystem)
        );
    }
}