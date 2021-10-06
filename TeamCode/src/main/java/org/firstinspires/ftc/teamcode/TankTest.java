package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class TankTest extends LinearOpMode {
    //declaring motors

    //motor1 = left, motor2 = right
    private DcMotor motor1;
    private DcMotor motor2;

    //defining time (in miliseconds) it will perform for each action
    final int msForward = 2000;
    final int msBack = 2000;
    final int msLeft = 2000;
    final int msRight = 2000;

    public void runOpMode() throws InterruptedException {
        //initializing motors
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("opModeIsActive", opModeIsActive());
            telemetry.update();

            //moving the robot forward
            goForward(1);
            Thread.sleep(msForward);

            //moving the robot back
            goBack(1);
            Thread.sleep(msBack);

            //turning the robot left
            turnLeft(1);
            Thread.sleep(msLeft);

            //turning the robot right
            turnRight(1);
            Thread.sleep(msRight);

            stopRobot();

            telemetry.addData("both motors power set to 1", "running");
            telemetry.update();
        }
    }

    //functions that move motors
    //you can guess what they do by their names

    public void goForward(int speed) {
        motor1.setPower(speed);
        motor2.setPower(speed);
    }

    public void goBack(int speed) {
        motor1.setPower(-speed);
        motor2.setPower(-speed);
    }

    public void turnLeft(int speed) {
        motor1.setPower(-speed);
        motor2.setPower(speed);
    }

    public void turnRight(int speed) {
        motor1.setPower(-speed);
        motor2.setPower(speed);
    }

    public void stopRobot() {
        motor1.setPower(0);
        motor2.setPower(0);
    }
}
