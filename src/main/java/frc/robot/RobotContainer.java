/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.drivetrain.TurnToTargetCommand;
import frc.robot.commands.funnel.FunnelInCommand;
import frc.robot.commands.neck.MoveUpNeckCommand;
import frc.robot.commands.neck.MoveDownNeckCommand;
import frc.robot.commands.shooting.ReverseShootCommand;
import frc.robot.commands.shooting.SetHoodAngleCommand;
import frc.robot.commands.shooting.ShootCommand;
import frc.robot.commands.shooting.TestServoCommand;
import frc.robot.commands.wheelOfFortune.PositionControlCommand;
import frc.robot.commands.intake.RollInCommand;
import frc.robot.commands.intake.RollOutCommand;
import frc.robot.commands.wheelOfFortune.RotationControlCommand;
import frc.robot.subsystems.*;

import java.util.function.BooleanSupplier;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public final Joystick leftJoy;
  public final Joystick rightJoy;
  public final Joystick secondaryJoy;
  private WheelOfFortuneSubsystem wheelOfFortuneSubsystem;
  private DrivetrainSubsystem drivetrainSubsystem;
  private IntakeSubsystem intakeSubsystem;
  private NeckSubsystem neckSubsystem;
  private ShooterSubsystem shooterSubsystem;
  private FunnelSubsystem funnelSubsystem;
  
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    leftJoy = new Joystick(Constants.LEFT_JOYSTICK);
    rightJoy = new Joystick(Constants.RIGHT_JOYSTICK);
    secondaryJoy = new Joystick(Constants.SECONDARY_JOYSTICK);

    drivetrainSubsystem = new DrivetrainSubsystem();
    wheelOfFortuneSubsystem = new WheelOfFortuneSubsystem();
    intakeSubsystem = new IntakeSubsystem();
    neckSubsystem = new NeckSubsystem();
    shooterSubsystem = new ShooterSubsystem();
    funnelSubsystem = new FunnelSubsystem();

//    drivetrainSubsystem.setDefaultCommand(new RunCommand(() -> {drivetrainSubsystem.drive(-getLeftY(), -getLeftY()))});
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton spin = new JoystickButton(leftJoy, 3);
    spin.whenPressed(new PositionControlCommand(wheelOfFortuneSubsystem));

    JoystickButton rotate = new JoystickButton(leftJoy, 4);
    rotate.whenPressed(new RotationControlCommand(wheelOfFortuneSubsystem));

    JoystickButton rollIn = new JoystickButton(secondaryJoy, 1);
    rollIn.whenPressed(new RollInCommand(intakeSubsystem));

    JoystickButton rollOut = new JoystickButton(secondaryJoy, 2);
    rollOut.whenPressed(new RollOutCommand(intakeSubsystem));

    JoystickButton neckMoveUp = new JoystickButton(secondaryJoy, 3);
    neckMoveUp.whenPressed(new MoveUpNeckCommand(neckSubsystem));

    JoystickButton neckMoveDown = new JoystickButton(secondaryJoy, 4);
    neckMoveDown.whenPressed(new MoveDownNeckCommand(neckSubsystem));

    JoystickButton shoot = new JoystickButton(secondaryJoy, 7);
    shoot.whenPressed(new ShootCommand(shooterSubsystem));

    JoystickButton reverseShoot = new JoystickButton(secondaryJoy, 8);
    reverseShoot.whenPressed(new ReverseShootCommand(shooterSubsystem));

    JoystickButton testServo = new JoystickButton(secondaryJoy, 12);
    testServo.whileHeld(new TestServoCommand(shooterSubsystem));

    JoystickButton shootingCommandGroup = new JoystickButton(secondaryJoy, 9);
    shootingCommandGroup.whenPressed(
            new SequentialCommandGroup(
                    new SetHoodAngleCommand(shooterSubsystem, 45),
                    new ParallelCommandGroup(
                            new FunnelInCommand(funnelSubsystem),
                            new MoveUpNeckCommand(neckSubsystem),
                            new ShootCommand(shooterSubsystem)
                    )
            ).withInterrupt(() -> !neckSubsystem.getUpperBeamBreak())
    );

    JoystickButton alignAndShootCommandGroup = new JoystickButton(secondaryJoy, 11);
    shootingCommandGroup.whenPressed(

            new SequentialCommandGroup(
                    new TurnToTargetCommand(drivetrainSubsystem),
                    new SetHoodAngleCommand(shooterSubsystem,45),
                    new ParallelCommandGroup(
                            new MoveUpNeckCommand(neckSubsystem),
                            new ShootCommand(shooterSubsystem)
                    )
            )
    );


    JoystickButton turnToTarget = new JoystickButton(secondaryJoy,10);
    turnToTarget.whenPressed(new TurnToTargetCommand(drivetrainSubsystem));
  }

  public double getLeftY() {
    return leftJoy.getY();
    // return leftJoy.getRawAxis(RobotMap.DRIVERSTATION_LEFT_Y_AXIS);
  }

  // Gets the Y direction of the left drive joystick
  public double getLeftX() {
    return leftJoy.getX();
  }

  // Gets the Y direction of the right drive joystick
  public double getRightY() {
    return rightJoy.getY();
    // return rightJoy.getRawAxis(RobotMap.DRIVERSTATION_RIGHT_Y_AXIS);
  }

  // Gets the X direction of the right drive joystick
  public double getRightX() {
    return rightJoy.getX();
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
}
