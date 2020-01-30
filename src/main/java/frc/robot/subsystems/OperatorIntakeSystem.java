/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/





package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;
import frc.robot.RobotContainer;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;





public class OperatorIntakeSystem extends SubsystemBase {



  public CANSparkMax intakeMotor = new CANSparkMax(Constants.intakeMotorDeviceID,MotorType.kBrushless);
  public CANSparkMax conveyorMotor = new CANSparkMax(Constants.conveyorMotorDeviceID,MotorType.kBrushless); //we may in the future have a second conveyorMotor so you'd group them together

  public final static DigitalInput intakeSensor = new DigitalInput(Constants.intakeSensorNumber);
  public final static DigitalInput conveyorSensor = new DigitalInput(Constants.conveyorSensorNumber);


  
  /**
   * Creates a new OperatorIntakeSystem.
   */
  public OperatorIntakeSystem() {

  }



  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
    boolean intake = RobotContainer.driverStick.getRawButtonPressed(Constants.intakeButtonNumber);
    boolean outtake = RobotContainer.driverStick.getRawButtonPressed(Constants.outtakeButtonNumber);

    if (intake == true && outtake == false){
      intakeMotor.set(Constants.intakeMotorSpeed);
      if(intakeSensor.get() == true){
        conveyorMotor.set(Constants.conveyorMotorSpeed);
      }
    } else if (outtake == true && intake == false){
      conveyorMotor.set(-Constants.intakeMotorSpeed); //Need Camerons Input
      intakeMotor.set(-Constants.conveyorMotorSpeed); //Need to decide how fast we want to outake (-1 to 1) and which direction (positive or negative) is out
    } else{
      conveyorMotor.set(0);
      intakeMotor.set(0);
    }
  }



}
