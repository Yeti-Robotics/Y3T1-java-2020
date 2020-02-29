package frc.robot.autoRoutines;


import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.funnel.FunnelInCommand;
import frc.robot.commands.neck.MoveUpNeckCommand;
import frc.robot.commands.shooting.SetHoodAngleCommand;
import frc.robot.commands.shooting.SpinFlywheelsCommand;
import frc.robot.commands.shooting.StopSpinningCommand;
import frc.robot.subsystems.FunnelSubsystem;
import frc.robot.subsystems.NeckSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class AdjustHoodAndShootAutoCommandGroup extends SequentialCommandGroup {
    public AdjustHoodAndShootAutoCommandGroup(ShooterSubsystem shooterSubsystem, FunnelSubsystem funnelSubsystem, NeckSubsystem neckSubsystem) {
        super();
        addCommands(
                new SpinFlywheelsCommand(shooterSubsystem),
                new SetHoodAngleCommand(shooterSubsystem),
                new ParallelCommandGroup(
                        new FunnelInCommand(funnelSubsystem),
                        new MoveUpNeckCommand(neckSubsystem)
                ).withTimeout(6),
                new StopSpinningCommand(shooterSubsystem)
        );
    }
}