package frc.robot.autoRoutines;


import edu.wpi.first.wpilibj.command.StartCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
//import frc.robot.commands.drivetrain.DriveForDistanceCommand;
import frc.robot.commands.hopper.HopperInCommand;
import frc.robot.commands.intake.ExtendIntakeCommand;
import frc.robot.commands.intake.IntakeInCommand;
import frc.robot.commands.intake.RetractIntakeCommand;
import frc.robot.commands.neck.MoveUpNeckCommand;
import frc.robot.commands.shooting.SetHoodAngleCommand;
import frc.robot.commands.shooting.SpinWithStopCommand;
import frc.robot.commands.shooting.StartSpinCommand;
import frc.robot.commands.shooting.StopSpinCommand;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.NeckSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootNoTurnCommandGroup extends SequentialCommandGroup {
    public ShootNoTurnCommandGroup(ShooterSubsystem shooterSubsystem, HopperSubsystem hopperSubsystem, NeckSubsystem neckSubsystem, IntakeSubsystem intakeSubsystem) {
        super();
        addCommands(
                        new SpinWithStopCommand(shooterSubsystem),
                        new ExtendIntakeCommand(intakeSubsystem),
//                      new SetHoodAngleCommand(shooterSubsystem),
                        new ParallelCommandGroup(
                                new HopperInCommand(hopperSubsystem),
                                new MoveUpNeckCommand(neckSubsystem),
                                new IntakeInCommand(intakeSubsystem)
                        )
        );
    }
}