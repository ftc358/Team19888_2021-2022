package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;

public class ClawTest extends LinearOpMode{
    private Servo s1;
    private Servo s2;
    private DcMotor motor1;

    //position/angle of a servo ranges from 0 to 1
    final double s1Ang = (double)55/360;
    final double s2Ang = (double)90/360;
    final int motorSpeed = 1;
    final int motorMs = 1000;

    public void runOpMode() throws InterruptedException {
        s1 = this.hardwareMap.servo.get("servo1");
        s2 = this.hardwareMap.servo.get("servo2");
        motor1 = this.hardwareMap.dcMotor.get("motor1");

        waitForStart();

        while (opModeIsActive()) {
            openClaw();
            closeClaw();

            clawUp();
            clawDown();

            sendForward();
            Thread.sleep(motorMs);
            motorStop();

            sendBack();
            Thread.sleep(motorMs);
            motorStop();
        }
    }

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
