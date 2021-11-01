package org.firstinspires.ftc.teamcode.tests;

import android.view.KeyEvent;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw_Test_2 {

    @TeleOp
    public class TeleOp19888 extends LinearOpMode {
        //declaring motors
        private DcMotor motorLeft;
        private DcMotor motorRight;
        private DcMotor motorWheel;
        private DcMotor motorSlide;
        private Servo servoClaw;


        public void runOpMode() throws InterruptedException {
            //initializing motors/servos
            motorLeft = hardwareMap.dcMotor.get("motor1");
            motorRight = hardwareMap.dcMotor.get("motor2");
            motorWheel = hardwareMap.dcMotor.get("motor3");
            motorSlide = hardwareMap.dcMotor.get("motor4");

            servoClaw = hardwareMap.servo.get("servo1");

            waitForStart();

            while (opModeIsActive()) {
                //Moves the claw when corresponding buttons are pressed


























                telemetry.addData("forward speed set to", -gamepad1.right_stick_y);
                telemetry.addData("turn speed set to", gamepad1.left_stick_x);
                telemetry.addData("left bumper", gamepad1.left_bumper);
                telemetry.addData("right bumper", gamepad1.right_bumper);
                telemetry.update();
            }
        }


        //TODO trying to fix seungho's code....
    public void keyPressed(KeyEvent gamepad1.a) {
        if(gamepad1.a.getKeyCode() == KeyEvent.gamepad1.a) {
            isgamepad1.a = true;
        }
    }

    if (gamepad1.a) {
        servoClaw.setPosition(servoClaw.getPosition() + 0.1);
        if gamepad1.b
    }
//    @Override
//    public void keyPressed(gamepad1.a) {
//        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
//            sSpaceButtonDown = false;
//        }
//    }

}
}
