package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareDevice;

public class BasicsMode {
    private DigitalChannel touchSensor;
    private DcMotor motor;
    private HardwareDevice DistSensor;
    private HardwareDevice ColorSensor;
    private HardwareDevice Servo;
    private HardwareDevice UltrasonicSensor;

    public void init(HardwareMap hwmap){
        touchSensor = hwmap.get(DigitalChannel.class, "touchsensor");
        touchSensor.setMode(DigitalChannel.Mode.INPUT);
        motor = hwmap.get(DcMotor.class, "motor");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
