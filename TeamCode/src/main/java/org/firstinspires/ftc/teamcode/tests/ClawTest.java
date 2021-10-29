package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class ClawTest extends LinearOpMode {
    //declaring servos and motors

    //servo that opens/closes the claw
    private Servo s1;

    //Servo position for opening the claw
    final double s1Ang = (double)80/360;

    //***position of a servo ranges from 0 to 1                            ***
    //***0 is 0 degrees, 1 is 360 degrees (it can do 1 revolution max)     ***

    public void runOpMode() throws InterruptedException {
        //initializing servos and motors
        s1 = this.hardwareMap.servo.get("servo1");

        waitForStart();

        while (opModeIsActive()) {
            rotateClaw();
            telemetry.addData("Servo angle", s1.getPosition());
            telemetry.update();
        }
    }

    //functions that move the servos and motors
    //you can guess what they do by their names

    public void rotateClaw() {
        //TODO fix this function; it keeps rotating after the bumper is released
        //suggestion: maybe change 0.1 to 0.01
        if (gamepad1.a && s1.getPosition() < s1Ang) {
            s1.setPosition(s1.getPosition()+0.1);
        }else if (gamepad1.b && s1.getPosition() > 0) {
            s1.setPosition(s1.getPosition()-0.1);
        }
    }


}