package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class VoforiaGamepad {

    /**
     * Represents the i/o relationship in a particular float zone
     */
    public interface Function {
        float func(double x);
    }

    /**
     * A section of a float control's output interval that obeys a unique
     * function
     */
    public class FloatZone {
        public final float LOWER, UPPER;
        public final Function FUNCTION;

        public FloatZone(float lower, float upper, Function func) {
            LOWER = lower;
            UPPER = upper;
            FUNCTION = func;
        }

        public float func(double x) { return FUNCTION.func(x); }
    }

    /**
     * Abstraction of an agent which produces output
     */
    public abstract class Control {
        public String NAME;

        public abstract float getState();
    }

    /**
     * Represents a binary control (letter buttons, dpad buttons, etc.)
     */
    public final class BooleanControl extends Control {
        public boolean held, pressed, released, inverted;

        public BooleanControl(String name) { NAME = name; }

        public void update(boolean state) {
            state = state ^ inverted;
            held = state;
            pressed = state && !held;
            released = !state && held;
        }

        @Override public float getState() { return held ? 1.0f : 0.0f; }
    }

    /**
     * Represents a float control (thumbsticks, triggers, etc.)
     * @see #addFloatZone(String, float, float, Function)
     */
    public final class FloatControl extends Control {
        public ArrayList<FloatZone> zones = new ArrayList<>();
        private float state;

        public FloatControl(String name) { NAME = name; }

        public void update(float state) {
            if (zones.size() == 0)
                this.state = state;
            else {
                for (FloatZone zone : zones)
                    if (Math.abs(state) >= zone.LOWER && Math.abs(state) < zone.UPPER) {
                        this.state = zone.func(state);
                        return;
                    }

                this.state = state;
            }
        }

        @Override public float getState() { return state; }
    }

    public Gamepad client;
    private ArrayList<Control> controls;
    private Map<String, Control> controlMap;

    public boolean a, a_pressed, a_released;
    public boolean b, b_pressed, b_released;
    public boolean x, x_pressed, x_released;
    public boolean y, y_pressed, y_released;

    public boolean dpad_up, dpad_up_pressed, dpad_up_released;
    public boolean dpad_down, dpad_down_pressed, dpad_down_released;
    public boolean dpad_left, dpad_left_pressed, dpad_left_released;
    public boolean dpad_right, dpad_right_pressed, dpad_right_released;

    public boolean left_bumper, left_bumper_pressed, left_bumper_released;
    public boolean right_bumper, right_bumper_pressed, right_bumper_released;
    public boolean left_stick, left_stick_pressed, left_stick_released;
    public boolean right_stick, right_stick_pressed, right_stick_released;

    public float left_stick_x, left_stick_y;
    public float right_stick_x, right_stick_y;
    public float left_trigger, right_trigger;

    /**
     * @param g The client gamepad, usually one of the instances inherited
     *          from the parent opmode
     */
    public VoforiaGamepad(Gamepad g) {
        client = g;
        controls = new ArrayList<Control>() {{
            add(new BooleanControl("a") );           // 0
            add(new BooleanControl("b") );           // 1
            add(new BooleanControl("x") );           // 2
            add(new BooleanControl("y") );           // 3

            add(new BooleanControl("dpad_up"));      // 4
            add(new BooleanControl("dpad_down"));    // 5
            add(new BooleanControl("dpad_left"));    // 6
            add(new BooleanControl("dpad_right"));   // 7

            add(new BooleanControl("left_bumper"));  // 8
            add(new BooleanControl("right_bumper")); // 9
            add(new BooleanControl("left_stick"));   // 10
            add(new BooleanControl("right_stick"));  // 11

            add(new FloatControl("left_stick_x"));   // 12
            add(new FloatControl("left_stick_y"));   // 13

            add(new FloatControl("right_stick_x"));  // 14
            add(new FloatControl("right_stick_y"));  // 15

            add(new FloatControl("left_trigger"));   // 16
            add(new FloatControl("right_trigger"));  // 17
        }};
        controlMap = new HashMap<>();

        for (Control cont : controls)
            controlMap.put(cont.NAME, cont);
    }

    /**
     * Update control variables by translating information from the client
     * gamepad to this gamepad. To get the most up-to-date information, this
     * method must be called prior to any bulk control reads (ideally at the
     * beginning of OpMode.loop()).
     *
     * If a single update() call is made to service a large amount of control
     * reads, latency may occur. This can be minimized by updating the gamepad
     * in its own thread, or mitigated completely by disregarding update() and
     * using the selective read methods instead.
     */
    public void update() {
        for (Control cont : controls)
            selectiveUpdate(cont);
    }

    private Control selectiveUpdate(Control cont) {
        if (cont instanceof BooleanControl) {
            BooleanControl bool = (BooleanControl)cont;
            bool.update(getClientBoolean(bool.NAME));
            mapBoolean(bool);
            return bool;
        } else {
            FloatControl flo = (FloatControl)cont;
            flo.update(getClientFloat(flo.NAME));
            mapFloat(flo);
            return flo;
        }
    }

    /**
     * Set whether or not a boolean control is inverted
     * @param name Name of the control--see valid control names in the constructor
     * @param inv Inverted?
     */
    public void setBooleanInverted(String name, boolean inv) {
        Control cont = controlMap.get(name);

        if (cont instanceof BooleanControl)
            ((BooleanControl)cont).inverted = inv;
    }

    /**
     * Add a new zone to a float control. Zones are ranges of thumbstick/trigger
     * input with their own unique functions. If zones are added but sections of
     * the input interval are left uncovered, input on those intervals return
     * their default values (themselves). Zones are mirrored across axes.
     * @param name Name of the control--see valid control names in the constructor
     * @param lower Lower bound of the zone (included)
     * @param upper Upper bound of the zone (excluded)
     * @param function Function
     */
    public void addFloatZone(String name, float lower, float upper, Function function) {
        FloatControl flo = (FloatControl)controlMap.get(name);
        flo.zones.add(new FloatZone(lower, upper, function));
    }

    /**
     * Methods for accessing controls by name. Only the specified control is
     * updated, rather than the entire map. This is preferable if running
     * gamepad updates in its own thread is impractical or latency issues occur
     * regardless.
     */
    public boolean bc(String name) {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controlMap.get(name));
        return cont != null && cont.held;
    }

    public boolean bc_pressed(String name) {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controlMap.get(name));
        return cont != null && cont.pressed;
    }

    public boolean bc_released(String name) {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controlMap.get(name));
        return cont != null && cont.released;
    }

    public float fc(String name) {
        FloatControl cont = (FloatControl)selectiveUpdate(controlMap.get(name));
        return cont == null ? 0.0f : cont.getState();
    }

    public boolean a() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(0));
        return cont.held;
    }

    public boolean a_pressed() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(0));
        return cont.pressed;
    }

    public boolean a_released() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(0));
        return cont.released;
    }

    public boolean b() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(1));
        return cont.held;
    }

    public boolean b_pressed() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(1));
        return cont.pressed;
    }

    public boolean b_released() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(1));
        return cont.released;
    }

    public boolean x() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(2));
        return cont.held;
    }

    public boolean x_pressed() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(2));
        return cont.pressed;
    }

    public boolean x_released() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(2));
        return cont.released;
    }

    public boolean y() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(3));
        return cont.held;
    }

    public boolean y_pressed() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(3));
        return cont.pressed;
    }

    public boolean y_released() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(3));
        return cont.released;
    }

    public boolean dpad_up() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(4));
        return cont.held;
    }

    public boolean dpad_up_pressed() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(4));
        return cont.pressed;
    }

    public boolean dpad_up_released() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(4));
        return cont.released;
    }

    public boolean dpad_down() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(5));
        return cont.held;
    }

    public boolean dpad_down_pressed() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(5));
        return cont.pressed;
    }

    public boolean dpad_down_released() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(5));
        return cont.released;
    }

    public boolean dpad_left() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(6));
        return cont.held;
    }

    public boolean dpad_left_pressed() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(6));
        return cont.pressed;
    }

    public boolean dpad_left_released() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(6));
        return cont.released;
    }

    public boolean dpad_right() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(7));
        return cont.held;
    }

    public boolean dpad_right_pressed() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(7));
        return cont.pressed;
    }

    public boolean dpad_right_released() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(7));
        return cont.released;
    }

    public boolean left_bumper() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(8));
        return cont.held;
    }

    public boolean left_bumper_pressed() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(8));
        return cont.pressed;
    }

    public boolean left_bumper_released() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(8));
        return cont.released;
    }

    public boolean right_bumper() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(9));
        return cont.held;
    }

    public boolean right_bumper_pressed() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(9));
        return cont.pressed;
    }

    public boolean right_bumper_released() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(9));
        return cont.released;
    }

    public boolean left_stick() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(10));
        return cont.held;
    }

    public boolean left_stick_pressed() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(10));
        return cont.pressed;
    }

    public boolean left_stick_released() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(10));
        return cont.released;
    }

    public boolean right_stick() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(11));
        return cont.held;
    }

    public boolean right_stick_pressed() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(11));
        return cont.pressed;
    }

    public boolean right_stick_released() {
        BooleanControl cont = (BooleanControl)selectiveUpdate(controls.get(11));
        return cont.released;
    }

    public float left_stick_x() {
        FloatControl cont = (FloatControl)selectiveUpdate(controls.get(12));
        return cont.getState();
    }

    public float left_stick_y() {
        FloatControl cont = (FloatControl)selectiveUpdate(controls.get(13));
        return cont.getState();
    }

    public float right_stick_x() {
        FloatControl cont = (FloatControl)selectiveUpdate(controls.get(14));
        return cont.getState();
    }

    public float right_stick_y() {
        FloatControl cont = (FloatControl)selectiveUpdate(controls.get(15));
        return cont.getState();
    }

    public float left_trigger() {
        FloatControl cont = (FloatControl)selectiveUpdate(controls.get(16));
        return cont.getState();
    }

    public float right_trigger() {
        FloatControl cont = (FloatControl)selectiveUpdate(controls.get(17));
        return cont.getState();
    }

    private void mapBoolean(BooleanControl bool) {
        boolean[] state = new boolean[] { bool.held, bool.pressed, bool.released };

        switch (bool.NAME) {
            case "a":
                a = state[0];
                a_pressed = state[1];
                a_released = state[2];
                break;

            case "b":
                b = state[0];
                b_pressed = state[1];
                b_released = state[2];
                break;

            case "x":
                x = state[0];
                x_pressed = state[1];
                x_released = state[2];
                break;

            case "y":
                y = state[0];
                y_pressed = state[1];
                y_released = state[2];
                break;

            case "dpad_up":
                dpad_up = state[0];
                dpad_up_pressed = state[1];
                dpad_up_released = state[2];
                break;

            case "dpad_down":
                dpad_down = state[0];
                dpad_down_pressed = state[1];
                dpad_down_released = state[2];
                break;

            case "dpad_left":
                dpad_left = state[0];
                dpad_left_pressed = state[1];
                dpad_left_released = state[2];
                break;

            case "dpad_right":
                dpad_right = state[0];
                dpad_right_pressed = state[1];
                dpad_right_released = state[2];
                break;

            case "left_bumper":
                left_bumper = state[0];
                left_bumper_pressed = state[1];
                left_bumper_released = state[2];
                break;

            case "right_bumper":
                right_bumper = state[0];
                right_bumper_pressed = state[1];
                right_bumper_released = state[2];
                break;

            case "left_stick":
                left_stick = state[0];
                left_stick_pressed = state[1];
                left_stick_released = state[2];
                break;

            case "right_stick":
                right_stick = state[0];
                right_stick_pressed = state[1];
                right_stick_released = state[2];
                break;
        }
    }

    private void mapFloat(FloatControl flo) {
        float state = flo.getState();

        switch (flo.NAME) {
            case "left_stick_x":
                left_stick_x = state;
                break;

            case "left_stick_y":
                left_stick_y = state;
                break;

            case "right_stick_x":
                right_stick_x = state;
                break;

            case "right_stick_y":
                right_stick_y = state;
                break;

            case "left_trigger":
                left_trigger = state;
                break;

            case "right_trigger":
                right_trigger = state;
                break;
        }
    }

    private boolean getClientBoolean(String name) {
        switch (name) {
            case "a": return client.a;
            case "b": return client.b;
            case "x": return client.x;
            case "y": return client.y;

            case "dpad_up": return client.dpad_up;
            case "dpad_down": return client.dpad_down;
            case "dpad_left": return client.dpad_left;
            case "dpad_right": return client.dpad_right;

            case "left_bumper": return client.left_bumper;
            case "right_bumper": return client.right_bumper;
            case "left_stick": return client.left_stick_button;
            case "right_stick": return client.right_stick_button;

            default: return false;
        }
    }

    private float getClientFloat(String name) {
        switch (name) {
            case "left_stick_x": return client.left_stick_x;
            case "left_stick_y": return client.left_stick_y;

            case "right_stick_x": return client.right_stick_x;
            case "right_stick_y": return client.right_stick_y;

            case "left_trigger": return client.left_trigger;
            case "right_trigger": return client.right_trigger;

            default: return 0.0f;
        }
    }
}