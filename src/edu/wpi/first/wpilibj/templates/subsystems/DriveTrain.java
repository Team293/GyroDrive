package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.Ports;

/**
 * Self explanatory code below. Controls the driveTrain using built in WPILib
 * class.
 */
public class DriveTrain {

    public static final Gyro gyro = new Gyro(Ports.gyro);
    public static final Talon leftDrivePWM1 = new Talon(Ports.leftDrivePWM1),
            leftDrivePWM2 = new Talon(Ports.leftDrivePWM2),
            rightDrivePWM1 = new Talon(Ports.rightDrivePWM1),
            rightDrivePWM2 = new Talon(Ports.rightDrivePWM2);
    static final RobotDrive drive = new RobotDrive(leftDrivePWM1, leftDrivePWM2, rightDrivePWM1, rightDrivePWM2);

    /**
     * TankDrive is driving the robot like a... tank.
     *
     * @param leftSpeed
     * @param rightSpeed
     */
    public static void tankDrive(double leftSpeed, double rightSpeed) {
        drive.tankDrive(leftSpeed * 0.8, rightSpeed * 0.8);
    }

    /**
     * ArcadeDrive is driving the robot similar to a RC car.
     *
     * @param moveSpeed
     * @param rotationalSpeed
     */
    public static void arcadeDrive(double moveSpeed, double rotationalSpeed) {
        drive.arcadeDrive(moveSpeed, rotationalSpeed);
    }

    /**
     * Rotates the robot at a defined speed.
     *
     * @param rotationalSpeed
     */
    public static void rotateDrive(double rotationalSpeed) {
        drive.arcadeDrive(0, rotationalSpeed);
    }

    static double kP = 0;

    public static void driveStraight(long time) {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < time) {
            //read the gyro
            OI.controlGyro();
            double angle = gyro.getAngle();
            SmartDashboard.putNumber("Gyro", DriveTrain.gyro.getAngle());
            //calculate motor output
            kP = SmartDashboard.getNumber("kP", 2);
            double rightMotorOutput = 0.4 + kP * angle;
            double leftMotorOutput = 0.4 - kP * angle;
            if (rightMotorOutput > 1) {
                rightMotorOutput = 1;
            }
            if (leftMotorOutput > 1) {
                leftMotorOutput = 1;
            }
            if (rightMotorOutput < -1) {
                rightMotorOutput = - 1;
            }
            if (leftMotorOutput < -1) {
                leftMotorOutput = -1;
            }
            //set motor output
            tankDrive(-leftMotorOutput, rightMotorOutput);
        }
    }

}
