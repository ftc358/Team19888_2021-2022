package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class AutoMain19888 extends LinearOpMode {
    private DcMotor motorLeft;
    private DcMotor motorRight;
    private DcMotor motorWheel;
    private DcMotor motorSlide;
    private Servo servoClaw;

    public void runOpMode() throws InterruptedException {
        motorLeft = hardwareMap.dcMotor.get("motor1");
        motorRight = hardwareMap.dcMotor.get("motor2");
        motorWheel = hardwareMap.dcMotor.get("motor3");
        motorSlide = hardwareMap.dcMotor.get("motor4");
        servoClaw = hardwareMap.servo.get("servo1");

        waitForStart();

        while (opModeIsActive()) {

        }
    }

    public void goForward(double speed, int distance) {

    }

    public void turnAround(double speed, int angle) {

    }

    public void grabBlock() {

    }

    public void releaseBlock(int level) {
        
    }
}
