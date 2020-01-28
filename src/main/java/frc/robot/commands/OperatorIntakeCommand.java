/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.OperatorIntakeSystem;

public class OperatorIntakeCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final OperatorIntakeSystem operatorIntakeSystem;
  
  /**
   * Creates a new OperatorItakeCommand
   *
   * @param subsystem The subsystem used by this command.
   */
  public OperatorIntakeCommand(OperatorIntakeSystem subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    operatorIntakeSystem = subsystem;
    addRequirements(operatorIntakeSystem);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    operatorIntakeSystem.periodic();
  }  

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
