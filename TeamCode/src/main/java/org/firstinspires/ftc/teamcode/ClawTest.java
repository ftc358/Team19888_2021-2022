package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class ClawTest extends LinearOpMode {
    //declaring servos and motors

    //servo that opens/closes the claw
    private Servo s1;
    //servo that lifts the claw up/down
    private Servo s2;
    //motor that moves the claw forward/back
    private DcMotor motor1;

    //Servo position for opening the claw
    final double s1Ang = (double)80/360;
    //Servo position for lifting up the claw
    final double s2Ang = (double)90/360;

    //***position of a servo ranges from 0 to 1                            ***
    //***0 is 0 degrees, 1 is 360 degrees (it can do 1 revolution max)     ***

    //speed of the motor used to send the claw forward/backwards
    final int motorSpeed = 1;
    //time (in miliseconds) it takes for the claw to move completely forward/backwards
    final int motorMs = 1000;

    public void runOpMode() throws InterruptedException {
        //initializing servos and motors
        s1 = this.hardwareMap.servo.get("servo1");
        s2 = this.hardwareMap.servo.get("servo2");
        motor1 = this.hardwareMap.dcMotor.get("motor1");

        waitForStart();

        while (opModeIsActive()) {
            //opening the claw
            openClaw();
            //closing the claw
            closeClaw();

            //rotating the claw up
            clawUp();
            //rotating the claw up
            clawDown();

            //sending the claw forward for "motorMS" miliseconds
            sendForward();
            Thread.sleep(motorMs);
            motorStop();

            //sending the claw backwards for "motorMS" miliseconds
            sendBack();
            Thread.sleep(motorMs);
            motorStop();
        }
    }

    //functions that move the servos and motors
    //you can guess what they do by their names

    public void openClaw() {
        s1.setPosition(s1Ang);
    }

    public void closeClaw() {
        s1.setPosition(0);
    }

    public void clawUp() {
        s2.setPosition(s2Ang);
    }

    public void clawDown() {
        s2.setPosition(0);
    }

    public void sendForward() {
        motor1.setPower(motorSpeed);
    }

    public void sendBack() {
        motor1.setPower(-motorSpeed);
    }

    public void motorStop() {
        motor1.setPower(0);
    }
}
