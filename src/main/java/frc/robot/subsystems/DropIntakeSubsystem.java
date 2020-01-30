/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
import frc.robot.RobotContainer;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;

public class DropIntakeSubsystem extends SubsystemBase {

  Relay intakeSpike = new Relay(Constants.intakeSpikeNumber);
  /**
   * Creates a new DropIntakeSubsystem.
   */
  public DropIntakeSubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    boolean release = RobotContainer.operatorStick.getRawButtonPressed(Constants.dropIntakeButtonNumber);
    
    if(release == true){
      intakeSpike.set(Relay.Value.kOn);
      Timer.delay(2.0);
      intakeSpike.set(Relay.Value.kOff);
      Timer.delay(2.0);
    }
  }
}
