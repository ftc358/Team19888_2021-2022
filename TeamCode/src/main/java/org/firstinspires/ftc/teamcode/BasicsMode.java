package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareDevice;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class BasicsMode {
    private DigitalChannel touchSensor;
    private DcMotor motor;
    private HardwareDevice DistSensor;
    private HardwareDevice ColorSensor;
    private HardwareDevice Servo;
    private HardwareDevice UltrasonicSensor;
    private Servo servo;
    private DistanceSensor distanceSensor;
    private ColorSensor colorSensor;

    public void init(HardwareMap hwmap){
        touchSensor = hwmap.get(DigitalChannel.class, "touchsensor");
        touchSensor.setMode(DigitalChannel.Mode.INPUT);
        motor = hwmap.get(DcMotor.class, "motor");
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        servo = hwmap.get(Servo.class, "servo");
        colorSensor = hwmap.get(ColorSensor.class, "sensor_color_distance");
        distanceSensor = hwmap.get(DistanceSensor.class, "sensor_color_distance");
    }
    public boolean isTouchSensorPressed(){
        return !touchSensor.getState();
    }
    public void setPowerSpeed(double speed){
        motor.setPower(speed);
    }
    public double getMotorRotation(){
        return motor.getCurrentPosition();
    }
    public void setServoPosition(double position){
        servo.setPosition(position);
    }
    public double getDistance(DistanceUnit du) {
        return distanceSensor.getDistance(du);
    }
    public double
}
