package frc.robot.autoRoutines;


import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.hopper.HopperInCommand;
import frc.robot.commands.neck.MoveUpNeckCommand;
import frc.robot.commands.shooting.SetHoodAngleCommand;
import frc.robot.commands.shooting.StartSpinCommand;
import frc.robot.commands.shooting.StopSpinCommand;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.NeckSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AdjustHoodAndShootAutoCommandGroup extends SequentialCommandGroup {
    public AdjustHoodAndShootAutoCommandGroup(ShooterSubsystem shooterSubsystem, HopperSubsystem hopperSubsystem, NeckSubsystem neckSubsystem) {
        super();
        addCommands(
                new StartSpinCommand(shooterSubsystem),
                new SetHoodAngleCommand(shooterSubsystem),
                new ParallelCommandGroup(
                        new HopperInCommand(hopperSubsystem),
                        new MoveUpNeckCommand(neckSubsystem)
                ).withTimeout(6),
                new StopSpinCommand(shooterSubsystem)
        );
    }
}