/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.networktables.*;

public class AlignRobotCommand extends CommandBase {
  /**
   * Creates a new AlignRobotCommand.
   */
  DriveSubsystem driveSubsystem;
  double tx;
  double ty;
  double tv;
  double distanceError;
  double headingError;
  public AlignRobotCommand(DriveSubsystem subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    driveSubsystem = subsystem;
    addRequirements(driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    headingError = -tx;
    distanceError = -ty;
    
    double turn = headingError * Constants.kPAim;
    double move = distanceError * Constants.kPDistance;
    if(move > Constants.maxMove && move > 0)
    {
      move = Constants.maxMove;
    }
    else if (move < 0 && move < (-1*Constants.maxMove))
    {
      move = -1 * Constants.maxMove;
    }
    if(turn > Constants.maxTurn && turn > 0){
      turn = Constants.maxTurn;
    }
    else if (turn < 0 && turn < (-1 * Constants.maxTurn))
    {
      turn = -1 * Constants.maxTurn;
    }
    
    driveSubsystem.autoAlignDrive(move, turn);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if(interrupted){
      driveSubsystem.autoAlignDrive(0, 0);
    }
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (Math.abs(headingError) < Constants.minHeadingError && Math.abs(distanceError) < Constants.minDistanceError);
  }
}
