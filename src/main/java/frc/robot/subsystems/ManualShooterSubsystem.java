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


  
  //* Creates a CANSparkMax variable manualShooterMotor *
  //*** This is a constructor of the CANSparkMax class and takes two parameters CANSparkMax(int, int) being the port number and the MotorType ***
  public CANSparkMax manualShooterMotor = new CANSparkMax(Constants.shooterMotorDeviceID, MotorType.kBrushless);



  //* Creates a DigitalInput variable manualShooterSensor *
  //*** This is a constructor of the DigitalInput class and takes one parameter DigitalInput(int) being the port number ***
  public final static DigitalInput manualShooterSensor = new DigitalInput(Constants.shooterSensorNumber);



  //* Creates a constructor of the class ManualShooterSubsystem() *
  //*** This allows us to create instances of the class ManualShooterSubsystem() and can be called in other classes ***
  public ManualShooterSubsystem() {
    
  }



  //* Creates a method periodic() that will be called once per scheduler run *
  //*** This allows us to repeate sections of code and acts similar in nature to a loop ***
  @Override
  public void periodic() {

    //* Creates one boolean value (true or false) that indicates whether or not button ManualShooter has been pressed or not *
    //*** True represents the button was pressed and false represents that the button was not pressed ***
    boolean manualShooter = RobotContainer.operatorStick.getRawButtonPressed(Constants.manualshooterButtonNumber);
    
    //* "if" tests if manualShooter is true (pressed) which will again test "if" manualShooterSensor.get() is true which will then start the manualShooterMotor *
    //* "else" makes it so when manualShooter is false (not pressed) it will stop the manualShooterMotor *
    if (manualShooter == true){
      if (manualShooterSensor.get() == true){
        manualShooterMotor.set(Constants.shooterMotorSpeed);
      } else{
        manualShooterMotor.set(0.0);
      }
    } else{
      manualShooterMotor.set(0.0);
    }

  }



}
