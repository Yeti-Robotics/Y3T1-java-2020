package frc.robot.commands.shooting;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;


public class SetHoodAngleCommand extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private double hoodAngle;

    public SetHoodAngleCommand(ShooterSubsystem shooterSubsystem) {
        this.shooterSubsystem = shooterSubsystem;
        addRequirements(shooterSubsystem);

    }

    @Override
    public void initialize() {
        hoodAngle = shooterSubsystem.calcHoodAngle();
    }

    @Override
    public void execute() {
        if(hoodAngle != Double.NaN){
            shooterSubsystem.setHoodAngle(hoodAngle);
        }
    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        System.out.println("is sethoodanglecommand done?" + (shooterSubsystem.getHoodAngle() >= hoodAngle));
        return shooterSubsystem.getHoodAngle() >= hoodAngle;
    }

    @Override
    public void end(boolean interrupted) {

    }
}
