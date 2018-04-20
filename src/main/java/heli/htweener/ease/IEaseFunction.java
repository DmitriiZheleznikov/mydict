package heli.htweener.ease;

public interface IEaseFunction {
    double _2PI_3  = (Math.PI * 2)/0.3;
    double _2PI_45 = (Math.PI * 2)/0.45;
    double _10log2 = 10 * Math.log(2);
    double _20log2 = 2 * _10log2;
    double _PI2 = Math.PI / 2;
    double _2PI = 2 / Math.PI;

    double transform(double ratio);
}
