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

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;

public class OperatorConveyorSubsystem extends SubsystemBase {
  
  public CANSparkMax conveyorMotor = new CANSparkMax(Constants.conveyorMotorDeviceID,MotorType.kBrushless);
  public final static DigitalInput conveyorSensor = new DigitalInput(Constants.conveyorSensorNumber);



  
  /**
   * Creates a new OperatorConveyorSubsystem.
   */
  public OperatorConveyorSubsystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    boolean conveyorUp = RobotContainer.operatorStick.getRawButtonPressed(Constants.conveyorUpButtonNumber);
    boolean conveyorDown = RobotContainer.operatorStick.getRawButtonPressed(Constants.conveyorDownButtonNumber);
   
    if (conveyorUp == true && conveyorDown == false){
      conveyorMotor.set(Constants.intakeMotorSpeed);
      if(conveyorSensor.get() == true){
        conveyorMotor.set(0);
      }
    } else if (conveyorDown == true && conveyorUp == false){
      conveyorMotor.set(-Constants.conveyorMotorSpeed);
    } else{
      conveyorMotor.set(0);
    }

  }


}

