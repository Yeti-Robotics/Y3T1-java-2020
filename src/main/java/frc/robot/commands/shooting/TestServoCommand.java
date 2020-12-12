package frc.robot.commands.shooting;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;


public class TestServoCommand extends CommandBase {
    private final ShooterSubsystem shooterSubsystem;
    private double position;
    private double sonic;
    private int count;

    public TestServoCommand(ShooterSubsystem shooterSubsystem, double pos, double speed){
        this.shooterSubsystem = shooterSubsystem;
        addRequirements(shooterSubsystem);
        this.position = pos;
        this.sonic = speed;
    }

    @Override
    public void initialize() {
        System.out.println("Command Works");
        count = 0;
    }

    @Override
    public void execute() {
        System.out.println("position: " + shooterSubsystem.hoodServo1.getPosition() + " angle: " + shooterSubsystem.hoodServo1.getAngle());

       
    }

    @Override
    public boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return Math.abs(shooterSubsystem.getHoodPosition() - position) == 0;
        
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Command End");
    }
}