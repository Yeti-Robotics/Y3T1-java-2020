package frc.robot.autoRoutines;


import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.hopper.HopperInCommand;
import frc.robot.commands.intake.IntakeInCommand;
import frc.robot.commands.neck.MoveUpNeckCommand;
import frc.robot.commands.shooting.StartSpinCommand;
import frc.robot.subsystems.*;

public class ShootCommandGroup extends SequentialCommandGroup {
    public ShootCommandGroup(ShooterSubsystem shooterSubsystem, HopperSubsystem hopperSubsystem, NeckSubsystem neckSubsystem, DrivetrainSubsystem drivetrainSubsystem, IntakeSubsystem intakeSubsytem) {
            super();
        addCommands(
                new StartSpinCommand(shooterSubsystem).withTimeout(2),
//                new ParallelCommandGroup(
//                        new TurnToTargetCommand(drivetrainSubsystem),
//                        new SetHoodAngleCommand(shooterSubsystem)
//                ),
                new ParallelCommandGroup(
                        new HopperInCommand(hopperSubsystem),
                        new MoveUpNeckCommand(neckSubsystem),
                        new IntakeInCommand(intakeSubsytem)
                )
        );
    }
}

