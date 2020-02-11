/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.ColorWheelSubsytem;

public class AutoColorWheelCommand extends CommandBase {
  /**
   * Creates a new AutoColorWheel.
   */
  ColorWheelSubsytem colorWheel;
  public AutoColorWheelCommand(ColorWheelSubsytem c) {
    // Use addRequirements() here to declare subsystem dependencies.
    colorWheel = c;
    addRequirements(colorWheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    ColorWheelSubsytem.autoColorWheel();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return RobotContainer.operatorStick.getRawButton(Constants.AutoColorButtonNumber);  }
}
