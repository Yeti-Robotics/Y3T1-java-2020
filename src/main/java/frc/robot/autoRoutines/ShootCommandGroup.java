package frc.robot.autoRoutines;


import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.TurnToTargetCommand;
import frc.robot.commands.hopper.HopperInCommand;
import frc.robot.commands.neck.MoveUpNeckCommand;
import frc.robot.commands.shooting.SetHoodAngleCommand;
import frc.robot.commands.shooting.StartSpinCommand;
import frc.robot.commands.shooting.StopSpinCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.NeckSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootCommandGroup extends SequentialCommandGroup {
    public ShootCommandGroup(ShooterSubsystem shooterSubsystem, HopperSubsystem hopperSubsystem, NeckSubsystem neckSubsystem, DrivetrainSubsystem drivetrainSubsystem) {
            super();
        addCommands(
                new StartSpinCommand(shooterSubsystem),
                new ParallelCommandGroup(
                        new TurnToTargetCommand(drivetrainSubsystem),
                        new SetHoodAngleCommand(shooterSubsystem)
                ),
                new ParallelCommandGroup(
                        new HopperInCommand(hopperSubsystem),
                        new MoveUpNeckCommand(neckSubsystem)
                ).withTimeout(6),
                new StopSpinCommand(shooterSubsystem)
        );
    }
}

