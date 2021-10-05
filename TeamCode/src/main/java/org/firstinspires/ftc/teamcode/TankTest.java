package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class TankTest extends LinearOpMode {
    //motor1 = left, motor2 = right
    private DcMotor motor1;
    private DcMotor motor2;

    final int msForward = 2000;
    final int msBack = 2000;
    final int msLeft = 2000;
    final int msRight = 2000;

    public void runOpMode() throws InterruptedException {
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");

        //motor1.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("opModeIsActive", opModeIsActive());
            telemetry.update();

            goForward(1);
            Thread.sleep(msForward);

            goBack(1);
            Thread.sleep(msBack);

            turnLeft(1);
            Thread.sleep(msLeft);

            turnRight(1);
            Thread.sleep(msRight);

            stopRobot();

            telemetry.addData("both motors power set to 1", "running");
            telemetry.update();
        }
    }

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
