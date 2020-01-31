/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;
import frc.robot.RobotContainer;


public class ManualShooterSubsystem extends SubsystemBase {

  public final static DigitalInput manualshooterSensor = new DigitalInput(Constants.shooterSensorNumber);
  public CANSparkMax manualshooterMotor = new CANSparkMax(Constants.shooterMotorDeviceID,MotorType.kBrushless);

  /**
  * Creates a new ManualShooter.
  */
  public ManualShooterSubsystem() {
    
  }

  //* Creates a method periodic() that will be called once per scheduler run *
  //*** This allows us to repeate sections of code and acts similar in nature to a loop ***
  @Override
  public void periodic() {

    boolean ManualShooter = RobotContainer.operatorStick.getRawButtonPressed(Constants.manualshooterButtonNumber);
    
    if (ManualShooter == true){

      if (manualshooterSensor.get() == true){
        manualshooterMotor.set(Constants.manualShooterSpeed);
      } else{
        manualshooterMotor.set(0.0);
      }

    } else{
      manualshooterMotor.set(0.0);
    }
  }
}
