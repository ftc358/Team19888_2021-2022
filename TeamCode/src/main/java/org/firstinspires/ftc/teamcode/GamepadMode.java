package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class GamepadMode extends LinearOpMode {
    //declaring motors

    //motor1 = left, motor2 = right
    private DcMotor motor1;
    private Servo armservo;

    public void runOpMode() throws InterruptedException {
        //initializing motors
        motor1 = hardwareMap.dcMotor.get("motor1");
        armservo = hardwareMap.servo.get("armservo");

        waitForStart();

        while (opModeIsActive()) {

            //moving the robot forward
            MotorControl1();
            Armservo();

            telemetry.addData("motor speed", gamepad1.right_stick_y );
            telemetry.addData("armservo position", gamepad1.left_stick_x );
            telemetry.update();
        }
    }

    //functions that move motors
    //you can guess what they do by their names

    public void MotorControl1() {
        motor1.setPower(gamepad1.right_stick_y );
        armservo.setPosition(gamepad1.right_stick_y );
    }

    public void Armservo() {
        motor1.setPower(-gamepad1.left_stick_x );
        armservo.setPosition(gamepad1.left_stick_x );
    }

}
