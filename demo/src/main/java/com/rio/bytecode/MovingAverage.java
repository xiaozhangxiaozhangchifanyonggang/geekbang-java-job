package com.rio.bytecode;

/**
 * 移动平均数
 *
 * @author Rio
 * @date 2021/6/26
 */
public class MovingAverage {
    private int count = 0;
    private double sum = 0.00;

    public void submit(double value) {
        this.count++;
        this.sum += value;
    }

    public double gatavg() {
        if (0 == this.count) {
            return this.sum;
        }
        return this.sum / this.count;
    }
}
