/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.*;



/**
 * An example command that uses an example subsystem.
 */
public class ManualDriveCommand extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveSubsystem driveSubsystem;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ManualDriveCommand(DriveSubsystem subsystem) {
    driveSubsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    double move = RobotContainer.driverStick.getY();
    double turn = RobotContainer.driverStick.getX();
   
    //double scale = Constants.driveScale;
    
    driveSubsystem.manualDrive(move, turn, scaleConstant());    
    
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

  //From CR's yellow sheet "turbo" method by CR 1/18/2020
  public double scaleConstant(){
    double scale = 0.0;
    boolean turbo = RobotContainer.driverStick.getRawButton(0);
    boolean slow = RobotContainer.driverStick.getRawButton(1);
    if(turbo == true){ //Turbo no cap on throttle
      scale = 1;
    }
    else if(slow==true){ //slow drive
      scale = .5;
    }
    else{
      scale = 0.88; //drivers constant
    }
    return scale;
  }
}
