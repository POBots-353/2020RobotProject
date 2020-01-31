/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/





package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;

import frc.robot.Constants;
import frc.robot.RobotContainer;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;





public class OperatorConveyorSubsystem extends SubsystemBase {
  


  //* Creates a CANSparkMax variable conveyorMotor *
  //*** This is a constructor of the CANSparkMax class and takes two parameters CANSparkMax(int, int) being the port number and the MotorType ***
  public CANSparkMax conveyorMotor = new CANSparkMax(Constants.conveyorMotorDeviceID,MotorType.kBrushless);



  //* Creates a DigitalInput variable conveyorSensor *
  //*** This is a constructor of the DigitalInput class and takes one parameter DigitalInput(int) being the port number ***
  public final static DigitalInput conveyorSensor = new DigitalInput(Constants.conveyorSensorNumber);



  //* Creates a constructor of the class OperatorConveyorSubsystem() *
  //*** This allows us to create instances of the class OperatorConveyorSubsystem() and can be called in other classes ***
  public OperatorConveyorSubsystem() {

  }



  //* Creates a method periodic() that will be called once per scheduler run *
  //*** This allows us to repeate sections of code and acts similar in nature to a loop ***
  @Override
  public void periodic() {

    //* Creates two boolean values (true or false) that indicates whether or not buttons conveyorUp and conveyorDown have been pressed or not *
    //*** True represents the button was pressed and false represents that the button was not pressed ***
    boolean conveyorUp = RobotContainer.operatorStick.getRawButtonPressed(Constants.conveyorUpButtonNumber);
    boolean conveyorDown = RobotContainer.operatorStick.getRawButtonPressed(Constants.conveyorDownButtonNumber);
   
    //* "if" tests if conveyorUp is true (pressed) and conveyorDown is false (not pressed) which will start the conveyorMotor to bring the balls higher in the conveyor *
    //* Nested "if" tests whether conveyorSensor is true and will stop the conveyorMotor *
    //* "else if" tests if conveyorDown is true (pressed) and conveyorUp is false (not pressed) which will start the conveyorMotor in the opposite direction to lower the balls in the conveyor *
    //* "else" makes it so any other combination of conveyorUp and conveyorDown occurs it stops the conveyorMotor *
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

