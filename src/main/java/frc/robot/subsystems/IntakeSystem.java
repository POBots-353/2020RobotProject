/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/





package frc.robot.subsystems;
import frc.robot.Robot;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;
import frc.robot.RobotContainer;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;





public class IntakeSystem extends SubsystemBase {



  //* Creates two CANSparkMax variables intakeMotor and conveyorMotor *
  //*** This is a constructor of the CANSparkMax class and takes two parameters CANSparkMax(int, int) being the port number and the MotorType ***
  public CANSparkMax intakeMotor = new CANSparkMax(Constants.intakeMotorDeviceID,MotorType.kBrushless);
  public CANSparkMax conveyorMotor = new CANSparkMax(Constants.conveyorMotorDeviceID,MotorType.kBrushless); //we may in the future have a second conveyorMotor so you'd group them together
  public Robot robot = new Robot();

  
  
  //* Creates two DigitalInput variables intakeSensor and conveyorSensor *
  //*** These are constructors of the DigitalInput class and takes one parameter DigitalInput(int) being the port number ***
  public final static DigitalInput intakeSensor = new DigitalInput(Constants.intakeSensorNumber);
  public final static DigitalInput conveyorSensor = new DigitalInput(Constants.conveyorSensorNumber);


  
  //* Creates a constructor of the class OperatorIntakeSystem() *
  //*** This allows us to create instances of the class OperatorIntakeSystem() and can be called in other classes ***
  public IntakeSystem() {

  }



  //* Creates a method periodic() that will be called once per scheduler run *
  //*** This allows us to repeate sections of code and acts similar in nature to a loop ***
  @Override
  public void periodic() {
    
    //* Creates two boolean values (true or false) that indicates whether or not buttons intake and outtake have been pressed or not *
    //*** True represents the button was pressed and false represents that the button was not pressed ***
    boolean intake = RobotContainer.driverStick.getRawButtonPressed(Constants.intakeButtonNumber);
    boolean outtake = RobotContainer.driverStick.getRawButtonPressed(Constants.outtakeButtonNumber);

    //* "if" tests if intake is true (pressed) and outtake is false (not pressed) which will start the intakeMotor to bring in new balls *
    //* Nested "if" tests whether intakeSensor is true and will start the conveyorMotor *
    //* "else if" tests if outtake is true (pressed) and intake is false (not pressed) which will start the intakeMotor in the opposite direction to get balls out of the intake *
    //* "else" makes it so any other combination of outtake and intake occurs it stops the intakeMotor and conveyorMotor *
    if (intake == true && outtake == false){
      intakeMotor.set(Constants.intakeMotorSpeed);
      if(intakeSensor.get() == true){
        //conveyorMotor.set(Constants.conveyorMotorSpeed);
        robot.changeIntakeIn(true);
        robot.changeIntakeOut(false);
      }
    } else if (outtake == true && intake == false){
      intakeMotor.set(-Constants.conveyorMotorSpeed);
      robot.changeIntakeIn(false);
      robot.changeIntakeOut(true);
    } else{
      //conveyorMotor.set(0);
      intakeMotor.set(0);
      robot.changeIntakeIn(false);
      robot.changeIntakeOut(false);

    }

  }



}
