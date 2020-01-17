/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DigitalInput;
/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public final static ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  public final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  
  public final static Joystick driverStick = new Joystick(Constants.driverStickPort);

  public final static DigitalInput conveyorSensor0 = new DigitalInput(Constants.conveyorSensor0Port); //Use DigitalInput to get values from photoelectric sensor
  public final static DigitalInput conveyorSensor1 = new DigitalInput(Constants.conveyorSensor1Port); //https://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/DigitalInput.html
  public final static DigitalInput conveyorSensor2 = new DigitalInput(Constants.conveyorSensor2Port); //https://www.chiefdelphi.com/t/how-to-wire-and-program-photoelectric-sensors-beginner/342448/6
  public final static DigitalInput conveyorSensor3 = new DigitalInput(Constants.conveyorSensor3Port);
  public final static DigitalInput conveyorSensor4 = new DigitalInput(Constants.conveyorSensor4Port);
  public final static DigitalInput conveyorSensor5 = new DigitalInput(Constants.conveyorSensor5Port);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
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
    //double move = driverStick.getY();
    //double turn = driverStick.getX();
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
