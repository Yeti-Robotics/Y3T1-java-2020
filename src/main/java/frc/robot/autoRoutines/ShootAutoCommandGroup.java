package frc.robot.autoRoutines;


import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.TurnToTargetCommand;
import frc.robot.commands.funnel.FunnelInCommand;
import frc.robot.commands.neck.MoveUpNeckCommand;
import frc.robot.commands.shooting.SetHoodAngleCommand;
import frc.robot.commands.shooting.SpinFlywheelsCommand;
import frc.robot.commands.shooting.StopSpinningCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.FunnelSubsystem;
import frc.robot.subsystems.NeckSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootAutoCommandGroup extends SequentialCommandGroup {
    public ShootAutoCommandGroup(ShooterSubsystem shooterSubsystem, FunnelSubsystem funnelSubsystem, NeckSubsystem neckSubsystem, DrivetrainSubsystem drivetrainSubsystem) {
        super();
        addCommands(
                new SpinFlywheelsCommand(shooterSubsystem),
                new ParallelCommandGroup(
                        new TurnToTargetCommand(drivetrainSubsystem),
                        new SetHoodAngleCommand(shooterSubsystem, shooterSubsystem.calcHoodAngle())
                ),
                new ParallelCommandGroup(
                        new FunnelInCommand(funnelSubsystem),
                        new MoveUpNeckCommand(neckSubsystem)
                ),
                new StopSpinningCommand(shooterSubsystem)
        );
    }
}

