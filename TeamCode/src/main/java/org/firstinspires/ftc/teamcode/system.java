package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import java.util.concurrent.TimeUnit;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class system {
    public void drive(Gamepad gamepad1, DcMotor motor_f_l, DcMotor motor_b_l, DcMotor motor_f_r, DcMotor motor_b_r) {

        // y_val reversed
        double y = gamepad1.left_stick_y;


        // Multiplier of 1.17 to counteract strafing range
        double x = -gamepad1.left_stick_x * 1.17;
        double rx = gamepad1.right_stick_x;

        // Range_limiter containes power within range [-1,1]
        double range_limiter = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double power_fl = -1 * ((y + x + rx) / range_limiter);
        double power_bl = -1 * ((y - x + rx) / range_limiter);
        double power_fr = (y - x - rx) / range_limiter;
        double power_br = (y + x - rx) / range_limiter;

        // Set Power
        motor_f_l.setPower(power_fl);
        motor_b_l.setPower(power_bl);
        motor_f_r.setPower(power_fr);
        motor_b_r.setPower(power_br);
    }

    public void extendo(Gamepad gamepad2, DcMotorEx extendo) {
        double slide_power = -gamepad2.left_stick_y;

        extendo.setPower(slide_power);
    }

    public void intake(Gamepad gamepad2, CRServo intake_crservo) {
        double fwd_spin = gamepad2.right_trigger;
        double reverse_spin = gamepad2.left_trigger;
        double spin_power = fwd_spin - reverse_spin;

        intake_crservo.setPower(spin_power);
    }

    public void lift(Gamepad gamepad2, DcMotorEx lift_l, DcMotorEx lift_r) {
        double lift_power = -gamepad2.right_stick_y;

        lift_l.setPower(lift_power);
        lift_r.setPower(lift_power);
    }

    public void intake_pod(Servo intake_pod) {
        double curr_pos = intake_pod.getPosition();
        List<Double> positions = new ArrayList<>(Arrays.asList(0.0, 0.33));
        double next_pos;


        if (curr_pos == positions.get(0)) {
            next_pos = positions.get(1);
        } else {
            next_pos = positions.get(0);
        }
        intake_pod.setPosition(next_pos);
    }

    public void pitch(Servo pitch_servo) {
        /*if (curr_pos == 0.4) {
            pitch_servo.setPosition(0.25);
        } else if (curr_pos == 0.25) {
            pitch_servo.setPosition(0.4);
        } else if (curr_pos == 0.1) {
            pitch_servo.setPosition(0.25);
        }*/
    }
}
