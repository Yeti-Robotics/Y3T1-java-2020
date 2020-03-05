package frc.robot.autoRoutines;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drivetrain.DriveForDistanceCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.NeckSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootThenForwardCommandGroup extends SequentialCommandGroup {
    public ShootThenForwardCommandGroup(ShooterSubsystem shooterSubsystem, HopperSubsystem hopperSubsystem, NeckSubsystem neckSubsystem, DrivetrainSubsystem drivetrainSubsystem) {
       super();
        addCommands(
                new ShootCommandGroup(shooterSubsystem, hopperSubsystem, neckSubsystem, drivetrainSubsystem),
                new DriveForDistanceCommand(drivetrainSubsystem, 18, .5,  .5 )
        );
    }
}