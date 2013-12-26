/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.Ports;

/**
 *
 * @author Peter
 */
public class OI {

    public static final Joystick leftJoystick = new Joystick(1),
            rightJoystick = new Joystick(1);
    public static final JoystickButton resetGyro = new JoystickButton(rightJoystick, Ports.resetGyroButton);

    public static void controlDrive() {
    }

    public static void controlGyro() {
        SmartDashboard.putNumber("Gyro", DriveTrain.gyro.getAngle());
    }

}
