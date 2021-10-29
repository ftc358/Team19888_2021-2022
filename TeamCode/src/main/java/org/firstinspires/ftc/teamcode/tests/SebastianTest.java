package org.firstinspires.ftc.teamcode.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/*

Test code for Sebastian's invention

 */

@TeleOp
public class SebastianTest extends LinearOpMode {
    //declaring motors

    //motor1 = left, motor2 = right
    private DcMotor motor1;
    private Servo s1;

    public void runOpMode() throws InterruptedException {
        //initializing motors
        motor1 = hardwareMap.dcMotor.get("motor1");
        s1 = this.hardwareMap.servo.get("servo1");

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("opModeIsActive", opModeIsActive());

            telemetry.update();motor1.setPower(0.5);

            telemetry.addData("motor power set to 1", "running");
            telemetry.update();
        }
    }

}
