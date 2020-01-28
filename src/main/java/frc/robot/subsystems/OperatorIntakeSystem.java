/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class OperatorIntakeSystem extends SubsystemBase {


  /*
  public CANSparkMax intakeMotor = new CANSparkMax(Constants.intakeMotorPort,MotorType.kBrushless);
  public CANSparkMax conveyorMotor = new CANSparkMax(Constants.conveyorMotorPort,MotorType.kBrushless); //we may in the future have a second conveyorMotor so you'd group them together

  boolean intake = RobotContainer.driverStick.getRawButtonPressed(3);
  boolean outtake = RobotContainer.driverStick.getRawButtonPressed(4);

  if (intake == true && outtake == false){
    intakeMotor.set(0.5);
    while(RobotContainer.intakeSensor.get() == true){
      conveyorMotor.set(0.25);
    }
    conveyorMotor.set(0);
  } else if (outtake == true && intake == false){
    conveyorMotor.set(-0.25);
    intakeMotor.set(-0.5); //Need to decide how fast we want to outake (-1 to 1) and which direction (positive or negative) is out
  } else{
    conveyorMotor.set(0);
    intakeMotor.set(0);
  }
*/





  /**
   * Creates a new OperatorIntakeSystem.
   */
  public OperatorIntakeSystem() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
