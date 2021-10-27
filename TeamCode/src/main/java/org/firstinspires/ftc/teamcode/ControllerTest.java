package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class ControllerTest extends LinearOpMode {
    //declaring motors

    //motor1 = left, motor2 = right, motor3 = spinning wheel, motor4 = ladder
    private DcMotor motor1;
    private DcMotor motor2;
    private DcMotor motor3;
    private DcMotor motor4;

    public void runOpMode() throws InterruptedException {
        //initializing motors
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor3");
        motor4 = hardwareMap.dcMotor.get("motor4");

        waitForStart();

        while (opModeIsActive()) {
            //moving the robot forward
            Turn2();
            ForwardBack();
            Spin();
            MoveLadder();

            telemetry.addData("forward speed set to", -gamepad1.left_stick_y);
            telemetry.addData("turn speed set to", gamepad1.right_stick_x);
            telemetry.addData("left bumper", gamepad1.left_bumper);
            telemetry.addData("right bumper", gamepad1.right_bumper);
            telemetry.update();
        }
    }

    //functions that move motors
    //you can guess what they do by their names

    public void ForwardBack() {
        motor1.setPower(-gamepad1.left_stick_y);
        motor2.setPower(-gamepad1.left_stick_y);
    }

    public void Turn() {
        motor1.setPower(-gamepad1.right_stick_x);
        motor2.setPower(gamepad1.right_stick_x);
    }

    public void Turn2() {
        if (gamepad1.right_stick_x > 0.5) {
            motor1.setPower(-gamepad1.right_stick_x);
        }else if (gamepad1.right_stick_x < -0.5) {
            motor2.setPower(gamepad1.right_stick_x);
        }
    }

    public void Spin() {
        motor3.setPower(gamepad1.right_trigger);
    }

    public void MoveLadder() {
        if (gamepad1.left_bumper) {
            motor4.setPower(1);
        }else if (gamepad1.right_bumper) {
            motor4.setPower(-1);
        }else {
            motor4.setPower(0);
        }
    }
}
